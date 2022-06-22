package ar.utn.edu.cursolink.tp.promocion;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ar.utn.edu.cursolink.tp.descuento.Descuento;
import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.usuario.proveedor.Proveedor;

@Entity
@Table(name="promociones_cupon")
public class PromoCupon extends Promocion{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="promoCupon_id")
	private Integer id;
	
	//todavia no puedo hacer que se creen promoscupon con cod diferentes
	//quiero que el cod sea unico
	@NotBlank
	@Size(min = 6, max = 6)
	@Column(name="promoCupon_codigo")
	private String codigo;
	
	@ManyToOne
	@NotNull
	private Proveedor proveedor;
	
	@Column(name="promoCupon_disponibilidad")
	private boolean disponible = true;
	
	
	//no me esta creando los descuentos que antes creaba, todo por la clase descuento, ver
	@ManyToOne
	@NotNull
	private Descuento tipoDescuento; 
	
	//Constructors
	protected PromoCupon() {
		super();
	}
	
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
