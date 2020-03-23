package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
public class Punto{
    /**Coordenada x del punto */
    private double x;

    /**Coordenada y del punto */
    private double y;

    /**
     *Método para crear un punto.
     *@param x double coordenada x.
     *@param y double coordenada y;
     */
    public Punto(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     *Método para modificar lar coordenadas del punto.
     *@param x double coordenada x;
     *@param y double coordenada y;
     */
    public void setCoor(double x, double y){
        this.x=x;
        this.y=y;
    }

    /**
     *Metodo para obtener la coordenada X
     *@return x double coordenada x
     */
    public double X(){
        return x;
    }

    /**
     *Método para obtener la coordenada Y
     *@return y double coordenada y.
     */
    public double Y(){
        return y;
    }

}