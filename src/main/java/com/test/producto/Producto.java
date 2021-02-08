package com.test.producto;

public class Producto {

	private String id;
	private String nombre;
	private Double valor;

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producto(String id, String nombre, Double valor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
