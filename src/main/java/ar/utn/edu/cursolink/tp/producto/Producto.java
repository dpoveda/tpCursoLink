package ar.utn.edu.cursolink.tp.producto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import ar.utn.edu.cursolink.tp.usuario.proveedor.Proveedor;


@Entity
@Table(name="productos")
public class Producto {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="prod_id")
	private Integer id;
	
	@NotBlank
	@Column(name="prod_nombre")
	private String nombre;
	
	@Min(value = 0, message = "El precio no debe ser menor a cero")
	@Column(name="prod_precio")
	private double precio;
	

	@ManyToOne
	@JoinColumn(name="prod_proveedor")
	private Proveedor proveedor;
	
	//Constructors
	protected Producto() { 
		super();
	}
	
	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Producto(String nombre, double precio, Proveedor proveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.proveedor = proveedor;
	}

	//Getters and setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	public void agregarEnProveedor(Proveedor proveedor) throws AgregarProductoException {
		if(proveedor.contieneProducto(this)) {
			throw new AgregarProductoException("ya esta el producto agregado", proveedor , this);
		}
		proveedor.agregar(this);
	
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre, proveedor);
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
		return Objects.equals(nombre, other.nombre) && Objects.equals(proveedor, other.proveedor);
	}

	//Para mostrar
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + "]";
	}
	



}
