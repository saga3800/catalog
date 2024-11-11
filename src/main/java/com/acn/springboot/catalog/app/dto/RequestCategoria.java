package com.acn.springboot.catalog.app.dto;

import javax.validation.constraints.NotNull;

public class RequestCategoria {

	@NotNull(message = "nombre no puede estar vacio")
	private String nombre;
	
	@NotNull(message = "codigounico no puede estar vacio")
	private String codigounico;
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigounico() {
		return codigounico;
	}
	public void setCodigounico(String codigounico) {
		this.codigounico = codigounico;
	}

	
	
}
