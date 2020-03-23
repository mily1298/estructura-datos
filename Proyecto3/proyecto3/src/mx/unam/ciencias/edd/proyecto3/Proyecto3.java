package mx.unam.ciencias.edd.proyecto3;
import mx.unam.ciencias.edd.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.SecurityException;
import java.lang.reflect.Array;
import java.io.File;
public class Proyecto3{
	public static void main(String[] args)throws IOException, IllegalArgumentException{	
		ContadorPalabras[] prueba = (ContadorPalabras[])Array.newInstance(new ContadorPalabras().getClass(), args.length-2);
		Lector lector = new Lector(args.length-2);
		VerificadorDirectorio verificador = new VerificadorDirectorio(args);
		try{
			verificador.verifica();
		}
		catch(ExceptionInvalidDirectory eid){
			System.err.println("El directorio especificado no es valido");
			return;
		}
		catch(SecurityException se){
			System.err.println("Error de Acceso en el directorio proporcionado");
		}
		try{
			verificador.comprobacion();
		}
		catch(SecurityException seee){
			System.err.println("Error de Acceso para crear el directorio");
		}
		for (int i=0;i<args.length-2 ; i++) {
			try{lector.leerArchivo(verificador.argumentos()[i], i);}
			catch(IllegalArgumentException iae){
				System.err.println("tu archivo "+ verificador.argumentos()[i]+" esta vacío");
				return;
			}
			catch(NoSuchElementException nsee ){
				for (int j=0;j<verificador.argumentos().length ;j++ ) {
					System.out.println(verificador.argumentos()[j]);
				}
				return;
			}

		}
		File[] archivos = new File[args.length-2];	
		String[] nombres = new String[args.length-2];
		int[] cantidadPalabras = new int[args.length-2];
		@SuppressWarnings("unchecked") Diccionario<String,Integer>[] documentos = (Diccionario<String,Integer>[])Array.newInstance(new Diccionario<String,Integer>().getClass(), args.length-2);
		for (int i=0;i<args.length-2 ;i++ ) {
			try{
			String nombre = args[i];
			nombre = nombre.substring(0,nombre.indexOf("."));
			nombres[i]=nombre;
			prueba[i]= new ContadorPalabras();
			prueba[i].contadorRepeticiones(lector.getLista(i));
			documentos[i] = prueba[i].getDiccionario();
			PalabrasTexto lista = new PalabrasTexto(prueba[i].getDiccionario());
			cantidadPalabras[i] = prueba[i].nPalabras();
			GraficaPastel pastel = new GraficaPastel(lista.getLista(), prueba[i].nPalabras());
			GraficaBarras barras = new GraficaBarras(lista.getLista(), prueba[i].nPalabras());	
			CreadorArchivos creador = new CreadorArchivos(verificador.getDirectorio(),nombre);
			archivos[i]=creador.getFile();
			CreadorHTML html = new CreadorHTML();
			html.nombreArchivo(nombre, prueba[i].nPalabras());
			html.agregarGraficaPastel(pastel.getSVG());
			html.insertaBotones(lista.getLista(), prueba[i].nPalabras());
			html.agregarGraficaBarras(barras.getSVG());
			IteradorLista<Palabra> iterador = lista.getLista().iteradorLista();
			iterador.end();
			ArbolAVL<Palabra> avl = new ArbolAVL<Palabra>();
			ArbolRojinegro<Palabra> rojinegro = new ArbolRojinegro<Palabra>();
			for (int j=0;j<15 ;j++ ) {
				Palabra aux = iterador.previous();
				avl.agrega(aux);
				rojinegro.agrega(aux);
			}
			ConvertidorArboles arbolavl = new ConvertidorArboles(avl);
			ConvertidorArboles arbolrojinegro = new ConvertidorArboles(rojinegro);
			html.agregarArbolAVL(arbolavl.getSVG());
			html.agregarArbolRojinegro(arbolrojinegro.getSVG());
			html.terminarEstructura();
			creador.mete(html.toString());

			 	}
			catch(NoSuchElementException nssss){
				System.err.println("no tienes suficientes elementos para crear un arbol de 15 elementos");
				return;
			}
			catch(SecurityException see ){
				System.err.println("Error de Acceso en el directorio proporcionado");
			}
		}
		CreadorIndex index = new CreadorIndex();
		ConvertidorGrafica grafica = new ConvertidorGrafica(documentos,nombres);
		index.agregarBotones(archivos, nombres, cantidadPalabras);
		index.agregarGrafica(grafica.getSVG());
		index.terminarIndex();
		CreadorArchivos indexhtml = new CreadorArchivos(verificador.getDirectorio(),"index");
		indexhtml.mete(index.getHTML());
	}
}