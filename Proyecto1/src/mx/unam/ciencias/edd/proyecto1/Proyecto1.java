package mx.unam.ciencias.edd.proyecto1;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Proyecto1{
    /**
     *Método Main para ordenar los archivos o lo leido en consola
     *@param args String[] arreglo de String que son los argumentos recibidos.
     */
    public static void main(String[] args)throws IOException{
        ReversaLista e = new ReversaLista(); 
        Boolean reverse = e.buscaR(args);
        Lector leer = new Lector();
        if(args.length == 0)
            leer.leerConsola();
        if(args.length == 1 && reverse==true)
            leer.leerConsola();
        if(reverse==true){
            for (int i= 0;i<args.length-1; i++)
                leer.leerArchivo(args[i]);
        }
        else{
            for (int i= 0;i<args.length; i++)
                leer.leerArchivo(args[i]);        
        }        
        if(!leer.esVacio()){
            Impresora impresora = new Impresora();
            impresora.imprimir(reverse, leer.lectura);
        }

    }
}