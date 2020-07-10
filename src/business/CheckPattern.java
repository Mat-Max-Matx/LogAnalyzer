package business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPattern {
	
	private Matcher matcher;
	private static final Pattern LINE_PATTERN =Pattern.compile("[a-zA-Z]{3}\\s+[0-9]{1,2}\\s+[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\s+[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}\\s+[a-z]{4}+.*");
	
	public CheckPattern() {
		
	}
	public boolean check(String string) {
		matcher = LINE_PATTERN.matcher(string);
		if(matcher.matches()) {
			return true;
		}else {
		return false;

		}
		}
}
