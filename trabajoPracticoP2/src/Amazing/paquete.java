package Amazing;

public abstract class paquete {
	protected int idpaquete;
	protected int volumen;
	protected float precio;
	public paquete(int idpaquete, int volumen, float precio) {
		this.idpaquete = idpaquete;
		this.volumen = volumen;
		this.precio = precio;		
	}
	
	public String toString(){
		return getClass().getSimpleName()
			+ " (idpaquete "+this.idpaquete+", volumen "+volumen+ "precio" + precio+")";
	}
	public abstract void agregarCosto();
	
	//public abstract void agregarCosto(float costoEnvio); // El costo de envio se le suma al precio
	//public abstract void agregarCosto(float porcentaje, float pagaExtra); // Si el volumen supera los 3000 se suma la "pagaExtra"
																		  // y en caso de superar los 5000 se suma el doble de "pagaExtra".
	
}
