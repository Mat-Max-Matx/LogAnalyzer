package types;

public class GeoIp {

	private String  ip;
	private String mask;
	private String stato;


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public void convertMask() {		
		switch(mask) {
			case "8":{
				mask="255.0.0.0";
				break;
			}
			case "9":{
				mask="255.128.0.0";
				break;
			}
			case "10":{
				mask="255.192.0.0";
				break;
			}
			case "11":{
				mask="255.224.0.0";
				break;
			}
			case "12":{
				mask="255.240.0.0";
				break;
			}
			case "13":{
				mask="255.248.0.0";
				break;
			}
			case "14":{
				mask="255.252.0.0";
				break;
			}
			case "15":{
				mask="255.254.0.0";
				
			}
			case "16":{
				mask="255.255.0.0";
				break;
			}
			case "17":{
				mask="255.255.128.0";
				break;
			}
			case "18":{
				mask="255.255.192.0";
				break;
			}
			case "19":{
				mask="255.255.224.0";
				break;
			}
			case "20":{
				mask="255.255.240.0";
				break;
			}
			case "21":{
				mask="255.255.248.0";
				break;
			}
			case "22":{
				mask="255.255.252.0";
				break;
			}
			case "23":{
				mask="255.255.254.0";
				break;
			}
			case "24":{
				mask="255.255.255.0";
				break;
			}
			case "25":{
				mask="255.255.255.128";
				break;
			}
			case "26":{
				mask="255.255.255.192";
				break;
			}
			case "27":{
				mask="255.255.255.224";
				break;
			}
			case "28":{
				mask="255.255.255.240";
				break;
			}
			case "29": {
				mask="255.255.255.248";
				break;
			}
			case "30":{
				mask="255.255.255.252";
				break;
			}
			case "31":{
				mask="255.255.255.254";
				break;
			}
			case "32":{
				mask="255.255.255.255";
				break;
			}
			default: // si puo mettere un controllo, ma in caso di default la maschera rimane la stessa 
		}
		
	}
	
}
