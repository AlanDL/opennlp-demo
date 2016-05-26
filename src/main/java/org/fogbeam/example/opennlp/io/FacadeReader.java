package org.fogbeam.example.opennlp.io;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * Clase que cumple la función de Fachada (patrón Facade) entre
 * el usuario y el resto de métodos que permiten leer ficheros
 * de diferentes lugares, fichero local, en un ftp, en la web, etc
 * 
 * @author Alan
 *
 */

public class FacadeReader {

	private static final Pattern PATRON_FICHERO_LOCAL_LINUX = Pattern.compile("^(/.+)+$");
	private static final Pattern PATRON_FICHERO_LOCAL_WINDOWS = Pattern.compile("^(/.+)+$");
	
	/**
	 * 
	 * Proporciona todo el fichero en una misma variable String
	 * 
	 * @param whereIs es la definición que indica donde
	 * se encuentra el fichero
	 * @return el fichero completo como un String
	 */
	
	public static String readAllBytes(String whereIs) {
		IFileReader fileReader = read(whereIs);
		
		return fileReader.readAllBytes(whereIs);
	}
	
	/**
	 * 
	 * Proporciona el fichero como un conjunto de líneas
	 * ordenado
	 * 
	 * @param whereIs es la definición que indica donde
	 * se encuentra el fichero
	 * @return el fichero como un conjunto de String, donde cada
	 * elemento de la lista representa una línea del fichero
	 */
	
	public static List<String> readLines(String whereIs) {
		IFileReader fileReader = read(whereIs);
		
		return fileReader.read(whereIs);
	}
	
	/**
	 * 
	 * Permite determinar, en base a los patrones y a la definición
	 * proporcionada por el usuario, el tipo de FileReader necesario
	 * para realizar una correcta lectura, independientemente del
	 * origen del fichero.
	 * 
	 * @param whereIs es la definición que indica donde
	 * se encuentra el fichero
	 * @return el FileReader específico para la definición proporcionada
	 * 
	 * @see IFileReader
	 */
	
	private static IFileReader read(String whereIs) {
		IFileReader fileReader = null;
		whereIs = whereIs.trim();
	
		if(PATRON_FICHERO_LOCAL_LINUX.matcher(whereIs).matches())
			fileReader = new LocalFileReader();
		else {
			try {
				throw new ExceptionReader(whereIs);
			} catch (ExceptionReader e) {
				e.printStackTrace();
			}
		}
		
		return fileReader;
	}
	
}
