package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class Palabra implements Comparable<Palabra> {
	/** Número de repeticiones del String*/
	public int repeticiones;

	/** String correspondiente a la palabra*/
	private	String palabra;


	/**
	*Constructor de una Palabra
	*@param a String correspondiente a la palabra correspondiente.
	*@param b int correspondiente al número de repeticiones de cada palabra
	*/
	public Palabra(String a, int b){
		repeticiones = b;
		palabra = a;
	}

	/**
	*Método para obtener el numero de repeticiones de una palabra
	*@param repeticiones int repeticiones correspondientes.
	*/
	public int getRepeticiones(){
		return repeticiones;
	}


	/**
	*Método para obtener el String de la palabra.
	*@param palabra String palabra correspondiente
	*/
	public String getPalabra(){
		return	palabra;
	}
	
	/**
	 *Método para representar en cadena una PALABRA
	 *@return representacion String de la PALABRA.
	 */
	public String toString(){
		return palabra +" "+repeticiones;
	}

    /**
     * Compara el indexable con otro indexable.
     * @param indexable el indexable.
     * @return un valor menor que cero si el indexable que llama el método es
     *         menor que el parámetro; cero si son iguales; o mayor que cero si
     *         es mayor.
     */
    @Override public int compareTo(Palabra palabra) {
       return Integer.compare(this.repeticiones, palabra.repeticiones);
   }
}