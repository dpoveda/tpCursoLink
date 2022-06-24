package ar.utn.edu.cursolink.tp.ordendecompra;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.exception.NoPuedeAplicarsePromoException;
import ar.utn.edu.cursolink.tp.exception.TarjetaNoEncontradaException;
import ar.utn.edu.cursolink.tp.promocion.Promocion;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

@Entity
public class OrdenDeCompra {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="orden_id")
	private Integer id;
	
	@NotBlank
	@Column(name="orden_codigo")
	private String codigo;
	
	@OneToOne
	@NotNull
	private Cliente cliente;
	
	@OneToOne
	private Promocion promo;
	
	
	//Constructors
	protected OrdenDeCompra() { 
		super();
	}
	
	public OrdenDeCompra(Cliente cliente) {
		super();
		this.setCliente(cliente);
	}
	
	
	//Getters and Setters		
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Promocion getPromo() {
		return promo;
	}

	public void setPromo(Promocion promo) {
		this.promo = promo;
	}
	

	//Methods

	public double calcularPrecioFinal() {
		double montoTotalSinPromo;
		
		montoTotalSinPromo = this.getCliente().getCarrito().calcularPrecioTotal();
		
		try {
			if(!promo.puedeAplicar(this)) {
				throw new NoPuedeAplicarsePromoException();
			}
			return promo.aplicarPromocion(montoTotalSinPromo);
		} catch (NoPuedeAplicarsePromoException e) {
		return montoTotalSinPromo;
		} catch (CuponYaUsadoException e) {
		return montoTotalSinPromo;
		} catch (TarjetaNoEncontradaException e) {
		return montoTotalSinPromo;
		}
			
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
		OrdenDeCompra other = (OrdenDeCompra) obj;
		return Objects.equals(codigo, other.codigo);
	}

	



}
