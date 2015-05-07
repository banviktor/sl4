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
		if(ControlState.get()){
			(new Thread(){
				public void run(){
					try {
						JButton button = (JButton) e.getSource();
						RobotController rc = gc.getRobotController();
						String action = e.getActionCommand();
						if(action.equals("Jump")){
							rc.nextTurn();
							button.setBackground(rc.getActualRobot().getColor().toColor());
							button.getParent().getComponent(0).setBackground(new Color(98, 77, 125));
							button.getParent().getComponent(2).setBackground(new Color(98, 77, 125));
						}else if(action.equals("Glue")){
							rc.toggleGlue();
							if(rc.getWillPlaceGlue()){
								button.setBackground(Color.GREEN);
								button.getParent().getComponent(0).setBackground(new Color(98, 77, 125));
							}
							else{
								button.setBackground(new Color(98, 77, 125));
							}
						}else if(action.equals("Oil")){
							rc.toggleOil();
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
						ControlState.release();
					}
				}
			}).start();
		}
	}

}
