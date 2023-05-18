package com.salesianostriana.dam.proyectofinal.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesianostriana.dam.proyectofinal.excepciones.ExcepcionCarritoVacio;

@ControllerAdvice
public class ControllerExceptionCarrito {
	@ExceptionHandler (ExcepcionCarritoVacio.class)	
	public String excepcioncarrito (Model model, ExcepcionCarritoVacio ecv) {
		
		model.addAttribute("excep", ecv);
		return "errorcarritovacio";
	}
}
