package phoebe.gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import phoebe.control.ActionButtonListener;
import phoebe.control.GameMouseListener;
import phoebe.control.PlayerNumberButtonListener;
import phoebe.game.GameController;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 2764832581974173222L;
	
	private JButton glue;
	private JButton oil;
	private JButton jump;
	private JPanel buttonPanel;
	private GameController gc;
	
	protected ViewPanel vp;
	
	/**
	 * Konstruktor, mely beállítja az ablak alapető tulajdonságait, és folytatja a játékot a játékosszám
	 * bekérésének elindításával.
	 * @param gc a játékhoz tartozó GameController
	 */
	public MainWindow(GameController gc) {
		initComponents();
		
		this.setSize(616, 675); // így a ViewPanel 600x600-as lesz
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//A GameController elmentése
		this.gc = gc;
		
		//A játékosszám beolvasásának indítása
		getPlayerNumber();
	}
	
	
	/**
	 * A komponensek inicializálása, elrendezésük beállítása
	 */
	private void initComponents(){
		this.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
	}
	
	/**
	 * Metódus új játék indítására
	 * @param n a játékosok száma
	 */
	public void newGame(int n) {
		// Új játék indítása
		gc.newGame(n);
		
		//Az új játék megjelenítése a képernyőn
		getContentPane().removeAll();
		getContentPane().repaint();
				
		setUpButtons();
		buttonPanel.setVisible(true);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		vp = new ViewPanel(gc);
		vp.addMouseListener(new GameMouseListener(gc.getRobotController()));
		//TODO remove after game end, (exceptions)
		this.add(vp ,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	
	/**
	 * Létrehozza az olaj, a ragacs és az ugrás gombját megfelelő stílussal, és a tárolójukba helyezi őket.
	 */
	private void setUpButtons(){
		glue = new JButton("Glue");
		oil = new JButton("Oil");
		jump = new JButton("Jump");
		
		//A közös ActionButtonListener létrehozása
		ActionButtonListener abl = new ActionButtonListener(gc.getRobotController());
		
		//A gombok kívánt kinézetének beállítása
		glue.setBackground(new Color(98, 77, 125));
		glue.setForeground(Color.WHITE);
		glue.setRolloverEnabled(false);
        glue.setFocusPainted(false);
        glue.addActionListener(abl);
		oil.setBackground(new Color(98, 77, 125));
		oil.setForeground(Color.WHITE);
		oil.setRolloverEnabled(false);
        oil.setFocusPainted(false);
        oil.addActionListener(abl);
		jump.setBackground(new Color(98, 77, 125));
		jump.setForeground(Color.WHITE);
		jump.setRolloverEnabled(false);
        jump.setFocusPainted(false);
        jump.addActionListener(abl);
        
        //Elhelyezzük őket a buttonPanelen
        buttonPanel.add(glue);
		buttonPanel.add(jump);
		buttonPanel.add(oil);
	}
	
	/**
	 * Metódus az új játék játékosszámának beolvasásához
	 */
	public void getPlayerNumber () {
		JPanel getNumberPanel = new JPanel();
		getNumberPanel.setLayout(new FlowLayout());
		
		//Négy különböző gombot hozunk létre a játékosszám beolvasásához
		JButton[] numberButton = new JButton[4];
		PlayerNumberButtonListener gbl = new PlayerNumberButtonListener(this);
		for(int i=0; i<4; ++i) {
			numberButton[i] = new JButton(Integer.toString(i+2));
			numberButton[i].setActionCommand((i+2) + " player");
			numberButton[i].addActionListener(gbl);
			numberButton[i].setBackground(new Color(98, 77, 125));
			numberButton[i].setForeground(Color.WHITE);
			numberButton[i].setRolloverEnabled(false);
			numberButton[i].setFocusPainted(false);
			getNumberPanel.add( numberButton[i] );
		}
		
		this.add(getNumberPanel, BorderLayout.CENTER);
		
	}
	
	/**
	 * A program belépési pontja, itt hozzuk létre az főablakot és indítjuk el külön szálon az időzítőt
	 * @param args a program futásának paraméterei
	 */
	public static void main(String[] args) {
		GameController gc = new GameController();
		MainWindow win = new MainWindow(gc);
		win.setVisible(true);
		
		//Időzítő a képernyőfrissítés megadásához
		Render render = new Render(win);
		render.start();
	}

}
