package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ConvertidorArboles{
	private Lista<VerticeSVG> verticesGraficables = new Lista<VerticeSVG>();
	private String svg = "<?xml version='1.0' encoding='UTF-8' ?> \n";
	private int[] arbolCompleto;
    private int elementos;
    /**
    *Constructor para convertir un arbol binario completo a SVG
    *@param arbol ArbolBinarioCompleto<Integer> arbol a convertir a SVG
    */
    public ConvertidorArboles(ArbolBinarioCompleto<Integer> arbol){
        this.elementos = arbol.getElementos();

        if(arbol.getElementos()!=0){
            svg+="<svg width='"+String.valueOf(arbol.getElementos()*220)+"' height='"+String.valueOf(arbol.altura()*250)+"'>\n";
            svg+="<g>\n";
            arbolCompleto = new int[arbol.getElementos()];
            Iterator<Integer> iterador = arbol.iterator();
            int contador =0;

            while(iterador.hasNext()){
                arbolCompleto[contador]=(int)iterador.next();
                contador++;
            }
            for (int i=0; i<arbolCompleto.length; i++) {
                if(i==0){
                  verticesGraficables.agrega(new VerticeSVG(arbolCompleto[0], (int)(this.elementos*220)/2, 50));
                  if(hayHIzquierdo(i))
                    verticesGraficables.agrega(new VerticeSVG(arbolCompleto[hIzquierdo(i)],verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),true,hIzquierdo(i), verticesGraficables.get(i).diferencia()));
                if(hayHDerecho(i))
                    verticesGraficables.agrega(new VerticeSVG(arbolCompleto[hDerecho(i)],verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),false,hDerecho(i),verticesGraficables.get(i).diferencia()));
            }
            else{
                VerticeSVG aux = verticesGraficables.get(i);
                if(hayHIzquierdo(i))
                    verticesGraficables.agrega(new VerticeSVG(arbolCompleto[hIzquierdo(i)],verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),true,hIzquierdo(i),verticesGraficables.get(i).diferencia()));
                if(hayHDerecho(i))
                    verticesGraficables.agrega(new VerticeSVG(arbolCompleto[hDerecho(i)],verticesGraficables.get(i).getX(),verticesGraficables.get(i).getY(),false,hDerecho(i),verticesGraficables.get(i).diferencia()));
            }
        }
    }

    else{
        svg+="<svg width='100' height='100'>\n";
        svg+="<g>\n";     
        svg+="</g>\n"+"</svg>\n";
    }
}
    /**
    *Constructor para convertir un arbol RojiNegro a SVG
    *@param arbol ArbolRojinegroS<Integer> arbol a convertir a SVG
    */
    public ConvertidorArboles(ArbolRojinegro<Integer> arbol){
        this.elementos = arbol.getElementos();
        if(this.elementos>0){
            verticesGraficables = new Lista<VerticeSVG>();
            svg+="<svg width='"+String.valueOf(arbol.getElementos()*220)+"' height='"+String.valueOf(arbol.altura()*250)+"'>\n";
            svg+="<g>\n";
            Cola<VerticeArbolBinario<Integer>> cola = new Cola<VerticeArbolBinario<Integer>>();
            VerticeArbolBinario<Integer> p = null;
            VerticeArbolBinario<Integer> hi=null;
            VerticeArbolBinario<Integer> hd=null;
            cola.mete(arbol.raiz());
            int contador =0;
            while(!cola.esVacia()){
                p = cola.saca();
                if(contador ==0){
                    VerticeSVG aux = new VerticeSVG(p.get(), (int)(this.elementos*220)/2, 50, arbol.getColor(p)); 
                    verticesGraficables.agrega(aux);
                    try{
                        hi = p.izquierdo();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(), (int)(this.elementos*220)/2, 50, arbol.getColor(hi), true ,contador+1 ,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(), (int)(this.elementos*220)/2, 50, arbol.getColor(hd), false, contador+2, aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),(int)(this.elementos*220)/2,50,arbol.getColor(hd),false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ }
                    }

                    contador++;
                }
                else{
                    VerticeSVG aux = verticesGraficables.get(contador); 
                    try{
                        hi = p.izquierdo();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(),aux.getX(),aux.getY(),arbol.getColor(hi),true,contador+1,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),arbol.getColor(hd),false,contador+2,aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),arbol.getColor(hd),false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ }
                    }

                    contador++;

                }
            }
        }
        else{
            svg+="<svg width='100' height='100'>\n";
            svg+="<g>\n";     
            svg+="</g>\n"+"</svg>\n";

        }
    }
    /**
    *Constructor para convertir un arbol binario ordenado a SVG
    *@param arbol ArbolBinarioOrdenado<Integer> arbol a convertir a SVG
    */
    public ConvertidorArboles(ArbolBinarioOrdenado<Integer> arbol){
        this.elementos = arbol.getElementos();
        if(this.elementos>0){
            verticesGraficables = new Lista<VerticeSVG>();
            svg+="<svg width='"+String.valueOf(arbol.getElementos()*220)+"' height='"+String.valueOf(arbol.altura()*250)+"'>\n";
            svg+="<g>\n";
            Cola<VerticeArbolBinario<Integer>> cola = new Cola<VerticeArbolBinario<Integer>>();
            VerticeArbolBinario<Integer> p = null;
            VerticeArbolBinario<Integer> hi=null;
            VerticeArbolBinario<Integer> hd=null;
            cola.mete(arbol.raiz());
            int contador =0;
            while(!cola.esVacia()){
                p = cola.saca();
                if(contador ==0){
                    VerticeSVG aux = new VerticeSVG(p.get(), (int)(this.elementos*220)/2, 50); 
                    verticesGraficables.agrega(aux);
                    try{
                        hi = p.izquierdo();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(), (int)(this.elementos*220)/2, 50, true ,contador+1 ,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(), (int)(this.elementos*220)/2, 50, false, contador+2, aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),(int)(this.elementos*220)/2,50,false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ }
                    }

                    contador++;
                }
                else{
                    VerticeSVG aux = verticesGraficables.get(contador); 
                    try{
                        hi = p.izquierdo();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(),aux.getX(),aux.getY(),true,contador+1,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),false,contador+2,aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            hd = p.derecho();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ }
                    }

                    contador++;

                }
            }
        }
        else{
            svg+="<svg width='100' height='100'>\n";
            svg+="<g>\n";     
            svg+="</g>\n"+"</svg>\n";

        }
    }
    /**
    *Constructor para convertir un arbolAVL a SVG
    *@param arbol ArbolAVL<Integer> arbol a convertir a SVG
    */
    public ConvertidorArboles(ArbolAVL<Integer> arbol){
        this.elementos = arbol.getElementos();
        if(this.elementos>0){
            verticesGraficables = new Lista<VerticeSVG>();
            svg+="<svg width='"+String.valueOf(arbol.getElementos()*220)+"' height='"+String.valueOf(arbol.altura()*250)+"'>\n";
            svg+="<g>\n";
            Cola<VerticeArbolBinario<Integer>> cola = new Cola<VerticeArbolBinario<Integer>>();
            VerticeArbolBinario<Integer> p = null;
            VerticeArbolBinario<Integer> hi=null;
            VerticeArbolBinario<Integer> hd=null;
            int balance =0;
            int ahi=0;
            int ahd=0;
            cola.mete(arbol.raiz());
            int contador =0;
            while(!cola.esVacia()){
                p = cola.saca();
                if(contador ==0){
                    VerticeSVG aux = new VerticeSVG(p.get(), (int)(this.elementos*220)/2, 50);
                    VerticeSVG aux2 = aux; 
                    verticesGraficables.agrega(aux);
                    try{
                        hi = p.izquierdo();
                        ahi = hi.altura();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(), (int)(this.elementos*220)/2, 50, true ,contador+1 ,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            ahd=hd.altura();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(), (int)(this.elementos*220)/2, 50, false, contador+2, aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ 
                            ahd = -1;
                        }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            ahi = -1;
                            hd = p.derecho();
                            ahd=hd.altura();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),(int)(this.elementos*220)/2,50,false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ 
                            ahd = -1;
                        }
                    }
                    aux2.convertirAvl();
                    aux2.setAltura(p.altura());
                    aux2.setBalance(ahi-ahd);
                    verticesGraficables.elimina(aux);
                    verticesGraficables.inserta(contador, aux2);
                    contador++;
                }
                else{
                    VerticeSVG aux = verticesGraficables.get(contador);
                    VerticeSVG aux2 = aux;  
                    try{
                        hi = p.izquierdo();
                        ahi = hi.altura();
                        cola.mete(hi);
                        verticesGraficables.agrega(new VerticeSVG(hi.get(),aux.getX(),aux.getY(),true,contador+1,aux.diferencia()));
                        try{
                            hd = p.derecho();
                            ahd = hd.altura();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),false,contador+2,aux.diferencia()));

                        }
                        catch(NoSuchElementException ns){ 
                            ahd = -1;
                        }
                    }
                    catch(NoSuchElementException nsee){
                        try{
                            ahi=-1;
                            hd = p.derecho();
                            ahd=hd.altura();
                            cola.mete(hd);
                            verticesGraficables.agrega(new VerticeSVG(hd.get(),aux.getX(),aux.getY(),false,contador+1,aux.diferencia()));

                        }
                        catch(NoSuchElementException nse){ 
                            ahd=-1;
                        }
                    }
                    aux2.convertirAvl();
                    aux2.setAltura(p.altura());
                    aux2.setBalance(ahi-ahd);
                    verticesGraficables.elimina(aux);
                    verticesGraficables.inserta(contador, aux2);
                    contador++;

                }
            }
        }
        else{
            svg+="<svg width='100' height='100'>\n";
            svg+="<g>\n";     
            svg+="</g>\n"+"</svg>\n";

        }
    }
