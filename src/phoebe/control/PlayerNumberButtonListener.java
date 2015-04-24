package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.gfx.MainWindow;

public class PlayerNumberButtonListener implements ActionListener{
	
	private MainWindow win;
	
	public PlayerNumberButtonListener(MainWindow w) {
		win = w;
	}
	

	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("2 player")) {
        	win.newGame(2);
        } else if(e.getActionCommand().equals("3 player")) {
        	win.newGame(3);
        } else if(e.getActionCommand().equals("4 player")) {
        	win.newGame(4);
        } else {
        	win.newGame(5);
        }
    } 
	
}
