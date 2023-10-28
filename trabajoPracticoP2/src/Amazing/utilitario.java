package Amazing;

public class utilitario extends transporte {
	float valorAdicional;
	public utilitario(int patente, int volumen, float precio, float valorAdicional) {
		super(patente,volumen,precio);
		this.valorAdicional = valorAdicional;
	}

	@Override
	public void agregarCarga() {
		// TODO Auto-generated method stub
		
	}
	public void agregarCarga(paquete paquete) throws Exception { // LOS comunes llevan paquetes ordinarios
		
			int espacioUsado = volumenUsado(); // Quiero saber cuanto espacio estoy ocupando con todos mis paquetes
			int espacioRestante = espacioUsado + paquete.getvolumen(); // Quiero saber si sumando el espacio ocupado mas el nuevo paquete aun me queda espacio
			if(hayEspacio() &&  this.volumen > espacioRestante) {
				this.Carga.put(paquete.getIdpaquete(), paquete); // La clave del map es igual al idpaquete del paquete
			}else {
				throw new Exception("No hay espacio disponible en el transporte");
			}
			
		
			
		
		
		
	}
	public void aumentarPaga() {
		int cantidad = cantidadDePaquetes();
		if(!cargaVacia() && cantidad > 3) {
			this.precio += this.valorAdicional;
			this.valorAdicional = 0; // si hay mas de 3 paquetes por viaje se cobra un Adicional
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

	


}
