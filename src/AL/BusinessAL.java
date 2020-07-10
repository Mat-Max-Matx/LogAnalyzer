package AL;

import java.io.File;
import java.util.ArrayList;

import types.GeoIp;
import types.Occorrenza;

public class BusinessAL {
	
	Parser readerUtenti, readerProtocolli;					//leggono file di Trainer
	ArrayList<Occorrenza> occorrenzeUtenti;					//contiene risultato lettura users.txt
	ArrayList<Occorrenza> occorrenzeProtocolli;				//contiene risultato lettura protocols.txt
	AL algo;												//here comes the big boy
	String fasciaOraria ;
	
	private static final String percorsoFileUtenti = "C:\\Users\\massi\\Desktop\\users.txt";
	private static final String percorsoFileProtocolli = "C:\\Users\\massi\\Desktop\\protocols.txt";
	
	
	public BusinessAL (ArrayList<GeoIp> database) {
		readerUtenti = 			new Parser(new File(percorsoFileUtenti));
		readerProtocolli = 		new Parser(new File(percorsoFileProtocolli));
		occorrenzeUtenti = 		new ArrayList<Occorrenza>();
		occorrenzeProtocolli = 	new ArrayList<Occorrenza>();								//ritorna ora media da file users
		algo =					new AL(occorrenzeUtenti, readerUtenti.parseUsers(occorrenzeUtenti), occorrenzeProtocolli, database);
	}
	
	public void inizializza() {
		readerProtocolli.parseProtocols(occorrenzeProtocolli);
	}
	
	public int calcola(String log) {
		return algo.calcola(log);
	}
	
	
	
	
	

}

