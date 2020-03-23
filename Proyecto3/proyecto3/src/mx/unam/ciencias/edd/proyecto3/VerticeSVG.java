package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
public class VerticeSVG{
	private int coorX;
	private int coorY;
	private int xPadre;
	private int yPadre;
	private Color color;
	private Palabra elemento;
	private int indice;
	private int balance;
	private int altura;
	private String arbol;
	private boolean esIzquierdo;
	private int diferencia;
	/**
	*Constructor para vertices de arboles ordenado y completo
	*@param elemento int es el elemento del vertice
	*@param xPadre int coordenada en x del padre
	*@param yPadre int coordenada en y del padre
	*@param esIzquierdo boolean si es un vertice izquierdo o no con respecto del padre
	*@param indice int el indice que tendra dentro de una lista
	*@param diferencia int  
	*/
	public VerticeSVG(Palabra elemento, int xPadre, int yPadre, boolean esIzquierdo, int indice, int diferencia){
		this.xPadre = xPadre;
		this.yPadre = yPadre;
		this.elemento= elemento;
		this.arbol = "arbolB";
		this.esIzquierdo= esIzquierdo;

		if(esIzquierdo){
			this.coorX = xPadre-diferencia;
			this.coorY = (int)this.yPadre+120;
		}
		else{
			this.coorX = this.xPadre+diferencia;
			this.coorY = (int)this.yPadre+120;
		}

		this.color = Color.NINGUNO;
		this.indice = indice;
		this.balance =0;
		this.altura = 0;
		this.esIzquierdo = esIzquierdo;
		this.diferencia = (int)(diferencia/2);
	}

