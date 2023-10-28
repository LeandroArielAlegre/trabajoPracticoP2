package Amazing;

import java.util.HashMap;
import java.util.Map;

public class pedido {
	private int idPedido;
	private String nombre;
	private String direccion;
	private int dni;

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
	 
	
	public void agregarPaquete(int volumen, int precio, int costoEnvio) {
		
	}
	public void agregarPaquete(int volumen, int precio, int porcentaje, int adicional) {
		
	}
	
	
	
	
	
	
}
