package modelo;

import java.util.ArrayList;

import excepciones.AccionInvalidaException;

public class Kardex {
	
	public final static boolean PEPS = true;
	
	ArrayList<Concepto> conceptos;
	boolean tipo;

	private ArrayList<Entrada> entradas;
	private ArrayList<Salida> salidas;
	
	public Kardex(boolean tipo) {
		this.tipo = tipo;
		
		/**
		 * Atributo para PEPS
		 */
		conceptos = new ArrayList<Concepto>();
		
		/**
		 * Atributos para PP
		 */
		entradas = new ArrayList<Entrada>();
		salidas = new ArrayList<Salida>();
		
	}
	//-----------------------
	//Metodos para PEPS
	//-----------------------
	
	public void saldoInicial(int cantidad, double valorxUnidad) {
		conceptos.add(new Concepto("Saldo inicial", 'i', new ArrayList<Saldo>()));
		conceptos.get(0).agregarEntrada(cantidad, valorxUnidad);
		
	}
	
	/**
	 * Lista de los ultimos saldos del ultimos concepto
	 * @return retorna los ultimos saldos en ser modificados por el ultimo
	 * concepto del Kardex
	 */
	public ArrayList<Saldo> listaSaldos(){
		return darUltimoConcepto().saldos;
	}
	
	
	public void nuevoConceptoPEPS(String nombre, char tipo) {
		conceptos.add(new Concepto(nombre, tipo, listaSaldos()));
	}
	
	public void entradaPEPS(int cantidad, double valorUnitario) {
		darUltimoConcepto().agregarEntrada(cantidad, valorUnitario);
	}
	
	public ArrayList<String> salidaPEPS(int cantidad) throws AccionInvalidaException{
		return darUltimoConcepto().agregarSalida(cantidad);
	}
	
	public void devolucionPEPS(int cantidad, double valorUnitario) throws AccionInvalidaException {
		darUltimoConcepto().devolucion(cantidad, valorUnitario);
	}
	
	public Concepto darUltimoConcepto() {
		return conceptos.get(conceptos.size()-1);
	}
	

	
	//-------------------------------------------------
	// Métodos para versión promedio ponderado
	//-------------------------------------------------
	
	public void agregarEntrada(int cantidad, double valorUnitario, double valorTotal) {
		
		entradas.add(new Entrada(cantidad, valorUnitario, valorTotal));
	}
	
	public void agregarSalida(int cantidad, double valorUnitario, double valorTotal) {
		
		salidas.add(new Salida(cantidad, valorUnitario, valorTotal));
	}
	
	public int calcularSaldoCantidadPP() {
		
		int cantidad = 0;
		
		for(int i = 0; i < entradas.size(); i++) {
			
			cantidad += entradas.get(i).getCantidad();
		}
		
		for(int j = 0; j < salidas.size(); j++) {
			
			cantidad -= salidas.get(j).getCantidad();
		}
		
		return cantidad;
	}
	
	public double calcularSaldoTotalPP() {
		
		double total = 0;
	
		for(int i = 0; i < entradas.size(); i++) {
			
			total += entradas.get(i).getValorTotal();
		}
		
		for(int j = 0; j < salidas.size(); j++) {
			
			total -= salidas.get(j).getValorTotal();
		}
		
		return total;
	}
	
	public double calcularSaldoUnitarioPP() {
		
		return calcularSaldoTotalPP()/calcularSaldoCantidadPP();
	}
	
	
	
}
