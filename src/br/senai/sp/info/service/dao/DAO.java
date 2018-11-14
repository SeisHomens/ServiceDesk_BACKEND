package br.senai.sp.info.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface DAO<T> {
	
	@Transactional
	public T buscar(Long id);
	
	@Transactional
	List<T> buscarPorCampo(String campo, Object valor);
	
	@Transactional
	public List<T> buscarPorCampos(Map<String, Object> campos);
	
	@Transactional
	public List<T> buscarTodos();
	
	@Transactional
	public void alterar(T obj);
	
	@Transactional
	public void deletar(Long id);
	
	@Transactional
	public void deletar(T obj);
	
	@Transactional
	public void inserir(T obj);
	
}
