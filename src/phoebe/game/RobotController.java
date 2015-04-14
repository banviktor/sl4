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
	 * A RobotController oszt�ly konstruktora
	 * @param r a j�t�k kezdet�n az elso robot // TODO ez �gy van?
	 * @param g a j�t�kot reprezent�l� objektum referenci�ja
	 * @param m a p�ly�t reprezent�l� objektum referenci�ja
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
		inputSpeedVector = v.normalized();
	}
	
	/**
	 * 
	 */
	public void nextTurn(){
		Log.enterFunction(RobotController.class, "nextTurn");
		
		//Lerakja a kiv�lasztott foltot
		if(willPlaceOil){
			map.addSmudge( actualRobot.createOil() );
		} else if(willPlaceGlue){
			map.addSmudge( actualRobot.createGlue() );
		}
		
		//Elugrik
		actualRobot.jump();
		
		//Ha r�esik takar�t�robotra, �sszet�ri azt
		game.deleteCleanerRobotsAt(actualRobot.getPosition());
		
		//Ha egy m�sik j�t�kos robotj�val �tk�zik
		game.collidePlayerRobotsWithActual();
		
		//Leesik
		if(!map.isOnRoad(actualRobot.getPosition())){
			// A robot leesett a p�ly�r�l
			game.deleteActualRobot();
		}
		
		//K�vetkez� robot bet�lt�se, �rt�kek alaphelyzetbe �ll�t�sa
		actualRobot = game.getNextPlayerRobot();
		inputSpeedVector = new Vector(0, 0);
		willPlaceOil = false;
		willPlaceGlue = false;
		
		//Az �j robot el�k�sz�t�se
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
	public PlayerRobot getActualRobot(){
		return actualRobot;
	}
	
	
	/**
	 * Met�dus annak lek�rdez�s�re, hogy fogunk-e olajfoltot lerakni ugr�skor
	 * @return logikai �rt�k, amely azt adja meg, hogy fogunk-e olajfoltot lerakni
	 */
	public boolean getWillPlaceOil() {
		return willPlaceOil;
	}
	
	
	/**
	 * Met�dus annak lek�rdez�s�re, hogy fogunk-e ragacsfoltot lerakni ugr�skor
	 * @return logikai �rt�k, amely azt adja meg, hogy fogunk-e ragacsfoltot lerakni
	 */
	public boolean getWillPlaceGlue() {
		return willPlaceGlue;
	}
	
	
	/**
	 * Visszaadja az elt�rolt m�os�t�vektort
	 * @return a m�dos�t�vektor referenci�ja
	 */
	public Vector getInputSpeedVector(){
		return inputSpeedVector;
	}
	
	/**
	 * Visszaadja az aktu�lis robot ugr�s�nak c�lj�t
	 * @return az ugr�s c�lj�nak vektora
	 */
	public Vector getJumpDestination(){
		return jumpDestination;
	}
	
}
