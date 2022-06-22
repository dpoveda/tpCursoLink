package ar.utn.edu.cursolink.tp.producto;

import ar.utn.edu.cursolink.tp.usuario.proveedor.Proveedor;

public class AgregarProductoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Proveedor proveedor;
	private Producto producto;
	
	public AgregarProductoException(String mensaje, Proveedor proveedor, Producto producto) {
		super(mensaje);
		this.proveedor = proveedor;
		this.producto = producto;
		
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Producto getProducto() {
		return producto;
	}

	protected AgregarProductoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected AgregarProductoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	protected AgregarProductoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	protected AgregarProductoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	protected AgregarProductoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
