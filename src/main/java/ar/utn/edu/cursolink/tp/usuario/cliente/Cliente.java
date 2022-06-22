package ar.utn.edu.cursolink.tp.usuario.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ar.utn.edu.cursolink.tp.carrito.Carrito;
import ar.utn.edu.cursolink.tp.carrito.ItemCarrito;
import ar.utn.edu.cursolink.tp.exception.NoPuedeGenerarLaOrdenException;
import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.producto.Producto;
import ar.utn.edu.cursolink.tp.tarjeta.Tarjeta;
import ar.utn.edu.cursolink.tp.usuario.Usuario;


public class Cliente extends Usuario {

	private String nombre;
	private int dni;
	private String email;
	private int telefono;
	private boolean miembro;
	private MedioDePago medioDePago;
	private double montoBilletera;
	private Set<Tarjeta> tarjetas;
	private Carrito carrito; 
	private Collection<OrdenDeCompra> comprasEfectuadas;
	
	
	//Constructors
	public Cliente(String nombre) {
		super();
		this.nombre = nombre;
		this.tarjetas = new HashSet<>();
		this.comprasEfectuadas = new ArrayList<OrdenDeCompra>();
	}
	
	
	//Getters and setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}	
	public boolean isMiembro() {
		return miembro;
	}
	public void setMiembro(boolean miembro) {
		this.miembro = miembro;
	}
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
	public Set<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(Set<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Collection<OrdenDeCompra> getComprasEfectuadas() {
		return comprasEfectuadas;
	}

	public void setComprasEfectuadas(Collection<OrdenDeCompra> comprasEfectuadas) {
		this.comprasEfectuadas = comprasEfectuadas;
	}


	public double getMontoBilletera() {
		return montoBilletera;
	}


	public void setMontoBilletera(double montoBilletera) {
		this.montoBilletera = montoBilletera;
	}


	//Methods
	public void agregarTarjeta(Tarjeta tarjeta) {
		this.tarjetas.add(tarjeta);
	}
	
	public MedioDePago elegirMedioDePago(MedioDePago medio) {
		this.setMedioDePago(medio);
		return medio;
	}
	
	public boolean tieneOrden(OrdenDeCompra orden) {
		return (this.getComprasEfectuadas().contains(orden))? true : false;
	}
	
	public void agregarAlCarrito(Producto producto, int cantidad) {	
		ItemCarrito item = new ItemCarrito(producto, cantidad);
		try {
			this.getCarrito().agregarItem(item);
		} catch (Exception tieneProductoRepetidoException) {
			this.getCarrito().solucionarCantidadProductoRepetido(item);
		}
		
	}
	
	public OrdenDeCompra generarOrdenDeCompra() throws NoPuedeGenerarLaOrdenException {
		if(this.getCarrito() == null) {
			throw new NoPuedeGenerarLaOrdenException();
		}else {
			OrdenDeCompra orden = new OrdenDeCompra(this);
			this.comprasEfectuadas.add(orden);
			return orden;
		}	
	}


	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre);
	}


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + "]";
	}



	//podria hacer un metodo mostrarProductos() que me muestre todos los items del carrito, sus productos y cant
	//y que me retorne esos items, despues ver si lo hago

	
}
