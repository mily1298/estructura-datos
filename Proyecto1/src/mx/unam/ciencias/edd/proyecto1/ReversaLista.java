package mx.unam.ciencias.edd.proyecto1;
public class ReversaLista{
    /**
     *Método que busca la bandera "-r" y nos dice si esta o no 
     *en nuestros argumentos.
     *@param args String[] arreglo de String que son los argumentos recibidos del Main.
     *@return true o false si esta o no la bandera dentro de nuestros argumentos.
     */
    public Boolean buscaR(String[] args){
        for (int i=0;i<args.length ;i++) {
            if(args[i].equals("-r")){
                args[i]=args[args.length-1];
                args[args.length-1]= "-r";
                return true;
            }

        }
        return false;
    }
}