package control;

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
        }
    } 
	
}
