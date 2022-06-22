package ar.utn.edu.cursolink.tp.promocion;

import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.exception.TarjetaNoEncontradaException;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;

public abstract class Promocion {
		
	public double aplicarPromocion(double base) throws CuponYaUsadoException {
		return this.calcularPromo(base);
	}
	
	public boolean puedeAplicarseEn(OrdenDeCompra ordenDeCompra) throws TarjetaNoEncontradaException{
		return this.puedeAplicar(ordenDeCompra);
	}
	
	protected abstract double calcularPromo(double base) throws CuponYaUsadoException;

	public abstract boolean puedeAplicar(OrdenDeCompra ordenDeCompra) throws TarjetaNoEncontradaException;
	 	
}
