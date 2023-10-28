package Amazing;

public class paqueteOrdinario extends paquete {
	private float costoEnvio;
	public paqueteOrdinario(int idpaquete, int volumen, float precio, float costoEnvio) {
		super(idpaquete,volumen,precio);
	}

	
	@Override
	public void agregarCosto(){// se suma elcosto de envio al precio del paquete
		this.precio += this.costoEnvio;
	}


}
