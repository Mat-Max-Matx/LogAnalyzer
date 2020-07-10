package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import types.GeoIp;

public class DataBaseReader extends Read<GeoIp> {
	
	private ArrayList<GeoIp> databasePaesi;

	public DataBaseReader(File file) {
		super(file);
	}

	@Override
	public void leggi(ArrayList<GeoIp> list) throws IOException {
		
		
		reader = new BufferedReader(new FileReader(file));
		
	    String text = "";
	    
	    text = reader.readLine(); //toglie l'intestazione
	    
	    while ((text = reader.readLine()) != null) {
	    	GeoIp instanza = new GeoIp();
	    	
	    	String[] splitTotale=text.split(",");
	    	String[] splitIp=splitTotale[0].split("/");
	    	
	    	instanza.setIp(splitIp[0].substring(1));
	    	instanza.setMask(splitIp[1].substring(0, splitIp[1].length()-1));
	    	instanza.convertMask();
	    	instanza.setStato((splitTotale[5].length()<4?splitTotale[3].substring(1,(splitTotale[3].length()-1)):splitTotale[5].substring(1,(splitTotale[5].length()-1))));
	        list.add(instanza);
	    }
	    if (reader != null) {
            reader.close();
        }
		
	}
	

}
