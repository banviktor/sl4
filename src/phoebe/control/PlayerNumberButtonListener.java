package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.gfx.MainWindow;

public class PlayerNumberButtonListener implements ActionListener{
	
	//A MainWindowon keresztül indít új játékot
	private MainWindow mw;
	
	public PlayerNumberButtonListener(MainWindow mw) {
		this.mw = mw;
	}
	
	/**
	 * Attól függően, hogy melyik gombra kattint a felhasználó,
	 * a gomb által mutatott számú játékossal indít új játékot
	 */
	public void actionPerformed(ActionEvent e) {
		
		//A kapott parancs értelmezése
        if(e.getActionCommand().equals("2")) {
        	//A parancsban átadott gomb számának megegyező játékossal indul el a játék
        	mw.newGame(2);
        } else if(e.getActionCommand().equals("3")) {
        	mw.newGame(3);
        } else if(e.getActionCommand().equals("4")) {
        	mw.newGame(4);
        } else {
        	//Ha az előzőek közűl egyik sem, akkor az 5ös gombra kattintottak
        	mw.newGame(5);
        }
    } 
	
}
