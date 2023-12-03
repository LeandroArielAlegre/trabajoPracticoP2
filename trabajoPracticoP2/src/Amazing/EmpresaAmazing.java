package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String cuit;
	private Map<String, transporte> ListaTransportes = new HashMap<>();
	private Map<Integer, pedido> ListaPedidos = new HashMap<>();
	private int ContadorCodigoPaquete = 0; // Asigna automaticamente una llave en un map
	private double facturacionTotal = 0;
	public EmpresaAmazing(String cuit) {
		this.cuit = cuit;
	}

	public String toString(){
		 StringBuilder stringBuilder = new StringBuilder();
		 
		   stringBuilder.append(getClass().getSimpleName())
           .append(" (cuit ").append(this.cuit).append(")\n");

   stringBuilder.append("Transportes:\n");
   for (Map.Entry<String, transporte> entry : ListaTransportes.entrySet()) {
       stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
   }

   stringBuilder.append("Pedidos:\n");
   for (Map.Entry<Integer, pedido> entry : ListaPedidos.entrySet()) {
       stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
   }

   stringBuilder.append("Contador Código Paquete: ").append(ContadorCodigoPaquete).append("\n");
   stringBuilder.append("Facturación Total: ").append(facturacionTotal);

   return stringBuilder.toString();
}
	
	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		
		if(!existePatente(patente)) {
			transporte automovil = new comunes(patente,volMax,valorViaje,maxPaq);
			this.ListaTransportes.put(patente, automovil);
		}else {
			throw new RuntimeException("Esta patente ya se encuentra registrada " + patente);
			
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
			throw new RuntimeException("Esta patente ya se encuentra registrada " + patente);
			
		}
		
		
	}
	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if(!existePatente(patente)) {
			transporte camion = new camion(patente,volMax,valorViaje,adicXPaq);
			this.ListaTransportes.put(patente, camion);
		}else {
			throw new RuntimeException("Esta patente ya se encuentra registrada " + patente);
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
		        	
		        }  throw new RuntimeException("No se encontró el pedido con el código: " + codPedido); 
		
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
	// Execption
	
	
	
	@Override
	public boolean quitarPaquete(int codPaquete) {
		for(pedido pedido: this.ListaPedidos.values()) {
			if (pedido.quitarPaquete(codPaquete)) {
                // Si el paquete se quitó con éxito, retornar true
                return true;
            }
		}
		
		//return false;
		throw new RuntimeException("El paquete no existe " + codPaquete);
	}
	@Override
	public double cerrarPedido(int codPedido) {
		double facturacion =0;
		if(estadoPedido(codPedido) && existePedido(codPedido)) {
			facturacion =this.ListaPedidos.get(codPedido).cerrarPedido();
		}else {
			throw new RuntimeException("No se encontró el pedido con el código o el pedido ya esta cerrado: " + codPedido);
		}
		this.facturacionTotal += facturacion;
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
	
	public String consultarCargaTransporte(String patente) {
		StringBuilder stringBuilder = new StringBuilder();
		if(this.ListaTransportes.containsKey(patente)) {
			Map<Integer, paquete> carga = new HashMap<>(this.ListaTransportes.get(patente).getCarga());
			for (Map.Entry<Integer, paquete> entry : carga.entrySet()) {
			       stringBuilder.append("LA CARGA ES :").append("\n").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			   }
			return stringBuilder.toString();
		}
		return stringBuilder.append("{ }").toString();
		
	}
	
	public String consultarPaquetesPedido(int idpedido) {
		StringBuilder stringBuilder = new StringBuilder();
		if(this.ListaPedidos.containsKey(idpedido)) {
			Map<Integer, paquete> carga = new HashMap<>(this.ListaPedidos.get(idpedido).getListaCarrito());
			for (Map.Entry<Integer, paquete> entry : carga.entrySet()) {
			       stringBuilder.append("LA CARGA ES :").append("\n").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			   }
			return stringBuilder.toString();
		}
		return stringBuilder.append("{ }").toString();
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
		            			result.append(" + [ ").append(pedido.getIdPedido()).append(" - ").append(paquete.getIdpaquete())
		            			.append(" ] ")
		            			.append(pedido.getDireccion()).append("\n");
		            			paquete.setEstado(); // si se logro cargar el paquete en el camion, se cambia el estado del paquete a cargado (false)
		            		}
		            	}
		                
		            }
		        }
		    }

		    if (result.length() == 0) {
		        System.out.println("No es posible cargar el transporte");
		    }
		 
		 
		 
		
		 return result.toString();
		
		
		
	}
	@Override
	public double costoEntrega(String patente) {
		double costoEntrega = 0;
		if (!this.ListaTransportes.containsKey(patente)) {
			throw new RuntimeException("El transporte no se encuentra registrado");
		}
		if (this.ListaTransportes.get(patente).cargaVacia()) {
			throw new RuntimeException("El transporte no se encuentra cargado");
		}

		costoEntrega +=this.ListaTransportes.get(patente).Facturacion();
		return costoEntrega;
		
		
		
		
	}
	
	@Override
	public Map<Integer, String> pedidosNoEntregados() {
	    Map<Integer, String> pedidosNoEntregados = new HashMap<>();
	    ArrayList<pedido> listaPedidos = listaPedidosCerrados();

	    for (pedido pedido : listaPedidos) {
	        for (paquete paquete : pedido.getListaCarrito().values()) {
	            if (!paquete.getEstado()) { //EXCLAMACION
	                pedidosNoEntregados.put(pedido.getIdPedido(), pedido.getNombre());
	                break; // No es necesario verificar más paquetes en este pedido
	            }
	        }
	    }

	    return pedidosNoEntregados;
	}
	
	
	@Override
	public double facturacionTotalPedidosCerrados() {
		return this.facturacionTotal;
	}
	


	public boolean hayTransportesIdenticos() {
	    List<transporte> listaTransportes = new ArrayList<>(ListaTransportes.values());
	    

	    for (int i = 0; i < listaTransportes.size()-1; i++) {
	        for (int j = i + 1; j < listaTransportes.size(); j++) {
	            transporte transporte1 = listaTransportes.get(i);
	            transporte transporte2 = listaTransportes.get(j);
	            
	    		
	            
	           
	            if(transporte1.getClass() == transporte2.getClass() && transporte1.getPatente() != transporte2.getPatente()) {
	            	// Utiliza hayCargasIdenticas para comparar las cargas de paquetes
		            if (transporte1.hayCargasIdenticas(transporte2)) {
		                return true; // Encontraste dos transportes idénticos
		            }
	            }
	           

	           
	        }
	    }
	    return false; // No se encontraron transportes idénticos
	}
	
	
	public String getCuit() {
		return cuit;
	}

}
