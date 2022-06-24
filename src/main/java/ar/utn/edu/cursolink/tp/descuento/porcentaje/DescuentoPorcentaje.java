package ar.utn.edu.cursolink.tp.descuento.porcentaje;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;


import ar.utn.edu.cursolink.tp.descuento.Descuento;

@Entity
public class DescuentoPorcentaje extends Descuento {

	
	@Min(value = 0, message = "El porcentaje no debe ser menor a cero")
	@Column(name="descPorc_porcentaje")
	private double porcentaje;
	
	//Constructors
	protected DescuentoPorcentaje() {
		super();
	}
	
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

	@Override
	public String toString() {
		return "DescuentoPorcentaje [porcentaje=" + porcentaje + "]";
	}
	

}
