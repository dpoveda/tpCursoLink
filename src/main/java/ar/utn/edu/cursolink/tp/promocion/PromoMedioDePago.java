package ar.utn.edu.cursolink.tp.promocion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ar.utn.edu.cursolink.tp.descuento.Descuento;
import ar.utn.edu.cursolink.tp.exception.TarjetaNoEncontradaException;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;

@Entity
public class PromoMedioDePago extends Promocion{
	
	@ManyToOne
	private Descuento tipoDescuento; 
	
	//Constructor
	protected PromoMedioDePago(Descuento tipoDescuento) {
		super();
		this.tipoDescuento = tipoDescuento;
	}

	//Methods
	@Override
	protected double calcularPromo(double base) {
		return base - tipoDescuento.descuento(base);
	}

	@Override
	public boolean puedeAplicar(OrdenDeCompra ordenDeCompra) throws TarjetaNoEncontradaException {
		boolean resultado = false;
		
		switch (ordenDeCompra.getCliente().getMedioDePago()) {
		case BILLETERAELECTRONICA:
			resultado = ordenDeCompra.getCliente().getMontoBilletera() >= ordenDeCompra.getCliente().getCarrito().calcularPrecioTotal(); 
			break;
		case TARJETADECREDITO:	
		case TARJETADEDEBITO:
			if(ordenDeCompra.getCliente().getTarjetas().isEmpty()) {
				throw new TarjetaNoEncontradaException();
			}else {
				resultado = ordenDeCompra.getCliente().getTarjetas().stream().anyMatch(t -> t.getTipo().equals(ordenDeCompra.getCliente().getMedioDePago()));
				break;
			}
		default:
			break;
		}

		return resultado;
		

	}

}
