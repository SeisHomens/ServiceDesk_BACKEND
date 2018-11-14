package br.senai.sp.info.service.dao;

import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.service.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario> {

	@Transactional
	public Usuario buscarPorEmailESenha(String email, String senha);
}
