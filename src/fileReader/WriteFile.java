package fileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class WriteFile {

	private File file;
	
	public WriteFile(File file) {
		this.file=file;
	}
	public void esegui(ArrayList<String> list) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8")); //bisogna guardare la gerarchia per una possibile domanda
			for(String s: list) {
				writer.append(s); //la classe Writer ha sia un metodo write e append
			}
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			System.out.println("errore apertura puntatori scrittura file: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("errore con la scrittura su file: "+e.getMessage());
			e.printStackTrace();
		}
	
		
	}
}
