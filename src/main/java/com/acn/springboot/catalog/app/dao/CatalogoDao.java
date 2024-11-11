package com.acn.springboot.catalog.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.acn.springboot.catalog.app.entity.CategoriaProducto;

@Component
public class CatalogoDao implements ICatalogoDao {

	@Autowired
	@Qualifier("jdbcSlave")
	private JdbcTemplate jdbcTemplate;

	public List<CategoriaProducto> findAll() {
		try {
		return jdbcTemplate.query("select * from categoriaproducto", new RowMapper<CategoriaProducto>() {
			@Override
			public CategoriaProducto mapRow(ResultSet rs, int arg1) throws SQLException {
				return new CategoriaProducto(rs.getString("nombre"), rs.getString("codigounico"));
			}

		});
		}catch(Exception e) {
			System.out.println("Error ejecutando query consulta productos " + e);
			return null;
		}
	}

	public void insertarCategoria(CategoriaProducto categoria) {
		
		try {
		
		jdbcTemplate.execute("create table categoriaproducto (id Long,nombre VARCHAR(255), codigounico VARCHAR(255))");
		
		jdbcTemplate.update("INSERT INTO categoriaproducto (nombre, codigounico) VALUES (?, ?)", categoria.getNombre(),
				categoria.getCodigounico());
		}catch(Exception e) {
			jdbcTemplate.update("INSERT INTO categoriaproducto (nombre, codigounico) VALUES (?, ?)", categoria.getNombre(),
			categoria.getCodigounico());
		}
	}
	
	
}
