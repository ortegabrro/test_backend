package com.test.factura;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.producto.Producto;
import com.test.producto.ProductoService;

@Controller
public class FacturaController {

	@Autowired
	FacturaService facturaService;

	@Autowired
	ProductoService productoService;

	@GetMapping("/facturas")
	public String facturas(Model model) {
		model.addAttribute("form", "Factura");
		model.addAttribute("facturas", facturaService.getFacturas());
		return "listar";
	}

	@GetMapping("/main")
	public String system(Model model) {
		model.addAttribute("form", "Formulario");
		return "main";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=actualizar")
	public String actualizar(@ModelAttribute(name = "factura") Factura factura, RedirectAttributes flash) {

		LocalTime t1 = factura.getHora();
		LocalTime t2 = LocalTime.now();
		if (t1.until(t2, ChronoUnit.HOURS) < 5) {
			flash.addFlashAttribute("factura", factura);
			flash.addFlashAttribute("productos", productoService.getProductos());
			flash.addFlashAttribute("boton", "actualizar");
			return "redirect:/main";
		} else {
			flash.addFlashAttribute("error", "No se puede actualizar la factura despues de creada por mas de 5 horas");
			return "redirect:/facturas";
		}

	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=update")
	public String update(@ModelAttribute(name = "factura") Factura factura,
			@RequestParam(name = "selects[]") String[] selects, RedirectAttributes flash) {

		boolean band = false;
		Double sum = 0.0;
		Factura f = facturaService.find(factura.getId());

		for (int i = 0; i < selects.length; i++) {
			Producto producto = productoService.find(selects[i]);
			sum += producto.getValor();
		}
		if (sum >= f.subtotal()) {
			for (int i = 0; i < selects.length; i++) {
				Producto producto = productoService.find(selects[i]);
				for (FacturaItem fi : f.getItems()) {
					if (producto.getValor() >= fi.getProducto().getValor()) {
						if (producto.getId().equals(fi.getProducto().getId())) {
							f.updateItemFactura(fi);
						} else {
							band = true;
						}
					}
				}
				if (band) {
					FacturaItem item = new FacturaItem(producto);
					f.getItems().add(item);
				}
			}
			if (sum > 100000.0) {
				f.setDomicilio(0.0);
			}
			facturaService.update(f);
		} else {
			flash.addFlashAttribute("factura", factura);
			flash.addFlashAttribute("productos", productoService.getProductos());
			flash.addFlashAttribute("boton", "actualizar");
			flash.addFlashAttribute("error",
					"Los productos seleccionados no cuestan igual o superan los productos anteriores");
			return "redirect:/main";
		}

		return "redirect:/facturas";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST, params = "action=eliminar")
	public String eliminar(@ModelAttribute(name = "factura") Factura factura, RedirectAttributes flash) {

		LocalTime t1 = factura.getHora();
		LocalTime t2 = LocalTime.now();
		Factura f = facturaService.find(factura.getId());
		if (t1.until(t2, ChronoUnit.HOURS) < 12) {
			f.deleteItems();
			f.setDomicilio(0.0);
		} else {
			f.deleteItems();
			f.setRecargo(0.10);
			f.setDomicilio(0.0);
		}
		return "redirect:/facturas";
	}

}
