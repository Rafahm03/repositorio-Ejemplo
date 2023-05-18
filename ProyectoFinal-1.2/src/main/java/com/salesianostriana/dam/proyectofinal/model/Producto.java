package com.salesianostriana.dam.proyectofinal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Producto {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Lob 
	private String descripcion;
	
	private float pvp;
	
	private float descuento;
	
	private String imagen;
	
	@ManyToOne
	private Categoria categoria;
	

	public Producto(String nombre, String descripcion, float pvp, float descuento, String imagen, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.descuento = descuento;
		this.imagen = imagen;
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pvp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(pvp) != Double.doubleToLongBits(other.pvp))
			return false;
		return true;
	}
}

