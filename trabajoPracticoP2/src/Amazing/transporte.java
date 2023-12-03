package Amazing;


import java.util.HashMap;
import java.util.Iterator;

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
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" (idpatente ").append(this.patente)
                .append(", volumen ").append(this.volumen)
                .append(", precio ").append(this.precio)
                .append(")");
        
        stringBuilder.append("\n---**Paquetes:**---\n");
        for (paquete paquete: Carga.values()){
            stringBuilder.append(paquete.toString()).append(" ").append("\n");
        }
    
        
        return stringBuilder.toString();
	}
	
	public abstract boolean agregarCarga(paquete paquete);
	public abstract double Facturacion();
	public int cantidadDePaquetes() {
		 return Carga.size();
	}
	public boolean cargaVacia() {
		 if(this.Carga.isEmpty()) {
			 return true;
		 }
		 return false;
	}
	public Map<Integer, paquete> getCarga() {
		return this.Carga;
	}
	public String getPatente() {
		return this.patente;
	}
	
	
	 public boolean hayCargasIdenticas(transporte otroTransporte) {
	        // Obtener las cargas de paquetes de los dos transportes
	        
		 Iterator<Map.Entry<Integer, paquete>> iterator1 = this.Carga.entrySet().iterator();
	     Iterator<Map.Entry<Integer, paquete>> iterator2 = otroTransporte.getCarga().entrySet().iterator();

	        
	       

	        // Verificar si las cargas tienen el mismo tamaño
	        if (this.Carga.size() != otroTransporte.getCarga().size()) {
	            return false;
	        }
	      

	        while (iterator1.hasNext() && iterator2.hasNext()) {
	            // Obtener la entrada actual de cada HashMap
	            Map.Entry<Integer, paquete> entry1 = iterator1.next();
	            Map.Entry<Integer, paquete> entry2 = iterator2.next();

	            // Acceder a la clave y al valor de cada entrada
	            
	            paquete valor1 = entry1.getValue();
	            paquete valor2 = entry2.getValue();
	         // Verificar si los paquetes son idénticos
	            if (valor1.equals(valor2)) {
	                return true;
	            }else {
	            	return false;
	            }
	        }
	        return false;


	        // Si todas las comparaciones fueron exitosas, las cargas son idénticas
	        
	    }

	   /* private boolean sonPaquetesIdenticos(paquete paquete1, paquete paquete2) {
	    	// verifico si son del mismo tipo
	    	if (paquete1.getClass() != paquete2.getClass()) {
	    	    return false;
	    	}
	    	// Comparo los atributos de ambos paquetes
	    	if(paquete1.getPrecio() == paquete2.getPrecio() && paquete1.getvolumen() == paquete2.getvolumen()) {
	    		return true;
	    	}
	    	
	    	return false;
	       
	    }*/
	
	
	
	
	
	


	
	
	

}
