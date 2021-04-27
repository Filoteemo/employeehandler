package com.Filoteemo.filehandling;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInput {
	
	
	public FileInput() {
		
		// how to get filepath on mac: right click file, hold down option
		Path file = Paths.get("/Users/sindreolsen/Java/test.txt");
		String s = "Hei Sindre";
		byte[] data = s.getBytes();
		
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