	/**
	*Constructor raiz de arbol ordenado y completo
	*@param elemento int es el elemento del vertice
	*@param x int coordenada en x a asignar al vertice
	*@param y int coordenada en y a asignar al vertice
	*/
	public VerticeSVG(Palabra elemento, int x, int y){
		this.xPadre = x;
		this.yPadre = y;
		this.coorX = x;
		this.coorY = y;
		this.elemento = elemento;
		this.indice=0;
		this.arbol = "arbolB";
		this.color = Color.NINGUNO;
		this.balance =0;
		this.altura = 0;
		this.diferencia = (int)(x/2);
	}
	/**
	*Constructor raiz de arbol rojinegro
	*@param elemento int es el elemento del vertice
	*@param x int coordenada en x a asignar al vertice
	*@param y int coordenada en y a asignar al vertice
	*@param color Color del vertice
	*/
	public VerticeSVG(Palabra elemento, int x, int y, Color color){
		this.xPadre = x;
		this.yPadre = y;
		this.coorX = x;
		this.coorY = y;
		this.elemento = elemento;
		this.color = color;
		this.indice=0;
		this.arbol = "arbolRN";
		this.balance =0;
		this.altura = 0;
		this.diferencia = (int)(x/2);
	}
	/**
	*Constructor raiz de arbol AVL
	*@param elemento int es el elemento del vertice
	*@param xPadre int coordenada en x del padre
	*@param yPadre int coordenada en y del padre
	*@param altura int altura del vertice
	*@param balance int entero correspondiente al balance del vertice
	*/
	public VerticeSVG(Palabra elemento, int x, int y, int altura, int balance){
		this.xPadre = x;
		this.yPadre = y;
		this.coorX = x;
		this.coorY = y;
		this.altura = altura;
		this.balance = balance;
		this.elemento = elemento;
		this.indice=0;
		this.arbol = "arbolAVL";
		this.color = Color.NINGUNO;
		this.diferencia = (int)(x/2);
	}
	/**
	*Contructor para vertices de arboles RojiNegros
	*@param elemento int es el elemento del vertice
	*@param xPadre int coordenada en x del padre
	*@param yPadre int coordenada en y del padre
	*@param color Color color del vertice
	*@param esIzquierdo boolean si es un vertice izquierdo o no con respecto del padre
	*@param indice int el indice que tendra dentro de una lista
	*@param diferencia int  
	*/
	public VerticeSVG(Palabra elemento, int xPadre, int yPadre, Color color,boolean esIzquierdo, int indice, int diferencia){
		this.xPadre = xPadre;
		this.yPadre = yPadre;
		this.elemento= elemento;
		this.color = color;
		this.arbol = "arbolRN";
		if(esIzquierdo){
			this.coorX = xPadre-diferencia;
			this.coorY = this.yPadre+120;
		}
		else{
			this.coorX = this.xPadre+diferencia;
			this.coorY = this.yPadre+120;
		}
		this.indice = indice;
		this.balance =0;
		this.altura = 0;
		this.esIzquierdo = esIzquierdo;
		this.diferencia = (int)(diferencia/2);
	}
	/**
	*Contructor de vertice SVG para un vertice arbolAVL
	*@param elemento int es el elemento del vertice
	*@param xPadre int coordenada en x del padre
	*@param yPadre int coordenada en y del padre
	*@param altura int altura del vertice
	*@param balance int balance del vertice
	*@param esIzquierdo boolean si es un vertice izquierdo o no con respecto del padre
	*@param indice int el indice que tendra dentro de una lista
	*@param diferencia int  
	*/
	public VerticeSVG(Palabra elemento, int xPadre, int yPadre, int altura,int balance,boolean esIzquierdo, int indice, int diferencia){
		this.xPadre = xPadre;
		this.yPadre = yPadre;
		this.elemento= elemento;
		this.arbol = "arbolAVL";
		this.altura = altura;
		this.balance = balance;
		if(esIzquierdo){
			this.coorX = xPadre-diferencia;
			this.coorY = this.yPadre+120;
		}
		else{
			this.coorX = this.xPadre+diferencia;
			this.coorY = this.yPadre+120;
		}
		this.color = Color.NINGUNO;
		this.indice = indice;
		this.esIzquierdo = esIzquierdo;
		this.diferencia = (int)(diferencia/2);
	}
	/**
	*Método para obtener la cordenada x del vértice
	*@return coorX int correspondiente a la coordenada en x del vértice
	*/
	public int getX(){
		return this.coorX;
	}
	/**
	*Método que regresa si el vértice es izquierdo o no con respecto del padre
	*@return esIzquiero boolean true o false dependiendo de lo asignado en el constructor
	*/
	public boolean izquierdo(){
		return this.esIzquierdo;
	}
	/**
	*Método para obtener la cordenada y del vértice
	*@return coorY int correspondiente a la coordenada en y del vértice
	*/
	public int getY(){
		return this.coorY;
	}
	/**
	*Método para obtener la coordenada en X del vértice Padre
	*@return xPadre int correspondiente a la coordenada en X del vértice padre
	*/
	public int getXpadre(){
		return this.xPadre;
	}
	/**
	*Método para obtener la coordenada en Y del vértice Padre
	*@return xPadre int correspondiente a la coordenada en X del vértice padre
	*/
	public int getYpadre(){
		return this.yPadre;
	}
	/**
	*Método para obtener el elemento del vértice
	*@return elemento int correspondiente al elemento del vértice.
	*/
	public Palabra elemento(){
		return this.elemento;
	}
	/**
	*Método para obtener el color del vértice
	*@return color Color correspondiente al color del vértice
	*/
	public Color getColor(){
		return this.color;
	}
	/**
	*Método para obtener la altura del vértice
	*@return altura int altura del vértice
	*/
	public int getAltura(){
		return this.altura;
	}
	/**
	*Método para obtener el balance del vértice
	*@return balance int balance del vértice
	*/
	public int getBalance(){
		return this.balance;
	}
	/**
	*Método para modificar la altura del vertice
	*@param altura int nueva altura a asignar al vértice
	*/
	public void setAltura(int altura){
		this.altura = altura;
	}
	/**
	*Método para modificar el balance del vértice
	*@param balnce int nuevo balance a asignar al vértice.
	*/
	public void setBalance(int balance){
		this.balance = balance;
	}
	/**
	*Método para modificar la clase de cualquier vértice SVG a uno arbolAVL 
	*/
	public void convertirAvl(){
		this.arbol = "arbolAVL";
	}
	/**
	*Método para obtener la clase arbol a la que corresponde el vértice
	*@return arbol String clase arbol del vértice
	*/
	public String getClaseArbol(){
		return this.arbol;
	}
	/**
	*Método para obtener el indice del vértice
	*@return indice int indice del vértice
	*/
	public int getIndice(){
		return this.indice;
	}
	/**
	*Método para obtener la diferencia de distancia entre vértices.
	*@return diferencia int diferencia de distancia del vértice.s
	*/
	public int diferencia(){
		return this.diferencia;
	}
}
