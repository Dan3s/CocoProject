package modelo;

import excepciones.AccionInvalidaException;

public class Saldo {
	
	private int cantidad;
	private double valorUnitario;
	private double valorTotal;
	
	public Saldo(int cantidad, double valorUnitario) {
		
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.valorTotal = cantidad*valorUnitario;
	}
	
	@Override
	public String toString() {
		return ""+cantidad+"-"+valorUnitario+"-"+valorTotal;
	}
	
	public void disminuirSaldo(int cantidad) throws AccionInvalidaException {
		if(cantidad > this.cantidad) {
			throw new AccionInvalidaException("No hay suficientes unidades en el inventario para deolver");
		}
		this.cantidad-=cantidad;
		calcularValorTotal();
	}
	
	public void aumentarSaldo(int cantidad) {
		this.cantidad+=cantidad;
		calcularValorTotal();
	}

	public void calcularValorTotal() {
		
		valorTotal= cantidad*valorUnitario;
	}
	
	public double calcularValorUnitario() {
		
		return valorTotal/cantidad; 
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		calcularValorTotal();
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
