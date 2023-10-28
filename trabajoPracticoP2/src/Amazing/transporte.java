package Amazing;

import java.util.HashMap;
import java.util.Map;



public abstract class transporte {
	protected String patente;
	protected int volumen;
	protected float precio;
	protected Map<Integer, paquete> Carga = new HashMap<>();
	public transporte(String patente, int volumen, float precio) {
		this.patente = patente;
		this.volumen = volumen;
		this.precio = precio;
		
	}
	
	public String toString(){
		return getClass().getSimpleName()
			+ " (idpaquete "+this.patente+", volumen "+volumen+ "precio" + precio+")";
	}
	
	public abstract void agregarCarga();
	public int cantidadDePaquetes() {
		int contador = 0;
		if(!cargaVacia()) {
			for (@SuppressWarnings("unused") paquete paquete : this.Carga.values()) {
		        contador =+1;
			}
		}
		return contador;
		
	}
	public boolean cargaVacia() {
		if(this.Carga ==null) {
			return true;
		}
		return false;
	}


}
