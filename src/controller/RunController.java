package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Business;

public class RunController implements ActionListener{
	private Business business;
	
	public RunController(Business business) {
		this.business=business;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		business.setRun();
	}

	
}
