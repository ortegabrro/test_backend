package com.test.producto;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoDao {

	private List<Producto> productos = Arrays.asList(new Producto("101", "Billetera", 100000.0),
			new Producto("102", "Celular", 800000.0), new Producto("103", "Reloj", 200000.0),
			new Producto("104", "Gafas", 60000.0), new Producto("105", "Sombrero", 85000.0),
			new Producto("106", "Chaqueta", 170000.0));

	@Override
	public List<Producto> getProductos() {
		return productos;
	}

	@Override
	public Producto find(String id) {
		Producto obj = new Producto();
		for (Producto producto : productos) {
			if (producto.getId().equals(id)) {
				obj = producto;
			}
		}
		return obj;
	}

}
