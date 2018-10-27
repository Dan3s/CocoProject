package modelo;

import excepciones.AccionInvalidaException;

public class Saldo {
	
	private int cantidad;
	private double valorUnitario;
	private double valorTotal;
	
	public Saldo(int cantidad, double valorUnitario, double valorTotal) {
		
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}
	
	public void disminuirSaldo(int cantidad)  {
		if(cantidad > this.cantidad) {
//			throw new AccionInvalidaException();
		}else {
			this.cantidad-=cantidad;
		}
	}

	public double calcularValorTotal() {
		
		return cantidad*valorUnitario;
	}
	
	public double calcularValorUnitario() {
		
		return valorTotal/cantidad; 
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
