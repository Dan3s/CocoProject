package modelo;

import java.util.ArrayList;
import java.util.Scanner;

import excepciones.AccionInvalidaException;

public class Kardex {
	
	public final static boolean PEPS = true;
	
	ArrayList<Concepto> conceptos;
	boolean tipo;

	
	/**
	 * Escaner para probar los menus
	 */
	private static Scanner sc;
	
	public Kardex(boolean tipo) {
		this.tipo = tipo;
		
		conceptos = new ArrayList<Concepto>();
		
		
		
	}

	
	public void saldoInicial(int cantidad, double valorxUnidad) {
		conceptos.add(new Concepto("Saldo inicial", 'i', new ArrayList<Saldo>()));
		conceptos.get(0).agregarEntrada(cantidad, valorxUnidad);
		
	}
	
	public void saldoInicialPP(int cantidad, double valorxUnidad) {
		conceptos.add(new Concepto("Saldo inicial", 'i', new Saldo(cantidad, valorxUnidad)));
		darUltimoConcepto().setSaldoPP(new Saldo(cantidad, valorxUnidad));
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
	
	public void nuevoConceptoPP(String nombre, char tipo) {
		conceptos.add(new Concepto(nombre, tipo, darUltimoConcepto().getSaldoPP()));
	}
	
	public void entradaPEPS(int cantidad, double valorUnitario) {
		darUltimoConcepto().agregarEntrada(cantidad, valorUnitario);
	}
	
	public void entradaPP(int cantidad, double valorUnitario) {
		darUltimoConcepto().agregarEntradaPP(cantidad, valorUnitario);
	}
	
	public String salidaPP(int cantidad) throws AccionInvalidaException {
		
		return darUltimoConcepto().agregarSalidaPP(cantidad);
	}
	
	public ArrayList<String> salidaPEPS(int cantidad) throws AccionInvalidaException{
		return darUltimoConcepto().agregarSalida(cantidad);
	}
	
	public void devolucionPP(int cantidad, double valorUnitario) throws AccionInvalidaException {
		darUltimoConcepto().devolucionPP(cantidad, valorUnitario);
	}
	
	public void devolucionPEPS(int cantidad, double valorUnitario) throws AccionInvalidaException {
		darUltimoConcepto().devolucion(cantidad, valorUnitario);
	}
	
	public Concepto darUltimoConcepto() {
		return conceptos.get(conceptos.size()-1);
	}
	

	

	/**
	 * menu para promedio ponderado
	 * @throws AccionInvalidaException
	 */
	public void menuPP() throws AccionInvalidaException {
		System.out.println("Ingrese nombre concepto");
		String nom = sc.nextLine();
		
		System.out.println("Ingrese tipo, (Entrada), (Salida), (Devolucion en Compra) y (Devolucion en Venta)");
		String tip = sc.nextLine();
		char tipoo;
		if(tip.equalsIgnoreCase("entrada")) {
			tipoo= Concepto.ENTRADA;
		}
		else if(tip.equalsIgnoreCase("salida")) {
			tipoo = Concepto.SALIDA;
		}
		else if(tip.equalsIgnoreCase("Devolucion en compra")) {
			tipoo = Concepto.DEVOLUCION_COMPRA;
		}
		else {
			tipoo = Concepto.DEVOLUCION_VENTA;
		}
		
		nuevoConceptoPP(nom, tipoo);
		
		switch(tipoo) {
		case Concepto.ENTRADA:
			System.out.println("Ingrese cantidad");
			int cant = sc.nextInt();
			System.out.println("Ingrese valor unitario");
			double uni= sc.nextDouble(); 
			entradaPP(cant, uni);
			
			break;
		
		
		case Concepto.SALIDA:
			System.out.println("Ingrese cantidad");
			int canti = sc.nextInt();
			//Imprime las salidas
			System.out.println("--Salidas--");
			System.out.println(salidaPP(canti));
			break;
			
		default:
			System.out.println("Ingrese cantidad");
			int cani = sc.nextInt();
			System.out.println("Ingrese valor Unitario");
			double valorU = sc.nextInt();
			devolucionPP(cani, valorU);
			break;
		}
		
		System.out.println("--Inventario despues de la operacion--");
		System.out.println(darUltimoConcepto().saldoPP.toString());
		sc.nextLine();
	}
	
	/**
	 * Menu para PEPS
	 * @throws AccionInvalidaException
	 */
	public void menuPEPS() throws AccionInvalidaException {
		//Scansc = new Scanner(System.in);
		System.out.println("Ingrese nombre concepto");
		String nom = sc.nextLine();
		
		System.out.println("Ingrese tipo, (Entrada), (Salida), (Devolucion en Compra) y (Devolucion en Venta)");
		String tip = sc.nextLine();
		char tipoo;
		if(tip.equalsIgnoreCase("entrada")) {
			tipoo= Concepto.ENTRADA;
		}
		else if(tip.equalsIgnoreCase("salida")) {
			tipoo = Concepto.SALIDA;
		}
		else if(tip.equalsIgnoreCase("Devolucion en compra")) {
			tipoo = Concepto.DEVOLUCION_COMPRA;
		}
		else {
			tipoo = Concepto.DEVOLUCION_VENTA;
		}
		
		nuevoConceptoPEPS(nom, tipoo);
		
		switch(tipoo) {
		case Concepto.ENTRADA:
			System.out.println("Ingrese cantidad");
			int cant = sc.nextInt();
			System.out.println("Ingrese valor unitario");
			double uni= sc.nextDouble(); 
			entradaPEPS(cant, uni);
			
			break;
		
		
		case Concepto.SALIDA:
			System.out.println("Ingrese cantidad");
			int canti = sc.nextInt();
			//Imprime las salidas
			System.out.println("--Salidas--");
			ArrayList<String> sali = salidaPEPS(canti);
			for (int i = 0; i < sali.size(); i++) {
				System.out.println(sali.get(i));
			}
			break;
			
		default:
			System.out.println("Ingrese cantidad");
			int cani = sc.nextInt();
			System.out.println("Ingrese valor Unitario");
			double valorU = sc.nextInt();
			devolucionPEPS(cani, valorU);
			break;
			
			
		}
		
		
		
		System.out.println("--Inventario despues de la operacion--");
		ArrayList<String> inventarioActu = darUltimoConcepto().inventarioActual();
		for (int i = 0; i < inventarioActu.size(); i++) {
			System.out.println(inventarioActu.get(i));
		}
		sc.nextLine();
		
		
	}
	
	
	
	
	public static void main(String[] args) throws AccionInvalidaException {
		
		sc = new Scanner(System.in);
		System.out.println("Tipo kardex, PEPS ó PP");
		String tipo = sc.nextLine();
		//sc.nextLine();
		if(tipo.equalsIgnoreCase("PEPS")) {
			Kardex kar = new Kardex(PEPS);
			System.out.println("Ingrese el saldo inicial a continuación");
			System.out.println("Cantidad: ");
			int cantidad = sc.nextInt();
		//	sc.nextLine();
			System.out.println("Valor unitario: ");
			double valorUni =sc.nextDouble();
			sc.nextLine();
			kar.saldoInicial(cantidad, valorUni);
			
		
		
			boolean salir = false;
			while(!salir) {
				
				System.out.println("¿Desea agregar un nuevo concepto? SI/NO");
				String res= sc.nextLine();
				if (res.equalsIgnoreCase("si")) {
					kar.menuPEPS();
				}
				else {
					salir = true;
				}
			}
			
		}
		else {
			Kardex kar = new Kardex(!PEPS);
			System.out.println("Ingrese el saldo inicial a continuación");
			System.out.println("Cantidad: ");
			int cantidad = sc.nextInt();
		//	sc.nextLine();
			System.out.println("Valor unitario: ");
			double valorUni =sc.nextDouble();
			sc.nextLine();
			kar.saldoInicialPP(cantidad, valorUni);
			
			
		
			
			boolean salir = false;
			while(!salir) {
				System.out.println("¿Desea agregar un concepto? SI/NO");
				String res= sc.nextLine();
				
				if (res.equalsIgnoreCase("si")) {
					kar.menuPP();
				}
				else {
					salir = true;
				}
			}
		}
		
	}
}
