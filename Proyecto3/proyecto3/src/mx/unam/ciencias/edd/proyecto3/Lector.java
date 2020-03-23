package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
public class Lector{
    /** Lista de String donde se guarda lo leido de cada archivo. */
    private Lista<String>[] archivos;
    /**
     *Método Constructor para Inicializar mi Lector sin Parametros
     */
    public Lector(int n ){
        archivos = nuevoArreglo(n+1);
    }

    /**
     *Método que regresa el número de archivos leidos
     *@return longitud del arreglo de listas
     */
    public int nArchivos(){
        return archivos.length;
    }

    /* Truco para crear un arreglo genérico. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
       @SuppressWarnings("unchecked")
       private Lista<String>[] nuevoArreglo(int n) {
        return (Lista<String>[])
        Array.newInstance(new Lista().getClass(), n);
      }    

    /**
     *Método para determinar si la n entrada del arreglo de listas es vacio
     *@param n int el numero de la entrada.
     *@return boolean true si es vacio, false en otro caso.
     */
    public boolean esVacio(int n){
        return archivos[n]==null;
    }

    /**
     *Método para obtener una lista<String> de la n-esima entrada.
     *@param n int n-esima entrada del arreglo de listas.
     *@return Lista<String> de la n-esima entrada.
     */
    public Lista<String> getLista(int n){
        return this.archivos[n];
    }
    /**
     *Método para lee el contenido de un archivo y agregar el contenido a una lista.
     *@param archivo String el nombre del archivo a leer.
     *@throws FileNotFountException la excepcion lanzada en caso de que el archivo no exista.
     *@throws IOException la excepcion lanzada en caso de ocurrir un error al leer el archivo.
     */
    public void leerArchivo(String archivo, int n)throws FileNotFoundException, IOException {
        String cadena;
        try{
            FileReader leer = new FileReader(archivo);
            BufferedReader leer2 = new BufferedReader(leer);
            archivos[n] = new Lista<String>();
            while((cadena=leer2.readLine())!=null){
                archivos[n].agrega(cadena);
            }
            leer2.close();
            if(archivos[n].getElementos()==0)
                throw new IllegalArgumentException();
        }
        catch(FileNotFoundException nsee){
            System.err.println(archivo+" "+" no existe");
            archivos[n]=null;
            throw new NoSuchElementException();
        }
        catch(IOException nsee){
            System.err.println("Ocurrio un error");
            archivos[n]=null;
            throw new IOException();

        }
    }

}