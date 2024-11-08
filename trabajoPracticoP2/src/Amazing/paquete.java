package Amazing;

public abstract class paquete {
	protected int idpaquete;
	protected int volumen;
	protected int precio;
	protected boolean estado= true; // True si no esta cargado
	public paquete(int idpaquete, int volumen, int precio) {
		this.idpaquete = idpaquete;
		this.volumen = volumen;
		this.precio = precio;		
	}
	
	public String toString(){
		return getClass().getSimpleName()
			+ " (idpaquete "+ this.idpaquete + ", volumen "+ volumen + " precio " + precio +")";
	}
	public abstract void agregarCosto();

	public int getIdpaquete() {
		return this.idpaquete;
	}
	public int getvolumen() {
		return this.volumen;
	}
	public int getPrecio() {
		return this.precio;
	}
	public boolean getEstado() {
		return this.estado;
	}
	public void setEstado() {
		this.estado = false;
	}
	
	public boolean equals(paquete paquete) {
		// verifico si son del mismo tipo
    	if (this.getClass() != paquete.getClass()) {
    	    return false;
    	}
    	// Comparo los atributos de ambos paquetes
    	if(this.getPrecio() == this.getPrecio() && paquete.getvolumen() == paquete.getvolumen()) {
    		return true;
    	}
    	
		
		return false;
	}
	
	
	
	
}
