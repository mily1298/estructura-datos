package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class PalabrasTexto{
        /**Lista de Palabras es decir la palabra y el número de repeticiones correspondiente */
		private Lista<Palabra> palabras;

        /**
        *Constructor para obtener y ordenar las palabras conforme a su número de repeticiones
        *@param leido Diccionario<String, Integer> correspondiente a lo leido del archivo.
        */
		public PalabrasTexto(Diccionario<String,Integer> leido){
			Iterator<String> iterador = leido.iteradorLlaves();
			palabras = new Lista<Palabra>();
			while(iterador.hasNext()){
				String aux = iterador.next();
				palabras.agrega(new Palabra(aux,leido.get(aux)));
			}
			palabras = palabras.mergeSort(new ComparadorPalabra());
		}

        /**
        *Método para obtener la lista de palabras.
        *@return palabras Lista<Palabra> 
        */
        public Lista<Palabra> getLista(){
            return palabras;
        }
	
}