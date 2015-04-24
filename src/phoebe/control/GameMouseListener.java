package phoebe.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import phoebe.game.RobotController;

public class GameMouseListener extends MouseAdapter implements MouseMotionListener{

	private RobotController rc;
		
	public GameMouseListener(RobotController rc) {
		this.rc = rc;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
