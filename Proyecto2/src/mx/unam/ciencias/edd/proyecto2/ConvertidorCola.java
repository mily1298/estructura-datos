package mx.unam.ciencias.edd.proyecto2;
import java.util.Iterator;
import mx.unam.ciencias.edd.*;
public class ConvertidorCola{
	private String svg= "<?xml version='1.0' encoding='UTF-8' ?> \n";
    private int elementos = 0;
    /**
    *Constructor para convertir una lista de elementos en forma de cola
    *@param cola Lista<Integer> lista que representa los elementos de la cola.
    */
    public ConvertidorCola(Lista<Integer> cola){
        this.elementos = cola.getElementos();
        svg+="<svg width='"+String.valueOf(cola.getElementos()*300)+"' height='300'>\n";
        svg+="<g>\n";
        int x=50;
        int y=50;
        int x2=0;
        int xtext=0;
        Iterator<Integer> iterador = cola.iteradorLista();
        while(iterador.hasNext()){
         xtext= x+75;
         svg+="<rect x='"+String.valueOf(x)+ "' y='50' width=\"150\" height=\"150\" style=\"fill:#ADFF2F;stroke:#ADFF2F;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.5\" />\n";
         svg+="<text fill='green' font-family='sans-serif' font-size='50' x='"+String.valueOf(xtext)+"' y='140' text-anchor='middle'>"+String.valueOf(iterador.next())+"</text>\n";
         if (iterador.hasNext()) {
            x+=151;
            x2 = x+100;
            svg+= "    <line x1='"+String.valueOf(x)+"' y1='125' x2='"+String.valueOf(x2)+"' y2='125' stroke='coral' stroke-width='2' /> \n";
            svg+= "    <line x1='"+String.valueOf(x2-20)+"' y1='115' x2='"+String.valueOf(x2)+"' y2='125' stroke='coral' stroke-width='2' />\n";
            svg+= "    <line x1='"+String.valueOf(x2-20)+"' y1='135' x2='"+String.valueOf(x2)+"' y2='125' stroke='coral' stroke-width='2' />\n";
            x= x2+4;	

        }

    }
    svg+="</g>\n"+"</svg>\n";
}
        /**
        *Método para obtener el codigo xml de la cola
        *@return svg String cadena xml de la cola.
        */
        public String svg(){
            if(elementos == 0)
                return "No ingresaste números";
            return this.svg;
        }
    }