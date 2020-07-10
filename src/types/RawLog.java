package types;


public class RawLog {
	
	protected String date;
	protected String ip;
	protected String protocol;
	protected String uid;
	protected String message;
	protected boolean failed;		//status == failed/failure/accepted in log
	
	
	public RawLog() {
		this.date = new String("");
		this.ip = new String("");
		this.protocol = new String("");
		this.uid = "0000";
		this.message = new String("");
	}
	
	public RawLog(String date, String ip, String protocol, String uid, String message) {
		this.date = date;
		this.ip = ip;
		this.protocol = protocol;
		this.uid = uid;
		this.message = new String(message);
	}
	
	public RawLog(String date, String ip, String protocol, String uid, String message, boolean status) {
		this.date = date;
		this.ip = ip;
		this.protocol = protocol;
		this.uid = uid;
		this.message = new String(message);
		this.failed = status;
	}
	
	
	public String getHours() {
		String[] pieces = this.date.split("/");
		String time = pieces[2];
		String[] splitted = time.split(":");
		String hour = splitted[0];
		return hour;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public String getProtocol() {
		return this.protocol;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public boolean getStatus() {
		return this.failed;
	}
	
	public String getLogRaw() {
		String lograw = new String(this.date +" | "+ this.ip +" | "+ this.protocol +" | "+ this.uid +" || "+ this.message);
		return lograw;
	}
	
	

}
