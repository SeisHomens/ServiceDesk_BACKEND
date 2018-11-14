package br.senai.sp.info.service.ws.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.service.dao.UsuarioDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.models.Usuario;
import br.senai.sp.info.service.services.UsuarioService;

@RestController
public class UsuarioRestController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioDAO usuarioDao;

	// ------------------- Retrieve All usuario ---------------

	@GetMapping("/rest/usuarios")
	public ResponseEntity<Object> buscarTodos() {
	
		try {
			return ResponseEntity.ok(usuarioService.buscarTodos());
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
		
			e.printStackTrace();
			
			return ResponseEntity.status(500).build();
		}
	}

	// ------------------- Retrieve Single usuario ------------

	@GetMapping(value = "usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getusuario(@PathVariable("id") long id) {
		System.out.println("Fetching usuario with id " + id);
		Usuario usuario = usuarioDao.buscar(id);
		if (usuario == null) {
			System.out.println("usuario with id " + id + " not found");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	// ------------------- Create a usuario --------------------

	@PostMapping(value = "/usuario/novo")
	public ResponseEntity<Usuario> createusuario(@RequestBody Usuario usuario) {
		System.out.println("Creating usuario " + usuario.getNome());

		usuario.hashearSenha();
		usuarioDao.inserir(usuario);

		return new ResponseEntity<Usuario>(HttpStatus.CREATED);
	}

	// ------------------- Update a usuario --------------------

	@PutMapping(value = "usuario/alterar/{id}")
	public ResponseEntity<Usuario> updateusuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		System.out.println("Updating usuario " + id);

		Usuario usuarioBuscado = usuarioDao.buscar(id);

		if (usuario == null) {
			System.out.println("usuario with id " + id + " not found");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		usuarioBuscado.setNome(usuario.getNome());
		usuarioBuscado.setSobrenome(usuario.getSobrenome());
		usuarioBuscado.setEmail(usuario.getEmail());
		usuarioBuscado.setSenha(usuario.getSenha());
		usuarioBuscado.setTipo(usuario.getTipo());

		usuarioBuscado.hashearSenha();
		usuario.hashearSenha();
		
		usuarioDao.alterar(usuarioBuscado);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	// ------------------- Delete a usuario -------------------

	@DeleteMapping(value = "usuario/deletar/{id}")
	public ResponseEntity<Usuario> deleteusuario(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting usuario with id " + id);

		Usuario usuario = usuarioDao.buscar(id);
		if (usuario == null) {
			System.out.println("Unable to delete. usuario with id " + id + " not found");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		usuarioDao.deletar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
