package Amazing;

public class paqueteEspecial extends paquete{
	private int porcentaje;
	private int adicional;
	public paqueteEspecial(int idpaquete, int volumen, int precio,int porcentaje, int adicional) {
		super(idpaquete,volumen,precio);
		this.porcentaje = porcentaje;
		this.adicional = adicional;
	}
	

	@Override
	public void agregarCosto() {
		// Si el volumen supera los 3000 se suma la "pagaExtra"
				// y en caso de superar los 5000 se suma el doble de "pagaExtra".
				this.precio = (this.precio * (this.porcentaje/100)); // porcentaje extra
				
				if(this.volumen > 3000 && this.volumen < 5000) {
					this.precio += this.adicional;
				}else if(this.volumen > 5000) {
					this.precio +=  this.adicional*2;
				}
		
	}
	
	
	

}
