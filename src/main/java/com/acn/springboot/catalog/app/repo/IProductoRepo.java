package com.acn.springboot.catalog.app.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acn.springboot.catalog.app.entity.Producto;


public interface IProductoRepo extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.sku = ?1")
	Page<Producto> findBySkupage(String sku, Pageable pageRequest);

	@Query("select p from Producto p where p.sku = ?1")
	List<Producto> findBySku(String sku);
	
}
