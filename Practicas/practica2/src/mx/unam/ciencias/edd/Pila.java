package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la pila.
     * @return una representación en cadena de la pila.
     */
    @Override public String toString() {
        if(esVacia())
            return "[]";
        if(cabeza == rabo){
            return "["+cabeza.elemento+"]";
        }
        String a = "[";
        Nodo b = cabeza;
        while (b.siguiente!=rabo)  {
            a=a+b.elemento+", ";
            b = b.siguiente;
        }
        a = a + rabo.elemento +"]";
        return a;
    }

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        if (elemento==null) {
            throw new IllegalArgumentException();
        }
        Nodo a = new Nodo(elemento);
        a.siguiente = this.cabeza;
        this.cabeza = a;
    }
}
