package br.senai.sp.info.service.dao;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.service.models.Chamado;

public interface ChamadoDAO extends DAO<Chamado>{
	
	@Transactional
	public Chamado buscarPorNome(String resumo);

}
