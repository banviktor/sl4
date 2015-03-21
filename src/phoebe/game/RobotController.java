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
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "toggleOil");
		
		willPlaceOil = !willPlaceOil;
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Megv�ltoztatja az ragacsfolt lehelyez�s�t jelzo flaget
	 */
	public void toggleGlue(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "toggleGlue");
		
		willPlaceGlue = !willPlaceGlue;
		
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
		
		inputSpeedVector = v;
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(RobotController.class, "nextTurn");
		// get next robot, vagy mi t�rt�nik?
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
