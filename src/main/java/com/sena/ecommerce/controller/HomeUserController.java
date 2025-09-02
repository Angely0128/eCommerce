package com.sena.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.ecommerce.model.Producto;
import com.sena.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeUserController {
	// Instancia del LOGGER

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(HomeUserController.class);

	// instancia de productoService

	@Autowired
	private IProductoService productoService;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}

	// metodo que carga el producto del usuario por el id producto

	@GetMapping("productohome/{id}")
	public String productohome(@PathVariable Integer id, Model model) {
		LOGGER.warn("Id producto enviado como parametro {}" , id );
		// variable de la clase producto
		Producto p = new Producto();
		Optional<Producto> op = productoService.get(id);
		// pasar el producto
		p = op.get();
		model.addAttribute("producto", p);
		return "usuario/productohome";
	}

}
