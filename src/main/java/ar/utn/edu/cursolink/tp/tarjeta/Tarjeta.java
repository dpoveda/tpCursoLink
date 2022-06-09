package ar.utn.edu.cursolink.tp.tarjeta;

import ar.utn.edu.cursolink.exception.TipoDeTarjetaIncorrectoException;
import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

public class Tarjeta {
	private String nombreBanco;
	private int numero;
	private Cliente cliente;
	private MedioDePago tipo;
	
	
	//Constructors
	public Tarjeta(Cliente cliente, String nombrebanco, int numero, MedioDePago tipo) throws TipoDeTarjetaIncorrectoException {
		super();
		this.setCliente(cliente);
		this.nombreBanco = nombrebanco;
		this.numero = numero;
		this.setTipo(tipo);
	}
	
	
	//Getters and setters
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public MedioDePago getTipo() {
		return tipo;
	}

	public void setTipo(MedioDePago tipo) throws TipoDeTarjetaIncorrectoException {
		switch(tipo) {
		case TARJETADECREDITO:
			this.tipo = tipo;
			break;
		case TARJETADEDEBITO:
			this.tipo = tipo;
			break;
		default:
			throw new TipoDeTarjetaIncorrectoException();
		}
		
	}
	


	@Override
	public String toString() {
		return "Tarjeta [nombreBanco=" + nombreBanco + ", numero=" + numero + ", cliente=" + cliente + ", tipo=" + tipo
				+ "]";
	}


	
	
	

}
