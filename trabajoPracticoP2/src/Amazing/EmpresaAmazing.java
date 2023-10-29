package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private Map<String, transporte> ListaTransportes = new HashMap<>();
	private Map<Integer, pedido> ListaPedidos = new HashMap<>();
	private int ContadorCodigoPaquete = 0; // Asigna automaticamente una llave en un map
	public EmpresaAmazing(String cuit) {
		this.cuit = cuit;
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
		int idPedido = this.ListaPedidos.size()+1;
		pedido pedido = new pedido(idPedido,cliente,direccion,dni);
		this.ListaPedidos.put(idPedido, pedido);
		
		return idPedido; // clave de id pedido
	}
	
	private boolean existePedido(int codPedido) {
		if(this.ListaPedidos.containsKey(codPedido)) {
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
			 this.ContadorCodigoPaquete +=1; 
				if (existePedido(codPedido) && estadoPedido(codPedido)) {
		            int codigoPaquete = this.ListaPedidos.get(codPedido).agregarPaquete(this.ContadorCodigoPaquete,volumen, precio, costoEnvio); // ingreso paquete
		            return codigoPaquete;
		        } else {
		            // Manejo de la situación en la que no existe el pedido
		        	throw new RuntimeException("No se encontró el pedido con el código: " + codPedido);
		        }   
		
	}
	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		this.ContadorCodigoPaquete +=1; 
		if (existePedido(codPedido) && estadoPedido(codPedido)) {
            int codigoPaquete = this.ListaPedidos.get(codPedido).agregarPaquete(this.ContadorCodigoPaquete,volumen, precio, porcentaje,adicional); // ingreso paquete
            return codigoPaquete;
        } else {
            // Manejo de la situación en la que no existe el pedido
        	throw new RuntimeException("No se encontró el pedido con el código: " + codPedido);
        }
	}
	@Override
	public boolean quitarPaquete(int codPaquete) {
		for(pedido pedido: this.ListaPedidos.values()) {
			if(pedido.estadoPedido()) {
				return pedido.quitarPaquete(codPaquete);
			}
		}
		return false;
	}
	@Override
	public double cerrarPedido(int codPedido) {
		double facturacion =0;
		if(estadoPedido(codPedido) && existePedido(codPedido)) {
			facturacion =this.ListaPedidos.get(codPedido).cerrarPedido();
		}else {
			throw new RuntimeException("No se encontró el pedido con el código o el pedido ya esta cerrado: " + codPedido);
		}
		return facturacion;
	}
	private boolean estadoPedido(int codPedido) {
		boolean ret =this.ListaPedidos.get(codPedido).estadoPedido();
		return ret;	
	}
	
	private  ArrayList<pedido> listaPedidosCerrados() {
		  ArrayList<pedido> pedidosCerrados = new ArrayList<>();
		  for (pedido pedido : this.ListaPedidos.values()) { // obtengo las llaves
			   if (!pedido.estadoPedido()) {
		            pedidosCerrados.add(pedido);
		        }
			}
		return pedidosCerrados;
		
	}
	
	@Override
	public String cargarTransporte(String patente) {
		 if (!ListaTransportes.containsKey(patente)) {
		        throw new RuntimeException("No se encontró un transporte con la patente: " + patente);
		    } // Verifico la existencia de esa patente en la lista de transportes
		 
		 transporte transporte = ListaTransportes.get(patente); // Obtengo el transporte
		 
		 
		 // Lo que devuelvo
		 StringBuilder result = new StringBuilder();
		 
		 for (pedido pedido : ListaPedidos.values()) {
		        if (!pedido.estadoPedido()) {
		            // El pedido está cerrado, se pueden cargar sus paquetes en el transporte
		            for (paquete paquete : pedido.getListaCarrito().values()) {
		            	if(paquete.getEstado()) {

			                // Aquí puedes agregar el paquete al transporte, por ejemplo:
		            		if(transporte.agregarCarga(paquete)) {
		            			result.append(" + [").append(pedido.getIdPedido()).append(" - ").append(paquete.getIdpaquete()).append("] ").append(pedido.getDireccion()).append("\n");
		            			paquete.setEstado(); // si se logro cargar el paquete en el camion, se cambia el estado del paquete a cargado (false)
		            		}
		            	}
		                
		            }
		        }
		    }

		    if (result.length() == 0) {
		        throw new RuntimeException("No hay ningún pedido cerrado, no es posible cargar el transporte");
		    }
		 
		 
		 
		
		 return result.toString();
		
		
		
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
	public String getCuit() {
		return cuit;
	}

}
