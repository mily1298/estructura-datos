package mx.unam.ciencias.edd.proyecto1;
import java.util.Iterator;
public class Impresora{
    /**
     *Método para imprimir lo leido de los archivos de manera ordenada 
     *lo leido, en A-Z o Z-A
     *@param d Boolean si invierte o no la impresion
     *@param b Lista<String> lista que se va a ordenar e imprimir.
     */
    public void imprimir(Boolean d, Lista<String> b){
        MiComparador a = new MiComparador();
        if(d==true){
            Lista<String> aux = b.mergeSort(a);
            aux = aux.reversa();
            Iterator<String> c= aux.iteradorLista();
            while(c.hasNext()){
                System.out.println(c.next());
            }
        }
        else{
            Lista<String> aux = b.mergeSort(a);
            Iterator<String> c = aux.iteradorLista();
            while(c.hasNext()){
                System.out.println(c.next());
            }
        }
    }
}