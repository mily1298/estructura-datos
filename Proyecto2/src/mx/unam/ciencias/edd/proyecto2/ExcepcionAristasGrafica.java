package mx.unam.ciencias.edd.proyecto2;
/**
*Clase Excepcion cuando las aristas pasadas como argumento presentan una inconsistencia
*/
public class ExcepcionAristasGrafica extends IndexOutOfBoundsException{
	/**
	*Constructor sin parametros de la excepcion
	*/
	public ExcepcionAristasGrafica() {}
	/**
	*Constructor para mostrar un mensaje cuando se lance la excepcion
	*@param mensaje String mensaje que se desea mandar con la excepcion
	*/
	public ExcepcionAristasGrafica(String mensaje) {
		super(mensaje);
	}
} 