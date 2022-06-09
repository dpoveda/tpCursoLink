package ar.utn.edu.cursolink.tp.ordendecompra;


import java.util.Objects;

import ar.utn.edu.cursolink.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.exception.NoPuedeAplicarsePromoException;
import ar.utn.edu.cursolink.exception.TarjetaNoEncontradaException;
import ar.utn.edu.cursolink.tp.promocion.Promocion;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

public class OrdenDeCompra {
	
	private String codigo;
	private Cliente cliente;
	private Promocion promo;
	
	
	//Constructors
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
