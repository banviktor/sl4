package phoebe.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import phoebe.game.GameController;
import phoebe.game.RobotController;

public class ActionButtonListener  implements ActionListener{
	private GameController gc;
	
	public ActionButtonListener(GameController gc) {
		this.gc = gc;
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		/*
		 * Külön szálon fut, hogy ne foglalja le hosszan a GUI-t.
		 * Ha már fut egy event-kezelés, akkor nem csinál semmit. Az ez alatt érkező eventeket eldobjuk.
		 * (Ugrás közben nem lehet foltot állítani, sem ugrani.)
		 * Ha szabad a vezérlés, az actionCommand-nek megfelelően foltot tesz vagy ugrik.
		 */
		if(ControlState.get()){
			(new Thread(){
				public void run(){
					try {
						JButton button = (JButton) e.getSource();
						RobotController rc = gc.getRobotController();
						String action = e.getActionCommand();
						// Ugrás
						if(action.equals("Jump")){
							// A robot elugrik
							rc.nextTurn();
							// Jump gomb robotszínű
							button.setBackground(rc.getActualRobot().getColor().toColor());
							// Oil gomb alapszínű
							button.getParent().getComponent(0).setBackground(new Color(98, 77, 125));
							// Glue gomb alapszínű
							button.getParent().getComponent(2).setBackground(new Color(98, 77, 125));
						// Ragacs
						}else if(action.equals("Glue")){
							// A robot ragacsot fog tenni
							rc.toggleGlue();
							// Ha tud ragacsot tenni, a Glue gomb zöld, az Oil alapszínű lesz
							if(rc.getWillPlaceGlue()){
								button.setBackground(Color.GREEN);
								button.getParent().getComponent(0).setBackground(new Color(98, 77, 125));
							}
							else{
								button.setBackground(new Color(98, 77, 125));
							}
						// Olaj
						}else if(action.equals("Oil")){
							// A robot olajat fog letenni
							rc.toggleOil();
							// Ha tud olajat letenni, az Oil gomb fekete, a Glue alapszínű lesz
							if(rc.getWillPlaceOil()){
								button.setBackground(Color.BLACK);
								button.getParent().getComponent(2).setBackground(new Color(98, 77, 125));
							}
							else{
								button.setBackground(new Color(98, 77, 125));
							}
							
						}else{
							//?
						}
					} catch (Exception e) {
					} finally {
						// Ha vége, elengedjük a vezérlést.
						ControlState.release();
					}
				}
			}).start();
		}
	}

}
