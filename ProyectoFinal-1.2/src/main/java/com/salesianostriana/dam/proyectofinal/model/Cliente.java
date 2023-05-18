
package com.salesianostriana.dam.proyectofinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;



	public Object getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNombre(Object nombre2) {
		// TODO Auto-generated method stub
		
	}

	public Object getApellido() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setApellido(Object apellido2) {
		// TODO Auto-generated method stub
		
	}

	public Object getCorreo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCorreo(Object correo2) {
		// TODO Auto-generated method stub
		
	}

    // constructores, getters y setters omitidos por brevedad
}
