package phoebe.gfx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	JButton glue;
	JButton oil;
	JButton jump;
	
	public Window(){
		initComponents();
		
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initComponents(){
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		glue = new JButton("Glue");
		oil = new JButton("Oil");
		jump = new JButton("Jump");
		
		buttonPanel.add(glue);
		buttonPanel.add(jump);
		buttonPanel.add(oil);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(new ViewPanel(),BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Window win = new Window();
		win.setVisible(true);
	}
}
