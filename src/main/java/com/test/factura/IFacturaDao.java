package com.test.factura;

import java.util.List;

public interface IFacturaDao {
	public List<Factura> getFacturas();

	public void save(Factura factura);

	public Factura find(String id);
	
	public void update(Factura factura);
	
	
}
