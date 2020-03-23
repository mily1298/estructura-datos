package mx.unam.ciencias.edd.proyecto3.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.Iterator;
import java.util.NoSuchElementException;
import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.ContadorPalabras;
public class TestcontadorPalabras{
    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /** EL contador de palabras */
	private ContadorPalabras contador;

    /**
     * Crea un contador de palabras para cada test.
     */
    public TestcontadorPalabras() {
        contador = new ContadorPalabras();
    }
    /**
     * Prueba unitaria para {@link contadorPalabras#contadorPalabras}.
     */
    @Test public void testConstructor(){
        Assert.assertTrue(contador.esVacio());
        Assert.assertTrue(contador.elementos() == 0);
    }

    /**
     * Prueba unitaria para {@link ContadorPalabras#contadorRepeticiones}
     */
    @Test public void testContadorRepeticiones(){
        Lista<String> aux = new Lista<String>();
        String[] leido ={"hola sa ds sa ds das as ", "Hóla as ds as sasa", "comi gnf ggfbf df", "como dsfgn f "," c fdgghf bgnf fd, editar dfbgdf  ", "edítar fdbdf ", "cómo dfdf d"," comó dffd"};
        for(int i=0; i<leido.length; i++)
            aux.agrega(leido[i]);
        contador.contadorRepeticiones(aux);
        int aux1 = contador.nRepeticiones("hola");
        Assert.assertTrue(contador.nRepeticiones("as") == 3);
        Assert.assertFalse(contador.esVacio());
        Assert.assertFalse(contador.nRepeticiones("hola") == 7);
        String[] prueba = {
            "edd, Edd EdD, EDd, Édd Êdd  ",
            "test Test TEST TeST TËsT",
            "corre, corré córre coRRe córré CoRre"};
        for(int i =0; i<prueba.length; i++)
            aux.agrega(prueba[i]);
        contador.contadorRepeticiones(aux);
        Assert.assertFalse(contador.nRepeticiones("hola")==aux1);
        Assert.assertFalse(contador.nRepeticiones("edd")== 5);
        Assert.assertTrue(contador.nRepeticiones("TEST")== 5);
        Assert.assertTrue(contador.elementos() != 3);
    }
}