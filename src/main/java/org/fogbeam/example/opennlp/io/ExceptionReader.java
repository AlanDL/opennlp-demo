package org.fogbeam.example.opennlp.io;


/**
 * Esta clase define una excepción que se encarga de notificar al usuario
 * que no se dispone del método necesario para acceder a la localización
 * del archivo.
 * 
 * @author Alan
 *
 */
public class ExceptionReader extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Al lanzar la excepción se muestra por consola el mensaje de error, junto con
	 * la definición proporcionada.
	 * 
	 * @param whereIs es una representación que nos indica donde se encuentra
	 * el fichero a leer
	 * @see FacadeReader
	 */
	public ExceptionReader(String whereIs) {
		System.out.println(error(whereIs));
	}
	
	/**
	 * Genera el mensaje de error
	 */
	private String error(String whereIs) {
		return "El lector de fichero no tiene definido ningún método para"
				+ whereIs;
	}
	
}
