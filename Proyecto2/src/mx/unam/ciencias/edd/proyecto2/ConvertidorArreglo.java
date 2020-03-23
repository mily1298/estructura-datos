package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class ConvertidorArreglo{
	private int elementos=0;
	private String svg= "<?xml version='1.0' encoding='UTF-8' ?> \n";
	/**
	*Constructor convertidor de una lista que simula un arreglo a SVG
	*@param arreglo Lista<Integer> lista que simula los elementos de un arreglo.
	*/
	public ConvertidorArreglo(Lista<Integer> arreglo){
		this.elementos = arreglo.getElementos();
		svg+="<svg width='"+String.valueOf(arreglo.getElementos()*180)+"' height='300'>\n";
		svg+="<g>\n";
		int x=50;
		int xText = x+75;
		int yText = 140;
		Iterator<Integer> iterador = arreglo.iteradorLista();
		for (int i=0;i<arreglo.getElementos();i++ ) {
			svg+="  <rect x=\""+String.valueOf(x)+"\" y=\"50\" width=\"150\" height=\"150\" style=\"fill:GreenYellow;stroke:LightSkyBlue;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" />\n";
			svg+=" <text fill='green' font-family='sans-serif' font-size='50' x='"+String.valueOf(xText)+"' y='140' text-anchor='middle'>"+String.valueOf(iterador.next())+"</text>\n";
			xText+=150;
			x+=150;
		}
		svg+="</g>\n"+"</svg>\n";
	}
    /**
    *Método para obtener la representacion en cadena del arreglo
    *@return svg String cadena que representa el arreglo en SVG
    */
    public String svg(){
      if(elementos == 0)
       return "No ingresaste números";
   return this.svg;
}
}