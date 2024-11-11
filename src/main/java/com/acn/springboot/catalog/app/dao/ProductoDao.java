package com.acn.springboot.catalog.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.acn.springboot.catalog.app.entity.Producto;

@Component
public class ProductoDao implements IProductoDao {

	@Autowired
	@Qualifier("jdbcMaster")
	private JdbcTemplate jdbcTemplate;

	public void cargarInfoInicial() {
		try {
			List<Producto> producto = jdbcTemplate.query("select * from producto", new RowMapper<Producto>() {
				@Override
				public Producto mapRow(ResultSet rs, int arg1) throws SQLException {
					return new Producto();

				}
			});

			if (producto.isEmpty()) {
				jdbcTemplate.execute("DROP TABLE categoriaproducto");
				jdbcTemplate.update(
						"INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)", "2000",
						"electrodomestico", "licuadora", 2000, "F1110", "https://imagenlicuadora.com");
				jdbcTemplate.update(
						"INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)", "2001",
						"electrodomestico", "nevera", 2000, "G9870", "https://imagennevera.com");
				jdbcTemplate.update(
						"INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)", "2002",
						"electrodomestico", "estufa", 2000, "H09810", "https://imagenestufa.com");
			}
		} catch (Exception e) {
			jdbcTemplate.update("INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)",
					"3001", "electrodomestico", "licuadora", 2000, "F1110", "https://imagenlicuadora.com");
			jdbcTemplate.update("INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)",
					"3002", "electrodomestico", "nevera", 2000, "G9870", "https://imagennevera.com");
			jdbcTemplate.update("INSERT INTO producto (id,categoria,nombre,precio,sku,url) VALUES (?, ?, ?, ?, ?, ?)",
					"3003", "electrodomestico", "estufa", 2000, "H09810", "https://imagenestufa.com");
		}
	}

}
