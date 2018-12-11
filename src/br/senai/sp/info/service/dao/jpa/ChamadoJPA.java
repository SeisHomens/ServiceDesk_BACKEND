package br.senai.sp.info.service.dao.jpa;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.senai.sp.info.service.dao.ChamadoDAO;
import br.senai.sp.info.service.models.Chamado;

@Repository("chamadoDao")
public class ChamadoJPA extends AbstractJPA<Chamado> implements ChamadoDAO {

	@Override
	public List<Chamado> buscarPorUsuario(String usuario) {
		List<Chamado> chamados = buscarPorCampos(new HashMap<String, Object>() {
			{
				put("usuario", usuario);
			}

		});

		return chamados;
	}

	@Override
	public String getEntityName() {
		return "Chamado";
	}

}
