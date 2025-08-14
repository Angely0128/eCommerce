package com.sena.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.sena.ecommerce.model.Orden;
import com.sena.ecommerce.model.Usuario;

public interface IOrdenService {
	
	public Orden save(Orden orden);
	
	public Optional<Orden> finById(Integer id);
	
	public List<Orden> findALL();
	
	public List<Orden> findByUsuario(Usuario usuario);
	
	public String generarNumeroOrden();
	
	

}
