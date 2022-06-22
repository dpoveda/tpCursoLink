package ar.utn.edu.cursolink.tp.tarjeta;

import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;

public interface TarjetaDTO {

	String getNombreBanco();
	
	String getCliente();
	
	MedioDePago getTipo();
	
}
