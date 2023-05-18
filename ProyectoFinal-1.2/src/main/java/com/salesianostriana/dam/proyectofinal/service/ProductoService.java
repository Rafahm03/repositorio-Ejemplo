package com.salesianostriana.dam.proyectofinal.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinal.model.Categoria;
import com.salesianostriana.dam.proyectofinal.model.Producto;
import com.salesianostriana.dam.proyectofinal.repos.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository repositorio;
	
	public List<Producto> findAll() {
		return repositorio.findAll();
	}
	
	public List<Producto> findAllByCategoria(Categoria categoria) {
		return repositorio.findByCategoria(categoria);
	}
	
	public List<Producto> findAllByCategoria(Long categoriaId) {
		return repositorio.findByCategoriaId(categoriaId);
	}
	
	public Producto save(Producto producto) {
		return repositorio.save(producto);
	}
	
	public Producto delete(Producto producto) {
		Producto result = findById(producto.getId());
		repositorio.delete(result);
		return result;
	}
	
	public int numeroProductosCategoria(Categoria categoria) {
		return repositorio.findNumProductosByCategoria(categoria);
	}
	
	
	/*
	 * Este método sirve para obtener un número de productos aleatorios.
	 * Lo realizamos en Java para abstraernos mejor de la base de datos
	 * concreta que vamos a usar.
	 * Algunos SGBDR nos permitirían usar la función RANDOM, y podríamos
	 * hacer esta consulta de forma nativa.
	 */
	public List<Producto> obtenerProductosAleatorios(int numero) {
		// Obtenemos los ids de todos los productos
		List<Long> listaIds = repositorio.obtenerIds();
		// Los desordenamos 
		Collections.shuffle(listaIds);
		// Nos quedamos con los N primeros, con N = numero.
		listaIds = listaIds.stream().limit(numero).collect(Collectors.toList());
		// Buscamos los productos con esos IDs y devolvemos la lista
		return repositorio.findAllById(listaIds);

	}
	
	@Autowired
	private ProductoRepository repository;
	
	public List<Producto> findAllProducts() {
		return repository.findAll();
	}
	
	public Producto findById(Long id) {
		// Antes estaba escrito
		// repository.findOne(id)
		// Al cambiar la versin de Spring Boot, ha cambiado la de JPA y algunos metodos
		return repository.findById(id).orElse(null);
	}

	public List<Producto> findByNombre(String nombre){
		return repository.findByNombreContainingIgnoreCase(nombre);
	}


	public Page<Producto> findAllPageable(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public  Page<Producto> findByNombreContainingIgnoreCasePageable(String nombre, Pageable pageable)
	{
		return repository.findByNombreContainingIgnoreCase(nombre, pageable);
	}

}
