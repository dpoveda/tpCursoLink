package ar.utn.edu.cursolink.tp.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "usuarios")
public class Usuario { 
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="usuario_id")
	private Integer id;
	
	private String username;
	private String password;
	private String tipo;
	//protected Rol rol;
	
	
	//Constructors
	protected Usuario() {
		super();
	}
	
	protected Usuario(String username, String password, String tipo) {
		super();
		this.username = username;
		this.password = password;
		this.tipo = tipo;
	}

	//Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	


}
