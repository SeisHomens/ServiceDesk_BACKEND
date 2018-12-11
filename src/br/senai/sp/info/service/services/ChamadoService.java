package br.senai.sp.info.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.service.core.SessionUtils;
import br.senai.sp.info.service.dao.ChamadoDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.exceptions.ValidacaoException;
import br.senai.sp.info.service.models.Chamado;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoDAO chamadoDao;

	@Autowired
	private SessionUtils sessionUtils;

	//public List<Chamado> buscarPorUsuario(Chamado chamado, BindingResult bindingResult) throws EntidadeNaoEncontradaException{
		
	
	public Chamado cadastrar (Chamado chamado, BindingResult bindingResult) throws ValidacaoException {
		
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		chamadoDao.inserir(chamado);
		
		return chamado;
	}
	
	public List<Chamado> buscarTodos() throws EntidadeNaoEncontradaException{
		
		return chamadoDao.buscarTodos();
		
	}
	
	public Chamado buscar(Long id) throws EntidadeNaoEncontradaException{
		 
		return chamadoDao.buscar(id);
	}
	
	public List<Chamado> buscarTodosPorUsuario() { 
		return chamadoDao.buscarPorUsuario(sessionUtils.getUsuarioLogado().getEmail());
	}
	
}
