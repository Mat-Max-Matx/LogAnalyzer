package business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import AL.BusinessAL;
import fileReader.DataBaseReader;
import fileReader.FileReadingOnTime;
import fileReader.UtentiReader;
import fileReader.WriteFile;
import event.LogEvent;
import event.LogListener;
import gui.Gui;
import macchinaStati.Brain;
import statistica.CercaDatabase;
import statistica.Stat;
import types.GeoIp;
import types.Lista;

public class Business {
	
	private Stato stato;
	private LogListener logListener;
	private ArrayList<String> logList,warnList, listaFileUtenti;
	private ArrayList<Lista> utentiList, paesiList;
	private ArrayList<GeoIp> databasePaesi;
	private String percorsoFile="C:\\prova_da_cancellare\\auth.log"; // da cambiare
	private String fileUtenti ="passwd.txt";//da cambiare
	private String databaseFile="geoip.txt";
	private String salvataggioWarning="salvataggioWarning.txt";
	private FileReadingOnTime fileReader;
	private CheckPattern checkPattern;
	private Stat stat;
	private CercaDatabase cercaDatabase;
	private UtentiReader readUtenti;
	private Brain interfaceMAS;
	private DataBaseReader readDatabase;
	private BusinessAL businessAL;
	
	public Business () {
		
		stato=Stato.RUN; //stato di default
		
		logList= 		new ArrayList<String>();//lista dei log totali
		utentiList= 	new ArrayList<Lista>();//statistica sugli utenti
		paesiList=  	new ArrayList<Lista>();//statistica sui paesi
		warnList= 		new ArrayList<String>();//previsioni di warning
		listaFileUtenti=new ArrayList<String>();//lista sui utenti totali caricati da file
		databasePaesi=	new ArrayList<GeoIp>();//database GeoIp
		
		readUtenti = new UtentiReader(new File(fileUtenti));
		checkPattern=new CheckPattern();
		fileReader = new FileReadingOnTime(new File(percorsoFile),logList); //oggetto per la lettura da file
		
		readDatabase=new DataBaseReader(new File(databaseFile));
		
		businessAL=	new BusinessAL(databasePaesi);
		
		stat = new Stat(logList,utentiList,listaFileUtenti);//Thread per stat
		cercaDatabase = new CercaDatabase(logList,paesiList,databasePaesi);
		interfaceMAS=new Brain(warnList);
	}
	
	public void init(Gui gui) {
		try {
			readUtenti.leggi(listaFileUtenti);
			readDatabase.leggi(databasePaesi);
		} catch (IOException e) {
			System.out.println("Errore lettura File Utenti/Database: "+e.getMessage());
			e.printStackTrace();
		}
		//------------------
		addLogListner(gui);
		businessAL.inizializza();
	}
	public void esegui() {
		
		if(stato!=Stato.STOP) { //se lo stato è STOP non leggiamo niente
			
			if(fileReader.esegui()) { //controlla se il file è stato aggiornato se si ritorna true e aggiorna la lista
				String logAttuale=logList.get(logList.size()-1);
				if(checkPattern.check(logAttuale)) { //controlla la regex
					
					stat.start(logAttuale); //parte il Thread per la stat
					cercaDatabase.start(logAttuale);
					int pericolosità=businessAL.calcola(logAttuale);
					System.out.println(pericolosità+"\n");
					if(pericolosità!=-1) {
						interfaceMAS.set(pericolosità,logAttuale);// assegnare il valore di pericolosità
					}
					sortaList();
				}
			}
		}
		//non sono sicuro sulla posizione di questo
		LogEvent logEvent = new LogEvent(this);
		logListener.onLogChange(logEvent);
	}
	
	
	//------------------------metodi 
	private void addLogListner(Gui gui) {
		this.logListener=gui;
	}
	private void sortaList() {
		Collections.sort(paesiList);
		Collections.sort(utentiList);
	}
	public Stato getStato() {
		return stato;
	}
	public void setStop() {
		stato=Stato.STOP;
	}
	public void setRun() {
		stato=Stato.RUN;
		
	}
	public void salvaWarning() {
		WriteFile salvaWarning = new WriteFile(new File(salvataggioWarning));
		salvaWarning.esegui(warnList);
	}
	public void setPause() {
		stato=Stato.PAUSE;
	}
	public String getLogAsText() {
		String full="";
		for(String s : logList) {
			full=full+"\n"+s;
		}
		return full;
	}
	public String getLogWarAsText() {
		String full="";
		for(String s : warnList) {
			full=full+"\n"+s;
		}
		return full;
	}
	public String getUtentiAsText() {
		String full="";
		for(Lista s : utentiList) {
			full=full+"\n"+s.getNome()+" "+s.getOccorenza();
		}
		return full;
	}
	public String getPaesiAsText() {
		String full="";
		for(Lista s : paesiList) {
			full=full+"\n"+s.getNome()+" "+s.getOccorenza();
		}
		return full;
	}

	public String getStatoMAS() {
		return interfaceMAS.getStato();
	}

}
