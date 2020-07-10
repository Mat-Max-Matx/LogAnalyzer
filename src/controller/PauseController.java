package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Business;

public class PauseController implements ActionListener {

	private Business business;
	

	public PauseController(Business business) {
		this.business=business;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		business.setPause();
	}


	
}
