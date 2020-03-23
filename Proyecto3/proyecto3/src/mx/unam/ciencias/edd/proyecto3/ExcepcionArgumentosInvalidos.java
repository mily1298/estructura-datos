package mx.unam.ciencias.edd.proyecto3;
/**
*Clase Excepcion cuando los argumentos leidos son invalidos
*/
public class ExcepcionArgumentosInvalidos extends IndexOutOfBoundsException{
		/**
	*Constructor sin parametros de la excepcion
	*/
	public ExcepcionArgumentosInvalidos() {}
		/**
	*Constructor para mostrar un mensaje cuando se lance la excepcion
	*@param mensaje String mensaje que se desea mandar con la excepcion
	*/
	public ExcepcionArgumentosInvalidos(String mensaje) {
		super(mensaje);
	}
} 