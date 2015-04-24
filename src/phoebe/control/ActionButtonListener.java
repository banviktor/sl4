package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.game.RobotController;

public class ActionButtonListener  implements ActionListener{

	private RobotController rc;
	
	
	public ActionButtonListener(RobotController rc) {
		this.rc = rc;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
