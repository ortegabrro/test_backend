package com.test.factura;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FacturaService implements IFacturaDao {

	private List<Factura> facturas = new ArrayList<Factura>();

	@Override
	public List<Factura> getFacturas() {
		// TODO Auto-generated method stub
		return facturas;
	}

	@Override
	public void save(Factura factura) {
		// TODO Auto-generated method stub
		facturas.add(factura);
	}

	@Override
	public Factura find(String id) {
		// TODO Auto-generated method stub
		for (Factura factura : facturas) {
			if (factura.getId().equals(id)) {
				return factura;
			}
		}
		return null;
	}

	@Override
	public void update(Factura factura) {
		// TODO Auto-generated method stub
		for (Factura f : facturas) {
			if (f.getId().equals(factura.getId())) {
				f.setCliente(factura.getCliente());
				f.setItems(factura.getItems());
			}
		}
	}

}
