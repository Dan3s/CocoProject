package modelo;

import java.util.ArrayList;

public class Kardex {
	
	public final static boolean PEPS = true;
	
	ArrayList<Concepto> conceptos;
	boolean tipo;

//	private ArrayList<Entrada> entradas;
//	private ArrayList<Salida> salidas;
//	private ArrayList<Saldo> saldo;
	
	public Kardex() {
		
		conceptos = new ArrayList<Concepto>();
		
		entradas = new ArrayList<Entrada>();
		salidas = new ArrayList<Salida>();
		saldo = new ArrayList<Saldo>();
	}
	
	public void nuevoConcepto(String nombre, int cantidad, double valorUnitario) {
		
	}
	
	public void devolucion(String nombre) {
		
	}
	
	public void agregarEntrada(int cantidad, double valorUnitario, double valorTotal) {
		
		entradas.add(new Entrada(cantidad, valorUnitario, valorTotal));
	}
	
	public void agregarSalida(int cantidad, double valorUnitario, double valorTotal) {
		
		salidas.add(new Salida(cantidad, valorUnitario, valorTotal));
	}
	
	//-------------------------------------------------
	// Métodos para versión promedio ponderado
	//-------------------------------------------------
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
	
	//-------------------------------------------------------
	// Metodos para versión PEPS
	//-------------------------------------------------------
	
	public void agregarSaldoInicial(int cantidad, double valorUnitario, double valorTotal) {
		saldo.add(new Saldo(cantidad, valorUnitario, valorTotal));
	}
	
	public void devolucionEnCompra(int cantidad, double valorUnitario, double valorTotal) {
		for (int i = 0; i < saldo.size(); i++) {
			if(valorUnitario == saldo.get(i).getValorUnitario()) {
				
			}
		}
	}
	
	public void agregarEntradaPEPS(int cantidad, double valorUnitario, double valorTotal) {
		entradas.add(new Entrada(cantidad, valorUnitario, valorTotal));
		saldo.add(new Saldo(cantidad, valorUnitario, valorTotal));
	}
	
	public void agregarSalidaPEPS(int cantidad, double valorUnitario, double valorTotal) {
		salidas.add(new Salida(cantidad, valorUnitario, valorTotal));
		saldo.add(new Saldo(cantidad, valorUnitario, valorTotal));
	}
	
	
	
}
