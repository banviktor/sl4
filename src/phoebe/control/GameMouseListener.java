package phoebe.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import phoebe.basic.Vector;
import phoebe.game.GameController;
import phoebe.game.Map;
import phoebe.game.PlayerRobot;
import phoebe.game.RobotController;

public class GameMouseListener extends MouseAdapter implements MouseMotionListener{

	private GameController gc;
		
	public GameMouseListener(GameController gc) {
		this.gc = gc;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseInput(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseInput(e);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void mouseInput(MouseEvent e){
		RobotController rc = gc.getRobotController();
		PlayerRobot actualRobot = rc.getActualRobot();
		if(actualRobot == null)
			return;
		Vector click = new Vector((Map.size*e.getX()/600), (Map.size*e.getY()/600));
		Vector input = new Vector(actualRobot.getPosition(), click);
		if(click.distance(actualRobot.getPosition()) < actualRobot.getRadius()){
			input = new Vector(0, 0);
		}
		rc.setInputSpeedVector(input);
	}

}
