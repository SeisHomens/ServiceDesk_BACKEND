package br.senai.sp.info.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/erro/401")
	public String abrirErro401NaoAutorizado() {
		return "erro/401";
	}
	
	@GetMapping("/erro/403")
	public String abrirErro403Proibido() {
		return "erro/403";
	}
	
	@GetMapping("/erro/404")
	public String abrirErro404NaoEncontrado() {
		return "erro/404";
	}
	
	@GetMapping("/erro/servidor")
	public String abrirErroServidor() {
		return "erro/servidor";
	}
	
}
