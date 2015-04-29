package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
						RobotController rc = gc.getRobotController();
						String action = e.getActionCommand();
						if(action.equals("Jump")){
							rc.nextTurn();
						}else if(action.equals("Glue")){
							rc.toggleGlue();
						}else if(action.equals("Oil")){
							rc.toggleOil();
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
