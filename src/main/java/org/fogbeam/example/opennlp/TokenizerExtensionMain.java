package org.fogbeam.example.opennlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.fogbeam.example.opennlp.io.FacadeReader;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Clase principal que se encarga de recibir por parámetros
 * los del número de ficheros, las definiciones de los ficheros y
 * el modelo que se va a emplear. Por ejemplo, 2 fichero1 fichero2 models/en-token.model
 * @author Alan
 *
 */
public class TokenizerExtensionMain {

	private final static int INDICE_DEFINICION_NUMERO_FICHEROS = 0;

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		InputStream modelIn = new FileInputStream(args[args.length - 1]);

		try {
			int numeroFicheros = getNumeroFicheros(args);
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);

			recorrerFicheros(getFileDefinition(args, numeroFicheros), tokenizer);

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if( modelIn != null ) {
				try {
					modelIn.close();
				}
				catch( IOException e ) {
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
	
	/**
	 * Proporciona el número de ficheros proporcionados como entrada
	 * @param args parámetros de entrada
	 * @return número de ficheros
	 */
	private static int getNumeroFicheros(String[] args) {
		return Integer.parseInt(args[INDICE_DEFINICION_NUMERO_FICHEROS]);
	}
	
	/**
	 * Extrae de los parámetros introducidos por el usuario las disitintas
	 * definiciones de los ficheros.
	 * @param args parámetros de entrada
	 * @param numeroFicheros
	 * @return conjunto de definiciones de fichero
	 */
	private static String[] getFileDefinition(String[] args, int numeroFicheros) {
		int from = INDICE_DEFINICION_NUMERO_FICHEROS + 1;
		int to = from + numeroFicheros;
		return Arrays.copyOfRange(args, from, to);
	}
	
	/**
	 * Muestra los tokens para cada fichero
	 * @param filesDefinition definiciones de los problemas
	 * @param tokenizer
	 */
	private static void recorrerFicheros(String[] filesDefinition, Tokenizer tokenizer) {
		for(int i = 0; i < filesDefinition.length; i++) {
			String[] tokens = tokenizer.tokenize(FacadeReader.readAllBytes(filesDefinition[i]));
			System.out.println(filesDefinition[i]);
			for(String token : tokens)
				System.out.println(token);
		}
	}

}
