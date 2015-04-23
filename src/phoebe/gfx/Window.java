package phoebe.gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import phoebe.control.GameButtonListener;
import phoebe.game.GameController;

public class Window extends JFrame{
	private static final long serialVersionUID = 2764832581974173222L;
	
	JButton glue;
	JButton oil;
	JButton jump;
	JPanel buttonPanel;
	GameController gc;
	
	public Window(GameController gc) {
		initComponents();
		
		this.setSize(616, 675); // így a ViewPanel 600x600-as lesz
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.gc = gc;
		
		getPlayerNumber();
	}
	
	private void initComponents(){
		this.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
	}
	
	public void newGame(int n) {
		gc.newGame(n);
		
		getContentPane().removeAll();
		getContentPane().repaint();
		
		glue = new JButton("Glue");
		oil = new JButton("Oil");
		jump = new JButton("Jump");
		
		setUpButtons();
		
		buttonPanel.add(glue);
		buttonPanel.add(jump);
		buttonPanel.add(oil);
		buttonPanel.setVisible(true);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(new ViewPanel(gc),BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private void setUpButtons(){
		glue.setBackground(new Color(98, 77, 125));
		glue.setForeground(Color.WHITE);
        glue.setFocusPainted(false);
		oil.setBackground(new Color(98, 77, 125));
		oil.setForeground(Color.WHITE);
        oil.setFocusPainted(false);
		jump.setBackground(new Color(98, 77, 125));
		jump.setForeground(Color.WHITE);
        jump.setFocusPainted(false);
	}
	
	public void getPlayerNumber () {
		JPanel getNumberPanel = new JPanel();
		getNumberPanel.setLayout(new FlowLayout());
		
		JButton[] numberButton = new JButton[4];
		for(int i=0; i<4; ++i) {
			numberButton[i] = new JButton(Integer.toString(i+2));
			numberButton[i].setActionCommand((i+2) + " player");
			numberButton[i].addActionListener(new GameButtonListener(this));
			getNumberPanel.add( numberButton[i] );
		}
		
		this.add(getNumberPanel, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		Window win = new Window(gc);
		win.setVisible(true);
	}

}
