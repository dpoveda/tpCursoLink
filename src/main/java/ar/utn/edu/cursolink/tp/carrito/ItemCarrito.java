package ar.utn.edu.cursolink.tp.carrito;

import java.util.Objects;

import ar.utn.edu.cursolink.tp.producto.Producto;

public class ItemCarrito {

	private Producto producto;
	private int cantidad;
	
	//Constructor
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
