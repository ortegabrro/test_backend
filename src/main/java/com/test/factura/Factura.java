package com.test.factura;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.test.cliente.Cliente;

public class Factura {

	private String id;
	private LocalTime hora;
	private Cliente cliente;
	private List<FacturaItem> items;
	private Double iva;
	private Double domicilio;
	private Double recargo;

	public Factura() {
		this.items = new ArrayList<FacturaItem>();
		this.iva = 1.19;
		this.recargo = 1.0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<FacturaItem> getItems() {
		return items;
	}

	public void setItems(List<FacturaItem> items) {
		this.items = items;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Double domicilio) {
		this.domicilio = domicilio;
	}

	public Double getRecargo() {
		return recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}

	public void addItemFactura(FacturaItem item) {
		this.items.add(item);
	}

	public void deleteItems() {
		this.items = new ArrayList<FacturaItem>();
	}

	public void updateItemFactura(FacturaItem item) {
		for (FacturaItem facturaItem : this.items) {
			if (facturaItem.getId().equals(item.getId())) {
				facturaItem.setCantidad(facturaItem.getCantidad() + 1);
			}
		}
	}

	public Double subtotal() {
		Double res = 0.0;
		for (int i = 0; i < items.size(); i++) {
			res += items.get(i).calcularImporte();
		}
		return res * recargo;
	}

	public Double total() {
		Double res = 0.0;
		for (int i = 0; i < items.size(); i++) {
			res += items.get(i).calcularImporte();
		}
		//System.out.println("valor iva: "+iva);
		//System.out.println("valor res: "+res);
		//System.out.println("valor domicilio: "+domicilio);
		//return 0.0;
		return (res * iva) + domicilio;
	}

}
