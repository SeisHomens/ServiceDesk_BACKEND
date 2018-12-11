package br.senai.sp.info.service.ws.rest.controllers;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.swing.JOptionPane;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.service.dao.ChamadoDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.models.Chamado;
import br.senai.sp.info.service.models.Usuario;
import br.senai.sp.info.service.services.ChamadoService;

@RequestMapping(value = "/rest")
@RestController
public class ChamadoRestController {

	@Autowired
	ChamadoService chamadoService;

	@Autowired
	ChamadoDAO chamadoDao;

	// buscar pelo usuario---------------------------------------------
//	@PostMapping("/chamados/usuario")
//	public ResponseEntity<Object> buscarPorUsuario(@RequestBody List<String> chamado, BindingResult bindingResult){
//		try {
//			return ResponseEntity.ok(chamadoService.buscarPorUsuario(chamado, bindingResult));
//		}catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(500).build();
//		}
//	}
	
	@GetMapping("/chamados/usuario")
	public ResponseEntity<Object> buscarPorUsuario(){
			return ResponseEntity.ok(chamadoService.buscarTodosPorUsuario());
	}
	
	// buscar todos----------------------------------------------------
	@GetMapping("/chamados")
	public ResponseEntity<Object> buscarTodos() {
		try {
			return ResponseEntity.ok(chamadoService.buscarTodos());

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(500).build();
		}
	}

	// buscar 1---------------------------------------------------------
	@GetMapping(value = "/chamado/{id}")
	public ResponseEntity<Chamado> getchamado(@PathVariable("id") long id) {
		System.out.println("procurando chamado com id" + id);
		Chamado chamado = chamadoDao.buscar(id);
		try {
			return ResponseEntity.ok(chamadoService.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(500).build();
			
		}
	}
	//criar------------------------------------------------------------
		
	@PostMapping(value = "/chamado/novo")
	public ResponseEntity<Chamado> createChamado(@RequestBody Chamado chamado) {
			
		chamadoDao.inserir(chamado);
			
		return new ResponseEntity<Chamado>(HttpStatus.CREATED);

		}
		
	//alterar-----------------------------------------------------------
	@PutMapping(value = "/chamado/alterar/{id}")
	public ResponseEntity<Chamado> updateChamado(@PathVariable("id") long id, @RequestBody Chamado chamado) {
		System.out.println("Alterando chamado" + id);
			
		Chamado chamadoBuscado = chamadoDao.buscar(id);
			
		if(chamado == null) {
			System.out.println("chamado com id" + id + "n�o encontrado");
			return new ResponseEntity<Chamado>(HttpStatus.NOT_FOUND);
				
		}
		
		chamadoBuscado.setUsuario(chamado.getUsuario());
		chamadoBuscado.setResumo(chamado.getResumo());
		chamadoBuscado.setDescricao(chamado.getDescricao());
		chamadoBuscado.setContato(chamado.getContato());
		chamadoBuscado.setDataCadastro(chamado.getDataCadastro());

			
		chamadoDao.alterar(chamadoBuscado);
		return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
	}
		
	//delete-------------------------------------------------------------
	@DeleteMapping(value = "/chamado/deletar/{id}")
	public ResponseEntity<Chamado> deletechamado(@PathVariable("id") long id) {
		System.out.println("procurando e deletando chamado com id" + id);
			
		Chamado chamado = chamadoDao.buscar(id);
		if(chamado == null) {
			System.out.println("N�o � possivel deletar o chamado com o id" + id + "n�o foi encontrado");
			return new ResponseEntity<Chamado>(HttpStatus.NOT_FOUND);
		}
		chamadoDao.deletar(id);
		return new ResponseEntity<Chamado>(HttpStatus.NO_CONTENT);
		}
	}
