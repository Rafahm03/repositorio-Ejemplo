package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.proyectofinal.model.Categoria;
import com.salesianostriana.dam.proyectofinal.service.CategoriaService;
import com.salesianostriana.dam.proyectofinal.service.ProductoService;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("categorias", categoriaService.findAll());
		return "admin/list-categoria";
	}

	@GetMapping("/nueva")
	public String nuevaCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "admin/form-categoria";
	}
	
	@PostMapping("/nueva/submit")
	public String submitNuevaCategoria(@ModelAttribute("categoria") Categoria categoria, Model model) {
		
		categoriaService.save(categoria);
		
		//En el redirect hay que poner la ruta completa del controller al que queremos ir, 
		//incluyendo lo escrito dentro del @RequestMapping del comienzo de la clase
		return "redirect:/admin/categoria/";
	}

	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable("id") Long id, Model model) {
		
		Categoria categoria = categoriaService.findById(id);
		
		
		/*Si te fijas en el método nuevaCategoría, al model le pasamos una new categoria ( ) vacía porque es el de agregar nueva categoría.
		 * Ahora pasamos la categoría que nos devuelve el método findById (id) que está justo encima de este comentario, por lo que, el 
		 * formulario ya se pinta con los datos de esta categoría buscada*/
		if (categoria != null) {
			model.addAttribute("categoria", categoria);
			return "admin/form-categoria";
		} else {
			return "redirect:/admin/categoria/";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarCategoria(@PathVariable("id") Long id, Model model) {
		
		Categoria categoria = categoriaService.findById(id);
		
		if (categoria != null) {
			
			if (productoService.numeroProductosCategoria(categoria) == 0) {
				categoriaService.delete(categoria);				
			} else {
				
			//Se ha agregado el parámetro error con valor true a la ruta	
				return "redirect:/admin/categoria/?error=true";
			}
			
		} 

		return "redirect:/admin/categoria/";	
	}

}
