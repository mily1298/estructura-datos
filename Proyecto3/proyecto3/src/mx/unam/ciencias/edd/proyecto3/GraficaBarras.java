package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;

public class GraficaBarras{
    /**Número total de palabras  */
	private int elementos;

    /**String con el código svg de la grafica */
	private String svg = "";

    /**String[] con el color en hexadesimal en cada entrada del arreglo */
	private String[] colores = {"#90EE90","#0140CA", "#7FFF00", "#16A6FE", "#DD1812"};
	

    /**
     *Método constructor de Grafica de Barras
     *a Lista<Palabra> lista de palabras con sus respectivas repeticiones y ordenada
     *n int número de palabras del documento.
     */
	public GraficaBarras(Lista<Palabra> a, int n){
        elementos = n;
        IteradorLista<Palabra> iterador = a.iteradorLista();
        iterador.end();
		int veces = a.getElementos()-5;
        double porcentajeUsado=0;
        double altura =0;
        double porcentaje=0;
        Palabra aux=null;
        double barra1=0;
        svg+="<center>\n";

		switch(veces){
            case -4:
                svg+="<svg width=\"90\" height=\"250\">\n";
                svg+="<g>\n";
                svg+="<line x1=\"20\" y1=\"10\" x2=\"20\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<line x1=\"20\" y1=\"240\" x2=\"90\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<text x=\"0\" y=\"245\" fill=\"black\" font-size=\"12\">0</text>\n";
                svg+="<text x=\"0\" y=\"120\" fill=\"black\" font-size=\"12\">"+(int)Math.floor(n/2)+"</text>\n";
                svg+="<text x=\"0\" y=\"10\" fill=\"black\" font-size=\"12\">"+n+"</text>\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                barra1 = porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"30\" y=\"0\" rx=\"8\" ry=\"8\" width=\"50\" height=\"240\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";
            break;
            case -3:
                svg+="<svg width=\"160\" height=\"250\">\n";
                svg+="<g>\n";
                svg+="<line x1=\"20\" y1=\"10\" x2=\"20\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<line x1=\"20\" y1=\"240\" x2=\"160\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<text x=\"0\" y=\"245\" fill=\"black\" font-size=\"12\">0</text>\n";
                svg+="<text x=\"0\" y=\"120\" fill=\"black\" font-size=\"12\">"+(int)Math.floor(n/2)+"</text>\n";
                svg+="<text x=\"0\" y=\"10\" fill=\"black\" font-size=\"12\">"+n+"</text>\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                barra1 = porcentaje;
                altura = altura(porcentaje);
                if(porcentaje>50)
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";
                else
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#0140CA;stroke:#0140CA;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                svg+="<rect x=\"95\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#7FFF00;stroke:#7FFF00;stroke-width:1;\" />\n";

            break;
            case -2:
                svg+="<svg width=\"220\" height=\"250\">\n";
                svg+="<g>\n";
                svg+="<line x1=\"20\" y1=\"10\" x2=\"20\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<line x1=\"20\" y1=\"240\" x2=\"220\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<text x=\"0\" y=\"245\" fill=\"black\" font-size=\"12\">0</text>\n";
                svg+="<text x=\"0\" y=\"120\" fill=\"black\" font-size=\"12\">"+(int)Math.floor(n/2)+"</text>\n";
                svg+="<text x=\"0\" y=\"10\" fill=\"black\" font-size=\"12\">"+n+"</text>\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                barra1 = porcentaje;
                altura = altura(porcentaje);
                if(porcentaje>50)
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";
                else
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#0140CA;stroke:#0140CA;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"95\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#7FFF00;stroke:#7FFF00;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"160\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#16A6FE;stroke:#16A6FE;stroke-width:1;\" />\n";

            break;
            case -1:
                svg+="<svg width=\"285\" height=\"250\">\n";
                svg+="<g>\n";
                svg+="<line x1=\"20\" y1=\"10\" x2=\"20\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<line x1=\"20\" y1=\"240\" x2=\"285\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<text x=\"0\" y=\"245\" fill=\"black\" font-size=\"12\">0</text>\n";
                svg+="<text x=\"0\" y=\"120\" fill=\"black\" font-size=\"12\">"+(int)Math.floor(n/2)+"</text>\n";
                svg+="<text x=\"0\" y=\"10\" fill=\"black\" font-size=\"12\">"+n+"</text>\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                barra1 = porcentaje;
                altura = altura(porcentaje);
                if(porcentaje>50)
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";
                else
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#0140CA;stroke:#0140CA;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"95\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#7FFF00;stroke:#7FFF00;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"160\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#16A6FE;stroke:#16A6FE;stroke-width:1;\" />\n";

                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"225\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#DD1812;stroke:#DD1812;stroke-width:1;\" />\n";
            
            break;
            default:
                svg+="<svg width=\"350\" height=\"250\">\n";
                svg+="<g>\n";
                svg+="<line x1=\"20\" y1=\"10\" x2=\"20\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<line x1=\"20\" y1=\"240\" x2=\"350\" y2=\"240\" stroke-width=\"1\" stroke=\"gray\"/>\n";
                svg+="<text x=\"0\" y=\"245\" fill=\"black\" font-size=\"12\">0</text>\n";
                svg+="<text x=\"0\" y=\"120\" fill=\"black\" font-size=\"12\">"+(int)Math.floor(n/2)+"</text>\n";
                svg+="<text x=\"0\" y=\"10\" fill=\"black\" font-size=\"12\">"+n+"</text>\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                barra1 = porcentaje;
                altura = altura(porcentaje);
                if(porcentaje>50)
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";
                else
                    svg+="<rect x=\"30\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\"  style=\"fill:#0140CA;stroke:#0140CA;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"95\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#7FFF00;stroke:#7FFF00;stroke-width:1;\" />\n";
                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"160\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#16A6FE;stroke:#16A6FE;stroke-width:1;\" />\n";

                aux =iterador.previous();
                porcentaje = porcentaje(aux.repeticiones);
                porcentajeUsado+=porcentaje;
                altura = altura(porcentaje);
                svg+="<rect x=\"225\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#DD1812;stroke:#DD1812;stroke-width:1;\" />\n";

                altura = altura(100-porcentajeUsado);
                if(barra1 >50)
                    svg+="<rect x=\"290\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#0140CA;stroke:#0140CA;stroke-width:1;\" />\n";
                else
                    svg+="<rect x=\"290\" y=\""+calcY(altura)+"\" rx=\"8\" ry=\"8\" width=\"50\" height=\""+altura+"\" style=\"fill:#90EE90;stroke:#90EE90;stroke-width:1;\" />\n";

            break;
        }

        svg+="</g>\n";
        svg+="</svg>\n";
        svg+="</center>\n";
	}

    /**
     *Método para calcular el porcentaje dado un numero de repeticiones de una palabra.
     *@param n int número de repeticiones de la palabra
     *@return porcentaje double dado el número de repetiones dado.
     */
    private double porcentaje(int n){
        return (n*100)/elementos;
    }

    /**
     *Método para obtener la altura dado el porcentaje de la palabra
     *@param porcentaje double pocentaje correspondiente de la palabra.
     *@return altura double correspondiente al porcentaje dado.
     */
    private double altura(double porcentaje){
        return (porcentaje*240)/100;
    }

    /**
     *Método para palcular la coordenada y apartir de la altura.
     *@param altura double altura para calcular la coordenada.
     *@return y Double correspondiente a la coordenada.
     */
    private double calcY(double altura){
        return 240-altura;
    }

    /**
     *Método que regresa el svg de la grafica de barras
     *@return svg String con el codigo SVG de la grafica.
     */
    public String getSVG(){
        return svg;
    }

}
