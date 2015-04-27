package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.basic.Vector;
import phoebe.game.RobotController;

public class ActionButtonListener  implements ActionListener{
	private RobotController rc;
	
	public ActionButtonListener(RobotController rc) {
		this.rc = rc;
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if(ControlState.get()){
			(new Thread(){
				public void run(){
					try {
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
