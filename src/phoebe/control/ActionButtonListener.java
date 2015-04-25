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
		if(ControlState.get()){
			(new Thread(){
				public void run(){
					try {
						System.out.println("doing some work");
						Thread.sleep(1000);
						System.out.println("work done");
						
					} catch (InterruptedException e) {
					} finally {
						ControlState.release();
					}
				}
			}).start();
		}
	}

}
