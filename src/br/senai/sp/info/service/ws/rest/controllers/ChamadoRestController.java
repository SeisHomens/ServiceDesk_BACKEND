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

import br.senai.sp.info.service.dao.ChamadoDAO;
import br.senai.sp.info.service.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.service.models.Chamado;
import br.senai.sp.info.service.services.ChamadoService;

@RestController
public class ChamadoRestController {

	@Autowired
	ChamadoService chamadoService;

	@Autowired
	ChamadoDAO chamadoDao;

	// buscar todos----------------------------------------------------
	@GetMapping("/rest/chamados")
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
	@GetMapping(value = "/rest/chamado/{id}")
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
		
	@PostMapping(value = "/rest/chamado/novo")
	public ResponseEntity<Chamado> createChamado(@RequestBody Chamado chamado) {
			
		chamadoDao.inserir(chamado);
			
		return new ResponseEntity<Chamado>(HttpStatus.CREATED);

		}
		
	//alterar-----------------------------------------------------------
	@PutMapping(value = "/rest/chamado/alterar/{id}")
	public ResponseEntity<Chamado> updateChamado(@PathVariable("id") long id, @RequestBody Chamado chamado) {
		System.out.println("Alterando chamado" + id);
			
		Chamado chamadoBuscado = chamadoDao.buscar(id);
			
		if(chamado == null) {
			System.out.println("chamado com id" + id + "não encontrado");
			return new ResponseEntity<Chamado>(HttpStatus.NOT_FOUND);
				
		}
			
		chamadoBuscado.setResumo(chamado.getResumo());
		chamadoBuscado.setDescricao(chamado.getDescricao());
		chamadoBuscado.setRotulos(chamado.getRotulos());
		chamadoBuscado.setFabricante(chamado.getFabricante());
		chamadoBuscado.setModelo(chamado.getModelo());
		chamadoBuscado.setGarantia(chamado.getGarantia());
			
		chamadoDao.alterar(chamadoBuscado);
		return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
	}
		
	//delete-------------------------------------------------------------
	@DeleteMapping(value = "/rest/chamado/deletar/{id}")
	public ResponseEntity<Chamado> deletechamado(@PathVariable("id") long id) {
		System.out.println("procurando e deletando chamado com id" + id);
			
		Chamado chamado = chamadoDao.buscar(id);
		if(chamado == null) {
			System.out.println("Não é possivel deletar o chamado com o id" + id + "não foi encontrado");
			return new ResponseEntity<Chamado>(HttpStatus.NOT_FOUND);
		}
		chamadoDao.deletar(id);
		return new ResponseEntity<Chamado>(HttpStatus.NO_CONTENT);
		}
	}
