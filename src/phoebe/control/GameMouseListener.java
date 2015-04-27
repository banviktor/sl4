package phoebe.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import phoebe.basic.Vector;
import phoebe.game.Map;
import phoebe.game.PlayerRobot;
import phoebe.game.RobotController;

public class GameMouseListener extends MouseAdapter implements MouseMotionListener{

	private RobotController rc;
		
	public GameMouseListener(RobotController rc) {
		this.rc = rc;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		PlayerRobot actualRobot = rc.getActualRobot();
		double xCoord = (Map.size*e.getX()/600)-actualRobot.getPosition().getX();
		double yCoord = (Map.size*e.getY()/600)-actualRobot.getPosition().getY();
		Vector input = new Vector(xCoord, yCoord);
		System.out.println(input.getX() + " " + input.getY());
		rc.setInputSpeedVector(input);
		System.out.println("input set");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
