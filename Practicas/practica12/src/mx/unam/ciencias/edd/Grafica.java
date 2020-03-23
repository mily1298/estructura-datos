package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase privada para iteradores de gráficas. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
            iterador = vertices.iterator();
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            return iterador.next().get();
        }
    }

    /* Vecinos para gráficas; un vecino es un vértice y el peso de la arista que
     * los une. Implementan VerticeGrafica. */
    private class Vecino implements VerticeGrafica<T> {

        /* El vecino del vértice. */
        public Vertice vecino;
        /* El peso de vecino conectando al vértice con el vecino. */
        public double peso;

        /* Construye un nuevo vecino con el vértice recibido como vecino y el
         * peso especificado. */
        public Vecino(Vertice vecino, double peso) {
            this.vecino = vecino;
            this.peso = peso;
        }

        /* Regresa el elemento del vecino. */
        @Override public T get() {
            return vecino.elemento;
        }

        /* Regresa el grado del vecino. */
        @Override public int getGrado() {
            return vecino.vecinos.getElementos();
        }

        /* Regresa el color del vecino. */
        @Override public Color getColor() {
            return vecino.getColor();
        }

        /* Regresa un iterable para los vecinos del vecino. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecino.vecinos;
        }
    }

    /* Vertices para gráficas; implementan la interfaz ComparableIndexable y
     * VerticeGrafica */
    private class Vertice implements VerticeGrafica<T>,
                          ComparableIndexable<Vertice> {

        /* El elemento del vértice. */
        public T elemento;
        /* El color del vértice. */
        public Color color;
        /* La distancia del vértice. */
        public double distancia;
        /* El índice del vértice. */
        public int indice;
        /* El diccionario de vecinos del vértice. */
        public Diccionario<T, Vecino> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento=elemento;
            color=Color.NINGUNO;
            distancia=Double.MAX_VALUE;
            vecinos=new Diccionario<T,Vecino>();
            indice=0;
        }

        /* Regresa el elemento del vértice. */
        @Override public T get() {
            return this.elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return this.vecinos.getElementos();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return this.color;
        }

        /* Regresa un iterable para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return this.vecinos;
        }

        /* Define el índice del vértice. */
        @Override public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Regresa el índice del vértice. */
        @Override public int getIndice() {
            return this.indice;
        }

        /* Compara dos vértices por distancia. */
        @Override public int compareTo(Vertice vertice) {
            return Double.compare(this.distancia, vertice.distancia);
        }
    }

    /* Interface para poder usar lambdas al buscar el elemento que sigue al
     * reconstruir un camino. */
    @FunctionalInterface
    private interface BuscadorCamino {
        /* Regresa true si el vértice se sigue del vecino. */
        public boolean seSiguen(Grafica.Vertice v, Grafica.Vecino a);
    }

    /* Vértices. */
    private Diccionario<T, Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        vertices = new Diccionario<T, Vertice>();
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    @Override public int getElementos() {
        return this.vertices.getElementos();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        return this.aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
        if(elemento==null||vertices.contiene(elemento))
            throw new IllegalArgumentException();
        Vertice aux = new Vertice(elemento);
        vertices.agrega(elemento, aux);
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b){
        Vertice aux1 = busca(vertices,a);
        Vertice aux2 = busca(vertices,b);
        if (aux2 ==null|| aux1==null){
         throw new NoSuchElementException();
     }
     if(a.equals(b)|| sonVecinos(a,b)){
         throw new IllegalArgumentException();
     }
     aux1.vecinos.agrega(b,new Vecino(aux2,1));
     aux2.vecinos.agrega(a,new Vecino (aux1,1));
     aristas++;
    }
    private Vertice busca(Diccionario<T,Vertice> vertices, T elemento){
      if(elemento!=null && !vertices.esVacio()){
         for(Vertice v : vertices){
            if(elemento.equals(v.elemento)){
               return v;
           }
       }
   }
   return null;
    }
    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @param peso el peso de la nueva vecino.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, si a es
     *         igual a b, o si el peso es no positivo.
     */
    public void conecta(T a, T b, double peso) {
        Vertice aux1 = busca(vertices,a);
        Vertice aux2 = busca(vertices,b);
        if (aux2 ==null|| aux1==null)
         throw new NoSuchElementException();
     if(a.equals(b)|| sonVecinos(a,b)|| peso<0)
         throw new IllegalArgumentException();

     aux1.vecinos.agrega(b,new Vecino(aux2,peso));
     aux2.vecinos.agrega(a,new Vecino (aux1,peso));
     aristas++;
    }

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
        Vertice aux1 = busca(vertices,a);
        Vertice aux2 = busca(vertices,b);
        if (aux2 ==null|| aux1==null){
         throw new NoSuchElementException();
     }
     if(a.equals(b)||!sonVecinos(a,b)){
         throw new IllegalArgumentException();
     }
     Vecino veA=buscaV(aux1.vecinos, b);
     Vecino veB=buscaV(aux2.vecinos, a);
     aux1.vecinos.elimina(b);
     aux2.vecinos.elimina(a);
     aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <tt>true</tt> si el elemento está contenido en la gráfica,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return busca(vertices,elemento)!=null;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
        Vertice vertice = busca(vertices,elemento);
        if(vertice==null){
         throw new NoSuchElementException();
     }
     vertices.elimina(vertice.elemento);
     for(Vertice v: vertices){
        Vecino vec=buscaV(v.vecinos, vertice.elemento); 
        v.vecinos.elimina(vec.vecino.elemento);
        aristas--;
    }
    }

    private Vecino buscaV(Diccionario<T,Vecino> aux, T elemento){
      if(elemento!=null && !aux.esVacio()){
         for(Vecino v : aux){
            if(elemento.equals(v.vecino.elemento)){
               return v;
           }
       }
   }
   return null;
}
    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <tt>true</tt> si a y b son vecinos, <tt>false</tt> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
        Vertice aux1 = busca(vertices,a);
        Vertice aux2 = busca(vertices,b);
        if (aux2 ==null|| aux1==null){
         throw new NoSuchElementException();
     }
     Vecino uno = buscaV(aux1.vecinos, b);
     Vecino dos= buscaV(aux2.vecinos, a);
     return uno!=null && dos!=null;
    }

    /**
     * Regresa el peso de la arista que comparten los vértices que contienen a
     * los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return el peso de la arista que comparten los vértices que contienen a
     *         los elementos recibidos.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public double getPeso(T a, T b) {
      if(!sonVecinos(a,b)){
         throw new IllegalArgumentException();
     }
     Vertice aux1=busca(vertices,a);
     if(aux1==null){
        throw new NoSuchElementException();
    }
    return buscaV(aux1.vecinos,b).peso;
    }

    /**
     * Define el peso de la arista que comparten los vértices que contienen a
     * los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @param peso el nuevo peso de la arista que comparten los vértices que
     *        contienen a los elementos recibidos.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados, o si peso
     *         es menor o igual que cero.
     */
    public void setPeso(T a, T b, double peso) {
        Vertice aux1 = busca(vertices,a);
        Vertice aux2 = busca(vertices,b);
        
        if(aux1 == null || aux2 ==null)
           throw new NoSuchElementException();
       if(a==b || peso<=0)
        throw new IllegalArgumentException();
    buscaV(aux1.vecinos,b).peso=peso;
    buscaV(aux2.vecinos,a).peso=peso;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        Vertice aux = busca(vertices, elemento);
        if(aux!=null){
         return aux;
     }
     throw new NoSuchElementException();
    }

    /**
     * Define el color del vértice recibido.
     * @param vertice el vértice al que queremos definirle el color.
     * @param color el nuevo color del vértice.
     */
    public void setColor(VerticeGrafica<T> vertice, Color color) {
        Vertice aux = (Vertice)vertice;
        if(!vertices.contiene(aux.elemento)){
         throw new NoSuchElementException();
     }
     aux.color=color;
    }

    /**
     * Nos dice si la gráfica es conexa.
     * @return <code>true</code> si la gráfica es conexa, <code>false</code> en
     *         otro caso.
     */
    public boolean esConexa() {
           Iterator<Vertice> iterador = vertices.iterator();
            Lista<VerticeGrafica<T>> aux= new Lista<VerticeGrafica<T>>();
        try{
            bfs(iterador.next().get(), (v) -> aux.agrega(v));
        }
        catch(NoSuchElementException nsee){}
        return aux.getElementos() == vertices.getElementos();
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        for (Vertice n : vertices)
            accion.actua(n);
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
       for(Vertice v : vertices){
         setColor(v,Color.ROJO);
     }
     Vertice w = busca(vertices,elemento);
     if(w == null){
         throw new NoSuchElementException();
     }
     Cola<Vertice> c = new Cola<Vertice>();
     setColor(w,Color.NEGRO);
     c.mete(w);
     while(!c.esVacia()){
       Vertice u = c.saca();
       accion.actua(u);
       for(Vecino n : u.vecinos){
          if(n.getColor()==Color.ROJO){
             setColor(n.vecino,Color.NEGRO);
             c.mete(n.vecino);
         }
     }
 }
 for(Vertice v : vertices){
     setColor(v,Color.NINGUNO);
 }
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
        for(Vertice v : vertices){
         setColor(v,Color.ROJO);
     }
     Vertice w = busca(vertices,elemento);
     if(w == null){
         throw new NoSuchElementException();
     }
     Pila<Vertice> c = new Pila<Vertice>();
     setColor(w,Color.NEGRO);
     c.mete(w);
     while(!c.esVacia()){
       Vertice u = c.saca();
       accion.actua(u);
       for(Vecino n : u.vecinos){
          if(n.getColor()==Color.ROJO){
             setColor(n.vecino,Color.NEGRO);
             c.mete(n.vecino);
         }
     }
 }
 for(Vertice v : vertices){
     setColor(v,Color.NINGUNO);
 }
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacio() {
        return vertices.getElementos()==0;
    }

    /**
     * Limpia la gráfica de vértices y aristas, dejándola vacía.
     */
    @Override public void limpia() {
        vertices.limpia();
        aristas=0;
    }

    /**
     * Regresa una representación en cadena de la gráfica.
     * @return una representación en cadena de la gráfica.
     */
    @Override public String toString() {
        Lista<String> lista = new Lista<String>();
        String s="";
        for(Vertice vertice : vertices){
           for(Vecino y : vertice.vecinos){
            if(!repetidos(vertice,y,lista))
             lista.agrega("(" + vertice.get() + ", " + y.get() + ")");
     }
    }
    s = lista.toString();
    s = s.replace("[","");
    s = s.replace("]","");  
    s+= ", ";
    return s;
    }


    private boolean repetidos(Vertice a, Vecino b, Lista<String> lista){
     String c1="(" + a.get() + ", " + b.get() + ")";
     String c2="(" + b.get() + ", " + a.get() + ")";
     return lista.contiene(c1) || lista.contiene(c2);
 }
    /**
     * Nos dice si la gráfica es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la gráfica es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Grafica<T> grafica = (Grafica<T>)o;
            if((grafica.vertices.getElementos() != vertices.getElementos()) ||
                (grafica.aristas!=this.aristas) || !(verticesIguales(vertices,grafica.vertices))){
                return false;
        }
        for(Vertice k : vertices){
           for(Vertice j : vertices){
            if(k.elemento!=j.elemento && sonVecinos(k.elemento,j.elemento)&&!grafica.sonVecinos(k.elemento, j.elemento)){
             return false;
         }
     }
    }
    return true;
    }

        private boolean verticesIguales(Diccionario<T,Vertice> aux,Diccionario<T,Vertice> aux2 ){
            for(Vertice vertice:aux){
               if(!aux2.contiene(vertice.elemento)){
                return false;
            }
        }
        return true;
        } 
    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
    private Lista<VerticeGrafica<T>> trayAux(BuscadorCamino camino, T origen, Vertice fin){
      Lista<VerticeGrafica<T>> tray = new Lista<VerticeGrafica<T>>();
      tray.agrega(vertice(fin.elemento));
      Vertice v = fin;
      while(!v.elemento.equals(origen)){
         for(Vecino u:v.vecinos){
            if(camino.seSiguen(v,u)){
               tray.agregaInicio(vertice(u.vecino.elemento));
               v=u.vecino;
               break;
           }
       }
   }
   return tray;
}
    /**
     * Calcula una trayectoria de distancia mínima entre dos vértices.
     * @param origen el vértice de origen.
     * @param destino el vértice de destino.
     * @return Una lista con vértices de la gráfica, tal que forman una
     *         trayectoria de distancia mínima entre los vértices <tt>a</tt> y
     *         <tt>b</tt>. Si los elementos se encuentran en componentes conexos
     *         distintos, el algoritmo regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> trayectoriaMinima(T origen, T destino) {
              Lista<VerticeGrafica<T>> lista=new Lista<VerticeGrafica<T>>();
              Vertice s = busca(vertices,origen);
              Vertice t = busca(vertices,destino);
              if(s==null || t==null){
               throw new NoSuchElementException();
           }
           if(origen.equals(destino)){
               lista.agrega(vertice(origen));
               return lista;
           }
           for(Vertice v:vertices){
            v.distancia=Double.MAX_VALUE;
        }
        s.distancia=0;
        Cola<Vertice> c = new Cola<Vertice>();
        c.mete(s);
        Vertice aux1=null;
        while(!c.esVacia()){
           aux1 = c.saca();
           for(Vecino u : aux1.vecinos){
            if(u.vecino.distancia==Double.MAX_VALUE){
             u.vecino.distancia = aux1.distancia+1;
             c.mete(u.vecino);
         }
        }
        }
        if(t.distancia == Double.MAX_VALUE){
            return lista;
        }
        return trayAux((v,a)->{return a.vecino.distancia == (v.distancia-1);
        },origen,t);
    }

    /**
     * Calcula la ruta de peso mínimo entre el elemento de origen y el elemento
     * de destino.
     * @param origen el vértice origen.
     * @param destino el vértice destino.
     * @return una trayectoria de peso mínimo entre el vértice <tt>origen</tt> y
     *         el vértice <tt>destino</tt>. Si los vértices están en componentes
     *         conexas distintas, regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> dijkstra(T origen, T destino) {
              Lista<VerticeGrafica<T>> lista=new Lista<VerticeGrafica<T>>();
              Vertice s = busca(vertices,origen);
              Vertice t = busca(vertices,destino);
              if(s==null || t==null)
                 throw new NoSuchElementException();
             if(origen.equals(destino)){
                 lista.agrega(vertice(origen));
                 return lista;
             }
             for(Vertice v:vertices)
                v.distancia=Double.MAX_VALUE;
            Iterator<Vertice> iterador = vertices.iterator();
            Lista<Vertice> aux= new Lista<Vertice>();
            while(iterador.hasNext())
                    aux.agrega(iterador.next());
            MonticuloMinimo<Vertice> m = new MonticuloMinimo<Vertice>(aux);
            s.distancia=0;
            Vertice aux1=null;
            
            while(!m.esVacio()){
             aux1 = m.elimina();
             for(Vecino u : aux1.vecinos){
                if(u.vecino.distancia>aux1.distancia+u.peso){
                   u.vecino.distancia = aux1.distancia+u.peso;
                   m.reordena(u.vecino);
               }
           }
        }
        if(t.distancia == Double.MAX_VALUE)
            return lista;
        
        return trayAux((v,a)->{return a.vecino.distancia == (v.distancia-a.peso);
        },origen,t);

        
    }
}
