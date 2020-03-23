package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
public class CreadorHTML{
	/** String donde ira la estructura HTML*/
	private String estructura;

    /**
     *Método constructor sin parametros para crear el codigo HTML.
     */
	public CreadorHTML(){
		estructura = "<!DOCTYPE html>\n"+
		"<html>\n"+
		"<head>\n"+
		"<title>Proyecto 3</title>\n"+
        "<meta charset=\"UTF-8\">\n"+
		"<style>\n"+
        "div.arboles{\n"+
        "width: 600px;\n"+
        "height: 400px;\n"+
        "border:1px solid #ccc;\n"+
        "overflow: scroll;\n"+
        "}\n"+
        "::-webkit-scrollbar {\n"+
        "width: 12px;\n"+
        "height: 12px;\n"+
        "}\n"+
        "::-webkit-scrollbar-track {\n"+
        "border: 1px solid yellowgreen;\n"+
        "border-radius: 10px;\n"+
        "}\n"+
        "::-webkit-scrollbar-thumb {\n"+
        "background: yellowgreen;\n"+ 
        "border-radius: 10px;\n"+
        "}\n"+
        "div.botones {\n"+
        "\twidth: 250px\n;"+
        "\tbox-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)\n;"+
        "\ttext-align: center;\n"+
        "}\n"+
        "table {\n"+
        "\twidth:80%\n"+
        "\tborder-collapse: collapse;\n"+
        "\tborder: 2px solid gray;\n"+    
        "\tborder-radius: 12px;\n"+
        "}\n"+
        ".button {\n"+
        "\tborder:none;\n"+
        "\tborder-radius: 12px;\n"+
        "\tcolor: black;\n"+
        "\tpadding: 15px 32px;\n"+
        "\ttext-align: center;\n"+
        "\ttext-decoration: none;\n"+
        "\tdisplay: inline-block;\n"+
        "\tfont-size: 16px;\n"+
        "\tmargin: 4px 2px;\n"+
        "\tcursor: pointer;\n"+
        "\t-webkit-transition-duration: 0.4s;\n"+
        "\ttransition-duration: 0.4s;\n"+
        "}\n"+
        ".button:hover {\n"+
        "\tbox-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);\n"+
        "}\n"+
        "</style>\n"+
        "</head>\n"+
        "<body>\n"+
        "<center>\n"+
        "<h1 style=\"color:#1E90FF;\">Proyecto 3</h1>";

	}

    /**
     *Método para ponerle el nombre en la pagina y tambien el numero de palabras.
     *@param nombre String nombre a colorcar en el HTML
     *@param palabras Integer número de palabras del archivo.
     */
    public void nombreArchivo(String nombre, int palabras){
        estructura+="<h3 style=\"text-align: left; color:#6A5ACD;\"> Archivo: "+nombre+".txt</h3>\n";
        estructura+="<h4 style=\"text-align:left; color:#708090;\"># Palabras = "+palabras+"</h4>\n";
        estructura+="<h3 style=\"color:#663399;\">Gráficas</h3>\n";
        estructura+= "<table>\n"+
        "\t<tr>\n"+
        "\t\t<th>\n";
    }

    /**
     *Método para anexar el svg correspondiente a la grafica de pastel 
     *@param svg String codigo svg de la grafica de pastel.
     */
	public void agregarGraficaPastel(String svg){
		estructura+=svg;
		estructura+="\t\t</th>\n"+
        "<th rowspan=\"2\">"+
        "<div class=\"botones\">\n";
    }

