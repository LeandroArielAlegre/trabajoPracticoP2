package Amazing;

public class camion extends transporte {
	float valorAdicional;
	public camion(String patente, int volumen, int precio, int valorAdicional) {
		super(patente,volumen,precio);
		this.valorAdicional = valorAdicional;
	}

	@Override
	public boolean agregarCarga(paquete paquete) {
		return false;
	}
	public boolean agregarCarga(paqueteEspecial paquete) throws Exception { // LOS CAMIONES ESPECIALES SOLAMENTE GUARDAN PAQUETES ESPECIALES
		int espacioUsado = volumenUsado(); // Quiero saber cuanto espacio estoy ocupando con todos mis paquetes
		int espacioRestante = espacioUsado + paquete.getvolumen(); // Quiero saber si sumando el espacio ocupado mas el nuevo paquete aun me queda espacio
		if(hayEspacio() &&  this.volumen > espacioRestante) {
			this.Carga.put(paquete.getIdpaquete(), paquete); // La clave del map es igual al idpaquete del paquete
			return true;
		}else {
			return false;
		}
		
	}
	
	public void aumentarPaga() {
		int cantidad = cantidadDePaquetes();
		if(!cargaVacia()) {
			this.valorAdicional *= cantidad;
			this.precio += this.valorAdicional;
			this.valorAdicional = 0; // POR CADA VIAJE SE COBRA UN EXTRA POR LA CANTIDAD DE PAQUETES EN LA CARGA
			// LUEGO DE TERMINAR EL VIAJE, VALOR ADICIONAL VUELVE A ESTAR EN 0.
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	private boolean hayEspacio() {
		// Verificamos si hay hay espacio en el camion
		
		int volumenDePaquetes = 0;
		for (paquete paquete : this.Carga.values()) {
		        volumenDePaquetes += paquete.getvolumen();
		}
		if (volumenDePaquetes > this.volumen) {  // Si la suma de los volumenes de los paquetes almacenados en el camion es superior al volumen
			return false;
		}
		
		return true;	
	}
	
	private int volumenUsado() {
		// Verificamos si hay hay espacio en el camion
		int volumenDePaquetes = 0;
		for (paquete paquete : this.Carga.values()) {
		        volumenDePaquetes += paquete.getvolumen();
		}
		return volumenDePaquetes;	
	}
	
	
	
	
	
	
	
	
	

}
