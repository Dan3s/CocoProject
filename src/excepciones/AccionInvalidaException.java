package excepciones;

public class AccionInvalidaException extends Exception {
	
	public AccionInvalidaException() {
		super("No es posible realizar esta accion");
	}

}
