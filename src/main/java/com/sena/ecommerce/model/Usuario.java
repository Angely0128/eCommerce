package com.sena.ecommerce.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// anotaciones jpa
@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String username;
	private String email;
	private String documento;
	private String direccion;
	private String telefono;
	private String password;
	private String tipo;// admin y user
	
	
	// metodo constructor vacio
	public Usuario() {

	}

// metodo constructor con campos 
	public Usuario(Integer id, String nombre, String username, String email, String documento, String direccion,
			String telefono, String password, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.documento = documento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		this.tipo = tipo;
	}
	
	//Relaciones DB
    @OneToMany(mappedBy = "usuario")
	private List<Producto> productos;
	
    @OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;


// metodos get y set 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
// metodo to string 
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", documento=" + documento + ", direccion=" + direccion + ", telefono=" + telefono + ", password="
				+ password + ", tipo=" + tipo + "]";
	}

}
