package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import manuale.Manuale;

public class HelpController implements ActionListener {
	private Manuale manuale;

	public HelpController() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manuale = new Manuale();
		manuale.init();
	}
	
}
