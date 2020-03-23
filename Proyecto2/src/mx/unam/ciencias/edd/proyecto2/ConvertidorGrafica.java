package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class ConvertidorGrafica{
	private String svg="<?xml version='1.0' encoding='UTF-8' ?> \n";
	private Grafica<Integer> grafica = new Grafica<Integer>();
	private Lista<Integer> aristas = new Lista<Integer>();
	/**
	*Constructor para convertir una grafica a SVG
	*@param aristas Lista<Integer> lista de números que representan las aristas
	*/
	public ConvertidorGrafica(Lista<Integer> aristas){
		if(aristas.getElementos()%2!=0)
			throw new ExcepcionAristasGrafica();
        svg+="<svg>\n";
        svg+="<g>\n";
        Iterator<Integer> iterador = aristas.iterator();
        while( iterador.hasNext() ) {
         Integer vertice1 = iterador.next();
         if( !grafica.contiene(vertice1) )
            grafica.agrega(vertice1);
        Integer vertice2 = iterador.next();
        if( !grafica.contiene(vertice2) )
            grafica.agrega(vertice2);
        try{
            grafica.conecta(vertice1,vertice2);
        }
        catch(IllegalArgumentException iae){
            throw  new ExcepcionAristasGrafica();
        }
    }

    for( Integer vertice : grafica )
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
        for( Integer vertice : aristas){
         VerticeGrafica<Integer> verticeGrafica = grafica.vertice(vertice);
         int x = (int)(250 + 240*Math.cos(index*rad));
         int y = (int)(250 + 240*Math.sin(index++*rad));
         verticesGraficables+="<circle cx=\""+String.valueOf(x)+"\" cy=\""+String.valueOf(y)+"\" r=\"40\" stroke=\"DarkGrey \" stroke-width=\"3\" fill=\"CornflowerBlue \" />\n";
         elementosVertice+="<text x=\""+String.valueOf(x-12)+"\" y=\""+String.valueOf(y+15)+"\" fill=\"Magenta  \" font-size='50'>"+String.valueOf(vertice)+"</text>\n";
         for( VerticeGrafica<Integer> vecino : verticeGrafica.vecinos() ){
            int vi = aristas.indiceDe(vecino.get())+1;
            int vy = (int)(250 + 240*Math.sin(vi*rad));
            int vx = (int)(250 + 240*Math.cos(vi*rad));
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
	public String svg(){
		if(grafica.getElementos()==0)
			return "No ingresaste números";
        crearGrafica();
        return this.svg;
    }
}