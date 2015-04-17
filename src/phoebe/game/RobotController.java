package phoebe.game;

import java.util.ArrayList;
import java.util.List;

import phoebe.Log;
import phoebe.UserIO;
import phoebe.basic.Vector;

public class RobotController {

	private PlayerRobot actualRobot;
	private Vector inputSpeedVector;
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
		this.actualRobot = r;
		this.game = g;
		this.map = m;
		
		this.willPlaceOil = false;
		this.willPlaceGlue = false;
		
		this.inputSpeedVector = new Vector(0, 0);
	}
	
	/**
	 * Megváltoztatja az olajfolt lehelyezését jelzo flaget
	 */
	public void toggleOil(){
		if (actualRobot.getOilNumber() > 0) {
			willPlaceOil = !willPlaceOil;
			willPlaceGlue = false;
			UserIO.println("Olajfolt " + (willPlaceOil?"be.":"ki.") );
			UserIO.println("Ragacs ki.");
		}
	}
	
	/**
	 * Megváltoztatja az ragacsfolt lehelyezését jelzo flaget
	 */
	public void toggleGlue(){
		if (actualRobot.getGlueNumber() > 0) {
			willPlaceGlue = !willPlaceGlue;
			willPlaceOil = false;
			UserIO.println("Ragacs " + (willPlaceGlue?"be.":"ki.") );
			UserIO.println("Olajfolt ki.");
		}
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
		//Lerakja a kiválasztott foltot
		if(willPlaceOil){
			map.addSmudge( actualRobot.createOil() );
			UserIO.println("Olajfolt lehelyezve.");
		} else if(willPlaceGlue){
			map.addSmudge( actualRobot.createGlue() );
			UserIO.println("Ragacsfolt lehelyezve.");
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
			List<CleanerRobot> deletedCleaners = new ArrayList<CleanerRobot>(
					game.deleteCleanerRobotsAt(actualRobot.getPosition()));
		
			//Ha egy másik játékos robotjával ütközik
			List<PlayerRobot> deletedPlayers = new ArrayList<PlayerRobot>(
					game.collidePlayerRobotsWithActual());
			
			//Ha volt ütközés
			if((deletedCleaners.size() + deletedPlayers.size()) > 0){
				UserIO.println("Ez a robot a következőkkel ütközött:");
				//Kiírjuk a játékosokat
				for(PlayerRobot pr : deletedPlayers){
					if(!pr.equals(actualRobot)){
						UserIO.println("\t" + pr.getColor().toString());
					}
				}
				//Kiírjuk a takarítókat (jelenleg sok értelme nincs)
				for(CleanerRobot cr : deletedCleaners){
					UserIO.println("\t" + "Takarítórobot");
				}
			}
			
		}
		
		//Következő robot betöltése, értékek alaphelyzetbe állítása
		actualRobot = game.getNextPlayerRobot();
		if(actualRobot != null){
			inputSpeedVector = new Vector(0, 0);
			willPlaceOil = false;
			willPlaceGlue = false;
			
			//Az új robot előkészítése
			List<Smudge> modifier = map.getSmudgesAt(actualRobot.getPosition());
			for(Smudge s : modifier){
				s.action(actualRobot);
			}
		}		
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
		return new Vector(inputSpeedVector.getX()
				+actualRobot.getPosition().getX()
				+actualRobot.getSpeedVector().getX(), 
				inputSpeedVector.getY()
				+actualRobot.getPosition().getY()
				+actualRobot.getSpeedVector().getY());
	}
	
}
