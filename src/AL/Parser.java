package AL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import types.Occorrenza;
import types.RawLog;



public class Parser {
	
	private File file;
	
	public Parser() {
	}
	
	public Parser(File file) {
		this.file = file;
	}
	
	
	
	
//-------------------------------------------------------------------	
	public static Occorrenza parse(String line) {
		String name = null;
		int occorrenze = 0;
		
		String[] args = line.split("-");
		
		try {
			name = args[0];
			occorrenze = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return new Occorrenza(name, occorrenze);
	}
	
	public String parseUsers(ArrayList<Occorrenza> list) {			//ritorna una stringa con l'ora media
		Scanner scanner = null;
		String ora = null;
		try {
			scanner = new Scanner(file);
			ora = new String(scanner.nextLine());
			
			while(scanner.hasNextLine()) {
				String line = new String(scanner.nextLine());
				list.add(Parser.parse(line));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Errore letture file utenti: " + e.getLocalizedMessage());
		} catch (Exception e1) {
			System.out.println("Errore generico: " + e1.getMessage());
		}
		return ora;
	}
	
	public void parseProtocols(ArrayList<Occorrenza> list) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);			
			while(scanner.hasNextLine()) {
				String line = new String(scanner.nextLine());
				list.add(Parser.parse(line));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Errore letture file protocolli: " + e.getLocalizedMessage());
		} catch (Exception e1) {
			System.out.println("Errore generico: " + e1.getMessage());
		}
	}
	
	
//---------------------------------------------------------------------------
	
	public RawLog parseLog(String log) {
		String date;
		String ip;
		String protocol;
		String uid;
		String message;
		boolean status;										//aggiunta status per 'failed' su log
		
		String[] args = log.split("\\s+", 6);
		String[] args2 = args[4].split("\\p{Punct}");
		String[] logargs = this.aggiusta(args, args2);
		
		date = logargs[0] +"/"+ logargs[1] +"/"+ logargs[2];
		ip = logargs[3];
		protocol = logargs[4];
		uid = logargs[5];
		message = logargs[6].toLowerCase();
		status = this.checkStatus(message);							//status declined/failed
		
		return new RawLog(date, ip, protocol, uid, message, status);
	}
	
	public String[] aggiusta(String[] a, String[] b) {			//unisce i multipli array riportati da split in una stringa unica
		String[] both = new String[a.length + 1];
	    System.arraycopy(a, 0, both, 0, 4);
	    System.arraycopy(a, 5, both, 6, 1);
	    System.arraycopy(b, 0, both, 4, b.length);
	    return both;
	}
	
	public boolean checkStatus(String message) {
		if(message.contains("failed") || message.contains("failure") || message.contains("bad") || message.contains("invalid")) {
			return true;
		}
		else return false;
	}

}
