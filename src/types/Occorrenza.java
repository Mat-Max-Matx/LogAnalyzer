package types;

import java.util.Objects;

public class Occorrenza implements Comparable<Occorrenza>{
	private String name;
	private int occorrenze;
	
	public Occorrenza() {
		this.name = new String("???");
		this.occorrenze = 1;
	}
	
	public Occorrenza(String name) {
		this.name = name.toLowerCase();
		this.occorrenze = 1;
	}
	
	public Occorrenza(String name, int occorrenze) {
		this.name = name.toLowerCase();
		this.occorrenze = occorrenze;
	}
	
	
	
//------------------------------------------------------
	public void setNome(String utente) {
		this.name = utente.toLowerCase();
	}
	
	public void setOccorenze(int occorrenze) {
		this.occorrenze = occorrenze;
	}
	
	public void incrementaOccorrenze() {
		this.occorrenze = this.occorrenze + 1;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getOccorrenze() {
		return this.occorrenze;
	}

	public int compareTo(Occorrenza altro) {						//ho dovuto scambiare gli int di ritorno per avere
		if (this.occorrenze > altro.occorrenze) return -1;			//l'utente con più occorrenze in posizione 0 (sort)
		if (this.occorrenze == altro.occorrenze) return 0;
		else return 1;
	}
	
	public String toString() {
		return new String(this.name + "-" + this.occorrenze);
	}
	
	public boolean equals(Object o) {
        boolean retVal = false;

        if (o instanceof Occorrenza){
            Occorrenza occ = (Occorrenza) o;
            retVal = this.getName() == occ.getName();
        }
     return retVal;
	}
	
	public int hashCode() {
        return Objects.hash(this.name, this.occorrenze);
    }
	
}
