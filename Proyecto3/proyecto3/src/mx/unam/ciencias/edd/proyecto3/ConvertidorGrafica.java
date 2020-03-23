package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class ConvertidorGrafica{
	private String svg=" ";
	private Grafica<String> grafica = new Grafica<String>();
	private Lista<String> aristas = new Lista<String>();
	/**
	*Constructor para convertir una grafica a SVG
	*@param aristas Diccionario<String ,Integer> contiene las palabras leidas de cada archivo.
    *@param nombres String[] contiene el nombre de cada archivo
	*/
	public ConvertidorGrafica(Diccionario<String,Integer>[] documentos, String[] nombres){
        svg+="<svg width=\"900px\" height=\"900px\">\n";
        svg+="<g>\n";
        int contador=0;
        for (int i=0;i<documentos.length ; i++) {
            if(!grafica.contiene(nombres[i]))
                grafica.agrega(nombres[i]);
            Iterator<String> iterador = documentos[i].iteradorLlaves();
            for (int j =i+1;j<documentos.length ;j++ ) {
               if(!grafica.contiene(nombres[j]))
                grafica.agrega(nombres[j]);
            while(iterador.hasNext()){
                String palabra = iterador.next();
                if(documentos[j].contiene(palabra) && palabra.length()>=5)
                    contador++;
            }
            if(contador>=10)
                grafica.conecta(nombres[i],nombres[j]);
            contador=0;
            iterador = documentos[i].iteradorLlaves();               
        }
    }
    for( String vertice : grafica )
     this.aristas.agrega(vertice);
}
	/**
	*Método para crear los vértices, aristas y elementos de la grafica
	*/
	private void crearGrafica(){
        String aristasGrafica = "";
        String verticesGraficables = "";
        String elementosVertice = "";
        double rad = Math.toRadians(360)/aristas.getElementos();
        int index = 1;
        for( String vertice : aristas){
         VerticeGrafica<String> verticeGrafica = grafica.vertice(vertice);
         int x = (int)(300 + 240*Math.cos(index*rad));
         int y = (int)(300 + 240*Math.sin(index++*rad));
         verticesGraficables+="<circle cx=\""+String.valueOf(x)+"\" cy=\""+String.valueOf(y)+"\" r=\"40\" stroke=\"DarkGrey \" stroke-width=\"3\" fill=\"CornflowerBlue \" />\n";
         elementosVertice+="<text x=\""+String.valueOf(x-20)+"\" y=\""+String.valueOf(y+10)+"\" fill=\"Magenta  \" font-size='12'>"+vertice+"</text>\n";
         for( VerticeGrafica<String> vecino : verticeGrafica.vecinos() ){
            int vi = aristas.indiceDe(vecino.get())+1;
            int vy = (int)(300 + 240*Math.sin(vi*rad));
            int vx = (int)(300 + 240*Math.cos(vi*rad));
            aristasGrafica+="<line x1=\""+String.valueOf(x)+"\" y1=\""+String.valueOf(y)+"\" x2=\""+String.valueOf(vx)+"\" y2=\""+String.valueOf(vy)+"\" style=\"stroke:YellowGreen ;stroke-width:2\" />\n";           
        }
    }
    svg+= aristasGrafica+verticesGraficables+elementosVertice;
    svg+="</g>\n";
    svg+="</svg>\n";
}
	/**
	*Método para obtener el codigo xml de la gráfica
	*/
	public String getSVG(){
        crearGrafica();
        return this.svg;
    }
}