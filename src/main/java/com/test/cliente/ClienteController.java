package com.test.cliente;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.factura.Factura;
import com.test.factura.FacturaItem;
import com.test.factura.FacturaService;
import com.test.producto.Producto;
import com.test.producto.ProductoService;

@Controller
public class ClienteController {

	@Autowired
	FacturaService facturaService;

	@Autowired
	ProductoService productoService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String index(Model model) {

		Factura factura = new Factura();
		String idFactura = "factura" + ThreadLocalRandom.current().nextInt(1, 99999 + 1);
		factura.setHora(LocalTime.now());
		factura.setId(idFactura);
		factura.setDomicilio(0.0);
		model.addAttribute(factura);
		model.addAttribute("form", "Formulario");
		model.addAttribute("productos", productoService.getProductos());
		model.addAttribute("boton", "guardar");
		return "main";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=guardar")
	public String postMain(@ModelAttribute(name = "factura") Factura factura,
			@RequestParam(name = "selects[]", required = false) String[] selects, RedirectAttributes flash) {

		Double sum = 0.0;

		if (factura.getCliente().isValid() && selects.length == 0) {
			flash.addAttribute("error",
					"Se requieren ingresar todos los campos y seleccionar al menos un producto a facturar");
			return "redirect:/main";
		}

		for (int i = 0; i < selects.length; i++) {
			Producto producto = productoService.find(selects[i]);
			FacturaItem item = new FacturaItem(producto);
			factura.addItemFactura(item);
			sum += producto.getValor();
		}

		// Historia1
		if (sum > 70000.0 && sum < 100000.0) {
			factura.setDomicilio(3000.0);
		} else if (sum > 100000.0) {
			factura.setDomicilio(0.0);
		}

		facturaService.save(factura);

		flash.addFlashAttribute("success", "Datos almacenados con exito");
		return "redirect:/";
	}
}
