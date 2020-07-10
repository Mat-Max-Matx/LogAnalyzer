package manuale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fileReader.NormalReader;

public class Manuale extends JFrame {
	
	private NormalReader readManuale;
	private ArrayList<String> list;
	private JTextArea textArea;
	private JScrollPane pane;
	//private static final int Lunghezza=30;
	//private static final int Altezza=40;
	
	public Manuale() {
		super("manuale"); //metto il nome sulla finestra
		readManuale= new NormalReader(new File("manuale.txt"));
		list= new ArrayList<String>();
	}
	
	public void init() {
		
		try {
			readManuale.leggi(list);
		} catch (IOException e) {
			System.out.println("errore apertura file manuale "+e.getMessage());
			e.printStackTrace();
		}
		
		//textArea=new JTextArea(Lunghezza,Altezza);
		textArea=new JTextArea();
		pane = new JScrollPane(textArea);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for(String s : list) {
			textArea.setText(textArea.getText()+"\n"+s);
		}
		add(pane);
		
		//--------------default
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(30,30);
		setVisible(true);
		setResizable(true);
		pack();
	}
}
