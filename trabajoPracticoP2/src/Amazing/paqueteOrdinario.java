package Amazing;

public class paqueteOrdinario extends paquete {
	private int costoEnvio;
	public paqueteOrdinario(int idpaquete, int volumen, int precio, int costoEnvio) {
		super(idpaquete,volumen,precio);
		this.costoEnvio = costoEnvio;
	}

	
	@Override
	public void agregarCosto(){// se suma elcosto de envio al precio del paquete
		this.precio += this.costoEnvio;
	}


}
