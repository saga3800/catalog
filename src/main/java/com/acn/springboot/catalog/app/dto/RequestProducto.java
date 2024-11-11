package com.acn.springboot.catalog.app.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestProducto {


	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "nombre no puede estar vacio")
	private String nombre;
	
	@NotNull(message = "categoria no puede estar vacio")
	private RequestCategoria categoria;

	@NotNull(message = "precio no puede estar vacio")
	private Double precio;

	@NotNull(message = "url no puede estar vacio")
	private String url;

	@NotNull(message = "sku no puede estar vacio")
	private String sku;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public RequestCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(RequestCategoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "RequestProducto [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio
				+ ", url=" + url + ", sku=" + sku + "]";
	}



	
}
