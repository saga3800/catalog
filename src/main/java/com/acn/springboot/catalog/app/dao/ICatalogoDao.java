package com.acn.springboot.catalog.app.dao;

import java.util.List;

import com.acn.springboot.catalog.app.entity.CategoriaProducto;

public interface ICatalogoDao {

	public List<CategoriaProducto> findAll();
	
	public void insertarCategoria(CategoriaProducto categoria);
}
