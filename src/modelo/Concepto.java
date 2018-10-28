package modelo;

import java.util.ArrayList;

import excepciones.AccionInvalidaException;

public class Concepto {
	
	public final static char ENTRADA = 'e';
	public final static char SALIDA = 's';
	
	/**
	 * c - devolucion en compra
	 * v - devolucion en venta
	 */
	public final static char DEVOLUCION_COMPRA = 'c';
	public final static char DEVOLUCION_VENTA = 'v';
	
	String nombre;
	char tipo;
	
//	Entrada entrada;
//	Salida salida;
	ArrayList<Saldo> saldos;
	Saldo saldoPP;
	
	/**
	 * Constructor para PEPS
	 * @param nombre
	 * @param tipo
	 * @param saldosAnteriores
	 */
	public Concepto(String nombre, char tipo, ArrayList<Saldo> saldosAnteriores) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		saldos = saldosAnteriores;

	}
	
	/**
	 * Constructor para PP
	 * @param nombre
	 * @param tipo
	 * @param saldo
	 */
	public Concepto(String nombre, char tipo, Saldo saldoAnterior) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		saldoPP = saldoAnterior;
	}
	
	
	
	public Saldo getSaldoPP() {
		return saldoPP;
	}

	public void setSaldoPP(Saldo saldoPP) {
		this.saldoPP = saldoPP;
	}

	public void agregarEntradaPP(int cantidad, double valorUnitario) {
		int cantidadTotal = cantidad+ saldoPP.getCantidad();
		double nuevoValorUni= ((cantidad*valorUnitario)+saldoPP.getValorTotal())/cantidadTotal;
		saldoPP =new Saldo(cantidadTotal, nuevoValorUni);
	}
	
	public String agregarSalidaPP(int cantidad) throws AccionInvalidaException {
	
		saldoPP.disminuirSaldo(cantidad);
		return cantidad+"-"+saldoPP.getValorUnitario()+"-"+cantidad*saldoPP.getValorUnitario();
	}
	
	public void devolucionPP(int cantidad, double valorUnitario) throws AccionInvalidaException {
		if(tipo == DEVOLUCION_COMPRA) {
			saldoPP.disminuirSaldo(cantidad);
		}
		else {
			agregarEntradaPP(cantidad, valorUnitario);
		}
	}
	
	public void agregarEntrada(int cantidad, double valorUnitario) {
		boolean parar = false;
		
		for (int i = 0; i < saldos.size() && !parar; i++) {
			if(saldos.get(i).getValorUnitario() == valorUnitario) {
				saldos.get(i).aumentarSaldo(cantidad);
				parar = true;	
			
			}
		}
		if(!parar) {
			saldos.add(new Saldo(cantidad, valorUnitario));
		}
		
	}
	
	/**
	 * Este método disminuye el inventario cuando hay una venta
	 * @param cantidad
	 * @return salidas - Arrayist de String en el cada posicion está la cantidad que salió, 
	 * su valor unitario y el valor total.
	 * @throws AccionInvalidaException 
	 */
	public ArrayList<String> agregarSalida(int cantidad) throws AccionInvalidaException {
		
		int i = 0;
		ArrayList<String> salidas = new ArrayList<String>();
		while(cantidad>0) {
			if(saldos.get(i).getCantidad()>cantidad) {
				saldos.get(i).disminuirSaldo(cantidad);
				salidas.add(""+cantidad+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorUnitario()*cantidad);
				cantidad = 0;
			}
			else if(saldos.get(i).getCantidad()==cantidad) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorUnitario()*cantidad);
				saldos.get(i).setCantidad(0);
				cantidad = 0;
			}
			else if (saldos.get(i).getCantidad()<cantidad){
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorUnitario()*saldos.get(i).getCantidad());
				cantidad = cantidad - saldos.get(i).getCantidad();
				saldos.get(i).setCantidad(0);
				i++;
				
			}
			else if(saldos.get(i).equals(saldos.get(saldos.size()-1)) && saldos.get(i).getCantidad()< cantidad) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorUnitario()*saldos.get(i).getCantidad());
				saldos.get(i).setCantidad(0);
				cantidad = 0;
				
			}
			
		}
		return salidas;
	}
	
	
	
	public void devolucion(int cantidad, double valorUnitario) throws AccionInvalidaException {

	
		boolean encontrado = false;
		for (int i = 0; i < saldos.size()&& !encontrado; i++) {
			
			
			if(tipo == DEVOLUCION_COMPRA) {
				if(saldos.get(i).getValorUnitario() == valorUnitario) {
					saldos.get(i).disminuirSaldo(cantidad);
					encontrado = false;
				}			
			}
			else {
				if(saldos.get(i).getValorUnitario() == valorUnitario) {
					saldos.get(i).aumentarSaldo(cantidad);
					encontrado = false;
				}
			}
		
		}
		
	}
	
	
	/**
	 * Este metodo muestra el inventario actual
	 * @return salidas - arrayList con los inventarios 
	 */
	public ArrayList<String> inventarioActual() {
		ArrayList<String> salidas = new ArrayList<String>();
		for (int i = 0; i < saldos.size(); i++) {
			if(saldos.get(i).getCantidad() != 0) {
				salidas.add(saldos.get(i).toString());
			}
		}
		return salidas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	/**
	 * Main para probar el metodo de agregarSalida(int)
	 * Descomentar para probar (recuerda comentar el resto de mains para que java no explote)
	 * @param args
	 * @throws AccionInvalidaException 
	 */
//	public static void main(String[] args) throws AccionInvalidaException {
//		Concepto con = new Concepto("Concepto1", 's', new ArrayList<Saldo>());
//		con.saldos.add(new Saldo(5, 20));
////		con.saldos.add(new Saldo(3, 11));
////		con.saldos.add(new Saldo(10, 17));
////		con.saldos.add(new Saldo(5, 14));
//		
//		System.out.println("--Inventario Original--");
//		for (int i = 0; i < con.inventarioActual().size(); i++) {
//			System.out.println(con.inventarioActual().get(i));
//		}
//		
//		
//		ArrayList<String> bla = con.agregarSalida(5);
//		System.out.println("--Inventario Despues de Salida de 8 unidades--");
//		for (int i = 0; i < con.inventarioActual().size(); i++) {
//			System.out.println(con.inventarioActual().get(i));
//		}
//		
//		System.out.println("--Salidas--");
//		for (int i = 0; i < bla.size(); i++) {
//			System.out.println(bla.get(i));
//		}
//	}
	
	/**
	 * Main para probar el metodo devolucion(int)
	 * @param args
	 */
//	public static void main(String[] args) {
//		//Cambiar el parámetro tipo para probar la devolucion en compra y venta
//		Concepto con = new Concepto("Concepto1", 'v', new ArrayList<Saldo>());
//		con.saldos.add(new Saldo(5, 20));
//		con.saldos.add(new Saldo(3, 11));
//		con.saldos.add(new Saldo(10, 17));
//		con.saldos.add(new Saldo(5, 14));
//		
//		System.out.println("--Inventario Original--");
//		for (int i = 0; i < con.inventarioActual().size(); i++) {
//			System.out.println(con.inventarioActual().get(i));
//		}
//		
//		
//		try {
//			con.devolucion(3, 17);
//			System.out.println("--Inventario Despues de devolucion de 3 unidades de valor 17--");
//			for (int i = 0; i < con.inventarioActual().size(); i++) {
//				System.out.println(con.inventarioActual().get(i));
//			}
//		} catch (AccionInvalidaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//	}
	

}
