package statistica;

import java.util.ArrayList;

import types.GeoIp;
import types.Lista;

public class CercaDatabase{

	ArrayList<String> logList;
	ArrayList<Lista> paesiList;
	ArrayList<GeoIp> databasePaesi;
	Subnet subnet;
	
	public CercaDatabase(ArrayList<String> logList, ArrayList<Lista> paesiList, ArrayList<GeoIp> databasePaesi) {
		this.logList=logList;
		this.paesiList=paesiList;
		this.databasePaesi=databasePaesi;
		subnet = new Subnet();
	}

	public void start(String s) {
		
			String[] ip=s.split(" "); //prendo il log e splitto
			
			for(GeoIp gp : databasePaesi) {
				if(subnet.isInSubnet(ip[3], gp.getIp(), gp.getMask())) { //controllo se un IP è presente in una rete
					boolean trova=false;
					
					if(paesiList.isEmpty()) {
						paesiList.add(new Lista(gp.getStato()));
						break;
					}
					
					for(Lista lista: paesiList)
						if(lista.getNome().equalsIgnoreCase(gp.getStato())) {
							lista.incrementaOccorenza();
							trova=true;
							break;
						}
					if(!trova)paesiList.add(new Lista(gp.getStato()));
					
				}
			}
			
	}
	
	

}
