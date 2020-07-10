package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import business.Business;
import business.Stato;
import controller.HelpController;
import controller.PauseController;
import controller.QuitController;
import controller.RunController;
import controller.SalvaController;
import controller.StopController;
import event.LogEvent;
import event.LogListener;

public class Gui extends JFrame implements LogListener {
	
	private Business business;
	private static final int LunghezzaPanGrandi = 40;
	private static final int AltezzaPanGrandi = 30;
	private static final int AltezzaPanPiccoli = 14;
	private JTextArea textAreaLog, textAreaLogWar, textAreaUtenti,textAreaPaesi;
	private JMenuBar menuBar;
	private JScrollPane scrollPaneLog, scrollPaneLogWar, scrollPaneUtenti, scrollPanePaesi;
	private JMenu menuFile, menuStatus, menuHelp;
	private JMenuItem menuItemRun, menuItemPause, menuItemStop,menuItemQuit,menuItemManuale, menuItemSalva;
	private JPanel raccoltaPanPrinc, panStatUtenti, panStatPaesi, panStatTotale, panLog, panLogWar,panBottom;
	private JLabel labelLogList, labelLogWar, labelStatUtente, labelStatPaese,labelStato,labelStaStatic,labelStatus,labelStatusFi;
	
	
	public Gui(Business business) {
		super("LOG"); // nome del titolo sulla finestra
		this.business=business;
		
		
	}
	public void init() {
		//------------ Barra del menu
		menuBar = new JMenuBar(); // oggetto barra
		
		menuItemSalva=	new JMenuItem("salva");
		menuItemQuit = 	new JMenuItem("Quit");
		menuFile =		new JMenu("File");
		
		menuFile.add(menuItemSalva);
		menuFile.add(menuItemQuit);
		menuBar.add(menuFile);
		//-------------------fine creazione menu File
	
		menuStatus =  new JMenu("Status");
		menuItemRun = 	new JMenuItem("Run");
		menuItemPause = new JMenuItem("Pause");
		menuItemStop = 	new JMenuItem("Stop");
		menuStatus.add(menuItemRun);
		menuStatus.add(menuItemPause);
		menuStatus.add(menuItemStop);
		menuBar.add(menuStatus);
		//-------------------fine creazione menu Edit
		menuItemManuale = new JMenuItem("Manuale");
		menuHelp= new JMenu("Help");
		
		menuHelp.add(menuItemManuale);
		menuBar.add(menuHelp);
		//------------------fine creazione menu Help
		
		setJMenuBar(menuBar);
		
		setLayout(new BorderLayout());//layout del frame principale
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // per chiudere il programma tramite la x sopra
		raccoltaPanPrinc = new JPanel(new FlowLayout(FlowLayout.LEFT)); //pannello principale di raccolta
		
		//-------------------inizializza JLabel dei titoli
		labelLogList = 	new JLabel("Lista log");
		labelLogWar =	new JLabel("Lista warning log");
		labelStatUtente=new JLabel("Lista Utenti");
		labelStatPaese= new JLabel("Lista accessi nel Mondo");
		labelStaStatic=	new JLabel("   Status: ");
		labelStato=		new JLabel("Run");
		labelStatus =	new JLabel("1");
		labelStatusFi= 	new JLabel(" -- Status M.A.S. warning : ");
		
		// fine JLabel
		//----------------------iniziallizzazione panelli centrali
		textAreaLog =		new JTextArea(AltezzaPanGrandi,LunghezzaPanGrandi);
		textAreaLogWar =	new JTextArea(AltezzaPanGrandi,LunghezzaPanGrandi);
		textAreaUtenti=		new JTextArea(AltezzaPanPiccoli,LunghezzaPanGrandi);
		textAreaPaesi = 	new JTextArea(AltezzaPanPiccoli,LunghezzaPanGrandi);
		
		textAreaLog.setEditable(false);
		textAreaLogWar.setEditable(false);
		textAreaUtenti.setEditable(false);
		textAreaPaesi.setEditable(false);
		
		scrollPaneLog = 	new JScrollPane(textAreaLog);
		scrollPaneLogWar=	new JScrollPane(textAreaLogWar);
		scrollPaneUtenti=	new JScrollPane(textAreaUtenti);
		scrollPanePaesi=	new JScrollPane(textAreaPaesi);
		
		scrollPaneUtenti.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePaesi.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneLogWar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//fine pannelli
		//--------------------------creo pannelli contenitori
		panStatUtenti = new JPanel(new BorderLayout());
		panStatPaesi=	new JPanel(new BorderLayout() );
		panStatTotale=	new JPanel(new BorderLayout());
		panLog=			new JPanel(new BorderLayout());
		panLogWar=		new JPanel(new BorderLayout());
		panBottom=		new JPanel(new FlowLayout(FlowLayout.LEFT));
		//fine pannelli contenitori
		
		//-------aggiungi i vari componenti
		panStatUtenti.add(labelStatUtente, BorderLayout.NORTH);
		panStatUtenti.add(scrollPaneUtenti, BorderLayout.SOUTH);
		panStatPaesi.add(labelStatPaese, BorderLayout.NORTH);
		panStatPaesi.add(scrollPanePaesi, BorderLayout.SOUTH);
		panStatTotale.add(panStatUtenti, BorderLayout.NORTH);
		panStatTotale.add(panStatPaesi, BorderLayout.SOUTH);
		panLog.add(labelLogList, BorderLayout.NORTH);
		panLog.add(scrollPaneLog,BorderLayout.SOUTH);
		panLogWar.add(labelLogWar, BorderLayout.NORTH);
		panLogWar.add(scrollPaneLogWar, BorderLayout.SOUTH);
		panBottom.add(labelStaStatic);
		panBottom.add(labelStato);
		panBottom.add(labelStatusFi);
		panBottom.add(labelStatus);
		//----------------------------------
		raccoltaPanPrinc.add(panLogWar);
		raccoltaPanPrinc.add(panLog);
		raccoltaPanPrinc.add(panStatTotale);
		//-------------------------------------
		add(raccoltaPanPrinc,BorderLayout.CENTER);
		add(panBottom, BorderLayout.SOUTH);
		initFinestra();
	}
	private void initFinestra(){
		setVisible(true);
		setResizable(false);
		pack(); // si adatta al contenuto
	}
	
//------Event che cambiano lo stato di un oggetto
	public void onLogChange(LogEvent logEvent) {
		
		Business b = (Business) logEvent.getSource();
		
		labelStatus.setText(b.getStatoMAS());
		
		if(b.getStato()==Stato.STOP)labelStato.setText("Stop");
		if(b.getStato()==Stato.PAUSE)labelStato.setText("Pause");
		
		if(b.getStato()==Stato.RUN) {
			labelStato.setText("Run");
			textAreaLog.setText(business.getLogAsText());
			textAreaLogWar.setText(business.getLogWarAsText());
			textAreaUtenti.setText(business.getUtentiAsText());
			textAreaPaesi.setText(business.getPaesiAsText());
		}
		
	}
	
	//----------------------------------------------------------Listener che prendono l'evento
	public void addHelpController(HelpController helpController) {
		menuItemManuale.addActionListener(helpController);
	}
	public void addSalvaController(SalvaController salvaController) {
		menuItemSalva.addActionListener(salvaController);
		
	}
	public void addPauseController(PauseController pauseController) {
		menuItemPause.addActionListener(pauseController);
	}
	public void addRunController(RunController runController) {
		menuItemRun.addActionListener(runController);
		
	}
	public void addQuitController(QuitController quitController) {
		menuItemQuit.addActionListener(quitController);
	}
	public void addStopController(StopController stopController) {
		menuItemStop.addActionListener(stopController);
		
	}
	
	
}
