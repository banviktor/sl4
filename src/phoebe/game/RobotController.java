package phoebe.game;

import java.util.List;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class RobotController {

	private Robot actualRobot;
	private Vector inputSpeedVector;
	private Vector jumpDestination;
	private boolean willPlaceOil;
	private boolean willPlaceGlue;
	private boolean speedModificationDisabled;
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
		
		this.inputSpeedVector = new Vector(0,0);
		this.jumpDestination = actualRobot.getPosition();
		this.willPlaceOil = false;
		this.willPlaceGlue = false;
		this.speedModificationDisabled = false;
		
		Log.exitFunction();
	}
	
	/**
	 * Megváltoztatja az olajfolt lehelyezését jelzõ flaget
	 */
	public void toggleOil(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "toggleOil");
		
		//Szkeleton megvalósítás: ez a függvényhívás itt valósul meg, de még nincs mellette feltételvizsgálat
		actualRobot.getOilNumber();
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Megváltoztatja az ragacsfolt lehelyezését jelzo flaget
	 */
	public void toggleGlue(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		//Szkeleton megvalósítás: ez a függvényhívás itt valósul meg, de még nincs mellette feltételvizsgálat
		actualRobot.getGlueNumber();
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Beállítja a felhasználótól kapott módosítóvektor attribútumát,
	 * hogy azzal számolhasson az osztály
	 * @param v a felhasználótól kapott vektor
	 */
	public void setInputSpeedVector(Vector v){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(RobotController.class, "setInputSpeedVector", v.toString());
		
		if (!UserInput.getBoolean("Olajfolton állunk?", true)) {
			inputSpeedVector = v.normalized();
			jumpDestination = actualRobot.getPosition().add(actualRobot.getSpeedVector().add(inputSpeedVector));
		}
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "nextTurn");
		
		// Szkeleton megvalósítás, megkérdezzük a felhasználótó, hogy rakunk-e le valamilyen foltot
		if (UserInput.getBoolean("Fogunk lerakni olajat?", false)) {
			map.addSmudge(actualRobot.createOil());
		} else {
			if (UserInput.getBoolean("Fogunk lerakni ragacsot?", false)) {
				map.addSmudge(actualRobot.createGlue());
			}
		}
		
		// Ugrunk a robottal
		actualRobot.setSpeedVector(actualRobot.getSpeedVector().add(inputSpeedVector));
		actualRobot.jump();
		
		// Vizsgáljuk, hogy pályára estünk-e
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a pályáról
			game.deleteActualRobot();
		}
		
		// Elkérjük a következõ robotot
		actualRobot = game.getNextRobot();		
		if(actualRobot != null){
			inputSpeedVector = new Vector(0, 0);
			this.jumpDestination = actualRobot.getPosition().add(actualRobot.getSpeedVector());
			willPlaceOil = false;
			willPlaceGlue = false;
			
			// A robot alatt lévõ foltok kifejtik hatásukat
			List<Smudge> modifier = map.getSmudgesAt(actualRobot.getPosition());
			for(Smudge s : modifier){
				s.action(actualRobot);
			}
			
			speedModificationDisabled = actualRobot.isSpeedModificationDisabled();			
		}
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	 
	/**
	 * Az épp aktív robot referenciáját adja vissza
	 * @return aktív robot regeferenciája
	 */
	public Robot getActualRobot(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "getActualRobot");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(actualRobot.toString());
		return actualRobot;
	}
	
	/**
	 * Visszaadja az eltárolt móosítóvektort
	 * @return a módosítóvektor referenciája
	 */
	public Vector getInputSpeedVector(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "getInputSpeedVector");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(inputSpeedVector.toString());
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktuális robot ugrásának célját
	 * @return az ugrás céljának vektora
	 */
	public Vector getJumpDestination(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(RobotController.class, "getJumpDestination");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(jumpDestination.toString());
		return jumpDestination;
	}
	
}
