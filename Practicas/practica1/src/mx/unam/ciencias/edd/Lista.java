package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento=elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if ( !hasNext() )
                throw new NoSuchElementException();
            T aux = this.siguiente.elemento;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return aux;
        }
        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if ( !hasPrevious() )
                throw new NoSuchElementException();
            T aux = anterior.elemento;
            siguiente = anterior;
            anterior = anterior.anterior;
            return aux;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
            siguiente=cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            this.anterior= rabo;
            this.siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacio() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
            if (elemento == null)
                throw new IllegalArgumentException("elemento es un null");
			agregaFinal(elemento);
    }
    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if(elemento==null)
            throw new IllegalArgumentException("Elemento es un null");
        Nodo a = new Nodo(elemento);
        if( cabeza == null )
            this.cabeza=rabo=a;
        else {
            rabo.siguiente= a;
            a.anterior = rabo;
            rabo = a;
        }
        longitud++;
        
    }
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if(elemento==null){
            throw new IllegalArgumentException("Elemento es un null");
        }
        if ( esVacio() ){
            agregaFinal(elemento);
            return ;
        }
        Nodo nuevo = new Nodo (elemento);
		cabeza.anterior = nuevo;
		nuevo.siguiente = cabeza;
		cabeza = nuevo;
		longitud ++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio, y si es mayor o igual que el número
     *          de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
		if(elemento==null){
			throw new IllegalArgumentException();
		}
        if ( i<=0){
            this.agregaInicio(elemento);
            return ;
        }
        if (i>=longitud) {
               this.agregaFinal(elemento);
               return;
           } 
        Nodo a = new Nodo(elemento);
        Nodo b = cabeza;
        for (int j=0;j<i ; j++) {
              b=b.siguiente;
          }  
	      a.siguiente = b;
	      a.anterior = b.anterior;
	      b.anterior.siguiente = a;
	      b.anterior = a;
          longitud++;
    }

    private Nodo busca(T elemento){
        Nodo n = cabeza;
        while (n!=null) {
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if (elemento==null)
            return ;
        Nodo n = busca(elemento);
        if (n==null) {
            return ;
        }
        if (longitud==1) {
            this.limpia();
            return;
        }
        if (n==cabeza){
            this.eliminaPrimero();
            return ;
        }
        if (n==rabo) {
            this.eliminaUltimo();
            return ; 
        }
        n.siguiente.anterior=n.anterior;
        n.anterior.siguiente=n.siguiente;
        longitud--;
        return;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if (esVacio())
			throw new NoSuchElementException("lista vacía");
		T aux = cabeza.elemento;
        if(longitud ==1)
			cabeza = rabo = null;
		else{
			cabeza = cabeza.siguiente;
			cabeza.anterior = null;
        }
        longitud --;
        return aux;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
            if (esVacio()) {
                throw new NoSuchElementException("lista vacia") ;
            }
            if (longitud == 1) 
                return eliminaPrimero();
            else {
                T aux = rabo.elemento;
                rabo = rabo.anterior;
                rabo.siguiente = null;
                longitud--;
                return aux;
			}
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return busca(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> n = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            n.agregaInicio(nodo.elemento);
            nodo= nodo.siguiente;
        }
        return n;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copia de la lista.
     */
    public Lista<T> copia() {
        Lista<T> n = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            n.agregaFinal(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return n;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza=rabo=null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
            if (esVacio())
                throw new NoSuchElementException("La lista es vacia");
            else
                return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if (esVacio())
                throw new NoSuchElementException("La lista es vacia");
        else
            return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i<0 || i>=longitud)
            throw new ExcepcionIndiceInvalido ("Indice invalido");
        if(cabeza == null)
            return null;
        Nodo n = cabeza;
        for (int j=0;j < i ;j++ ) {
            n= n.siguiente;
        }
        return n.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int counter=0;
        if (elemento==null) {
            return -1;
        }
        if (esVacio()) {
            return -1;
        }
        if (cabeza.elemento.equals(elemento)) {
            return 0;
        }
        if(rabo.elemento.equals(elemento)){
            return longitud-1;
        }
        else{
            Nodo a = this.cabeza;
            Nodo aux = new Nodo(elemento);
            while(a!=null){
                if (a.elemento.equals(aux.elemento)) {
                    return counter;
                }
                a= a.siguiente;
                counter++;
            }
            return -1;
        }
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if(esVacio())
            return "[]";
        if(cabeza == rabo){
            return "["+cabeza.elemento+"]";
        }
        String a = "[";
        Nodo b = cabeza;
        for (int i=0 ;i<longitud-1;i++) {
            a=a+b.elemento+", ";
            b = b.siguiente;
        }
        a = a + rabo.elemento +"]";
        return a;
    }


    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Lista))
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
        if(lista.longitud==this.longitud){
            Nodo a = lista.cabeza;
            Nodo b = this.cabeza;
            while(a!=null && b!=null){
                if (a.elemento.equals(b.elemento)) {
                    a = a.siguiente;
                    b = b.siguiente;
                }
                else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
