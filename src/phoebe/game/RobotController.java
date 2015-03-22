package phoebe.game;

import java.util.List;

import phoebe.Log;
import phoebe.basic.Vector;

public class RobotController {

	private Robot actualRobot;
	private Vector inputSpeedVector;
	private Vector jumpDestination;
	private boolean willPlaceOil;
	private boolean willPlaceGlue;
	private Game game;
	private Map map;
	
	/**
	 * A RobotController osztály konstruktora
	 * @param r a játék kezdetén az elso robot // TODO ez így van?
	 * @param g a játékot reprezentáló objektum referenciája
	 * @param m a pályát reprezentáló objektum referenciája
	 */
	public RobotController(Robot r, Game g, Map m){
		Log.enterFunction(RobotController.class, "RobotController", r.toString() +", Game" + ", Map");
		
		this.actualRobot = r;
		this.game = g;
		this.map = m;
		
		this.willPlaceOil = false;
		this.willPlaceGlue = false;
		
		Log.exitFunction();
	}
	
	/**
	 * Megváltoztatja az olajfolt lehelyezését jelzo flaget
	 */
	public void toggleOil(){
		Log.enterFunction(RobotController.class, "toggleOil");
		
		willPlaceOil = !willPlaceOil;
		
		Log.exitFunction();
	}
	
	/**
	 * Megváltoztatja az ragacsfolt lehelyezését jelzo flaget
	 */
	public void toggleGlue(){
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		willPlaceGlue = !willPlaceGlue;
		
		Log.exitFunction();
	}
	
	/**
	 * Beállítja a felhasználótól kapott módosítóvektor attribútumát,
	 * hogy azzal számolhasson az osztály
	 * @param v a felhasználótól kapott vektor
	 */
	public void setInputSpeedVector(Vector v){
		Log.enterFunction(RobotController.class, "setInputSpeedVector");
		
		inputSpeedVector = v;
		
		Log.exitFunction();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		Log.enterFunction(RobotController.class, "nextTurn");
		// TODO lerakni a foltokat
		actualRobot.jump();
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a pályáról
			game.deleteActualRobot();
		}
		
		actualRobot = game.getNextRobot();
		inputSpeedVector = new Vector(0, 0);
		willPlaceOil = false;
		willPlaceGlue = false;
		
		List<Smudge> modifier = map.getSmudgesAt(actualRobot.getPosition());
		for(Smudge s : modifier){
			s.action(actualRobot);
		}
		
		Log.exitFunction();
	}
	 
	/**
	 * Az épp aktív robot referenciáját adja vissza
	 * @return aktív robot regeferenciája
	 */
	public Robot getActualRobot(){
		Log.enterFunction(RobotController.class, "getActualRobot");
		
		Log.exitFunction(actualRobot.toString());
		return actualRobot;
	}
	
	/**
	 * Visszaadja az eltárolt móosítóvektort
	 * @return a módosítóvektor referenciája
	 */
	public Vector getInputSpeedVector(){
		Log.enterFunction(RobotController.class, "getInputSpeedVector");
		
		Log.exitFunction(inputSpeedVector.toString());
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktuális robot ugrásának célját
	 * @return az ugrás céljának vektora
	 */
	public Vector getJumpDestination(){
		Log.enterFunction(RobotController.class, "getJumpDestination");
		
		Log.exitFunction(jumpDestination.toString());
		return jumpDestination;
	}
	
}
