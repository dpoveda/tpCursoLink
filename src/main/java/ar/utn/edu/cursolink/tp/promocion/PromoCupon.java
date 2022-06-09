package ar.utn.edu.cursolink.tp.promocion;

import java.util.Objects;

import ar.utn.edu.cursolink.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.descuento.Descuento;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.usuario.proveedor.Proveedor;

public class PromoCupon extends Promocion{
	
	private String codigo;
	private Proveedor proveedor;
	private boolean disponible = true;
	private Descuento tipoDescuento; 
	
	
	//Constructors
	public PromoCupon(String codigo, Descuento tipoDescuento, Proveedor proveedor) {
		super();
		this.codigo = codigo;
		this.setProveedor(proveedor);
		this.tipoDescuento = tipoDescuento;
	}

	//Getters and Setters
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	//Methods
	@Override
	protected double calcularPromo(double base) throws CuponYaUsadoException {

		if(this.isDisponible() == false) {
			throw new CuponYaUsadoException();
		}
		this.setDisponible(false);
		return base - tipoDescuento.descuento(base);
		
		
	}

	@Override
	public boolean puedeAplicar(OrdenDeCompra ordenDeCompra) {
		return (ordenDeCompra.getCliente().getCarrito().getItems().stream().anyMatch(producto->producto.getProducto().getProveedor().equals(this.getProveedor())))?true:false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromoCupon other = (PromoCupon) obj;
		return Objects.equals(codigo, other.codigo);
	}

	
}
