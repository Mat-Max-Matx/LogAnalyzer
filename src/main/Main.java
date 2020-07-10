package main;

import business.Business;
import controller.LogController;
import gui.Gui;

public class Main {

	public static void main(String[] args) {
		
		Business business = new Business();
		Gui gui =			new Gui(business);
		LogController controller = new LogController(business,gui);
		//--------------------------------------------------------
		
		gui.init();
		controller.init();
		
		business.init(gui);
		while(true) {
			business.esegui();
		}
	}

}
