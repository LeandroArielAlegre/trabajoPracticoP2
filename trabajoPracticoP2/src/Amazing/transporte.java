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
	
	public abstract boolean agregarCarga(paquete paquete);
	public abstract double Facturacion();
	public int cantidadDePaquetes() {
		 return Carga.size();
	}
	public boolean cargaVacia() {
		 if(this.Carga == null) {
			 return true;
		 }
		 return false;
	}
	
	
	
	
	
	
	
	
	

}
