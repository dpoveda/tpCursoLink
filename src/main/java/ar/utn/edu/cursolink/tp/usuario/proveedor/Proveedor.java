package ar.utn.edu.cursolink.tp.usuario.proveedor;

import java.util.Objects;

import ar.utn.edu.cursolink.tp.usuario.Usuario;

public class Proveedor extends Usuario {
	private String nombre;

	public Proveedor(String nombre) {
		super();
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
 

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
}
