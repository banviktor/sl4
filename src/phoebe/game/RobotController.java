package phoebe.game;

import java.util.List;

import phoebe.Log;
import phoebe.UserIO;
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
		
		if (actualRobot.getOilNumber() > 0) {
			willPlaceOil = !willPlaceOil;
			willPlaceGlue = false;
			UserIO.println("Olajfolt " + (willPlaceOil?"be.":"ki.") );
			UserIO.println("Ragacs ki.");
		}
		Log.exitFunction();
	}
	
	/**
	 * Megváltoztatja az ragacsfolt lehelyezését jelzo flaget
	 */
	public void toggleGlue(){
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		if (actualRobot.getGlueNumber() > 0) {
			willPlaceGlue = !willPlaceGlue;
			willPlaceOil = false;
			UserIO.println("Ragacs " + (willPlaceGlue?"be.":"ki.") );
			UserIO.println("Olajfolt ki.");
		}
		Log.exitFunction();
	}
	
	/**
	 * Beállítja a felhasználótól kapott módosítóvektor attribútumát,
	 * hogy azzal számolhasson az osztály
	 * @param v a felhasználótól kapott vektor
	 */
	public void setInputSpeedVector(Vector v){
		inputSpeedVector = v.normalized();
		UserIO.println("Inputvector: " + v.normalized());
	}
	
	/**
	 * A robot elvégzi a kör végi teendőit, leteszi a foltot, ugrik,
	 * majd a következő robot felkészül a körére
	 */
	public void nextTurn(){
		Log.enterFunction(RobotController.class, "nextTurn");
		
		//Lerakja a kiválasztott foltot
		if(willPlaceOil){
			map.addSmudge( actualRobot.createOil() );
		} else if(willPlaceGlue){
			map.addSmudge( actualRobot.createGlue() );
		}
		
		// Robot sebességének növelése
		actualRobot.setSpeedVector( actualRobot.getSpeedVector().add(inputSpeedVector) );
		
		//Elugrik
		actualRobot.jump();
		
		//Leesik
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a pályáról
			game.deleteActualRobot();
			UserIO.println("A robot leesett.");
		}
		else {
			//Ha ráesik takarítórobotra, összetöri azt
			game.deleteCleanerRobotsAt(actualRobot.getPosition());
		
			//Ha egy másik játékos robotjával ütközik
			game.collidePlayerRobotsWithActual();
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
	 * @return aktív robot referenciája
	 */
	public PlayerRobot getActualRobot(){
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
