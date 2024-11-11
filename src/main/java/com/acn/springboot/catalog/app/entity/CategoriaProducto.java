package com.acn.springboot.catalog.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoriaproducto")
public class CategoriaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "codigounico")
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

	public CategoriaProducto(String nombre, String codigounico) {
		super();
		this.nombre = nombre;
		this.codigounico = codigounico;
	}

	public CategoriaProducto() {
		// TODO Auto-generated constructor stub
	}

}
