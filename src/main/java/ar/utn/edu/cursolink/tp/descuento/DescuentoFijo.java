package ar.utn.edu.cursolink.tp.descuento;

import java.time.LocalDate;

public class DescuentoFijo extends Descuento {
	private double monto;

	
	//Constructors
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
