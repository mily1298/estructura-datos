package mx.unam.ciencias.edd.proyecto3;

/**
 * Clase para excepciones de índices de lista inválidos.
 */
public class ExceptionInvalidDirectory extends IndexOutOfBoundsException {

    /**
     * Constructor vacío.
     */
    public ExceptionInvalidDirectory() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExceptionInvalidDirectory(String mensaje) {
        super(mensaje);
    }
}