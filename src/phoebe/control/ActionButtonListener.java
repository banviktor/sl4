package phoebe.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import phoebe.game.GameController;

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
						String action = e.getActionCommand();
						if(action.equals("Jump")){
							gc.getRobotController().nextTurn();
							if(!gc.isRunning()){
								//Ha a játék véget ért
							}
						}else if(action.equals("Glue")){
							gc.getRobotController().toggleGlue();
						}else if(action.equals("Oil")){
							gc.getRobotController().toggleOil();
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
