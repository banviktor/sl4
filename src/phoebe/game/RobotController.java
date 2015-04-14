package phoebe.game;

import java.util.List;

import phoebe.Log;
import phoebe.basic.Vector;

public class RobotController {

	private PlayerRobot actualRobot;
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
	public RobotController(PlayerRobot r, Game g, Map m){
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
		inputSpeedVector = v.normalized();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		Log.enterFunction(RobotController.class, "nextTurn");
		
		//Lerakja a kiválasztott foltot
		if(willPlaceOil){
			map.addSmudge(new Oil(actualRobot.getPosition()));
		} else if(willPlaceGlue){
			map.addSmudge(new Glue(actualRobot.getPosition()));
		}
		
		//Elugrik
		actualRobot.jump();
		
		//Ha ráesik takarítórobotra, összetöri azt
		game.deleteCleanerRobotsAt(actualRobot.getPosition());
		
		//Leesik
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a pályáról
			game.deleteActualRobot();
		}
		
		//Következő robot betöltése, értékek alaphelyzetbe állítása
		actualRobot = game.getNextPlayerRobot();
		inputSpeedVector = new Vector(0, 0);
		willPlaceOil = false;
		willPlaceGlue = false;
		
		//Az új robot előkészítése
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
		return actualRobot;
	}
	
	
	/**
	 * Metódus annak lekérdezésére, hogy fogunk-e olajfoltot lerakni ugráskor
	 * @return logikai érték, amely azt adja meg, hogy fogunk-e olajfoltot lerakni
	 */
	public boolean getWillPlaceOil() {
		return willPlaceOil;
	}
	
	
	/**
	 * Metódus annak lekérdezésére, hogy fogunk-e ragacsfoltot lerakni ugráskor
	 * @return logikai érték, amely azt adja meg, hogy fogunk-e ragacsfoltot lerakni
	 */
	public boolean getWillPlaceGlue() {
		return willPlaceGlue;
	}
	
	
	/**
	 * Visszaadja az eltárolt móosítóvektort
	 * @return a módosítóvektor referenciája
	 */
	public Vector getInputSpeedVector(){
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktuális robot ugrásának célját
	 * @return az ugrás céljának vektora
	 */
	public Vector getJumpDestination(){
		return jumpDestination;
	}
	
}
