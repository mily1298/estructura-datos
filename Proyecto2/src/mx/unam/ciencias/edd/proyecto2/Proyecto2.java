package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
public class Proyecto2{
    /**
    *Método principal
    *@param args String[] arreglo de argumentos recibidos.
    */
    public static void main(String[] args)throws IOException, ExcepcionClaseInvalida, ExcepcionArgumentosInvalidos{
      Lector leer = new Lector();
      if(args.length == 0){
        leer.leerConsola();
        if(leer.getLista().esVacio()){
            System.err.println("No se recibio nada en la entrada estandar ");
            return;
        }
    }
    else{      
        try{
            for (int i= 0;i<args.length; i++)
                leer.leerArchivo(args[i]);
            if(leer.getLista()==null){
                System.err.println("Tipo de Archivo Invalido");
                return;
            }    
            if(leer.getLista().esVacio()){
                System.err.println("No hay argumentos");
                return;
            }    
        }
        catch(IOException ioe){
            System.err.println("Tipo de archivo Invalido");
            return;
        }
    }
    Verificador a = new Verificador(leer.getLista());
    String clase = " ";
    Lista<Integer> numeros = null;
    try{
        a.commentKiller();
        a.verificaClase();
        clase = a.getClase();
        a.chequeoArgumentos();
        numeros = a.getNumeros();
    }
    catch(ExcepcionClaseInvalida exi){
        System.err.println("No ingresaste una clase valida");
        return;
    }
    catch(ExcepcionArgumentosInvalidos eai){
        System.err.println("Tus argumentos ingresados al programa\n no son validos");
        return;
    }
    Iterator<Integer> iterador = numeros.iteradorLista();
    switch(clase){
        case("pila"):
        Pila<Integer> pila = new Pila<Integer>();
        while(iterador.hasNext())
            pila.mete(iterador.next());
        ConvertidorPila m = new ConvertidorPila(pila);
        System.out.println(m.svg());
        break;
        case("cola"):
        ConvertidorCola m1 = new ConvertidorCola(numeros);
        System.out.println(m1.svg());
        break;
        case("lista"):
        ConvertidorLista m2 = new ConvertidorLista(numeros);
        System.out.println(m2.svg());
        break;
        case("arreglo"):
        ConvertidorArreglo m3 = new ConvertidorArreglo(numeros);
        System.out.println(m3.svg());
        break;
        case("arreglos"):
        ConvertidorArreglo m4 = new ConvertidorArreglo(numeros);
        System.out.println(m4.svg());
        break;
        case("arbolbinariocompleto"):
        ArbolBinarioCompleto<Integer> arbol = new ArbolBinarioCompleto<Integer>(numeros);
        ConvertidorArboles m5 = new ConvertidorArboles(arbol);
        System.out.println(m5.svg());
        break;
        case("arbolbinarioordenado"):
        ArbolBinarioOrdenado<Integer> arbol1 = new ArbolBinarioOrdenado<Integer>(numeros);
        ConvertidorArboles m6 = new ConvertidorArboles(arbol1);
        System.out.println(m6.svg());
        break;
        case("arbolrojinegro"):
        ArbolRojinegro<Integer> arbol2 = new ArbolRojinegro<Integer>(numeros);
        ConvertidorArboles m7 = new ConvertidorArboles(arbol2);
        System.out.println(m7.svg());
        break;
        case("arbolavl"):
        ArbolAVL<Integer> arbol3 = new ArbolAVL<Integer>(numeros);
        ConvertidorArboles m8 = new ConvertidorArboles(arbol3);
        System.out.println(m8.svg());
        break;
        case("monticulominimo"):
        Lista<Indexable<Integer>> lista = new Lista<Indexable<Integer>>();
        for ( int num:numeros ){
            Indexable<Integer> indexable = new Indexable<Integer>(num,num);
            lista.agrega(indexable);
        }
        MonticuloMinimo<Indexable<Integer>>  monticulo = new MonticuloMinimo<Indexable<Integer>> (lista);
        ConvertidorMonticulo m9 = new ConvertidorMonticulo(monticulo);
        System.out.println(m9.svg());
        break;
        case("monticulosminimos"):
        Lista<Indexable<Integer>> lista2 = new Lista<Indexable<Integer>>();
        for ( int num:numeros ){
            Indexable<Integer> indexable = new Indexable<Integer>(num,num);
            lista2.agrega(indexable);
        }
        MonticuloMinimo<Indexable<Integer>>  monticulo2 = new MonticuloMinimo<Indexable<Integer>> (lista2);
        ConvertidorMonticulo m10 = new ConvertidorMonticulo(monticulo2);
        System.out.println(m10.svg());
        break;
        case("grafica"):
        try{
            ConvertidorGrafica m11 = new ConvertidorGrafica(numeros);
            System.out.println(m11.svg());
        }
        catch(ExcepcionAristasGrafica ear){
            System.err.println("Error en el formato de las aristas");
        }
        break;
    }

}
}
