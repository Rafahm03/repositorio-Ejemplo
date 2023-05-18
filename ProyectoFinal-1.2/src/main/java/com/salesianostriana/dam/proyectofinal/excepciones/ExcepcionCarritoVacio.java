package com.salesianostriana.dam.proyectofinal.excepciones;

public class ExcepcionCarritoVacio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionCarritoVacio () {
		
	}
	
	public ExcepcionCarritoVacio (String mensaje) {
		
		super ("No ha comprado ningún producto todavía");
	}
	
}
