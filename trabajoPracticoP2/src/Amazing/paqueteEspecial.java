package Amazing;

public class paqueteEspecial extends paquete{

	public paqueteEspecial(int idpaquete, int volumen, float precio) {
		super(idpaquete,volumen,precio);
	}
	

	@Override
	public void agregarCosto() {
		// TODO Auto-generated method stub
		
	}
	public void agregarCosto(float porcentaje, float pagaExtra) {  
		// Si el volumen supera los 3000 se suma la "pagaExtra"
		// y en caso de superar los 5000 se suma el doble de "pagaExtra".
		this.precio = (this.precio * (porcentaje/100)); // porcentaje extra
		
		if(this.volumen > 3000 && this.volumen < 5000) {
			this.precio += pagaExtra;
		}else if(this.volumen > 5000) {
			this.precio +=  pagaExtra*2;
		}
		
	}
	
	public int getIdpaquete() {
		return this.idpaquete;
	}
	public int getvolumen() {
		return this.volumen;
	}

}
