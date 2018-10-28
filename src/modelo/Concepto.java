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
	
	String nombre;
	char tipo;
	
//	Entrada entrada;
//	Salida salida;
	ArrayList<Saldo> saldos;
	
	public Concepto(String nombre, char tipo, ArrayList<Saldo> saldosAnteriores) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
//		this.entrada = new Entrada(0,0,0);
//		this.salida = new Salida(0,0,0);
		saldos = saldosAnteriores;
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
				salidas.add(""+cantidad+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorTotal());
				cantidad = 0;
			}
			else if(saldos.get(i).getCantidad()==cantidad) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorTotal());
				saldos.get(i).setCantidad(0);
				cantidad = 0;
			}
			else if (saldos.get(i).getCantidad()<cantidad){
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorTotal());
				cantidad = cantidad - saldos.get(i).getCantidad();
				saldos.get(i).setCantidad(0);
				i++;
				
			}
			else if(saldos.get(i).equals(saldos.get(saldos.size()-1)) && saldos.get(i).getCantidad()< cantidad) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorTotal());
				saldos.get(i).setCantidad(0);
				cantidad = 0;
				
			}
			
		}
		return salidas;
	}
	
	public void devolucion(int cantidad, double valorUnitario) throws AccionInvalidaException {
//		if(tipo == DEVOLUCION[0] && (cantidad > cantidadTotalDeUnidades() || )) {
//			throw new AccionInvalidaException("No hay suficientes unidades en el inventario para devolver");
//		}
	
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
	 * Metodo privado (solo lo usa esta clase) que retorna el total de unidades
	 * @return cantidadTotal
	 */
	private double cantidadTotalDeUnidades() {
		int cantidadTotal = 0;
	
		for (int i = 0; i < saldos.size(); i++) {
			cantidadTotal+=saldos.get(i).getCantidad();
		}
	
	return cantidadTotal;	
	}
	
	/**
	 * Esye metodo muestra el inventario actual
	 * @return salidas - arrayList con los inventarios 
	 */
	public ArrayList<String> inventarioActual() {
		ArrayList<String> salidas = new ArrayList<String>();
		for (int i = 0; i < saldos.size(); i++) {
			if(saldos.get(i).getCantidad() != 0) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).getValorUnitario()+"-"+saldos.get(i).getValorTotal());
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

//	public Entrada getEntrada() {
//		return entrada;
//	}
//
//	public void setEntrada(Entrada entrada) {
//		this.entrada = entrada;
//	}
//
//	public Salida getSalida() {
//		return salida;
//	}
//
//	public void setSalida(Salida salida) {
//		this.salida = salida;
//	}
//
//	public Saldo getSaldo() {
//		return saldo;
//	}
//
//	public void setSaldo(Saldo saldo) {
//		this.saldo = saldo;
//	}
	
	/**
	 * Main para probar el metodo de agregarSalida(int)
	 * Descomentar para probar (recuerda comentar el resto de mains para que java no explote)
	 * @param args
	 */
//	public static void main(String[] args) {
//		Concepto con = new Concepto("Concepto1", 's', new ArrayList<Saldo>());
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
//		ArrayList<String> bla = con.agregarSalida(8);
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
	public static void main(String[] args) {
		//Cambiar el parámetro tipo para probar la devolucion en compra y venta
		Concepto con = new Concepto("Concepto1", 'v', new ArrayList<Saldo>());
		con.saldos.add(new Saldo(5, 20));
		con.saldos.add(new Saldo(3, 11));
		con.saldos.add(new Saldo(10, 17));
		con.saldos.add(new Saldo(5, 14));
		
		System.out.println("--Inventario Original--");
		for (int i = 0; i < con.inventarioActual().size(); i++) {
			System.out.println(con.inventarioActual().get(i));
		}
		
		
		try {
			con.devolucion(3, 17);
			System.out.println("--Inventario Despues de devolucion de 3 unidades de valor 17--");
			for (int i = 0; i < con.inventarioActual().size(); i++) {
				System.out.println(con.inventarioActual().get(i));
			}
		} catch (AccionInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("--Salidas--");
//		for (int i = 0; i < bla.size(); i++) {
//			System.out.println(bla.get(i));
//		}
	}
	

}
