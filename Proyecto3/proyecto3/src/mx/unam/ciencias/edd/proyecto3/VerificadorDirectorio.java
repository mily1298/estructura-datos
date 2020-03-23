package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.io.File;
public class VerificadorDirectorio{

    /** String donde se encontrara el argumento despues de la bande -o en caso de ser encontrada */
    private String direccion;

    /** File donde se encontrara la ruta obsuluta o relativa del directorio a manejar.*/
    private File directorio;

    /**Arreglo de argumentos acomodado */
    private String[] argumentos;
    /**
     *Método constructor para verificar directorio.
     *@param args String[] arreglo de argumentos recibidos por el programa.
     *@throws ExceptionInvalidArgument en caso de que el arreglo tenga menos a 3 argumentos. 
     *@throws ExceptionInvalidDirectory en caso de que no tenga un directorio en los argumentos 
     *o no tenga la bandera en los argumentos.
     */
    public VerificadorDirectorio(String[] args){
        argumentos = args;
    	if(argumentos.length <=2)
    		throw new ExceptionInvalidArgument();
    	for (int i=0;i<argumentos.length ; i++) {
    		if(argumentos[i].equals("-o") && i+1 <argumentos.length){
    			direccion = argumentos[i+1];
    			acomodar(i);
    			break;
    		}
    		if((argumentos[i].equals("-o") && i+1 >= argumentos.length) || (!argumentos[i].equals("-o") && i==argumentos.length-1))
    			throw new ExceptionInvalidDirectory();
    	}
    }
    /**
     *Método para acomodar las 2 entradas correspondiente a la bandera
     *y a la direccion correpiente dentro del arreglo de argumentos
     *@param a int entrada de la bandera dentro del arreglo
     *@param b int entrada de la direccion dentro del arreglo
     *@param aux String[] arreglo de argumentos.
     */
    private void acomodar(int a){
    	String aux1 = argumentos[a];
    	String aux2 = argumentos[a+1];
        argumentos[a] = argumentos[argumentos.length-1];
        argumentos[a+1] = argumentos[argumentos.length-2];
        argumentos[argumentos.length-1]=aux2;
        argumentos[argumentos.length-2]=aux1;

    }

    /**
     *Método que regresa los argumentos acomodados.
     *@return args String[] los argumentos.
     */
    public String[] argumentos(){
        return argumentos;
    }

    /**
     *Método que regresa la direccion dada en los parametros.
     *@return direccion String correspondiente a la dada
     */
    public String getDireccion(){
    	return directorio.getAbsolutePath();
    }

    /**
     *Método que regresa la direccion dada en los parametros.
     *@return directorio File correspondiente a directorio creado con
     * la direccion recibida en el argumento. 
     */
    public File getDirectorio(){
        return directorio;
    }
    /**
     *Método que verifica la direccion dada.
     */
    public void verifica(){
        if(direccion.contains("../")){
            File aux = new File("");
            direccion = direccion.substring(2,direccion.length());
            if(direccion.contains("."))
                throw new ExceptionInvalidDirectory();
            direccion = aux.getAbsolutePath() + direccion; 
        }
        if(direccion.contains(".")){
            directorio= null;
            throw new ExceptionInvalidDirectory();        
        }
        directorio = new File(direccion);
    }
    /**
     * Método que crea el directorio en caso de no existir.
     */
    public void comprobacion(){
        if(!directorio.exists())
            directorio.mkdirs();
    }
    /**
     *Método para eliminar el directorio en caso de que haya ocurrido un error.
     */
    public void kill(){
        directorio.delete();
    }

    /**
     *Método para obtener el String direccion
     *@return direccion String que contiene la direccion del directorio si es que la hay.
     */
    public String getDirec(){
        return direccion;
    }

}
