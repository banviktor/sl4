package phoebe.game;

import java.util.List;

public class Game {
	
	private GameController gameController;
	private int playerNumber;
	private int actualRobotNumber;
	private int turnsRemaining;
	private Map map;
	private List<Robot> robots;
	
	public Game(int n, Map m, GameController gc){
		playerNumber = n;
		map = m;
		gameController = gc;
	}
	public Robot getNextRobot(){ return null; }
	public void deleteActualRobot(){}
	public void gameEnd(){}
	public List<Robot> getRobots(){ return null; }
	
}
