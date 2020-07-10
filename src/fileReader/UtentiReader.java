package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UtentiReader extends Read<String> {

	public UtentiReader(File file) {
		super(file);
	}

	@Override
	public void leggi(ArrayList<String> list) throws IOException {

		reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    
	    while ((text = reader.readLine()) != null) {
	        String[] text1 = text.split(":");
	    	list.add(text1[0]);
	    }
	    if (reader != null) {
            reader.close();
        }
	}


}
