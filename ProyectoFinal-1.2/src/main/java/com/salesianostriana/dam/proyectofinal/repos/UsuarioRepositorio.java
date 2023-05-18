package com.salesianostriana.dam.proyectofinal.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectofinal.model.Usuario;

public interface UsuarioRepositorio 
	extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findFirstByUsername(String username);

}
