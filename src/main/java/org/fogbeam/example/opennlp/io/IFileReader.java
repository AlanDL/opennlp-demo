package org.fogbeam.example.opennlp.io;

import java.util.List;

/**
 * 
 * Interfaz que representa un lector de fichero general
 * independiente del origen del mismo
 * 
 * @author Alan
 *
 */

public interface IFileReader {
	
	/**
	 * 
	 * @param whereIs define donde se encuentra el archivo
	 * @return una lista con las líneas del fichero
	 */
	
	public List<String> read(String whereIs);

	/**
	 * 
	 * @param whereIs define donde se encuentra el archivo
	 * @return una variable String que contiene todo el fichero
	 */
	
	public String readAllBytes(String whereIs);
	
}
