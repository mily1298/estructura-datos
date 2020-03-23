package mx.unam.ciencias.edd.proyecto1;
import java.text.Collator;
import java.util.Comparator;
public class MiComparador implements Comparator<String>{
	/**
	 *Método que para comparar 2 cadenas a través de un Collator
	 *@param aux String primera cadena a comparar
	 *@param aux2 String segunda cadena a comparar
	 *@return regresa un entero usando el comparador de Collator
	 */
    public int compare(String aux, String aux2) {
        Collator comparador = Collator.getInstance();
        comparador.setStrength(Collator.PRIMARY);
        return comparador.compare(aux, aux2);
    }
}