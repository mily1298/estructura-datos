package mx.unam.ciencias.edd.proyecto3.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.Iterator;
import java.util.NoSuchElementException;
import mx.unam.ciencias.edd.*;
import java.io.File;
import mx.unam.ciencias.edd.proyecto3.VerificadorDirectorio;
import mx.unam.ciencias.edd.proyecto3.ExceptionInvalidDirectory;

public class TestVerificadorDirectorio{
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

	/** un objeto verificador Directorio*/
	 private VerificadorDirectorio verificador;

     /**Arreglo para simular los parametros*/
	 private String[] auxiliar = new String[10];;

	 /**
	  *Crea un verificador Directorio para las pruebas unitarias.
	  */
	 public TestVerificadorDirectorio(){
	 	auxiliar[0] =  "archivo1.txt";
	 	auxiliar[1] =  "archivo2.txt";
	 	auxiliar[2] =  "archivo3.txt";
	 	auxiliar[3] =  "archivo4.txt";
	 	auxiliar[4] =  "archivo5.txt";
	 	auxiliar[5] =  "archivo6.txt";
	 	auxiliar[6] =  "archivo7.txt"; 
	 	auxiliar[7] = "-o";
	 	auxiliar[8] =  "../proyectoprueba/"; 
	 	auxiliar[9] =   "archivo8.txt";
	 	verificador = new VerificadorDirectorio(auxiliar);

	 }
	 /**
      * Prueba unitaria para {@link VerificadorDirectorio#VerificadorDirectorio}.
      */
	 @Test public void testConstructor(){
	 	Assert.assertTrue(!verificador.getDirec().equals(""));
	 	Assert.assertTrue("../proyectoprueba/".equals(verificador.getDirec()));
    Assert.assertTrue("../proyectoprueba/".equals(verificador.argumentos()[auxiliar.length-1]));	 
   }

	 /**
	  * Prueba unitaria para {@link VerificadorDirectorio/verifica}.
	  */
     @Test public void testVerifica(){
        String[] aux1 = new String[10];
        aux1[0] =  "archivo1.txt";
        aux1[1] =  "archivo2.txt";
        aux1[2] =  "archivo3.txt";
        aux1[3] =  "archivo4.txt";
        aux1[4] =  "archivo5.txt";
        aux1[5] =  "archivo6.txt";
        aux1[6] =  "archivo7.txt"; 
        aux1[7] = "-o";
        aux1[8] = "/home/archivo.txt";
        aux1[9] =   "archivo8.txt";
        verificador = new VerificadorDirectorio(aux1);
        try{
            verificador.verifica();
        }
        catch(ExceptionInvalidDirectory eid){
            Assert.assertFalse(verificador.getDirectorio()!=null);
        }
     }

     /**
      * Prueba unitaria para{@link VerificadorDirectorio/getDireccion}
      */
     @Test public void testGetDireccion(){
      Assert.assertFalse("/home/archivo.txt".equals(verificador.getDirec()));
      
     }
}	  