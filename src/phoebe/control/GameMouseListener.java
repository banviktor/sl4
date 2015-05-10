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
	
	//Ismernie kell a GameControllert, hogy irányítani tudja a játékot
	private GameController gc;
		
	public GameMouseListener(GameController gc) {
		this.gc = gc;
	}
	
	/**
	 * Kattintás esetén beállítja az inputvectort
	 * @param e a függvényhívást kiváltó esemény
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseInput(e);
	}

	/**
	 * Az egér húzása esetén állítja be az inputvectort
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseInput(e);	
	}
	
	/**
	 * A közös inputvectort beállító függvény
	 * @param e a kiváltó esemény
	 */
	private void mouseInput(MouseEvent e){
		RobotController rc = gc.getRobotController();
		PlayerRobot actualRobot = rc.getActualRobot();
		
		// Amennyiben nincsen robot, nincsen minek inputvectort adni
		if(actualRobot == null)
			return;
		
		// Kiszámoljuk a kattintás koordinátáit a játék koordinátarendszerében
		Vector click = new Vector((Map.size*e.getX()/600), (Map.size*e.getY()/600));
		
		// Létrehozzuk az inputvectort
		Vector input = new Vector(actualRobot.getPosition(), click);
		
		// Amennyiben a roboton kattintunk, az input vector 0 hosszú lesz
		if(click.distance(actualRobot.getPosition()) < actualRobot.getRadius()){
			input = new Vector(0, 0);
		}
		
		// Átadjuk az inputvectort
		rc.setInputSpeedVector(input);
	}

}
