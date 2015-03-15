package phoebe.game;

import phoebe.basic.Vector;

public class RobotController {

	private Robot actualRobot;
	private Vector inputSpeedVector;
	private Vector jumpDestination;
	private boolean willPlaceOil;
	private boolean willPlaceGlue;
	private Game game;
	private Map map;
	
	public RobotController(Robot r, Game g, Map m){}
	public void toggleOil(){}
	public void toggleGlue(){}
	public void setInputSpeedVector(Vector v){}
	public void nextTurn(){}
	public Robot getActualRobot(){ return null; }
	public Vector getInputSpeedVector(){ return null; }
	public Vector getJumpDestination(){ return null; }
	
}
