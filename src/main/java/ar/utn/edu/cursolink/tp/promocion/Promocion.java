package ar.utn.edu.cursolink.tp.promocion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import ar.utn.edu.cursolink.tp.exception.CuponYaUsadoException;
import ar.utn.edu.cursolink.tp.exception.TarjetaNoEncontradaException;
import ar.utn.edu.cursolink.tp.ordendecompra.OrdenDeCompra;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Promocion {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="promo_id")
	private Integer id;
	
	public double aplicarPromocion(double base) throws CuponYaUsadoException {
		return this.calcularPromo(base);
	}
	
	public boolean puedeAplicarseEn(OrdenDeCompra ordenDeCompra) throws TarjetaNoEncontradaException{
		return this.puedeAplicar(ordenDeCompra);
	}
	
	protected abstract double calcularPromo(double base) throws CuponYaUsadoException;

	public abstract boolean puedeAplicar(OrdenDeCompra ordenDeCompra) throws TarjetaNoEncontradaException;
	 	
}
