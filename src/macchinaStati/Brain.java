package macchinaStati;

import java.util.ArrayList;

public class Brain {
	
	private ArrayList<String> warnList;
	private MAS mas;
	private static int LIMITEWARN=90;
	
	public Brain(ArrayList<String> warnList) {
		mas=MAS.getInstance();
		this.warnList=warnList;
	
	}
	public void set(int valore, String log) {
		if(valore>LIMITEWARN)warnList.add(log);
		if(mas.set(valore)) {
			warnList.add(log);
		}
	}
	public String getStato() {
		return mas.getStato();
	}

}
