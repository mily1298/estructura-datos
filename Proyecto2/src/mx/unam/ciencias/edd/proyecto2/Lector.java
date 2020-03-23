package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Lector{
    private Lista<String> lectura;
    /**
     *Método Constructor para Inicializar mi Lector sin Parametros
     */
    public Lector(){
        lectura = new Lista<String>();
    }
    /**
     *Método para determinar si el Lector es vacío
     */
    public boolean esVacio(){
        return lectura==null;
    }
    public Lista<String> getLista(){
        return this.lectura;
    }
    /**
     *Método para leer desde la consola e ir guardando lo leido en una lista.
     *@throws IOException la excepcion lanzada en caso de ocurrir un error al leer de la consola.
     */
    public void leerConsola()throws IOException{
        String cadena;
        try{
            BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
            while((cadena=leer.readLine())!=null){
                lectura.agrega(cadena);
            }
            leer.close();
        }
        catch(IOException nsee){
            System.err.println("Ocurrio un error");
            lectura=null;
            return;
        }
    }
    /**
     *Método para lee el contenido de un archivo y agregar el contenido a una lista.
     *@param archivo String el nombre del archivo a leer.
     *@throws FileNotFountException la excepcion lanzada en caso de que el archivo no exista.
     *@throws IOException la excepcion lanzada en caso de ocurrir un error al leer el archivo.
     */
    public void leerArchivo(String archivo)throws FileNotFoundException, IOException {
        String cadena;
        try{
            FileReader leer = new FileReader(archivo);
            BufferedReader leer2 = new BufferedReader(leer);
            while((cadena=leer2.readLine())!=null){
                lectura.agrega(cadena);
            }
            leer2.close();
        }
        catch(FileNotFoundException nsee){
            System.err.println(archivo+" "+" no existe");
            lectura=null;
            return;
        }
        catch(IOException nsee){
            System.err.println("Ocurrio un error");
            lectura=null;
            return;
        }
    }

}