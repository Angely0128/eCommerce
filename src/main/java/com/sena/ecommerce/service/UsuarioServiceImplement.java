package com.sena.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.ecommerce.model.Usuario;
import com.sena.ecommerce.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

	// Instancia de objeto de tipo privado del repositorio

	@Autowired
	private IUsuarioRepository usuariorepository;

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariorepository.save(usuario);
	}

	@Override
	public Optional<Usuario> get(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Usuario> findbyId(Integer id) {
		// TODO Auto-generated method stub
		return usuariorepository.findById(id);
	}

	@Override
	public Optional<Usuario> findbyEmail(String email) {
		// TODO Auto-generated method stub
		return usuariorepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findALL() {
		// TODO Auto-generated method stub
		return usuariorepository.findAll();
	}

}
