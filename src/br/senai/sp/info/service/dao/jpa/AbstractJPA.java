package br.senai.sp.info.service.dao.jpa;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.senai.sp.info.service.dao.DAO;

@Repository
public abstract class AbstractJPA<T> implements DAO<T>{
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public abstract String getEntityName();

	@Override
	public T buscar(Long id) {
		List<T> resultado = buscarPorCampo("id", id);
		
		if(resultado.isEmpty()) {
			return null;
		}else {
			return resultado.get(0);
		}
	}

	@Override
	public List<T> buscarTodos() {
		String hql = "FROM " + getEntityName() + " o";
		Query query = getSession().createQuery(hql);

		return query.list();
	}

	@Override
	public void alterar(T obj) {
		getSession().update(obj);
	}

	@Override
	public void deletar(T obj) {
		getSession().delete(obj);
	}
	
	@Override
	public void deletar(Long id) {
		this.deletar(buscar(id));	
	}

	@Override
	public void inserir(T obj) {
		getSession().persist(obj);
	}
	
	public List<T> buscarPorCampo(String campo, Object valor){
		return getSession().createQuery("FROM " + getEntityName() + " o WHERE o." + campo + " = :valor").setParameter("valor", valor).list();
	}
	
	public List<T> buscarPorCampos(Map<String, Object> campos){
		
		if(campos.size() == 0) {
			throw new IllegalArgumentException("Você deve informar mais de um campo na busca de camppos");
		}
		
		String hql = "FROM " + getEntityName() + " e";
		
		//Cria o hql com os filtros
		int ultimoIndice = campos.size() - 1;
		int i = 0;
		for (String campo : campos.keySet()) {
			if(i == 0) {
				hql += " WHERE ";
			}else {
				hql += " AND ";
			}
			
			hql += "e." + campo + " = :" + campo;
			
			i++;
		}
		
		//Cria o objeto query
		Query query = getSession().createQuery(hql);
		
		//Define os valores dos campos
		for (String campo : campos.keySet()) {
			query.setParameter(campo, campos.get(campo));
		}
		
		return query.list();
	}
	
}
