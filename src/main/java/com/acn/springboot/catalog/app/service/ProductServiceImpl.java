package com.acn.springboot.catalog.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acn.springboot.catalog.app.dao.ICatalogoDao;
import com.acn.springboot.catalog.app.dto.RequestProducto;
import com.acn.springboot.catalog.app.entity.CategoriaProducto;
import com.acn.springboot.catalog.app.entity.Producto;

@Service
public class ProductServiceImpl implements IServiceImpl {

	@Autowired
	private ICatalogoDao catalogodao;

	public Producto validaRequest(RequestProducto requestproducto) {

		Producto product = new Producto();

		product.setNombre(requestproducto.getNombre());
		product.setCategoria(requestproducto.getCategoria().getNombre());
		product.setPrecio(requestproducto.getPrecio());
		product.setSku(requestproducto.getSku());
		product.setUrl(requestproducto.getUrl());

		return product;

	}

	public Producto guardarCategoria(RequestProducto requestproducto) {
		
		CategoriaProducto catproducto = new CategoriaProducto();
		catproducto.setNombre(requestproducto.getCategoria().getNombre());
		catproducto.setCodigounico(requestproducto.getCategoria().getCodigounico());

		catalogodao.insertarCategoria(catproducto);

		return null;
	}
}
