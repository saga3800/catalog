package com.acn.springboot.catalog.app.dto;

import java.util.List;

import com.acn.springboot.catalog.app.entity.Producto;
import com.acn.springboot.catalog.app.paginator.PageItem;

public class ProductoResponse {

	private List<Producto> producto;

	private int totalPaginas;

	private int paginaActual;

	private List<PageItem> paginas;

	private boolean isFirst;
	private boolean isLast;
	private boolean isHasNext;
	private boolean isHasPrevious;

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isHasNext() {
		return isHasNext;
	}

	public void setHasNext(boolean isHasNext) {
		this.isHasNext = isHasNext;
	}

	public boolean isHasPrevious() {
		return isHasPrevious;
	}

	public void setHasPrevious(boolean isHasPrevious) {
		this.isHasPrevious = isHasPrevious;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PageItem> paginas) {
		this.paginas = paginas;
	}

	public ProductoResponse() {
		super();
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

}
