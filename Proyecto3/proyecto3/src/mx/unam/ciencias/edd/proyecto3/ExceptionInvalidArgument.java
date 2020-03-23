package mx.unam.ciencias.edd.proyecto3;

/**
 * Clase para excepciones de índices de lista inválidos.
 */
public class ExceptionInvalidArgument extends IndexOutOfBoundsException {

    /**
     * Constructor vacío.
     */
    public ExceptionInvalidArgument() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExceptionInvalidArgument(String mensaje) {
        super(mensaje);
    }
}