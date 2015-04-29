package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.gfx.MainWindow;

public class PlayerNumberButtonListener implements ActionListener{
	
	private MainWindow mw;
	
	public PlayerNumberButtonListener(MainWindow mw) {
		this.mw = mw;
	}
	

	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("2")) {
        	mw.newGame(2);
        } else if(e.getActionCommand().equals("3")) {
        	mw.newGame(3);
        } else if(e.getActionCommand().equals("4")) {
        	mw.newGame(4);
        } else {
        	mw.newGame(5);
        }
    } 
	
}