    /**
     *Método para agregar botones de color de la simbologia de la gráfica
     *asi como tambien su porcentaje.
     *@param a Lista<Palabra> lista ordenada para sacar las palabras usadas en las graficas
     *@paran n int número de palabras del archivo.
     */
    public void insertaBotones(Lista<Palabra> a, int n){
        IteradorLista<Palabra> iterador = a.iteradorLista();
        Palabra auxiliar=null;
        iterador.end();
        double aux=0;
        double usado=0;
        int veces = a.getElementos()-5;
        String[] colores ={"#90EE90","#0140CA", "#7FFF00", "#16A6FE", "#DD1812"};
        switch(veces){
            case -4:
                auxiliar=iterador.previous();
                estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
            break;
            case -3:
                auxiliar=iterador.previous();
                aux = porcentaje(auxiliar.repeticiones,n);
                if(aux> 50)
                     estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                else
                     estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 
            break;
            case -2:
                auxiliar=iterador.previous();
                aux = porcentaje(auxiliar.repeticiones,n);
                if(aux> 50)
                     estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                else
                     estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }

            break;
            case -1:
                auxiliar=iterador.previous();
                aux = porcentaje(auxiliar.repeticiones,n);
                if(aux> 50)
                     estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                else
                     estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                                 if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }

            break;
            case 0:
                auxiliar=iterador.previous();
                aux = porcentaje(auxiliar.repeticiones,n);
                if(aux> 50)
                     estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                else
                     estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#DD1812;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#DD1812;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#DD1812;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
            break;
            default:
                auxiliar=iterador.previous();
                aux = porcentaje(auxiliar.repeticiones,n);
                usado+=aux;
                if(aux > 50)
                     estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                else
                     estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", aux)+"% </button><br>\n";
                if(aux>50){
                    auxiliar=iterador.previous();
                    usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#0140CA;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                     usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#7FFF00;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                     usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#16A6FE;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                     usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#DD1812;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                if(aux>50){
                    auxiliar=iterador.previous();
                    usado+=porcentaje(auxiliar.repeticiones,n);
                    estructura+="<button class=\"button\" Style=\"background-color:#DD1812;\">"+auxiliar.getPalabra()+" "+String.format("%.2f", porcentaje(auxiliar.repeticiones,n))+"% </button><br>\n";
                 }
                 else {
                     auxiliar=iterador.previous();
                    estructura+="<button class=\"button\" Style=\"background-color:#90EE90;\">Otras Palabras "+String.format("%.2f", (100-usado))+"% </button><br>\n";
                 }
        }
        estructura+="</div>\n";
        estructura+="</th>\n"+
        "</tr>\n"+
        "<tr>\n"+
        "<td>\n";
    }

    /**
     *Método para agregar el codigo svg de la grafica de barras
     *@param barras String correspondiente al codigo svg de la grafica
     */
    public void agregarGraficaBarras(String barras){
        estructura+=barras;
        estructura+="</tr>\n";
        estructura+="</table>\n";
        estructura+="<br>\n";
        estructura+="<h3 style=\" color:#663399;\">Arbol AVL</h3>\n";
        estructura+="<div class=\"arboles\"\n>";
    }


    /**
     *Método para obtener el porcetanje dado el numero de repeticiones de una 
     *palabra y el numero total de palabras.
     *@param repeticiones int número de repeticiones de una palabra.
     *@param elementos int número de palabras total. 
     */
    private double porcentaje(int  repeticiones, int elementos){
        return(repeticiones*100)/elementos;
    }


    /**
     *Método para agregar el codigo SVG de un arbol AVL
     *@param arboles String codigo svg del arbol AVL.
     */
    public void agregarArbolAVL(String arboles){
        estructura+= arboles;
        estructura+="</div>\n";
        estructura+="<br>\n";
        estructura+="<h3 style=\"color:#663399;\">Arbol RojiNegro</h3>\n";
        estructura+="<div class=\"arboles\"\n>";
    }
    /**
     *Método para agregar el codigo SVG de un arbol Rojinegro
     *@param arboles String codigo svg del arbol Rojinegro.
     */
    public void agregarArbolRojinegro(String arboles){
        estructura+= arboles;
    }


    /**
     *Método para cerrar la estructura html del documento.
     */
	public void terminarEstructura(){
        estructura+="</div>\n";
        estructura+="</center>\n";
		estructura+="</body>\n";
		estructura+="</html>\n";
	}

    /**
     *Método que regresa la representacion en cadena html.
     *@return estructura String cadena que contiene el html.
     */
	public String toString(){
		return estructura;
	}
}