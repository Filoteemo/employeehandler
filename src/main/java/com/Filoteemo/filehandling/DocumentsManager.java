package com.Filoteemo.filehandling;

import java.io.File;
import java.io.IOException;

public interface DocumentsManager {

	
	void writeToFile(String text, File file)throws IOException;
	
	String readFromFile(File file) throws IOException;
}
