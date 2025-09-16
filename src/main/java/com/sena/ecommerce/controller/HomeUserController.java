package com.sena.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sena.ecommerce.model.DetalleOrden;
import com.sena.ecommerce.model.Orden;
import com.sena.ecommerce.model.Producto;
import com.sena.ecommerce.model.Usuario;
import com.sena.ecommerce.service.IDetalleOrdenService;
import com.sena.ecommerce.service.IOrdenService;
import com.sena.ecommerce.service.IProductoService;
import com.sena.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping("/")
public class HomeUserController {
	// Instancia del LOGGER

	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(HomeUserController.class);

	// instancia de productoService

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordenService;

	@Autowired
	private IDetalleOrdenService detalleOrden;

	// lista de detalles de la orden para guardar en la db
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	// objeto que almacena
	Orden orden = new Orden();

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}

	// metodo que carga el producto del usuario por el id producto

	@GetMapping("productohome/{id}")
	public String productohome(@PathVariable Integer id, Model model) {
		LOGGER.warn("Id producto enviado como parametro {}", id);
		// variable de la clase producto
		Producto p = new Producto();
		Optional<Producto> op = productoService.get(id);
		// pasar el producto
		p = op.get();
		model.addAttribute("producto", p);
		return "usuario/productohome";
	}

	// metodo para enviar del boton del formulario de producto home al carrito de
	// compras
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Double cantidad, Model model) {
		DetalleOrden detaorden = new DetalleOrden();
		Producto p = new Producto();
		// variable de tipo double que siempre que se ingrese ne le metodo se inicializa
		// en 0 despues de cada compra
		double sumaTotal = 0;
		Optional<Producto> op = productoService.get(id);
		LOGGER.warn("Producto añadido {}", op.get());
		LOGGER.warn("Cantidad añadida{}", cantidad);
		p = op.get();
		detaorden.setCantidad(cantidad);
		detaorden.setPrecio(p.getPrecio());
		detaorden.setNombre(p.getNombre());
		detaorden.setTotal(p.getPrecio() * cantidad);
		detaorden.setProducto(p);
		// validacion para evitar duplicados de productos
		Integer idProducto = p.getId();
		// funcion lamda stream y funcion anonima con predicado anyMatch
		boolean insertado = detalles.stream().anyMatch(prod -> prod.getProducto().getId() == idProducto);
		// si no es verdadero añade el producto a la llista
		if (!insertado) {
			detalles.add(detaorden);
		}
		// sumA de totales que el usuario añade al carrito
		// funciones de java 8 lamda >Stream
		// funcion anonima java8
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		// pasar variable a la vista
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}

	// metodo para quitar productos del carrito de compras
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		// lista de productos
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();
		// quitar un objeto de la lista detalle orden
		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNuevas.add(detalleOrden);
			}
		}
		// cargar nueva lista con los productos restantes
		detalles = ordenesNuevas;
		// recalcular los totales de la lista
		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}

	// metodo para redirigir el carrito de compra sin prudctos
	@GetMapping("/getCart")
	public String getCart(Model model) {
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "/usuario/carrito";
	}
	// metodo para redirigir a la vista el resumen de la orden

	@GetMapping("/orden")
	public String orden(Model model) {
		Usuario u = usuarioService.findbyId(2).get();
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", u);
		return "/usuario/resumenorden";
	}

	// metodo que genera la orden y detalles de la orden
	@GetMapping("/saveOrder")
	public String saveOrder() {
		Date fechacreacion = new Date();
		orden.setFechacreacion(fechacreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		Usuario u = usuarioService.findbyId(2).get();
		orden.setUsuario(u);
		ordenService.save(orden);
//guardar detalles de la orden
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			detalleOrden.save(dt);
			// descuento de cantidad de productocomprada dek stock del producto
			Producto p = dt.getProducto();
			int cantComprada = dt.getCantidad().intValue();// conversion de double a int
			if (p.getCantidad() >= cantComprada) {
				p.setCantidad(p.getCantidad() - cantComprada);
				productoService.update(p);
			} else {
				throw new IllegalStateException("Stock insuficiente para el producto: " + p.getNombre());

			}
		}
//limpiar los valores que no se añadan a la sigueinte orden o la orden recien guardada
		orden = new Orden();
		detalles.clear();
		return "redirect:/";
	}

	// metodo post para buscar productos de la vista principal o home de usuarios
	@PostMapping("/search")
	public String searchProducto(@RequestParam String nombre, Model model) {
		LOGGER.warn("nombre del producto: {}", nombre);
		List<Producto> productos = productoService.findAll().stream()
				.filter(p -> p.getNombre().toUpperCase().contains(nombre.toUpperCase())).collect(Collectors.toList());
		model.addAttribute("productos", productos);
		return "usuario/home";
	}

}
