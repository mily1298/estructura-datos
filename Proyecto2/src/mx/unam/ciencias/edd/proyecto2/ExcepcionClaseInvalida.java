package mx.unam.ciencias.edd.proyecto2;
/**
* Clase excepcion cuando la clase recibida no es valida
*/
public class ExcepcionClaseInvalida extends IndexOutOfBoundsException{
	/**
	*Constructor de la excepcion
	*/
	public ExcepcionClaseInvalida() {}
	/**
	*Contructor de la excepcion para enviar mensaje
	*@param mensaje String cadena que se desea mandar con la excepcion
	*/
	public ExcepcionClaseInvalida(String mensaje) {
		super(mensaje);
	}
} 