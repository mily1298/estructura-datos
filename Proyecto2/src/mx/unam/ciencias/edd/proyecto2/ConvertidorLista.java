package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class ConvertidorLista{
	private int elementos =0;
	private  String svg = "<?xml version='1.0' encoding='UTF-8' ?> \n";
	/**
	*Constructor para convertir una lista a SVG
	*@param lista Lista<Integer> lista a convertir 
	*/
	public ConvertidorLista(Lista<Integer> lista){
		this.elementos =0;
		svg+="<svg width='"+String.valueOf(lista.getElementos()*300)+"' height='300'>\n";
		svg+="<g>\n";
		int x=50;
		int y=50;
		int x2=0;
		int xtext=0;
		Iterator<Integer> iterador = lista.iteradorLista();
		while(iterador.hasNext()){
			xtext= x+75;
			svg+="<rect x='"+String.valueOf(x)+ "' y='"+ String.valueOf(y)+ "' width=\"150\" height=\"150\" style=\"fill:#ADFF2F;stroke:#ADFF2F;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.5\" />\n";
			svg+="          <text fill='green' font-family='sans-serif' font-size='50' x='"+String.valueOf(xtext)+"' y='140' text-anchor='middle'>"+String.valueOf(iterador.next())+"</text>\n";
			if (iterador.hasNext()) {
				x+=151;
				x2 = x+100;
				svg+= "    <line x1='"+String.valueOf(x)+"' y1='100' x2='"+String.valueOf(x2)+"' y2='100' stroke='coral' stroke-width='2' /> \n";
				svg+= "    <line x1='"+String.valueOf(x2-20)+"' y1='110' x2='"+String.valueOf(x2)+"' y2='100' stroke='coral' stroke-width='2' />\n";
				svg+= "    <line x1='"+String.valueOf(x2-20)+"' y1='90' x2='"+String.valueOf(x2)+"' y2='100' stroke='coral' stroke-width='2' />\n";
				svg+= "    <line x1='"+String.valueOf(x)+"' y1='150' x2='"+String.valueOf(x2)+"' y2='150' stroke='coral' stroke-width='2' />\n";
				svg+= "    <line x1='"+String.valueOf(x)+"' y1='150' x2='"+String.valueOf(x+20)+"' y2='140' stroke='coral' stroke-width='2' />\n";
				svg+= "    <line x1='"+String.valueOf(x)+"' y1='150' x2='"+String.valueOf(x+20)+"' y2='160' stroke='coral' stroke-width='2' />\n";
				x= x2+4;	

			}

		}
		svg+="</g>\n"+"</svg>\n";
	}
	/**
	*Método que regresa la cadena del codigo xml de la lista.
	*@return svg String regresa el codigo xml de la representacion svg de la lista
	*/
	public String svg(){
		if(elementos ==0)
			return "No ingresaste números";
		return this.svg;
	}
}