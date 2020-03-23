package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.util.Iterator;
import java.io.File;
public class CreadorIndex{
	/** String donde se guardara el codigo html del index*/
	private String html="";

	/**
	 *Método constructor del index sin parametros.
	 */
	public CreadorIndex(){
		html = "<!DOCTYPE html>\n"+
		"<html>\n"+
		"<head>\n"+
		"<title>Proyecto 3</title>\n"+
		"<meta charset=\"UTF-8\">\n"+
		"<style>\n"+
		"div.grafica{\n"+
		"width: 60%;\n"+
		"height: 900px;\n"+
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
		"\twidth: 350px\n;"+
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
		"<h1 style=\"color:#1E90FF; text-align:center;\">Proyecto 3</h1>\n"+
		"<h4 style=\"text-align:left; color:#708090;\">Index</h4>"+
		"<div class=\"botones\">\n";
	}

	/**
	 *Método para crear los botones para ingresar a los archivos
	 *@param archivos File[] archivos con ruta absoluta.
	 *@param nombres String nombrede los archivos.
	 *@param cantidadPalabras int[] cantidad de palabras de cada archivo.
	 */
	public void agregarBotones(File[] archivos, String[] nombres, int[] cantidadPalabras){
		for (int i=0;i<archivos.length ;i++ )
			html+="<button class=\"button\" Style=\"background-color:#90EE90;\"><a href=\""+archivos[i].getAbsoluteFile()+"\">"+nombres[i]+"</a> N° Palabras :"+cantidadPalabras[i]+"</button><br>\n";
		html+="</div>\n";
		html+="<br>\n";
		html+="<br>\n";
		html+="<center>\n";
		html+="<h3 style=\"color:#708090;\">Gráfica de archivos</h3>\n";
		html+="<div class=\"grafica \">\n";
		html+="<center>\n";
	}

	/**
	*Método para agregar la grafica de los archivos al index.
	*@param grafica String cadena del svg de la grafica
	*/
	public  void agregarGrafica(String grafica){
		html+=grafica;
	}

	/**
	 *Método para cerrar el archivo html del index
	 */
	public void terminarIndex(){
		html+="</center>\n";
        html+="</div>\n";
        html+="</center>\n";
		html+="</body>\n";
		html+="</html>\n";
	}


	/**
	 *Método que regresa la cadena correspondiente al html
	 *@return html String cadena del html del index.
	 */
	public String getHTML(){
		return html;
	}

}