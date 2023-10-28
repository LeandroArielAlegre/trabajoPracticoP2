package Amazing;

import java.util.HashMap;
import java.util.Map;

public class pedido {
	private int idPedido;
	private String nombre;
	private String direccion;
	private int dni;

	private Map<Integer, paquete> ListaCarrito = new HashMap<>();
	private int keyMap = 0; // Asigna automaticamente una llave en un map
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
	 
	
	public int agregarPaquete(int volumen, int precio, int costoEnvio) { // Agrego paquete ordinario
		this.keyMap +=1; 
		paquete paquete = new paqueteOrdinario(keyMap,volumen,precio,costoEnvio);
		this.ListaCarrito.put(keyMap, paquete);
		return this.keyMap;
		
	}
	public int agregarPaquete(int volumen, int precio, int porcentaje, int adicional) { //paquete especial
		this.keyMap +=1;
		paquete paquete = new paqueteEspecial(keyMap,volumen,precio,porcentaje,adicional);
		this.ListaCarrito.put(keyMap, paquete);
		return this.keyMap;
		
	}
	
	
	public boolean quitarPaquete(int idPaquete) {
		if (ListaCarrito.containsKey(idPaquete)) {
	        // Si existe, quitar el paquete
			ListaCarrito.remove(idPaquete);
	        return true;
	    } 
	       return false; // si no pudo devuelve false
	    
		
	}
	
	
	
	
	
	
}
