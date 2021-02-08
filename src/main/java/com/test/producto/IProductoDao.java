package com.test.producto;

import java.util.List;

public interface IProductoDao {

	public List<Producto> getProductos();

	public Producto find(String id);
}
