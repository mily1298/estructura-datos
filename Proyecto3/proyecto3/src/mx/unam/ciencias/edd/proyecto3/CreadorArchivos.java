package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CreadorArchivos{
	File archivo;

	/**
	 *Constructor para crear archivos html
	 *@param direccion File para poder sacar la direccion absoluta donde crear el archivo.
	 *@param nombre String nombre a dar al archivo.
	 */
	public CreadorArchivos(File direccion, String nombre){
		archivo = new File(direccion.getAbsolutePath()+"/"+nombre+".html");
		
	}
	/**
	 *Método para ingresar el codigo html al archivo ya creado.
	 *@param html String el codigo correspondiente a ingresar al archivo.
	 */
	public void mete(String html)throws IOException{
		BufferedWriter br = new BufferedWriter(new FileWriter(archivo));
		br.write(html);
		br.close();
	}

	/**
	 *Método que regresa el archivo File.
	 *@return archivo File nombre del archivo.
	 */
	public File getFile(){
		return archivo;
	}
}