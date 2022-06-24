package ar.utn.edu.cursolink.tp.usuario;

public class Usuario { 
	
	
	private String username;
	private String password;
	private String tipo;
	
	
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
