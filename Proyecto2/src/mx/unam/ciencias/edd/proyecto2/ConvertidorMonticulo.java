    package mx.unam.ciencias.edd.proyecto2;
    import mx.unam.ciencias.edd.*;
    import java.util.Iterator;
    public class ConvertidorMonticulo{
    	private Lista<VerticeSVG> verticesGraficables = new Lista<VerticeSVG>();
    	private String svg = "<?xml version='1.0' encoding='UTF-8' ?> \n";
    	private int elementos;
    	private int yMax;
        /**
        *Constructor para convertir un monticulo a SVG
        *@param monticulo MonticuloMinimo<Indexable<Integer>> monticulo a convertir,
        */
        public ConvertidorMonticulo(MonticuloMinimo<Indexable<Integer>> monticulo){
          this.elementos = monticulo.getElementos();
          svg+="<svg width='"+String.valueOf(monticulo.getElementos()*220)+"' height='"+String.valueOf( ((int)Math.floor(Math.log(monticulo.getElementos()) / Math.log(2)))*300)+"'>\n";
          svg+="<g>\n";
          Iterator<Indexable<Integer>> iterador = monticulo.iterator();
          for (int i=0; i<this.elementos; i++) {
             if(i==0){
                verticesGraficables.agrega(new VerticeSVG(monticulo.get(0).getElemento(), (int)(this.elementos*220)/2, 50));
                if(hayHIzquierdo(i))
                   verticesGraficables.agrega(new VerticeSVG(monticulo.get(hIzquierdo(i)).getElemento(),verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),true,hIzquierdo(i), verticesGraficables.get(i).diferencia()));
               if(hayHDerecho(i))
                   verticesGraficables.agrega(new VerticeSVG(monticulo.get(hDerecho(i)).getElemento(),verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),false,hDerecho(i),verticesGraficables.get(i).diferencia()));
           }
           else{
              VerticeSVG aux = verticesGraficables.get(i);
              if(hayHIzquierdo(i))
                 verticesGraficables.agrega(new VerticeSVG(monticulo.get(hIzquierdo(i)).getElemento(),verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),true,hIzquierdo(i),verticesGraficables.get(i).diferencia()));
             if(hayHDerecho(i))
                 verticesGraficables.agrega(new VerticeSVG(monticulo.get(hDerecho(i)).getElemento(),verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),false,hDerecho(i),verticesGraficables.get(i).diferencia()));
         }
     }
     yMax = verticesGraficables.getUltimo().getY()+ 150;
 }
        /**
        *Método para crear las aristas correspondiente al arbol generado por el monticulo
        */
        private void crearAristas(){
          Iterator iterador = verticesGraficables.iteradorLista();
          while(iterador.hasNext()){
             VerticeSVG aux = (VerticeSVG)iterador.next();
             svg+="<line x1=\""+String.valueOf(aux.getX())+"\" y1=\""+String.valueOf(aux.getY())+"\" x2=\""+String.valueOf(aux.getXpadre())+"\" y2=\""+String.valueOf(aux.getYpadre())+"\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />\n";           
         }
     }
        /**
        *Método para crear los vertices correspondientes al arbol generado por el monticulo
        */
        private void crearVertices(){
          Iterator iterador = verticesGraficables.iteradorLista();
          while(iterador.hasNext()){
             VerticeSVG aux = (VerticeSVG)iterador.next();
             svg+="<circle cx=\""+String.valueOf(aux.getX())+"\" cy=\""+String.valueOf(aux.getY())+"\" r=\"40\" stroke=\"DodgerBlue\" stroke-width=\"3\" fill=\"aqua\" />\n";
         }
     }
        /**
        *Método para crear los elementos en los vertices ya creados correspondientes al monticulo
        */
        private void crearElementos(){
          Iterator iterador = verticesGraficables.iteradorLista();
          while(iterador.hasNext()){
             VerticeSVG aux = (VerticeSVG)iterador.next();
             svg+="<text x=\""+String.valueOf(aux.getX()-12)+"\" y=\""+String.valueOf(aux.getY()+15)+"\" fill=\"red\" font-size='50'>"+String.valueOf(aux.elemento())+"</text>\n";
         }
     }
        /**
        *Método para crear el arreglo correspondiente al monticulo
        */
        private void crearArreglo(){
          int x=50;
          int xText = x+50;
          int yText = yMax+65;
          Iterator<VerticeSVG> iterador = verticesGraficables.iteradorLista();
          for (int i=0;i<verticesGraficables.getElementos();i++ ) {
             svg+="  <rect x=\""+String.valueOf(x)+"\" y=\""+String.valueOf(yMax)+"\" width=\"100\" height=\"100\" style=\"fill:GreenYellow;stroke:LightSkyBlue;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9\" />\n";
             svg+=" <text fill='green' font-family='sans-serif' font-size='50' x='"+String.valueOf(xText)+"' y='"+String.valueOf(yText)+"' text-anchor='middle'>"+String.valueOf(iterador.next().elemento())+"</text>\n";
             xText+=100;
             x+=100;
         }
     }
     public String svg(){
       if(elementos == 0)
          return "No ingresaste números";
      crearAristas();
      crearVertices();
      crearElementos();
      crearArreglo();
      svg+="</g>\n"+"</svg>\n";
      return this.svg;
  }
        /**
        *metodo para obtener el hijo derecho a partir del indice del arreglo
        *@param indice int indice del arreglo del padre
        *@return indice int indice de la posicion del hijo derecho
        */
        private int hDerecho(int indice){
          return (indice*2)+2;
      }
      
        /**
        *metodo para obtener el hijo izquierdo a partir del indice del arreglo
        *@param indice int indice del arreglo del padre
        *@return indice int indice de la posicion del hijo izquierdo
        */
        private int hIzquierdo(int indice){
          return hDerecho(indice)-1;
      }
        /**
        *metodo para saber si hay hijo izquierdo a partir del indice del arreglo
        *@param indice int indice del arreglo del padre
        *@return true si hay hijo izquierdo, falso en caso contrario
        */
        private boolean hayHIzquierdo(int indice){
          return hIzquierdo(indice)<this.elementos?true:false;
      }
      
        /**
        *metodo para saber si hay hijo izquierdo a partir del indice del arreglo
        *@param indice int indice del arreglo del padre
        *@return true si hay hijo izquierdo, falso en caso contrario
        */
        private boolean hayHDerecho(int indice){
          return hDerecho(indice)<this.elementos?true:false;
      }
  }