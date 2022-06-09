package ar.utn.edu.cursolink.tp.descuento;

import java.time.LocalDate;

public abstract class Descuento {
	protected LocalDate comienzo; 
	protected LocalDate fin;
	
	public void aplicarDescuento(double base) {
		this.descuento(base);
	}
	
	public abstract double descuento(double base);
	
}
