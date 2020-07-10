package types;

public class Lista implements Comparable<Lista> {
	
	private String nome;
	private int occorenza;
	
	public Lista(String nome) {
		this.nome=nome;
		occorenza=1;
	}

	public Lista(String s, int i) {
		this.nome=s;
		occorenza=0;
	}

	public String getNome() {
		return nome;
	}

	public int getOccorenza() {
		return occorenza;
	}

	public void setOccorenza(int occorenza) {
		this.occorenza = occorenza;
	}
	
	public void incrementaOccorenza() {
		this.occorenza++;
	}

	@Override
	public int compareTo(Lista o) {
		if(this.occorenza>o.getOccorenza())return -1;
		if(this.occorenza<o.getOccorenza())return 1;
		return 0;
	}
	
	

}
