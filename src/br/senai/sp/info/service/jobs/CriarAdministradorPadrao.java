package br.senai.sp.info.service.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.service.dao.UsuarioDAO;
import br.senai.sp.info.service.models.TiposUsuario;
import br.senai.sp.info.service.models.Usuario;

@Component
public class CriarAdministradorPadrao implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Usuario adm = new Usuario();
		adm.setEmail("adminSenai@email.com");
		adm.setSenha("admin");
		adm.setNome("Administrador");
		adm.setSobrenome("do Sistema");
		adm.setTipo(TiposUsuario.ADMINISTRADOR);
		adm.hashearSenha();
		 
		
		if(usuarioDao.buscarPorEmailESenha(adm.getEmail(), adm.getSenha()) == null) {
			System.out.println(adm.getSenha());
			usuarioDao.inserir(adm);
		}
	}

}
