package com.salesianostriana.dam.proyectofinal.controller;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
