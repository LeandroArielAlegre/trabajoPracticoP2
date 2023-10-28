package Amazing;

public class paqueteEspecial extends paquete{
	private float porcentaje;
	private float pagaExtra;
	public paqueteEspecial(int idpaquete, int volumen, float precio,float porcentaje, float pagaExtra) {
		super(idpaquete,volumen,precio);
		this.porcentaje = porcentaje;
		this.pagaExtra = pagaExtra;
	}
	

	@Override
	public void agregarCosto() {
		// Si el volumen supera los 3000 se suma la "pagaExtra"
				// y en caso de superar los 5000 se suma el doble de "pagaExtra".
				this.precio = (this.precio * (this.porcentaje/100)); // porcentaje extra
				
				if(this.volumen > 3000 && this.volumen < 5000) {
					this.precio += this.pagaExtra;
				}else if(this.volumen > 5000) {
					this.precio +=  this.pagaExtra*2;
				}
		
	}
	
	
	

}
