package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String nombre;
	private Map<String, transporte> ListaTransportes = new HashMap<>();
	private Map<Integer, pedido> ListaPedidos = new HashMap<>();
	private int keyMapPedido = 0;
	public EmpresaAmazing(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		
		if(!existePatente(patente)) {
			transporte automovil = new comunes(patente,volMax,valorViaje,maxPaq);
			this.ListaTransportes.put(patente, automovil);
		}else {
			System.out.println("Patente ya registrada");
			
		}
		
		
	}
	private boolean existePatente(String patente) {
		if(this.ListaTransportes.containsKey(patente)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if(!existePatente(patente)) {
			transporte utilitario = new utilitario(patente,volMax,valorViaje,valorExtra);
			this.ListaTransportes.put(patente, utilitario);
		}else {
			System.out.println("Patente ya registrada");
			
		}
		
		
	}
	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if(!existePatente(patente)) {
			transporte camion = new camion(patente,volMax,valorViaje,adicXPaq);
			this.ListaTransportes.put(patente, camion);
		}else {
			System.out.println("Patente ya registrada");
		}
		
	}
	//UN CLIENTE PUEDE TENER MAS DE 1 PEDIDO
	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		this.keyMapPedido +=1;
		pedido pedido = new pedido(keyMapPedido,cliente,direccion,dni);
		this.ListaPedidos.put(keyMapPedido, pedido);
		
		return this.keyMapPedido; // clave de id pedido
	}
	
	private boolean existePedido(int codPedido) {
		if(this.ListaPedidos.containsKey(codPedido)) {
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
				if (existePedido(codPedido)) {
		            int codigoPaquete = this.ListaPedidos.get(codPedido).agregarPaquete(volumen, precio, costoEnvio); // ingreso paquete
		            return codigoPaquete;
		        } else {
		            // Manejo de la situación en la que no existe el pedido
		        	throw new RuntimeException("No se encontró el pedido con el código: " + codPedido);
		        }   
		
	}
	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		if (existePedido(codPedido)) {
            int codigoPaquete = this.ListaPedidos.get(codPedido).agregarPaquete(volumen, precio, porcentaje,adicional); // ingreso paquete
            return codigoPaquete;
        } else {
            // Manejo de la situación en la que no existe el pedido
        	throw new RuntimeException("No se encontró el pedido con el código: " + codPedido);
        }
	}
	@Override
	public boolean quitarPaquete(int codPaquete) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public double cerrarPedido(int codPedido) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String cargarTransporte(String patente) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double costoEntrega(String patente) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Map<Integer, String> pedidosNoEntregados() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double facturacionTotalPedidosCerrados() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean hayTransportesIdenticos() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getNombre() {
		return nombre;
	}

}
