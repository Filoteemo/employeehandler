package com.Filoteemo.filehandling;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInput {

	
	public FileInput(String s) {
		
		Path file = Paths.get("C:\\users\\sindr\\eclipse-workspace\\OBJ2100\\stifiltest.txt");
		
		byte[]data = s.getBytes();
		
		try {
			FileOutputStream output = new FileOutputStream(file.toString());
			output.write(data);
			output.close();
			System.out.println("Success");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
