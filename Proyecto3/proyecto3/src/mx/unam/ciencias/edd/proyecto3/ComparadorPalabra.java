package mx.unam.ciencias.edd.proyecto3;
import java.util.Comparator;
public class ComparadorPalabra implements Comparator<Palabra>{
	/**
	 *Método compare para cla clase Palabra
	 *@param a Palabra palabra 1 a comparar 
	 *@param b Palabra palabra 2 a comparar
	 *@return regresa la diferencia de repetiociones de la Palabra a con la 
	 *Palabra b.
	 */
	@Override
	public int compare(Palabra a, Palabra b){
		return a.repeticiones - b.repeticiones;
	}

}