package statistica;

import java.util.ArrayList;

import types.Lista;

public class Stat {
	
	//private Business business;
	private ArrayList<String> listaFileUtenti;
	private ArrayList<Lista> utentiList;
	
	public Stat(ArrayList<String> logList, ArrayList<Lista> utentiList, ArrayList<String> listaFileUtenti) {
		this.utentiList = utentiList;
		this.listaFileUtenti=listaFileUtenti;
	}

	public void start(String z) { //log	
		for(String s: listaFileUtenti) { //prendo tutti i nomi di passwd
			
			if(z.toLowerCase().contains(s.toLowerCase())) {
				
				boolean trova=false;
			
				if(utentiList.isEmpty()) {
					utentiList.add(new Lista(s));
					break;
				}
				
				for(int i=0;i<utentiList.size();i++) {
					if(utentiList.get(i).getNome().equalsIgnoreCase(s)) {
						utentiList.get(i).incrementaOccorenza();
						trova=true;
						break;
						}
					}
					if(!trova)utentiList.add(new Lista(s));
				}
			
			}
		}
	}
	


