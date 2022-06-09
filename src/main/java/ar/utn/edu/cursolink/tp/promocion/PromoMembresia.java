package ar.utn.edu.cursolink.tp.promocion;

import java.util.ArrayList;
import java.util.Collection;

import ar.utn.edu.cursolink.tp.descuento.Descuento;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

public class PromoMembresia extends Promocion{
	
	private Collection <Cliente> clientes;
	private Descuento tipoDescuento; 
	
	//Constructors
	public PromoMembresia(Descuento tipoDescuento) {
		super();
		this.tipoDescuento = tipoDescuento;
	}
	
	//Getters and setters
	//Clone
		public Collection<Cliente> getClientes() {
			Collection<Cliente> clientes2 = new ArrayList<Cliente>();
			clientes2.addAll(this.clientes);
			return clientes2;
		}

		public void setClientes(Collection<Cliente> clientes) {
			this.clientes = clientes;
		}

		public Descuento getTipoDescuento() {
			return tipoDescuento;
		}

		public void setTipoDescuento(Descuento tipoDescuento) {
			this.tipoDescuento = tipoDescuento;
		}

	//Methods
	@Override
	protected double calcularPromo(double base) {
		return base - tipoDescuento.descuento(base);
	}

	@Override
	public boolean puedeAplicar(OrdenDeCompra ordenDeCompra) {
		return (ordenDeCompra.getCliente().isMiembro())? true : false;
	}

	public void agregarMiembro(Cliente cliente) {
		cliente.setMiembro(true);
		this.getClientes().add(cliente);
	}
	
}
