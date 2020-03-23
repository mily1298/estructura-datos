package mx.unam.ciencias.edd.proyecto3;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.text.Normalizer;
import mx.unam.ciencias.edd.*;
public class ContadorPalabras{
	private Diccionario<String,Integer> contador;
    private int elementos;
    private int palabras;
    /**
     * Método constructor del objeto ContadorPalabras sin parametros.
     */
	public ContadorPalabras(){
		contador = new Diccionario<String,Integer>(2000);
	}

    /**
     *Método para contar las repeticiones de cada palabra de todo un archivo.
     *@param a Lista que contiene las lineas leidas de un archivo.
     */
    public void contadorRepeticiones(Lista<String> a){
        Iterator<String> iterador = a.iteradorLista();
        while(iterador.hasNext()){
            int aux1 =0;
            int aux2 =0;
            String cadena  = Normalizer.normalize(iterador.next().trim(), Normalizer.Form.NFD);
            cadena = cadena.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            cadena = cadena.replaceAll("\\p{P}+","");
            cadena = cadena.toLowerCase();
            String palabra;
            while (0!= cadena.length()){
                aux1 = cadena.indexOf(" ");
                if(aux1== -1){
                    palabra = cadena;
                    cadena = "";
                }
                else{
                    palabra = cadena.substring(aux2, aux1);
                    cadena = cadena.substring(aux1,cadena.length()).trim();
                }

                if(contador.contiene(palabra)){
                    palabras++;
                    contador.agrega(palabra, contador.get(palabra)+1);
                }
                else{
                    palabras++;
                    contador.agrega(palabra,1);
                }
            }
        }
    }

    /**
     *Método para saber si el contador de palabras es vacío.
     *@return true si es vacio el diccionario o false en otro caso.
     */
    public boolean esVacio(){
        return contador.esVacio();
    }

    /**
     *Método que regresa el diccionario con las palabras y el numero de repeticiones.
     *@return contador Diccionario<String, Integer> el diccionario correspondiente usado para contar palabras
     */
    public Diccionario<String,Integer> getDiccionario(){
        return contador;
    }

    /**
     *Método que te regresa el número de repeticiones de una palabra
     *@return int el número de repticiones de la palabra recibida.
     *@throws IllegalArgumentExcepcion en caso de que la palabra dada no haya estado en el diccionario contador
     */
    public int nRepeticiones(String aux){
        aux = aux.trim();
        aux = aux.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        aux = aux.toLowerCase();
        if(contador.contiene(aux))
            return contador.get(aux);
        else
            throw new IllegalArgumentException();
    }
    /**
     *Método para saber cuantas palabras tiene el diccionario.
     *@return número de palabras contenidas en el diccionario.
     */
    public int elementos(){
        return contador.getElementos();
    }

    /**
    *Método que regresa el numero de palabras de un archivo
    *@return palabras int numero de palabras del archivo
    */
    public int nPalabras(){
        return palabras;
    }
}