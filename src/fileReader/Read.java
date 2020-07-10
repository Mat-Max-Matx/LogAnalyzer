package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Read<T> {
	
		File file;
		BufferedReader reader = null;
		
		public Read(File file) {
			this.file=file;
		}
		
		public abstract void leggi(ArrayList<T> list) throws IOException;
		
}
