package ar.utn.edu.cursolink.tp.promocion;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.utn.edu.cursolink.tp.descuento.Descuento;
import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.proveedor.Proveedor;

@Entity
public class PromoCupon extends Promocion{
	
	@NotBlank
	@Size(min = 6, max = 6)
	@Column(name="promoCupon_codigo")
	private String codigo;
	
	@ManyToOne
	@NotNull
	private Proveedor proveedor;
	
	@Column(name="promoCupon_disponibilidad")
	private boolean disponible = true;
	
	
	@ManyToOne
	private Descuento tipoDescuento; 
	
	//Constructors
	protected PromoCupon() {
		super();
	}
	
	public PromoCupon(String codigo, Descuento tipoDescuento, Proveedor proveedor) {
		super();
		this.codigo = codigo;
		this.setProveedor(proveedor);
		this.setTipoDescuento(tipoDescuento);
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
	
	
	public Descuento getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(Descuento tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
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
