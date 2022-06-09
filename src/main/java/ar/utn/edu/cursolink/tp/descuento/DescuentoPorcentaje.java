package ar.utn.edu.cursolink.tp.descuento;

import java.time.LocalDate;

public class DescuentoPorcentaje extends Descuento {
	private double porcentaje;
	
	//Constructors
	public DescuentoPorcentaje(double porcentaje) {
		super();
		this.porcentaje = porcentaje;
		this.comienzo = LocalDate.now();
	}
	
	
	//Getters and Setters
	public double getPorcentaje() {
		return porcentaje;
	}


	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	//Methods
	@Override
	public double descuento(double base) {
		return (int) (base * this.getPorcentaje());
	}


	

}
