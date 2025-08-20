package com.sena.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//DECIRLE A LA CLASE QUE ES DE TIPO CONTROLADOR 
@Controller
@RequestMapping("/administrador") // SOLICITUD DE MAPEO AL DIRECTORIO ADMINISTRADOR
public class AdministradorController {

	@GetMapping("")
	public String home() {
		return "administrador/home";
	}

}
