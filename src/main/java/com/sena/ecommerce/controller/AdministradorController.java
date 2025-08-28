package com.sena.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.ecommerce.model.Producto;
import com.sena.ecommerce.service.IProductoService;

//DECIRLE A LA CLASE QUE ES DE TIPO CONTROLADOR 
@Controller
@RequestMapping("/administrador") // SOLICITUD DE MAPEO AL DIRECTORIO ADMINISTRADOR
public class AdministradorController {

	// Instancia LOGGER
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IProductoService productoService;

	@GetMapping("")
	public String home(Model model) { // model permite enviar informacion de hibernate a la vistas
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		return "administrador/home";
	}

}
