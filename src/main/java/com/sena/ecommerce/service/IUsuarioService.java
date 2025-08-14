package com.sena.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.sena.ecommerce.model.Usuario;

public interface IUsuarioService {
	
	public Usuario save(Usuario usuario);
	
	public Optional<Usuario> get (Integer id);
	
	public void update(Usuario usuario);
	
	public void delete(Integer id);
	
	public Optional<Usuario> findbyId(Integer id);
	
	public Optional<Usuario> findbyEmail(String email);
	
	List<Usuario> findALL();
	
	

}
