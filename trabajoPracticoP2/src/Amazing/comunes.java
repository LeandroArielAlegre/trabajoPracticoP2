package Amazing;



public class comunes extends transporte {
	int limiteDePaquetes;
	public comunes(String patente, int volumen, float precio, int limiteDePaquetes) {
		super(patente,volumen,precio);
		this.limiteDePaquetes = limiteDePaquetes;
	}

	@Override
	public boolean agregarCarga(paquete paquete) {
		if(paquete instanceof paqueteOrdinario) {
			return agregarCarga((paqueteOrdinario) paquete);
		}
		return false;
	}
	public boolean agregarCarga(paqueteOrdinario paquete) { // LOS comunes llevan paquetes ordinarios
	
		if(!maximoAlcanzado() && paquete.getvolumen() <= 2000 ) { // si el limite de paquetes no fue alcanzado y si el volumen del paquete a ingresar es menor a 2000
			int espacioUsado = volumenUsado(); // Quiero saber cuanto espacio estoy ocupando con todos mis paquetes
			int espacioRestante = espacioUsado + paquete.getvolumen(); // Quiero saber si sumando el espacio ocupado mas el nuevo paquete aun me queda espacio
			if(hayEspacio() &&  this.volumen >= espacioRestante) {
				this.Carga.put(paquete.getIdpaquete(), paquete); // La clave del map es igual al idpaquete del paquete
				return true;
			}else {
				return false;
				
			}
			
		}else {
			return false;
			
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
		// Verificamos si hay hay espacio 
		int volumenDePaquetes = 0;
		for (paquete paquete : this.Carga.values()) {
		        volumenDePaquetes += paquete.getvolumen();
		}
		return volumenDePaquetes;	
	}
	
	
	private boolean maximoAlcanzado() {
		int contadorPaquetes = cantidadDePaquetes();
		if(this.limiteDePaquetes == contadorPaquetes) {
			return true;
		}
		return false;
	}

	@Override
	public double Facturacion() {
		// TODO Auto-generated method stub
		return this.precio;
	}

	
	
	
	

}
