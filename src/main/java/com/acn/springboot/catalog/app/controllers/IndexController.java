package com.acn.springboot.catalog.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acn.springboot.catalog.app.dao.CatalogoDao;
import com.acn.springboot.catalog.app.dao.IProductoDao;
import com.acn.springboot.catalog.app.dto.ProductoCreateResponse;
import com.acn.springboot.catalog.app.dto.ProductoResponse;
import com.acn.springboot.catalog.app.dto.RequestProducto;
import com.acn.springboot.catalog.app.entity.CategoriaProducto;
import com.acn.springboot.catalog.app.entity.Producto;
import com.acn.springboot.catalog.app.middleware.IMiddleware;
import com.acn.springboot.catalog.app.paginator.PageRender;
import com.acn.springboot.catalog.app.repo.IProductoRepo;
import com.acn.springboot.catalog.app.service.IServiceImpl;

@RestController
@RequestMapping("/api")
public class IndexController {

	@Autowired
	private IProductoRepo productrepo;

	@Autowired
	private IMiddleware md;

	@Autowired
	private IServiceImpl service;

	@Autowired
	private CatalogoDao catalogodao;
	
	@Autowired
	private IProductoDao productodao;

	@GetMapping("/productos")
	public ResponseEntity<ProductoResponse> consultaProducto(@RequestParam(value = "sku", required = false) String sku,
			@RequestParam(name = "paginas", defaultValue = "1", required = false) int page,
			@RequestParam(name = "CantidadRegistrosxPagina", defaultValue = "1", required = false) int cantidad,
			@RequestHeader(required = false, value = "token") String token) {

		try {
			productodao.cargarInfoInicial();
			ProductoResponse pr = new ProductoResponse();
			boolean acceso = md.validateToken(token);

			if (acceso == false) {
				return new ResponseEntity<ProductoResponse>(HttpStatus.UNAUTHORIZED);
			}

			Pageable pageRequest = PageRequest.of(page, cantidad);
			List<Producto> producto = new ArrayList<>();
			PageRender<Producto> pageRender;
			if (sku == null) {
				producto = productrepo.findAll();
				Page<Producto> productos = productrepo.findAll(pageRequest);
				pageRender = new PageRender<>("/productos", productos);
			} else {
				producto = productrepo.findBySku(sku);
				Page<Producto> productos = productrepo.findBySkupage(sku, pageRequest);
				pageRender = new PageRender<>("/productos", productos);
			}
			;

			pr.setProducto(producto);
			pr.setPaginaActual(pageRender.getPaginaActual());
			pr.setPaginas(pageRender.getPaginas());
			pr.setTotalPaginas(pageRender.getTotalPaginas());
			pr.setFirst(pageRender.isFirts());
			pr.setLast(pageRender.isLast());
			pr.setHasNext(pageRender.isHasNext());
			pr.setHasPrevious(pageRender.isHasPrevious());

			if (producto.isEmpty()) {
				return new ResponseEntity<ProductoResponse>(pr, HttpStatus.NOT_FOUND);

			}
			return new ResponseEntity<ProductoResponse>(pr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductoResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/crearproducto")
	public ResponseEntity<ProductoCreateResponse> consumoRestPost(@Valid @RequestBody RequestProducto requestproducto,
			@RequestHeader(required = false, value = "token") String token,
			BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> resultado = result.getFieldErrors().stream().map(abc->"nombre" + abc.getField()+" "+ abc.getDefaultMessage() ).collect(Collectors.toList());
			return new ResponseEntity<ProductoCreateResponse>( HttpStatus.BAD_REQUEST);
		}
		ProductoCreateResponse pcr = new ProductoCreateResponse();

		ProductoResponse pr = new ProductoResponse();
		boolean acceso = md.validateToken(token);

		if (acceso == false) {
			return new ResponseEntity<ProductoCreateResponse>(HttpStatus.UNAUTHORIZED);
		}

		try {
			List<Producto> producto = new ArrayList<>();
			producto = productrepo.findBySku(requestproducto.getSku());
			if (producto.isEmpty()) {

				productrepo.save(service.validaRequest(requestproducto));
				service.guardarCategoria(requestproducto);
				pcr.setSuccess(true);
				pcr.setMessage("Producto Creado Correctamente");
				return new ResponseEntity<ProductoCreateResponse>(pcr, HttpStatus.CREATED);
			} else {
				pcr.setSuccess(false);
				pcr.setMessage("Producto " + requestproducto.getSku()
						+ " Ya existe, por favor crear uno con nombre diferente");
				return new ResponseEntity<ProductoCreateResponse>(pcr, HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception e) {
			pcr.setSuccess(false);
			pcr.setMessage("Error creando producto " + e);
			return new ResponseEntity<ProductoCreateResponse>(pcr, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categorias")
	public List<CategoriaProducto> consultaProducto(@RequestParam(value = "sku", required = false) String sku,
			@RequestHeader(required = false, value = "token") String token) {

		ProductoResponse pr = new ProductoResponse();
		boolean acceso = md.validateToken(token);

		if (acceso == false) {
			return (List<CategoriaProducto>) new ResponseEntity<CategoriaProducto>(HttpStatus.UNAUTHORIZED);
		}

		try {
			List<CategoriaProducto> categorias = new ArrayList<>();

			categorias = catalogodao.findAll();
			return categorias;
		} catch (Exception e) {
			return (List<CategoriaProducto>) new ResponseEntity<CategoriaProducto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
