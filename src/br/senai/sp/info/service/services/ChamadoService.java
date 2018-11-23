package br.senai.sp.info.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.service.dao.ChamadoDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.exceptions.ValidacaoException;
import br.senai.sp.info.service.models.Chamado;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoDAO chamadoDao;

	public Chamado buscarPorNome(Chamado chamado, BindingResult bindingResult) throws ValidacaoException, EntidadeNaoEncontradaException {
		
		if(bindingResult.hasFieldErrors("resumo")) {
			throw new ValidacaoException();
		}
		
		Chamado chamadoBuscado = chamadoDao.buscarPorNome(chamado.getResumo());
		if(chamadoBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return chamadoBuscado;
		
	}
	
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
	
}
