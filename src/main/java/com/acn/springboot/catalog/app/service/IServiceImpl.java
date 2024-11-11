package com.acn.springboot.catalog.app.service;

import com.acn.springboot.catalog.app.dto.RequestProducto;
import com.acn.springboot.catalog.app.entity.Producto;

public interface IServiceImpl {
	public Producto validaRequest(RequestProducto requestproducto);
	
	public Producto guardarCategoria(RequestProducto requestproducto);
}
