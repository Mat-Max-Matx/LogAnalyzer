package AL;


import java.util.ArrayList;

import statistica.Subnet;
import types.GeoIp;

public class CercaDatabaseAL{

	ArrayList<GeoIp> databasePaesi;
	Subnet subnet;
	private static final String ITALIA = "italy";
	
	public CercaDatabaseAL(ArrayList<GeoIp> databasePaesi) {
		this.databasePaesi=databasePaesi;
		subnet = new Subnet();
	}

	public boolean isItalian(String ip) {
		for(GeoIp gp : databasePaesi) {
			if(subnet.isInSubnet(ip, gp.getIp(), gp.getMask())) {
				if(gp.getStato().equalsIgnoreCase(ITALIA))return true;
			}
		}
		return false;
	}

}
