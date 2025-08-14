package com.sena.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.ecommerce.model.Orden;
import com.sena.ecommerce.model.Usuario;
import com.sena.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImplement implements IOrdenService {

	@Autowired
	private IOrdenRepository ordenRepository;

	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return ordenRepository.save(orden);
	}

	@Override
	public Optional<Orden> finById(Integer id) {
		// TODO Auto-generated method stub
		return ordenRepository.findById(id);
	}

	@Override
	public List<Orden> findALL() {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return ordenRepository.findByUsuario(usuario);
	}

	@Override
	public String generarNumeroOrden() {
		
		int numero = 0;
		
		String numeroConcatenado = "";
		
		List<Orden> ordenes = findALL();
		
		List<Integer> numeros =new ArrayList<Integer>();
		
		//funciones de java 
		//crear variable anonima
		
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));	
		
		//Validacion 
		
		if(ordenes.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if(numero < 10) {
			numeroConcatenado = "T000000000" + String.valueOf(numero);
		}else if (numero < 100){
			numeroConcatenado = "T000000000" + String.valueOf(numero);
		}else if (numero < 1000) {
			numeroConcatenado = "T000000000" + String.valueOf(numero);
		}
		return numeroConcatenado;
	}

}
