package Amazing;

public class paqueteOrdinario extends paquete {
	
	public paqueteOrdinario(int idpaquete, int volumen, float precio) {
		super(idpaquete,volumen,precio);
	}

	
	@Override
	public void agregarCosto(){
	}
	public void agregarCosto(float costoEnvio) { // se suma elcosto de envio al precio del paquete
		this.precio += costoEnvio;
		
	}



}
