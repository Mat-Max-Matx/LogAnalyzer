package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NormalReader extends Read<String> {

	public NormalReader(File file) {
		super(file);
	}

	@Override
	public void leggi(ArrayList<String> list) throws IOException {
		reader = new BufferedReader(new FileReader(file));
	    String text = "";
	    
	    while ((text = reader.readLine()) != null) {
	        list.add(text);
	    }
	    if (reader != null) {
            reader.close();
        }
	}

}
