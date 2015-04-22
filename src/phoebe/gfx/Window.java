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
	JButton glue;
	JButton oil;
	JButton jump;
	JPanel buttonPanel;
	GameController gc;
	
	public Window(GameController gc) {
		initComponents();
		
		this.setSize(300, 300);
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
		//gc.newGame(n);
		
		getContentPane().removeAll();
		getContentPane().repaint();
		
		glue = new JButton("Glue");
		glue.setVisible(true);
		oil = new JButton("Oil");
		jump = new JButton("Jump");
		
		setUpButtons();
		
		buttonPanel.add(glue);
		buttonPanel.add(jump);
		buttonPanel.add(oil);
		buttonPanel.setVisible(true);
		
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		getContentPane().add(new ViewPanel(),BorderLayout.CENTER);
		
		
		
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
		JButton twoPlayerButton = new JButton("2 játékos");
		twoPlayerButton.setActionCommand("2 player");
		twoPlayerButton.addActionListener(new GameButtonListener(this));
		this.add(twoPlayerButton, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		Window win = new Window(gc);
		win.setVisible(true);
	}

}
