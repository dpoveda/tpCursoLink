package ar.utn.edu.cursolink.tp.usuario.cliente;

import java.util.List;

import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.tarjeta.Tarjeta;

public interface ClienteDTO {
	String getNombre(); 
	String getDni(); 
	String getEmail(); 
	String getTelefono(); 
	MedioDePago getMedioDePago();
	double getMontoBilletera();
	List<Tarjeta> getTarjetas();
}
