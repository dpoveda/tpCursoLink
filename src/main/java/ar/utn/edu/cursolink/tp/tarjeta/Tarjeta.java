package ar.utn.edu.cursolink.tp.tarjeta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ar.utn.edu.cursolink.tp.exception.TipoDeTarjetaIncorrectoException;
import ar.utn.edu.cursolink.tp.mediodepago.MedioDePago;
import ar.utn.edu.cursolink.tp.usuario.cliente.Cliente;

@Entity
public class Tarjeta {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tarj_id")
	private Integer id;
	
	@NotBlank
	@Column(name="tarj_nombre")
	private String nombreBanco;
	
	@NotNull
	@Column(name="tarj_numero")
	private int numero;
	
	@ManyToOne
	@JoinColumn(name="tarj_cliente")
	private Cliente cliente;
	
	@NotNull
	@Column(name="tarj_tipoMedio")
	private MedioDePago tipo;
	
	
	//Constructors
	protected Tarjeta() { 
		super();
	}
	
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
