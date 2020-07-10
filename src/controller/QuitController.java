package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitController implements ActionListener{	

	public QuitController() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
		
	}

}
