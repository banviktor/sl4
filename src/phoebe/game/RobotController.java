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
		
		this.inputSpeedVector = new Vector(0,0);
		this.jumpDestination = actualRobot.getPosition();
		this.willPlaceOil = false;
		this.willPlaceGlue = false;
		this.speedModificationDisabled = false;
		
		Log.exitFunction();
	}
	
	/**
	 * Megv�ltoztatja az olajfolt lehelyez�s�t jelz� flaget
	 */
	public void toggleOil(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "toggleOil");
		
		//Szkeleton megval�s�t�s: ez a f�ggv�nyh�v�s itt val�sul meg, de m�g nincs mellette felt�telvizsg�lat
		actualRobot.getOilNumber();
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Megv�ltoztatja az ragacsfolt lehelyez�s�t jelzo flaget
	 */
	public void toggleGlue(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		//Szkeleton megval�s�t�s: ez a f�ggv�nyh�v�s itt val�sul meg, de m�g nincs mellette felt�telvizsg�lat
		actualRobot.getGlueNumber();
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Be�ll�tja a felhaszn�l�t�l kapott m�dos�t�vektor attrib�tum�t,
	 * hogy azzal sz�molhasson az oszt�ly
	 * @param v a felhaszn�l�t�l kapott vektor
	 */
	public void setInputSpeedVector(Vector v){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(RobotController.class, "setInputSpeedVector", v.toString());
		
		if (!UserInput.getBoolean("Olajfolton �llunk?", true)) {
			inputSpeedVector = v.normalized();
			jumpDestination = actualRobot.getPosition().add(actualRobot.getSpeedVector().add(inputSpeedVector));
		}
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "nextTurn");
		
		// Szkeleton megval�s�t�s, megk�rdezz�k a felhaszn�l�t�, hogy rakunk-e le valamilyen foltot
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
		
		// Vizsg�ljuk, hogy p�ly�ra est�nk-e
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a p�ly�r�l
			game.deleteActualRobot();
		}
		
		// Elk�rj�k a k�vetkez� robotot
		actualRobot = game.getNextRobot();		
		if(actualRobot != null){
			inputSpeedVector = new Vector(0, 0);
			this.jumpDestination = actualRobot.getPosition().add(actualRobot.getSpeedVector());
			willPlaceOil = false;
			willPlaceGlue = false;
			
			// A robot alatt l�v� foltok kifejtik hat�sukat
			List<Smudge> modifier = map.getSmudgesAt(actualRobot.getPosition());
			for(Smudge s : modifier){
				s.action(actualRobot);
			}
			
			speedModificationDisabled = actualRobot.isSpeedModificationDisabled();			
		}
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	 
	/**
	 * Az �pp akt�v robot referenci�j�t adja vissza
	 * @return akt�v robot regeferenci�ja
	 */
	public Robot getActualRobot(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "getActualRobot");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(actualRobot.toString());
		return actualRobot;
	}
	
	/**
	 * Visszaadja az elt�rolt m�os�t�vektort
	 * @return a m�dos�t�vektor referenci�ja
	 */
	public Vector getInputSpeedVector(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "getInputSpeedVector");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(inputSpeedVector.toString());
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktu�lis robot ugr�s�nak c�lj�t
	 * @return az ugr�s c�lj�nak vektora
	 */
	public Vector getJumpDestination(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "getJumpDestination");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(jumpDestination.toString());
		return jumpDestination;
	}
	
}
