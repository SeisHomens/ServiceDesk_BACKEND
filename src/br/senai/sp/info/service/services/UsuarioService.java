package br.senai.sp.info.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.service.dao.UsuarioDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.exceptions.ValidacaoException;
import br.senai.sp.info.service.models.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario buscarPorEmailESenha(Usuario usuario, BindingResult bindingResult) throws ValidacaoException, EntidadeNaoEncontradaException {
		//Verifica se ocorrem erros de valida��o no usu�rio e senha
		if(bindingResult.hasFieldErrors("email") || bindingResult.hasFieldErrors("senha")) {
			throw new ValidacaoException();
		}
		
		//verifica se o usu�rio existe atrav�s do email e senha
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		if(usuarioBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return usuarioBuscado;
	}
	
	/**
	 * Realiza o cadastro do usu�rio no banco de dados
	 * @param usuario - O usu�rio que ser� cadastrado
	 * @param bindingResult
	 * @return
	 * @throws ValidacaoException
	 */
	public Usuario cadastrar(Usuario usuario, BindingResult bindingResult) throws ValidacaoException {
		
		//Verifica poss�veis erros no usu�rio
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		//Verifica se o e-mail do usu�rio j� esta em uso
		if(usuarioDAO.buscarPorCampo("email", usuario.getEmail()) != null){
			bindingResult.addError(new FieldError("usuario", "email", "O e-mail j� est� sendo utilizado"));
			throw new ValidacaoException();
		}
		
		//Hasheia a senha e realiza o cadastro do usu�rio no banco de dados
		usuario.hashearSenha();
		usuarioDAO.inserir(usuario);
		
		return usuario;
	}
	
	public List<Usuario> buscarTodos() throws EntidadeNaoEncontradaException {
		
		return usuarioDAO.buscarTodos(); 
	}

}

