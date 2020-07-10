package controller;

import business.Business;
import gui.Gui;

public class LogController {
	
	private Business business=null;
	private Gui gui=null;

	public LogController(Business business, Gui gui) {
		this.business=business;
		this.gui=gui;
	}
	public void init() {
		gui.addSalvaController(new SalvaController (business));
		gui.addStopController(new StopController(business));
		gui.addHelpController(new HelpController());
		gui.addPauseController(new PauseController(business));
		gui.addRunController(new RunController(business));
		gui.addQuitController(new QuitController());
	}
	
}
