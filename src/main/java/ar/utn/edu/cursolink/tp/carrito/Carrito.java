package ar.utn.edu.cursolink.tp.carrito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ar.utn.edu.cursolink.tp.exception.TieneItemRepetidoException;

@Entity
public class Carrito {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="carr_id")
	private Integer id;
	
	@OneToMany
	private List<ItemCarrito> items;
	
	//Constructor
	protected Carrito() {
		super();
	}

	public Carrito(Collection<ItemCarrito> items) {
		super();
		this.items = new ArrayList<ItemCarrito>();
	}
	
	
	//Getters and Setters
	//Clone
	public List<ItemCarrito> getItems() { //me da una copia de items, utilizar esto cuando quiero usar la collection items
		List<ItemCarrito> items2 = new ArrayList<ItemCarrito>();
		   items2.addAll(this.items);
		   return items2;
	}

	
	public void setItems(List<ItemCarrito> items) {
		this.items = items;
	}



	//Methods
	public void agregarItem(ItemCarrito item) throws TieneItemRepetidoException {
		if(tieneProductoRepetido(item)) {
			throw new TieneItemRepetidoException();
		}
		items.add(item);
	}
	

	public void solucionarCantidadProductoRepetido(ItemCarrito item) {
		ItemCarrito itemEncontrado = this.getItems().stream().filter(x->x.equals(item)).findFirst().get();
		int cantTotal =  item.getCantidad() + itemEncontrado.getCantidad();
		itemEncontrado.setCantidad(cantTotal);	
	}


	public boolean tieneItem(ItemCarrito item) {
		return (this.getItems().contains(item))? true : false;
	}

	public boolean tieneProductoRepetido(ItemCarrito item) {
		return(this.getItems().stream().anyMatch(itemX->itemX.getProducto().equals(item.getProducto())))? true : false;
	}
	
	public double calcularPrecioTotal() {
		return this.getItems().stream().mapToDouble(item -> item.obtenerPrecio()).sum();
	}
		
		
	@Override
	public String toString() {
		return "Carrito [items=" + items + "]";
	}



}
