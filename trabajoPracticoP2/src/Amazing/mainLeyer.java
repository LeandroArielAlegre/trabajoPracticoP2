package Amazing;

public class mainLeyer {

	public static void main(String[] args) {
		EmpresaAmazing empresa = new EmpresaAmazing("30-456789-2");
		System.out.println(" test unit de rangler");
		
			// REGISTRAR VEHICULOS
		   empresa.registrarUtilitario("AC36ZBA", 21000, 6000, 9000);
		   empresa.registrarUtilitario("BBITO24", 55000, 300000, 35);
		   
		   //pedido 1
		// paquete Ordinario
		   int p10 = empresa.registrarPedido("Raul Meza",  "TIJUANA 121", 28322132);
		   int paq100= empresa.agregarPaquete(p10, 1000, 5500, 1100);
		  
		   
		   empresa.cerrarPedido(p10);
		   System.out.println(empresa.cargarTransporte("AC36ZBA"));
		   
		   
		   //pedido 2
		   
		   int p20 = empresa.registrarPedido("Guillermo DelToro",  "Farias 500", 19456398);
		   // paquete especial
		   int paq20= empresa.agregarPaquete(p20, 1000, 5500, 1100);
		   empresa.cerrarPedido(p20);
		   
		   System.out.println(empresa.cargarTransporte("BBITO24"));
		   System.out.println(empresa.toString());
		   
		   
		   System.out.println(empresa.pedidosNoEntregados());
		   //System.out.println(empresa.cargarTransporte("BBITO24"));
		   
		   
		   System.out.println(empresa.hayTransportesIdenticos());
	

	}

}
