//package ar.utn.edu.cursolink.tp.spring.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//	@ExceptionHandler(ProductoRepetidoException.class)
//	@ResponseBody
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public String repetido(ProductoRepetidoException ex) {
//		return "El producto " + ex.getProducto().getNombre() + " ya existe.";
//	}
//}
