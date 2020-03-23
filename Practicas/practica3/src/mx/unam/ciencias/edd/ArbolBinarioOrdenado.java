package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Construye un iterador con el vértice recibido. */
        public Iterador() {
            pila = new Pila<Vertice>();
            if (raiz==null)
                return;
            Vertice aux = raiz;
            pila.mete(raiz);
            while(aux.hayIzquierdo()){
                aux= aux.izquierdo;
                pila.mete(aux);
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
            Vertice aux = pila.saca();
            T aux2 = aux.get();
            if(aux.hayDerecho()){
                pila.mete(aux.derecho);
                aux = aux.derecho;
                while(aux.hayIzquierdo()){
                    aux = aux.izquierdo;
                    pila.mete(aux);
                }
            }
            return aux2;
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        if(elemento==null)
         throw new IllegalArgumentException();

     if(raiz == null){
         raiz = new Vertice(elemento);
         ultimoAgregado = raiz;
         elementos++;
         return;
     }
     agrega(elemento, raiz);
     elementos++;
 }

 private void agrega(T elemento, Vertice v){
    if(v.elemento.compareTo(elemento) <= 0){

     if(v.derecho == null){
      Vertice vertice = new Vertice(elemento);
      v.derecho = vertice;
      vertice.padre = v;
      ultimoAgregado = vertice;
  }else
  agrega(elemento, v.derecho);
}else{
 if(v.izquierdo == null){
  Vertice vertice = new Vertice(elemento);
  v.izquierdo = vertice;
  vertice.padre = v;
  ultimoAgregado = vertice;
}else
agrega(elemento, v.izquierdo);
}
}

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if (elemento==null)
            return;
        Vertice v = (Vertice)busca(elemento);
        if(v==null)
            return;
        elementos--;
        if(elementos==0){
            raiz=null;
            return;
        }
        if(v.hayIzquierdo() && v.hayDerecho())
            v=intercambiaEliminable(v);
        eliminaVertice(v);
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        Vertice v = maximoSubArbol(vertice.izquierdo);
        T aux = v.elemento;
        v.elemento=vertice.elemento;
        vertice.elemento = aux;
        return v;
    }

    protected Vertice maximoSubArbol(Vertice v){
        if(!v.hayDerecho())
            return v;
        return maximoSubArbol(v.derecho);
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
     if(vertice.hayPadre()){
        if(vertice.padre.hayIzquierdo()){ 
            if(vertice.padre.izquierdo.equals(vertice)){
                if(vertice.hayIzquierdo()){
                    vertice.padre.izquierdo=vertice.izquierdo;
                    vertice.izquierdo.padre=vertice.padre;
                    return;
                }
                if(vertice.hayDerecho()){
                    vertice.padre.izquierdo=vertice.derecho;
                    vertice.derecho.padre=vertice.padre;
                    return;
                }
                vertice.padre.izquierdo=null;
            }
            else{
                if (vertice.hayIzquierdo()) {
                    vertice.padre.derecho=vertice.izquierdo;
                    vertice.izquierdo.padre=vertice.padre;

                    return;
                }
                if (vertice.hayDerecho()) {
                    vertice.padre.derecho=vertice.derecho;
                    vertice.derecho.padre=vertice.padre;

                    return;
                }
                vertice.padre.derecho=null;
            }
        }
        else{
            if (vertice.hayIzquierdo()) {
                vertice.padre.derecho=vertice.izquierdo;
                vertice.izquierdo.padre=vertice.padre;

                return;
            }
            if (vertice.hayDerecho()) {
                vertice.padre.derecho=vertice.derecho;
                vertice.derecho.padre=vertice.padre;

                return;
            }
            vertice.padre.derecho=null;
        }
    }
    else{
        if (vertice.hayIzquierdo()) {
            raiz=vertice.izquierdo;
            vertice.izquierdo.padre=null;
            return;
        }
        if (vertice.hayDerecho()) {
            raiz=vertice.derecho;
            vertice.derecho.padre=null;
            return;
        }
    }
}

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <tt>null</tt>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <tt>null</tt> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
     if(raiz == null || elemento == null)
         return null;
     return busca(elemento,raiz);
 }

 private VerticeArbolBinario<T> busca(T elemento, Vertice v){
     if(v.elemento.equals(elemento))
         return (VerticeArbolBinario<T>) v;
     if(v.elemento.compareTo(elemento) < 0){
         if(v.derecho != null)
          return busca(elemento, v.derecho);
      return null;
  }else{
     if(v.izquierdo != null)
      return busca(elemento, v.izquierdo);
  return null;
}
}

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

       /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
       public void giraDerecha(VerticeArbolBinario<T> vertice) {
        if (!vertice.hayIzquierdo() || vertice==null) 
            return;
        Vertice v = (Vertice)vertice;
        if (v.hayPadre()) {
            v.izquierdo.padre = v.padre;
            if(esIzquierdo(v)){
                v.padre.izquierdo = v.izquierdo;
            }
            else{
                v.padre.derecho=v.izquierdo;
            }
        }
        else{
            v.izquierdo.padre=null;
            raiz=v.izquierdo;
        }
        v.padre= v.izquierdo;
        if (!v.padre.hayDerecho()) {
            v.padre.derecho=v;
            v.izquierdo= null;
        }
        else{
            v.izquierdo=v.padre.derecho;
            v.padre.derecho.padre=v;
            v.padre.derecho = v;
        }

    }
    private boolean esDerecho(Vertice vertice){
        return vertice.padre.derecho.equals(vertice);
    }
    private boolean esIzquierdo(Vertice vertice){
        return vertice.padre.izquierdo.equals(vertice);
    }
     /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
     public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        if (!vertice.hayDerecho() || vertice==null)
            return;
        Vertice v = (Vertice)vertice;
        if (v.hayPadre()) {
            v.derecho.padre= v.padre;
            if (esIzquierdo(v)) {
               v.padre.izquierdo = v.derecho;
           } 
           else{
            v.padre.derecho = v.derecho;
        }
    }
    else{
        v.derecho.padre=null;
        raiz=v.derecho;
    }
    v.padre = v.derecho;
    if(!v.padre.hayIzquierdo()){
        v.padre.izquierdo=v;
        v.derecho=null;
    }
    else{
        v.derecho=v.padre.izquierdo;
        v.padre.izquierdo.padre=v;
        v.padre.izquierdo=v;
    }

}

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
     dfsPreOrder(accion, raiz);
 }

 private void dfsPreOrder(AccionVerticeArbolBinario<T> a,Vertice v){
     if(v == null)
         return;
     a.actua(v);
     dfsPreOrder(a,v.izquierdo);
     dfsPreOrder(a,v.derecho);
 }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        dfsInOrder(accion,raiz);
    }

    private void dfsInOrder(AccionVerticeArbolBinario<T> accion, Vertice v){
     if(v==null)
         return;
     dfsInOrder(accion,v.izquierdo);
     accion.actua(v);
     dfsInOrder(accion,v.derecho);
 }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
     dfsPostOrder(accion,raiz);
 }

 private void dfsPostOrder(AccionVerticeArbolBinario<T> accion, Vertice v){
     if(v==null)
         return;
     dfsPostOrder(accion,v.izquierdo);
     dfsPostOrder(accion,v.derecho);
     accion.actua(v);
 }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
