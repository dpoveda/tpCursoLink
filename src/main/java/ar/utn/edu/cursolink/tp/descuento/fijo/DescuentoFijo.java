package ar.utn.edu.cursolink.tp.descuento.fijo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import ar.utn.edu.cursolink.tp.descuento.Descuento;


@Entity
//@Table(name="descuentos_fijos")
public class DescuentoFijo extends Descuento {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="descFijo_id")
	private Integer id;
	
	
	@Min(value = 0, message = "El monto no debe ser menor a cero")
	@Column(name="descFijo_monto")
	private double monto;

	
	//Constructors
	protected DescuentoFijo() {
		super();
	}
	
	public DescuentoFijo(double monto) {
		super();
		this.monto = monto;
		this.comienzo = LocalDate.now();
	}


	//Getters and Setters
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	//Methods
	@Override
	public double descuento(double base) {	
		return this.getMonto();
	}
}
