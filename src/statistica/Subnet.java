package statistica;

import java.util.StringTokenizer;

public class Subnet {
	
	public boolean isInSubnet(String ipAddre, String subnet, String mask) {
		
		Integer[] ipAddrInt = new Integer[4];
		Integer[] subnetInt = new Integer[4];
		Integer[] maskInt = new Integer[4];
		
		if(!parseNumericAddress(ipAddre, ipAddrInt))return false;
		if(!parseNumericAddress(subnet, subnetInt))return false;
		if(!parseNumericAddress(mask, maskInt))return false;
		
		for(int i=0;i<ipAddrInt.length;i++) {
			if((ipAddrInt[i] & maskInt[i])!=subnetInt[i])return false;
		}
		return true;
	}
	private boolean parseNumericAddress(String string, Integer[] stringInt) {
		
		// se diversa da null o ha pochi numero o troppi
		if(string==null||string.length()<7||string.length()>15)
			return false;
		// devono esserci esattamente 4 punti
		 StringTokenizer token = new StringTokenizer(string,".");
		    if ( token.countTokens() != 4)
		      return false;
		 
		 for(int i=0;token.hasMoreTokens();i++) {
			 
			 int ipVal = Integer.valueOf(token.nextToken());
			 
			 if(ipVal<0 || ipVal>255) return false;
			 
			 stringInt[i]=ipVal;
		 }
		return true;

	}
	

}
