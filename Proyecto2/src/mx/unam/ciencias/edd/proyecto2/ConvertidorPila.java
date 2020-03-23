package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class ConvertidorPila{
	private int elementos =0;
	private String svg= "<?xml version='1.0' encoding='UTF-8' ?> \n";
	/**
	*Constructor para convertir una pila a SVG
	*@param pila Pila<Integer> pila a convertir
	*/
	public ConvertidorPila(Pila<Integer> pila){
		Lista<Integer> aux= new Lista<Integer>();
		while(!pila.esVacia()){
			aux.agregaFinal(pila.saca());
		}
		this.elementos = aux.getElementos();
		svg+="<svg width='200' height='"+String.valueOf(aux.getElementos()*180)+"'>\n";
		svg+="<g>\n";
		int y=25;
		int xText = 100;
		int yText = 125;
		Iterator<Integer> iterador = aux.iteradorLista();
		for (int i=0;i<aux.getElementos() ;i++ ) {
			svg+="  <rect x=\"25\" y=\""+String.valueOf(y)+"\" width=\"150\" height=\"150\" style=\"fill:blue;stroke:#ADD8E6;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" />\n";
			svg+=" <text fill='green' font-family='sans-serif' font-size='50' x='100' y='"+String.valueOf(yText)+"' text-anchor='middle'>"+String.valueOf(iterador.next())+"</text>\n";
			yText+=150;
			y+=150;
		}
		svg+="</g>\n"+"</svg>\n";
	}
	/**
	*Método que regresa el texto xml correspondiente al svg
	*@return svg String cadena que representa el SVG
	*/
	public String svg(){
		if(elementos>0)
			return this.svg;
		return "No ingresaste números";
	}
}