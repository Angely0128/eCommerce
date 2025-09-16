package com.sena.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.ecommerce.model.Usuario;
import com.sena.ecommerce.repository.IOrdenRepository;
import com.sena.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenRepository ordenService;

	@GetMapping("/registro")
	public String createUser() {
		return "usuario/registro";
	}

	@PostMapping("/save")
	public String save(Usuario usuario, Model model) {
		LOGGER.warn("Usuario a registrar: {}", usuario);
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "usuario/login";

	}

	// crea un metodo en caso de tener login no modal
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		LOGGER.warn("Accessos: {}", usuario);
		// aceder a la DB para validar
		Optional<Usuario> userEmail = usuarioService.findbyEmail(usuario.getEmail());
		LOGGER.warn("Usuario obtenido de la DB: {}", userEmail.get());
		// condicion temporal sin spring security
		if (userEmail.isPresent()) {
			// id del usuario encontrado
			session.setAttribute("idUsuario", userEmail.get().getId());
			// validacion tipo de usuario
			if (userEmail.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/";
			}
		} else {
			LOGGER.warn("Usuario no existe en DB");
		}
		return "redirect:/";
	}

}
