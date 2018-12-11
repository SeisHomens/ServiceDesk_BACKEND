package br.senai.sp.info.service.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.service.models.Chamado;

public interface ChamadoDAO extends DAO<Chamado>{
	
	@Transactional
	public List<Chamado> buscarPorUsuario(String usuario);

}
