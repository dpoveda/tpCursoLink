package ar.utn.edu.cursolink.tp.carrito;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ar.utn.edu.cursolink.tp.producto.Producto;

@Entity
public class ItemCarrito {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="item_id")
	private Integer id;
	
	@NotNull
	@OneToOne 
	private Producto producto; 
	
	@Min(value = 1, message = "La cantidad no debe ser menor a cero")
	@Column(name="item_cantidad")
	private int cantidad;
	
	//Constructor
	protected ItemCarrito() {
		super();
	}
	
	public ItemCarrito(Producto producto, int cantidad) {
		super();
		this.setProducto(producto);
		this.setCantidad(cantidad);
	}

	//Getters and setters
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	//Methods
	public double obtenerPrecio() {
		return this.getProducto().getPrecio() * this.getCantidad() ;
	}


	//Para mostrar
	@Override
	public String toString() {
		return "ItemCarrito [" + producto + ", cantidad  "+ cantidad+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrito other = (ItemCarrito) obj;
		return Objects.equals(producto, other.producto);
	}

	
	
	
}
