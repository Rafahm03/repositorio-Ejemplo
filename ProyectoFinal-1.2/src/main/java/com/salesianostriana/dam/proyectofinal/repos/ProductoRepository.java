package com.salesianostriana.dam.proyectofinal.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.proyectofinal.model.Categoria;
import com.salesianostriana.dam.proyectofinal.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	public List<Producto> findByCategoria(Categoria categoria);
	
	@Query("select p.id from Producto p")
	public List<Long> obtenerIds();
	
	@Query("select p from Producto p where p.categoria.id = ?1")
	public List<Producto> findByCategoriaId(Long categoriaId);
	
	@Query("select count(p) from Producto p where p.categoria = ?1")
	public int findNumProductosByCategoria(Categoria categoria);
	
	public  List<Producto> findByNombreContainingIgnoreCase(String nombre);
	

	public  Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
