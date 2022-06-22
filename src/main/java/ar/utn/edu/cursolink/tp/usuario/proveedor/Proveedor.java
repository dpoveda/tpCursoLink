package ar.utn.edu.cursolink.tp.usuario.proveedor;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import ar.utn.edu.cursolink.tp.producto.Producto;
//import ar.utn.edu.cursolink.tp.usuario.Rol;
import ar.utn.edu.cursolink.tp.usuario.Usuario;

@Entity
public class Proveedor extends Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) //ver si despues cambio el auto por identity, de esa forma me genera la clave primaria
	@Column(name="prov_id") 
	private Integer id;
	
	@NotBlank
	@Column(name="prov_nombre")
	private String nombre;

	//Rol nombreRol = Rol.PROVEEDOR;
	
	
	@OneToMany(mappedBy="proveedor")
	private List<Producto> productos;
	
	
	protected Proveedor() {
		super();
	}

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



	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	
	public boolean contieneProducto(Producto producto) {
		return this.getProductos().contains(producto);
	}
	
	public void agregar(Producto producto) {
		this.productos.add(producto);
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
	
	@Override
	public String toString() {
		return "Proveedor [nombre=" + nombre + "]";
	}

	
}
