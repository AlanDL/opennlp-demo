package org.fogbeam.example.opennlp.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * Lector de ficheros que se encuentras almacenados en local
 * 
 * @author Alan
 *
 * @see IFileReader
 */

public class LocalFileReader implements IFileReader {

	/*
	 * (non-Javadoc)
	 * @see org.fogbeam.example.opennlp.io.IFileReader#read(java.lang.String)
	 */
	@Override
	public List<String> read(String whereIs) {
		List<String> lines = new ArrayList<>();
		
		try {
			Stream<String> stream = Files.lines(new File(whereIs).toPath());
			stream.forEach(s -> lines.add(s));
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.fogbeam.example.opennlp.io.IFileReader#readAllBytes(java.lang.String)
	 */
	@Override
	public String readAllBytes(String whereIs) {
		String output = "";
		
		try {
			output = new String(Files.readAllBytes(Paths.get(whereIs)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return output;
	}


}
