package com.test.factura;

import java.util.concurrent.ThreadLocalRandom;

import com.test.producto.Producto;

public class FacturaItem {

	private String id;
	private Producto producto;
	private Integer cantidad;

	public FacturaItem(Producto producto) {
		super();
		this.id = "item" + ThreadLocalRandom.current().nextInt(1, 99999 + 1);;
		this.producto = producto;
		this.cantidad = 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getValor() {
		return producto.getValor();
	}

	public Double calcularImporte() {
		return cantidad.doubleValue() * producto.getValor();
	}

}
