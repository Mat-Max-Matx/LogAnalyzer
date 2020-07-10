package macchinaStati;

public class Stato_MAS {

	private Stato_MAS next,pre;
	private boolean attuale,last;
	private final int VALORE;
	private final int rangeContatore;
	private int contatoreSaturazione;
	private static final int FINECONTATORE=15;// dopo questo numero di valori il MAS skippa
	
	public Stato_MAS(int valore, int rangeContatore) {
		this.VALORE=valore;
		this.rangeContatore=rangeContatore;
		contatoreSaturazione=0;
	}
	
	public void setNext(Stato_MAS next) {
		this.next=next;
	}

	public void setPre(Stato_MAS pre) {
		this.pre=pre;
	}

	public void setAttuale(boolean attuale) {
		this.attuale=attuale;
	}
	private void azzeraContatore() {
		contatoreSaturazione=0;
	}

	public void setLast(boolean last) {
		this.last=last;
		
	}
	private Stato_MAS getNext() {
		return next;
	}
	public Stato_MAS get2Next() {
		return next.getNext();
	}
	public int getValore() {
		return VALORE;
	}
	public boolean isLast() {
		if(last)return true;
		return false;
	}

	public boolean isAttuale() {
		if(attuale)return true;
		return false;
	}

	private void successivo() {
		azzeraContatore();
		next.setAttuale(true);
		attuale=false;
	}
	public boolean setValore(int valore) {
		if(valore>rangeContatore&&valore<VALORE)contatoreSaturazione++;
		if(contatoreSaturazione>FINECONTATORE||(valore>VALORE&&valore<next.getValore())) {
			successivo();
		}
			
		if(valore>next.getValore()) {
			if(!next.isLast())
				next.setValore(next.getValore()+1); // salto doppio
				next.get2Next().setAttuale(true);
				attuale=false;
				
		}else successivo();
		
	/*	
		valoreAttuale=valoreAttuale+(valore*coeficciente);
		if(valoreAttuale>VALORE) { 
				if(valoreAttuale>VALORE*2) {// in questo modo la macchina a stati non può saltare due stati
					next.setAttuale(true);
					next.setValore(VALORE+1);
				}else {
					next.setAttuale(attuale);
					attuale=false;
				}
		}
		*/
		if(valore<0) {
			if(!pre.isLast()) {
				pre.setAttuale(attuale);
				attuale=false;
				azzeraContatore();
			}
		}
		
		return false;
	}

}
