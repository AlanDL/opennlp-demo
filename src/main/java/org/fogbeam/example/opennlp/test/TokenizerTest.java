package org.fogbeam.example.opennlp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.fogbeam.example.opennlp.TokenizerExtensionMain;

import junit.framework.TestCase;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TokenizerTest extends TestCase {
	private String numeroFicheros = "2";
	private String fichero1 = "/home/alan/Escritorio/pruebaOpenNPL.txt";
	private String fichero2 = "/home/alan/Escritorio/pruebaOpenNPL.txt";
	private String modelo = "models/en-token.model";
	private String comandoEntradaTest = numeroFicheros + " " + fichero1 + " " + fichero2 + " " + modelo;
	private String[] args = comandoEntradaTest.split(" ");

	public void testParametrosEntradaNumeroFicheros() {

		int salida = TokenizerExtensionMain.getNumeroFicheros(args);
		assertEquals(2, salida);

	}

	public void testParametrosEntradaFicheros() {

		String[] salida = TokenizerExtensionMain.getFileDefinition(args, Integer.parseInt(numeroFicheros));
		String[] salidaEsperada = {
				fichero1,
				fichero2
		};
		for(int i = 0; i < salida.length; i++)
			assertTrue(salida[i].equals(salidaEsperada[i]));

	}

	public void testParametrosEntradaTokens() {
		String[] tokensEsperados = {
				
				"A" , 
				"ranger" , 
				"journeying" , 
				"with" , 
				"Oglethorpe" , 
				"," , 
				"founder" , 
				"of" , 
				"the" , 
				"Georgia" , 
				"Colony" , 
				"," , 
				"mentions" , 
				"\"three" , 
				"Mounts" , 
				"raised" , 
				"by" , 
				"the" , 
				"Indians" , 
				"over" , 
				"three" , 
				"of" , 
				"their" , 
				"Great" , 
				"Kings" , 
				"who" , 
				"were" , 
				"killed" , 
				"in" , 
				"the" , 
				"Wars" , 
				"." , 
				"\""
				
		};


		InputStream modelIn;
		try {
			modelIn = new FileInputStream(args[args.length - 1]);
			try {
				TokenizerModel model = new TokenizerModel(modelIn);
				Tokenizer tokenizer = new TokenizerME(model);
				String[] salida = TokenizerExtensionMain.getTokens(fichero1, tokenizer);
				assertEquals(salida.length, tokensEsperados.length);
				for(int i = 0; i < salida.length; i++)
					assertTrue(salida[i].equals(tokensEsperados[i]));

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
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}


	}

}
