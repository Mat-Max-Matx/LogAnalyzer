package AL;

import java.util.ArrayList;

import types.GeoIp;
import types.Occorrenza;
import types.RawLog;

public class AL {
	
	
	ArrayList<Occorrenza> listaUtenti, listaProtocolli;
	private CercaDatabaseAL checkIp;
	String oramedia;
	
	
	
	public AL() {		
	}
	
	public AL(ArrayList<Occorrenza> listaUtenti, String oramedia, ArrayList<Occorrenza> listaProtocolli, ArrayList<GeoIp> database) {
		this.listaUtenti = listaUtenti;
		this.listaProtocolli = listaProtocolli;
		this.oramedia = oramedia;
		this.checkIp = new CercaDatabaseAL(database);
	}
	
	public int calcola(String logIn) {
		double UtentePer = 	3.0/10.0;
		double OraPer = 	2.0/10.0;
		double ProtoPer = 	0.5/10.0;
		double IPPer = 		1.5/10.0;
		double StatusPer=	3.0/10.0;
		double indiceUtente;
		double indiceOra;
		double indiceIp;
		double indiceAccesso;
		double indiceProtocollo;
		Parser parser = new Parser();
		RawLog rawlog = new RawLog();
		rawlog = parser.parseLog(logIn);
		
		Occorrenza utenteTarget = null;
		Occorrenza protocolloTarget = null;
		
		for(Occorrenza user: this.listaUtenti) {
			if(rawlog.getMessage().contains(user.getName())) {
				utenteTarget = new Occorrenza(user.getName());
				break;
			}
		}
		
		for(Occorrenza proto: listaProtocolli) {
			if(rawlog.getProtocol().equalsIgnoreCase(proto.getName())) {
				protocolloTarget= new Occorrenza(proto.getName());
				break;
			}
		}
		if(utenteTarget==null)return -1;
		if(protocolloTarget==null)return-1;
		indiceUtente = ((utente(listaUtenti, utenteTarget.getName())) *UtentePer);
		indiceOra = ((fasciaOraria(oramedia, rawlog.getHours())) *OraPer);
		indiceAccesso = ((statoLog(rawlog)) *StatusPer);
		indiceProtocollo = ((protocolli(listaProtocolli, protocolloTarget.getName())) *ProtoPer);
		indiceIp = ((ip(rawlog.getIp())) *IPPer);
		
		int indiceLog = new Integer((int) (indiceUtente + indiceOra + indiceIp + indiceAccesso + indiceProtocollo));
		return indiceLog;		
	}
	
	private double fasciaOraria(String oramedia, String oraAttuale) {
		int oramed = Integer.parseInt(oramedia);
		int ora = Integer.parseInt(oraAttuale);
		if(ora > (oramed-6) && ora < (oramed+6)) return 0.0;
		if((ora > (oramed-8)) && (ora < (oramed-6)) || (ora < (oramed+8)) && (ora > (oramed+6)) ) return 50.0;
		else return 100.0;
	}
	
	private double statoLog(RawLog log) {			//possibile cambiare con string messaggio
		if(log.getStatus()) return 100.0;
		else return 0.0;
	}
	
	private double utente(ArrayList<Occorrenza> listaUtenti, String username) {
		double occorrenzeTotali = 0.0;
		double occorrenzeUtente = 0.0;
		Occorrenza target = new Occorrenza(username);
				
		for(Occorrenza utente: listaUtenti) {
			occorrenzeTotali += utente.getOccorrenze();
			if(target.equals(utente)) {
				occorrenzeUtente = utente.getOccorrenze();
			}
		}	
		double percentuale = ((occorrenzeUtente*100.0) / occorrenzeTotali);
		return percentuale;
	}
	
	private double protocolli(ArrayList<Occorrenza> listaProtocolli, String protocollo) {
		double occorrenzeTotali = 0.0;
		double occorrenzeProtocollo = 0.0;
		Occorrenza target = new Occorrenza(protocollo);
				
		for(Occorrenza proto: listaProtocolli) {
			occorrenzeTotali += proto.getOccorrenze();
			if(target.equals(proto)) {
				occorrenzeProtocollo = proto.getOccorrenze();
			}
		}
		
		double percentuale = ((occorrenzeProtocollo*100.0) / occorrenzeTotali);
		return percentuale;
	}
	
	private double ip(String ip){
		if(checkIp.isItalian(ip))return 0.0;
		else return 100.0;
	}

}
