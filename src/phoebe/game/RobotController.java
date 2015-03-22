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
	 * A RobotController oszt�ly konstruktora
	 * @param r a j�t�k kezdet�n az elso robot // TODO ez �gy van?
	 * @param g a j�t�kot reprezent�l� objektum referenci�ja
	 * @param m a p�ly�t reprezent�l� objektum referenci�ja
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
	 * Megv�ltoztatja az olajfolt lehelyez�s�t jelzo flaget
	 */
	public void toggleOil(){
		Log.enterFunction(RobotController.class, "toggleOil");
		
		willPlaceOil = !willPlaceOil;
		
		Log.exitFunction();
	}
	
	/**
	 * Megv�ltoztatja az ragacsfolt lehelyez�s�t jelzo flaget
	 */
	public void toggleGlue(){
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		willPlaceGlue = !willPlaceGlue;
		
		Log.exitFunction();
	}
	
	/**
	 * Be�ll�tja a felhaszn�l�t�l kapott m�dos�t�vektor attrib�tum�t,
	 * hogy azzal sz�molhasson az oszt�ly
	 * @param v a felhaszn�l�t�l kapott vektor
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
			// A robot leesett a p�ly�r�l
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
	 * Az �pp akt�v robot referenci�j�t adja vissza
	 * @return akt�v robot regeferenci�ja
	 */
	public Robot getActualRobot(){
		Log.enterFunction(RobotController.class, "getActualRobot");
		
		Log.exitFunction(actualRobot.toString());
		return actualRobot;
	}
	
	/**
	 * Visszaadja az elt�rolt m�os�t�vektort
	 * @return a m�dos�t�vektor referenci�ja
	 */
	public Vector getInputSpeedVector(){
		Log.enterFunction(RobotController.class, "getInputSpeedVector");
		
		Log.exitFunction(inputSpeedVector.toString());
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktu�lis robot ugr�s�nak c�lj�t
	 * @return az ugr�s c�lj�nak vektora
	 */
	public Vector getJumpDestination(){
		Log.enterFunction(RobotController.class, "getJumpDestination");
		
		Log.exitFunction(jumpDestination.toString());
		return jumpDestination;
	}
	
}
