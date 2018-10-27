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
	public final static char[] DEVOLUCION = {'c', 'v'};
	
	String nombre;
	char tipo;
//	Entrada entrada;
//	Salida salida;
	ArrayList<Saldo> saldos;
	
	public Concepto(String nombre, ArrayList<Saldo> saldosAnteriores) {
		super();
		this.nombre = nombre;
//		this.entrada = new Entrada(0,0,0);
//		this.salida = new Salida(0,0,0);
		saldos = saldosAnteriores;
	}
	
	public void agregarEntrada(int cantidad, double valorUnitario) {
		
		//entrada = new Entrada(cantidad, valorUnitario, valorTotal);
		saldos.add(new Saldo(cantidad, valorUnitario, cantidad*valorUnitario));
	}
	
	/**
	 * Este método disminuye en inventario cuando hay una venta
	 * @param cantidad
	 * @return salidas - Arrayist de String en el cada posicion a cantidad que salió, 
	 * su valor unitario y el valor total.
	 */
	public ArrayList<String> agregarSalida(int cantidad) {
		int i = 0;
		ArrayList<String> salidas = new ArrayList<String>();
		while(cantidad>0) {
			if(saldos.get(i).getCantidad()>cantidad) {
				saldos.get(i).disminuirSaldo(cantidad);
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).calcularValorUnitario()+"-"+saldos.get(i).calcularValorTotal());
				cantidad = 0;
			}
			else if(saldos.get(i).getCantidad()==cantidad) {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).calcularValorUnitario()+"-"+saldos.get(i).calcularValorTotal());
				saldos.remove(saldos.get(i));
				cantidad = 0;
			}
			else {
				salidas.add(""+saldos.get(i).getCantidad()+"-"+saldos.get(i).calcularValorUnitario()+"-"+saldos.get(i).calcularValorTotal());
				cantidad -= cantidad - saldos.get(i).getCantidad();
				saldos.remove(saldos.get(i));
				i++;
			}
			
		}
		return salidas;
	}
	
	public void devolucion(int cantidad) throws AccionInvalidaException {
		boolean encontrado = false;
		for (int i = 0; i < saldos.size()&& !encontrado; i++) {
			
		
		
			if(tipo == DEVOLUCION[0]) {
				if(saldos.get(i).getValorUnitario() == entrada.getValorUnitario()) {
					saldos.get(i).disminuirSaldo(cantidad);
				}			
			}
			else {
				if(saldos.get(i).getValorUnitario() == salida.getValorUnitario()) {
					saldos.get(i).disminuirSaldo(cantidad);
				}
			}
		
		}
	}
	
//	public void devolucion(int cantidad, boolean tipo) {
//		if(tipo) {
//			entrada
//		}
//	}
	
	
	
	
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Salida getSalida() {
		return salida;
	}

	public void setSalida(Salida salida) {
		this.salida = salida;
	}

	public Saldo getSaldo() {
		return saldo;
	}

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}
	
	
	
	

}
