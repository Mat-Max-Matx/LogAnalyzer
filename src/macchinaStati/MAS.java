package macchinaStati;


public class MAS {
	
	private static boolean flag=false;
	private  static MAS mas;
	
	private  final int PRECISIONE=10;
	private  int contaPrecisione=0;
	
	private  final int NUMEROSTATI=5;
	private  Stato_MAS[] mas_stato;
	private  Stato_MAS statoAttuale;
	
	//-------------------------
	//SINGLETON
	//--------------------------
	private MAS() {
		mas_stato= new Stato_MAS[5];
		for(int i=0;i<NUMEROSTATI;i++) 
			mas_stato[i] = new Stato_MAS((100/NUMEROSTATI)*(i+1),((i+1)/2));
		
		for(int i=0;i<NUMEROSTATI;i++) {
			mas_stato[i].setNext(i+1>=NUMEROSTATI ? mas_stato[0]:mas_stato[i+1]);
			mas_stato[i].setPre(i-1<0? mas_stato[NUMEROSTATI-1]:mas_stato[i-1]);
			mas_stato[i].setAttuale(i==0?true:false);
			mas_stato[i].setLast(i==NUMEROSTATI?true:false);
		}
		
	}
	
	public static MAS getInstance() {
		if(flag)return mas;
		flag=true;
		return mas = new MAS();
	}
	
	public void finalize() {
		flag=false;
	}
	//----------------------------

	public boolean set(int valore) {
		statoAttuale=getStatoAttuale();
		statoAttuale.setValore(valore); 
			if(statoAttuale.isLast())return true;
			delay();
		
		return false;
	}

	private void delay() {
		contaPrecisione++;
		if(contaPrecisione==PRECISIONE)statoAttuale.setValore(-1);
	}

	private Stato_MAS getStatoAttuale() {
		for(int i=0;i<NUMEROSTATI;i++) {
			if(mas_stato[0].isAttuale())return mas_stato[0];
		}
		return null;
	}

	public String getStato() {
		for(int i=0;i<NUMEROSTATI;i++) {
			if(mas_stato[0].isAttuale())return Integer.toString(i+1);
		}
		return null;
	}
	
}