package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.gfx.Window;

public class GameButtonListener implements ActionListener{
	
	private Window win;
	
	public GameButtonListener(Window w) {
		win = w;
	}
	

	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "2 player") {
        	win.newGame(2);
        } else if(e.getActionCommand() == "3 player") {
        	win.newGame(3);
        } else if(e.getActionCommand() == "4 player") {
        	win.newGame(4);
        } else {
        	win.newGame(5);
        }
    } 
	
}
