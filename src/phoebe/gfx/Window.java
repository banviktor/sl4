package phoebe.gfx;

import java.awt.BorderLayout;
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
		
		this.removeAll();
		
		glue = new JButton("Glue");
		oil = new JButton("Oil");
		jump = new JButton("Jump");
		
		buttonPanel.add(glue);
		buttonPanel.add(jump);
		buttonPanel.add(oil);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(new ViewPanel(),BorderLayout.CENTER);
		
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
