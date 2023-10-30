package Amazing;

import java.util.HashMap;
import java.util.Map;

public class pedido {
	private int idPedido;
	private String nombre;
	private String direccion;
	private int dni;
	private boolean EstadoPedido = true; // Si esta en True significa que el pedido esta abierto

	private Map<Integer, paquete> ListaCarrito = new HashMap<>();
	public pedido(int idpedido, String nombre, String direccion, int dni) {
		this.idPedido = idpedido;
		this.nombre = nombre;
		this.direccion = direccion;
		this.dni = dni;		
	}
	public String toString(){
		return getClass().getSimpleName()
			+ " (idPedido "+this.idPedido+", nombre "+this.nombre+ "direccion" + this.direccion+ "dni" + this.dni+")";
	}
	 
	
	public int agregarPaquete(int idPaquete,int volumen, int precio, int costoEnvio) { // Agrego paquete ordinario
		paquete paquete = new paqueteOrdinario(idPaquete,volumen,precio,costoEnvio);
		this.ListaCarrito.put(idPaquete, paquete);
		return idPaquete;
		
	}
	public int agregarPaquete(int idPaquete,int volumen, int precio, int porcentaje, int adicional) { //paquete especial
		paquete paquete = new paqueteEspecial(idPaquete,volumen,precio,porcentaje,adicional);
		this.ListaCarrito.put(idPaquete, paquete);
		return idPaquete;
		
	}
	
	
	public boolean quitarPaquete(int idPaquete) {
		if (ListaCarrito.containsKey(idPaquete)) {
	        // Si existe, quitar el paquete
			ListaCarrito.remove(idPaquete);
	        return true;
	    } 
		throw new RuntimeException("El paquete no existe " + idPaquete);
		
	}
	public double cerrarPedido() {
		if(this.ListaCarrito == null) {
			return 0;
		}
		
		this.EstadoPedido = false;
		double facturacionTotal = 0;
		for (paquete paquete : this.ListaCarrito.values()) {
			paquete.agregarCosto();
			facturacionTotal += paquete.getPrecio();
		}
		
		return facturacionTotal;
	}
	/*
	// Devuelve la facturacion de todos los paquetes sin tener encuenta la suma del costo de envio, porcentaje y valores adicionales
	public double FacturacionTotal() {
		double facturacionTotal =0;
		for (paquete paquete : this.ListaCarrito.values()) {
			facturacionTotal += paquete.getPrecio();
		}
		return facturacionTotal;
	}*/
	
	
	public boolean estadoPaquete(int idPaquete) {
		if(ListaCarrito.get(idPaquete).getEstado()) {
			return true;
		}
		return false;
		
	}
	
	public boolean estadoPedido() {
		return this.EstadoPedido;
	}
	public String getDireccion() {
		// TODO Auto-generated method stub
		return this.direccion;
	}
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	public int getIdPedido() {
		// TODO Auto-generated method stub
		return this.idPedido;
	}
	public Map<Integer, paquete> getListaCarrito() {
	    return this.ListaCarrito;
	}
	
	
	
	
	
	
}
