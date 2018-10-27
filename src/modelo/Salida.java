package modelo;

public class Salida {

	private int cantidad;
	private double valorUnitario;
	private double valorTotal;
	
	public Salida(int cantidad, double valorUnitario, double valorTotal) {
		
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public double calcularValorTotal(int cantidad, double valorUnitario) {
		
		return cantidad*valorUnitario;
	}
	
	public double calcularValorUnitario(double valorTotal, int cantidad) {
		
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
