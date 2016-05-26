package org.fogbeam.example.opennlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.fogbeam.example.opennlp.io.FacadeReader;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class TokenizerExtensionMain {

	private final static int INDICE_DEFINICION_FICHERO = 0;
	
	public static void main(String[] args) throws Exception {
		
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try {
			TokenizerModel model = new TokenizerModel(modelIn);
		
			Tokenizer tokenizer = new TokenizerME(model);
			
			String[] tokens = tokenizer.tokenize(FacadeReader.readAllBytes(args[INDICE_DEFINICION_FICHERO]));
			
			for(String token : tokens) {
				System.out.println( token );
			}
			
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
	
}
