package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
  */
public class Arreglos {

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        quickSort(arreglo,0,arreglo.length-1,comparador);
    }

    public static <T>  void
    quickSort(T[] arreglo, int ini, int fin, Comparator<T> comparador){
        if(ini>=fin)
            return;
        int i = ini+1;
        int j = fin;
        while(i<j){
            if (comparador.compare(arreglo[i],arreglo[ini])>=0 &&
                comparador.compare(arreglo[j],arreglo[ini])<0) {
                intercambia(arreglo,i, j);
                i=i+1;
                j=j-1;
            }
            else if (comparador.compare(arreglo[i],arreglo[ini])<0)
                i=i+1;
            else
                j=j-1; 
        }
        if (comparador.compare(arreglo[i],arreglo[ini])>0)
                i=i-1;
            

        intercambia(arreglo,ini,i);
        quickSort(arreglo, ini, i-1, comparador);
        quickSort(arreglo,i+1,fin, comparador);  
    }

    public static <T> void intercambia(T[] arreglo, int a, int b){
        T aux = arreglo[a];
        arreglo[a] = arreglo[b];
        arreglo[b] = aux;
    }

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        int min;
        for (int i=0;i<arreglo.length ;i++ ) {
            min=i;
            for (int j=i+1;j<arreglo.length ;j++ ) {
                if (comparador.compare(arreglo[i],arreglo[j])>=0){
                    min=j;
                    intercambia(arreglo,i,min);
                }
            }

        }

    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        if(elemento==null)
            return -1;
        int i = (arreglo.length-1)/2;
        int ini=0;
        int fin =arreglo.length-1;
        while(ini<=fin){
            if (comparador.compare(arreglo[i],elemento)>0) {
              fin= i-1;
              i = (ini+fin)/2;
            }
            if (comparador.compare(arreglo[i], elemento)<0) {
                ini= i+1;
              i= (ini+fin)/2;
            }
            if (comparador.compare(arreglo[i],elemento)==0) {
                return i;
            }
        }
        if (ini>fin) 
            return -1;
        return -1;
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }
}
