package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la cola.
     * @return una representación en cadena de la cola.
     */
    @Override public String toString() {
        if(esVacia())
            return "[]";
        if(cabeza == rabo){
            return "["+cabeza.elemento+"]";
        }
        String a = "[";
        Nodo b = cabeza;
        while (b.siguiente!=rabo) {
            a=a+b.elemento+", ";
            b = b.siguiente;
        }
        a = a + rabo.elemento +"]";
        return a;
    }

    /**
     * Agrega un elemento al final de la cola.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
         if (elemento==null) {
            throw new IllegalArgumentException();
        }
        if (esVacia()) {
        Nodo a = new Nodo(elemento);
        this.cabeza=this.rabo= a;
            return;
        }
        if(!esVacia()){
        Nodo a = new Nodo(elemento);
        this.rabo.siguiente= a;
        this.rabo = a;
        }

    }
}
