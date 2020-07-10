package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import business.Business;

public class SalvaController implements ActionListener{
	private Business business;

	public SalvaController(Business business) {
		this.business=business;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		business.salvaWarning();
	}
	
	
}