/**
*Método para crear las aristas del arbol
*/
private void crearAristas(){
    Iterator iterador = verticesGraficables.iteradorLista();
    while(iterador.hasNext()){
        VerticeSVG aux = (VerticeSVG)iterador.next();
        svg+="<line x1=\""+String.valueOf(aux.getX())+"\" y1=\""+String.valueOf(aux.getY())+"\" x2=\""+String.valueOf(aux.getXpadre())+"\" y2=\""+String.valueOf(aux.getYpadre())+"\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />\n";           
    }
}
/**
*Método para crear los vertices del arbol
*/
private void crearVertices(){
    Iterator iterador = verticesGraficables.iteradorLista();
    while(iterador.hasNext()){
        VerticeSVG aux = (VerticeSVG)iterador.next();
        if(aux.getClaseArbol().equals("arbolRN")){
         if(aux.getColor() == Color.NEGRO)
            svg+="<circle cx=\""+String.valueOf(aux.getX())+"\" cy=\""+String.valueOf(aux.getY())+"\" r=\"40\" stroke=\"black\" stroke-width=\"3\" fill=\"black\" />\n";
        if(aux.getColor() == Color.ROJO)
         svg+="<circle cx=\""+String.valueOf(aux.getX())+"\" cy=\""+String.valueOf(aux.getY())+"\" r=\"40\" stroke=\"red\" stroke-width=\"3\" fill=\"red\" />\n";               
 }
 if(aux.getClaseArbol().equals("arbolB"))
    svg+="<circle cx=\""+String.valueOf(aux.getX())+"\" cy=\""+String.valueOf(aux.getY())+"\" r=\"40\" stroke=\"DodgerBlue\" stroke-width=\"3\" fill=\"aqua\" />\n";
if(aux.getClaseArbol().equals("arbolAVL"))
    svg+="<circle cx=\""+String.valueOf(aux.getX())+"\" cy=\""+String.valueOf(aux.getY())+"\" r=\"40\" stroke=\"MediumSpringGreen \" stroke-width=\"3\" fill=\"BlueViolet \" />\n";
}
}
/**
*Método para crear los elementos de los vertices del arbol.
*/
private void crearElementos(){
    Iterator iterador = verticesGraficables.iteradorLista();
    while(iterador.hasNext()){
        VerticeSVG aux = (VerticeSVG)iterador.next();
        if(aux.getClaseArbol().equals("arbolRN"))
            svg+="<text x=\""+String.valueOf(aux.getX()-12)+"\" y=\""+String.valueOf(aux.getY()+15)+"\" fill=\"white\" font-size='50'>"+String.valueOf(aux.elemento())+"</text>\n";
        if(aux.getClaseArbol().equals("arbolB"))
            svg+="<text x=\""+String.valueOf(aux.getX()-12)+"\" y=\""+String.valueOf(aux.getY()+15)+"\" fill=\"red\" font-size='50'>"+String.valueOf(aux.elemento())+"</text>\n";
        if(aux.getClaseArbol().equals("arbolAVL")){
            svg+="<text x=\""+String.valueOf(aux.getX()-12)+"\" y=\""+String.valueOf(aux.getY()+15)+"\" fill=\"white\" font-size='50'>"+String.valueOf(aux.elemento())+"</text>\n";
            svg+= "<text x=\""+String.valueOf(aux.getX()+20)+"\" y=\""+String.valueOf(aux.getY())+"\" fill=\"white\" font-size='10'>"+String.valueOf(aux.getAltura())+"\\"+String.valueOf(aux.getBalance())+"</text>\"\n";   
        }
    }
}
/**
*Método que regresa la representacion en cadena del SVG
*@return svg String cadena del SVG
*/
public String svg(){
    if(elementos>0){
        crearAristas();
        crearVertices();
        crearElementos();
        svg+="</g>\n"+"</svg>\n";
        return this.svg;
    }
    else
        return "No ingresaste números";
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