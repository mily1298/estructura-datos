package mx.unam.ciencias.edd.proyecto2;
import mx.unam.ciencias.edd.*;
public class Verificador{
	private Lista<String> l = new Lista<String>();
	private Lista<String> argumentos = new Lista<String>();
	private Lista<Integer> numeros=new Lista<Integer>();
	private String clase="";
	/**
	*Método contructor del verificador
	*@param lista Lista<String> lista de lo leido por el lector
	*/
	public Verificador(Lista<String> lista){
		for(String s : lista){
			this.l.agrega(s);
		}
	}
	/**
	*Método para obtener la lista de números recibidos por el lector
	*@return numeros Lista<Integer> lista de numeros leidos
	*/
	public Lista<Integer> getNumeros(){
		return this.numeros;
	}
	/**
	*Método para obtener la estructura recibida por el lector
	*@return clase String estructura de datos recibido
	*/
	public String getClase(){
		return this.clase;
	}
	/**
	*Método para verificar la estructura de datos
	*@throws ExcepcionClaseInvalida excepcion si es que no se encontro una clase valida en los argumentos
	*/
	public void verificaClase(){
		String[] aux1 = argumentos.getPrimero().split(" ");
		String aux = "";
		for (int i=0;i<aux1.length ; i++) {
			if(!aux1[i].isEmpty()){
				aux = aux1[i];
				break;}
			}
			switch(aux){
				case("pila"):
				clase = aux;
				break;
				case("cola"):
				clase = aux;
				break;
				case("lista"):
				clase = aux;
				break;
				case("arreglo"):
				clase = aux;
				break;
				case("arreglos"):
				clase = aux;
				break;
				case("arbolbinariocompleto"):
				clase = aux;
				break;
				case("arbolbinarioordenado"):
				clase = aux;
				break;
				case("arbolrojinegro"):
				clase = aux;
				break;
				case("arbolavl"):
				clase = aux;
				break;
				case("monticulominimo"):
				clase = aux;
				break;
				case("monticulosminimos"):
				clase = aux;
				break;
				case("grafica"):
				clase = aux;
				break;
				default:
				throw new ExcepcionClaseInvalida();
			}
		}
	/**
	*Método para eliminar comentarios
	*/
	public void commentKiller(){

		for(String s:l){ 
			int posicion= s.indexOf("#");
			String aux = s;
			if(posicion!=-1){
				s = s.substring(0,posicion).replaceAll("^\\s*","");
				if(!s.isEmpty())
					argumentos.agrega(s.toLowerCase());
			}
			else{
				s = s.replaceAll("^\\s*","");
				argumentos.agrega(s.toLowerCase());
			}
		}
	}
	/**
	*Método para saber si una cadena es un número
	*@param s String cadena a saber si es número
	*/
	private  boolean esNumero(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e){
			return false;                        
		}
	}
	/**
	*Método para convertir una cadena a un entero
	*@param a String cadena a convertir
	*@return aux int entero convertido a partir del String
	*/
	private int convertidorNumero(String a){
		int aux = 0;
		aux = Integer.parseInt(a);
		return aux;
		
	}
	/**
	*Método para saber si los argumentos leidos son validos para procesar
	*/
	public void chequeoArgumentos(){
		String[] sar;
		for(String s: argumentos){
			sar = s.split(" ");
			for(int i=0; i<sar.length;i++){
				if(esNumero(sar[i])){
					numeros.agrega(convertidorNumero(sar[i]));
				}
				else if (i!=0){
					throw new ExcepcionArgumentosInvalidos();
				}
			}
		}
		if(numeros.getElementos() ==0)
			throw new ExcepcionArgumentosInvalidos();
	}
}
