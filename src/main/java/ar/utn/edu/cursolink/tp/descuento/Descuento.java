package ar.utn.edu.cursolink.tp.descuento;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public abstract class Descuento{
	

	//esto es nuevo recien agregado
//	@Id @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="desc_id")
//	private Integer id;
	
	
	protected LocalDate comienzo; 
	protected LocalDate fin;
	
	public void aplicarDescuento(double base) {
		this.descuento(base);
	}
	
	public abstract double descuento(double base);
	
}
