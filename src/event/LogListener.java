package event;

import java.util.EventListener;

public interface LogListener extends EventListener{
	public void onLogChange(LogEvent logEvent);

}
