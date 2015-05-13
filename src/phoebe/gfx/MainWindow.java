package phoebe.gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import phoebe.control.ActionButtonListener;
import phoebe.control.GameMouseListener;
import phoebe.control.PlayerNumberButtonListener;
import phoebe.game.GameController;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 2764832581974173222L;
	
	private GameController gc;
	private ViewPanel viewPanel;
	private Render render;
	
	private JPanel contentPane;
	private JPanel menuPanel;
	private JPanel gamePanel;
	private JPanel gameControlPanel;
	
	private JButton[] startButtons;
	private JButton btnOil;
	private JButton btnGlue;
	private JButton btnJump;
	private JButton btnRestart;	
	
	/**
	 * Konstruktor, mely beállítja az ablak alapető tulajdonságait, és folytatja a játékot a játékosszám
	 * bekérésének elindításával.
	 * @param gc a játékhoz tartozó GameController
	 */
	public MainWindow() {
		//A fő ablak beállítása
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Phoebe");
		
		//A GameController létrehozása
		gc = new GameController();
		
		//Komponensek inicializálása
		initComponents();
		
		//Menü elindítása
		showMenu();
	}
	
	
	/**
	 * A komponensek inicializálása, elrendezésük beállítása
	 */
	private void initComponents(){
		//Játékosválasztó nézet elemeinek beállítása
		//ContentPane beállítása
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Menü beállítása
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNewGame = new JLabel("New game");
		lblNewGame.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(lblNewGame);
		
		JLabel lblNrOfPlayers = new JLabel("Number of playerzz:");
		lblNrOfPlayers.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNrOfPlayers.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.add(lblNrOfPlayers);
		
		JPanel startGameButtonsPanel = new JPanel();
		menuPanel.add(startGameButtonsPanel);
		
		startButtons = new JButton[4];
		PlayerNumberButtonListener pnbl = new PlayerNumberButtonListener(this);
		for(int i = 0; i <= 3; ++i){
			startButtons[i] = new JButton(Integer.toString(i + 2));
			startButtons[i].addActionListener(pnbl);
			startGameButtonsPanel.add(startButtons[i]);
		}
		
		//Játék nézet elemeinek beállítása
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout(0, 0));
		
		gameControlPanel = new JPanel();
		gameControlPanel.setLayout(new FlowLayout());
		
		btnOil = new JButton("Oil");
		btnGlue = new JButton("Glue");
		btnJump = new JButton("Jump");
		
		btnOil.setBackground(new Color(98, 77, 125));
		btnOil.setForeground(Color.WHITE);
		btnOil.setRolloverEnabled(false);
		btnOil.setFocusPainted(false);
		
		btnGlue.setBackground(new Color(98, 77, 125));
		btnGlue.setForeground(Color.WHITE);
		btnGlue.setRolloverEnabled(false);
		btnGlue.setFocusPainted(false);
		
		btnJump.setBackground(phoebe.basic.Color.RED.toColor());
		btnJump.setForeground(Color.WHITE);
		btnJump.setRolloverEnabled(false);
		btnJump.setFocusPainted(false);
				
		btnRestart = new JButton("New game");
		btnRestart.setBackground(new Color(98, 77, 125));
		btnRestart.setForeground(Color.WHITE);
		btnRestart.setRolloverEnabled(false);
		btnRestart.setFocusPainted(false);
		btnRestart.addActionListener((new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				render.stopSignal();
				viewPanel = null;
				render = null;
				showMenu();
			}
			
		}));
	}
	
	/**
	 * Játékosválasztó menü visszaállítása, a játék végén
	 */
	private void showMenu() {
		contentPane.removeAll();
		
		setBounds(100, 100, 250, 120);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.add(menuPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Játék nézet beállítása, a játék elején
	 */
	private void showGame() {
		contentPane.removeAll();
		
		setBounds(100, 100, 606, 665);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		GameMouseListener mouseListener = new GameMouseListener(gc);
		
		//Ha először indítunk játékot
		if(viewPanel == null){
			viewPanel = new ViewPanel(this);
			viewPanel.addMouseListener(mouseListener);
			viewPanel.addMouseMotionListener(mouseListener);	
			
			ActionButtonListener abl = new ActionButtonListener(gc);
			btnOil.addActionListener(abl);
			btnGlue.addActionListener(abl);
			btnJump.addActionListener(abl);
		}				
		
		viewPanel.init();
		
		render = new Render(this);
		render.start();
		
		btnJump.setBackground(phoebe.basic.Color.RED.toColor());
		gameControlPanel.removeAll();
		gameControlPanel.add(btnOil);
		gameControlPanel.add(btnJump);
		gameControlPanel.add(btnGlue);
		
		gamePanel.add(viewPanel, BorderLayout.CENTER);
		gamePanel.add(gameControlPanel, BorderLayout.SOUTH);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		
		repaint();
	}
	
	/**
	 * Új játék indítása
	 * @param nrOfPlayers játékosok száma
	 */
	public void newGame(int nrOfPlayers){
		gc.newGame(nrOfPlayers);
		showGame();
	}
	
	/**
	 * Játék vége, kiteszi a Restart gombot
	 */
	public void endGame() {		
		gameControlPanel.removeAll();
		gameControlPanel.add(btnRestart);
		gameControlPanel.revalidate();
		
		repaint();
	}
	
	/**
	 * Visszaadja az ablak ViewPanel-jét
	 * @return viewPanel
	 */
	public ViewPanel getViewPanel(){
		return viewPanel;
	}
	
	/**
	 * Visszaadja a gameController példányt
	 * @return gc
	 */
	public GameController getGameController(){
		return gc;
	}
	
	/**
	 * A program belépési pontja, itt hozzuk létre az főablakot és indítjuk el külön szálon az időzítőt
	 * @param args a program futásának paraméterei
	 */
	public static void main(String[] args) {		
		MainWindow win = new MainWindow();
		win.setVisible(true);
	}

}
