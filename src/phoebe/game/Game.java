package phoebe.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import phoebe.Log;
import phoebe.UserIO;
import phoebe.basic.Color;
import phoebe.basic.Vector;

/**
 * A játékot megvalósító osztály, mely tárolja a robotokat és ütemezi a köröket,
 * észleli a játék végét és megállapítja a nyertest
 */
public class Game {
	
	private int playerNumber;
	private int actualRobotNumber;
	private int round;
	private Map map;
	private GameController gameController;
	private RobotController robotController;
	private List<PlayerRobot> playerRobots;
	private List<CleanerRobot> cleanerRobots;
		
	/**
	 * Konstruktor
	 * @param n a körök száma a játékban
	 * @param m a pálya, amin a játék játszódik
	 * @param gc a játékhoz tartozó gameController
	 */
	public Game(int n, Map m, GameController gc){
		
		playerNumber = n;
		map = m;
		gameController = gc;
		actualRobotNumber = 0;
		playerRobots = new ArrayList<PlayerRobot>();
		cleanerRobots = new ArrayList<CleanerRobot>();
		round = 1;
		
		Random rand = new Random();
		
		// Robotok lerakása a kezdőpozíciójukra
		for (int i=0; i<playerNumber; ++i) {
			PlayerRobot newRobot;
			boolean collision;
			do {
				collision = false;
				Vector startingVector = UserIO.getVector(Color.values()[i].toString() + " Robot kezdőpozíciója", 
						new Vector(rand.nextDouble() * Map.size, rand.nextDouble() * Map.size));
				newRobot = new PlayerRobot( Color.values()[i], startingVector);
				
				// Megnézzük, hogy ütközik-e ezen a pozíción másik robottal
				// Még csak játékosrobotok lehetnek
				for(PlayerRobot p : playerRobots){
					if(p.overlaps(newRobot)){
						collision = true;
						break;
					}
				}
			} while(!map.isOnRoad(newRobot.getPosition()) | collision);
			playerRobots.add(newRobot);
		}
		
		robotController = new RobotController(playerRobots.get(actualRobotNumber), this, map);
		
	}
	
	/**
	 * A sorrendben következő robotot adja vissza, és megjegyzi hogy 
	 * az épp átadott az aktuális robot
	 * @return a sorrendben következő robot
	 */
	public PlayerRobot getNextPlayerRobot(){
		++actualRobotNumber;
		
		//Ha csak egy robot maradt, vége a játéknak. Ez kör közben is előfordulhat.
		if (playerRobots.size() < 2) {
			gameEnd();
			return null;
		}
		
		//Ha a körben az utolsó robot is lépett, új kör kezdődik
		if ( actualRobotNumber >= playerRobots.size() ) {
			actualRobotNumber = 0;
			++round;
			
			if ( round > map.getRounds() ) {
				//Ha vége a játéknak, meghívjuk a hozzá tartozó metódust
				gameEnd();
				return null;
				
			} else {
				//Ha nem ért véget a játék de új kör van, akkor ezt jelezzük a Mapnek
				map.nextRound();
				
				//És új takarítórobotot hozhatunk létre, az aktuálisakat pedig működtetjük
				spawnCleanerRobot();
				for(CleanerRobot cr : cleanerRobots) {
					cr.clear();
				}
			}
		}	
		
		UserIO.println("Ez a(z) " + (round) + ". kör.");
		UserIO.println("A " + playerRobots.get(actualRobotNumber).getColor().toString() + " Robot következik.");
		
		return playerRobots.get(actualRobotNumber);
	}
	
	
	
	
	private void spawnCleanerRobot() {
		Random r = new Random();
		
		// Spawnolási esély: ([aktuális foltok száma] - [aktuális takarítók száma]) * 20%
		double chance = (double) (map.getSmudges().size() - cleanerRobots.size()) * r.nextDouble();
		boolean toSpawn = chance <= 0.2;
		
		// Ha új robotot spawnolunk
		if(UserIO.getBoolean("Legyen új takarítórobot?", toSpawn)){
			double x = r.nextInt(2) * 10;
			double y = r.nextInt(2) * 10;
			Vector p = UserIO.getVector("Az új takarítórobot pozíciója", new Vector(x, y));
			cleanerRobots.add(new CleanerRobot(p, map, this));
			UserIO.println("Takarítórobot indul útnak innen: " + p.getX() + ", " + p.getY());
		}
	}
	
	
	
	/**
	 * Törli az aktuális robotot
	 */
	public void deleteActualRobot(){
		// Kitörli a robotot a listából 
		playerRobots.remove( actualRobotNumber );
		// Visszalép, hogy a következő robot helyes legyen
		actualRobotNumber--;
	}
	
	
	/**
	 * Megsemmisíti az adott pozícion lévő takarítórobotokat
	 * @param p az adott pozíció vektora
	 */
	public List<CleanerRobot> collideCleanerRobotsWithActual(){
		List<CleanerRobot> deleted = new ArrayList<CleanerRobot>();
		
		Iterator<CleanerRobot> robotIterator = cleanerRobots.iterator();
		
		while(robotIterator.hasNext()){
			CleanerRobot cr = robotIterator.next();
			if(cr.overlaps(playerRobots.get(actualRobotNumber))){
				
				//Olajfolt létrehozása
				map.addSmudge(cr.createOil());
				
				//takarítórobot törlése
				deleted.add(cr);
				robotIterator.remove();
			}
		}
		
		return deleted;
	}
	
	
	/**
	 * Megvizsgálja az ütközö robotokat, és kitörli a kisebb sebességüt
	 */
	public List<PlayerRobot> collidePlayerRobotsWithActual() {
		List<PlayerRobot> deleted = new ArrayList<PlayerRobot>();
		PlayerRobot actualRobot = playerRobots.get(actualRobotNumber);
		
		// Létrehozunk a robotokról egy listát, amiben az actualrobot nincs benne
		List<PlayerRobot> robotsWithoutActual = new ArrayList<PlayerRobot>(playerRobots);
		robotsWithoutActual.remove(actualRobot);
		
		// Ennek a listának az iterátorával haladunk
		Iterator<PlayerRobot> robotIterator = robotsWithoutActual.iterator();
		
		while(robotIterator.hasNext()){
			PlayerRobot pr = robotIterator.next();
			if(pr.overlaps(actualRobot)){
				if(pr.getSpeedVector().length() > actualRobot.getSpeedVector().length()){					
					Vector avgSpeed = actualRobot.getSpeedVector()
							.add(pr.getSpeedVector()).multiply(0.5);
					
					// Amennyiben a másik robot a gyorsabb az épp ugró robot összetörik
					deleted.add(actualRobot);
					deleteActualRobot();
					
					// A másik a kettő átlagával megy tovább
					pr.setSpeedVector(avgSpeed);
					
					//Véget ér az iteráció, mivel az ugró megsemmisült
					return deleted;
					
				} else if(pr.getSpeedVector().length() < actualRobot.
						getSpeedVector().length()){
					
					Vector avgSpeed = actualRobot.getSpeedVector()
							.add(pr.getSpeedVector()).multiply(0.5);
					
					//Amennyiben az aktuális robot a gyorsabb a másik robot törik össze
					deleted.add(pr);
					playerRobots.remove(pr);
					if(playerRobots.indexOf(pr) <= actualRobotNumber)
						actualRobotNumber--;
					robotIterator.remove();
					
					//Az aktuális a kettő átlagával megy tovább, és ütközhet másokkal
					actualRobot.setSpeedVector(avgSpeed);
					
				} else if(pr.getSpeedVector().length() == actualRobot
						.getSpeedVector().length()){
					
					//Ha egyforma gyorsak mindeketten összetörnek
					deleted.add(pr);
					playerRobots.remove(pr);
					if(playerRobots.indexOf(pr) <= actualRobotNumber)
						actualRobotNumber--;
					robotIterator.remove();
					
					//Ha az ugró egyedül maradt, ő nyer, ezért akkor életben hagyjuk
					if (playerRobots.size() > 1) {
						deleted.add(actualRobot);
						deleteActualRobot();
					}
					
					//Az iteráció véget ér
					return deleted;
					
				}
			}
		}
		return deleted;
	}
	
	
	/**
	 * Megállapítja, hogy az adott pozíción van-e bármilyen robot
	 * @param p az adott pozíció vektora
	 * @return tartózkodik-e robot az adott helyen
	 */
	public boolean isRobotAt(Vector p) {
		for(PlayerRobot r : playerRobots){
			if(r.isAt(p)){
				return true;
			}
		}
		for(CleanerRobot cr : cleanerRobots){
			if(cr.isAt(p)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	private void gameEnd(){
		
		//Megnézzük az összes robot közül kinek a legnagyobb a megtett távolsága
		PlayerRobot winner = playerRobots.get(0);
		for (PlayerRobot i : playerRobots) {
			if ( i.getDistance() > winner.getDistance() ) {
				winner = i;
			}
		}
		
		//Értesítjük gameControllert a játék végéről
		gameController.gameEnded();
		
		//Kiírjuk a nyertest
		UserIO.println("A " + winner.getColor() + " Robot nyert!");
		Log.writeLine("The winner is Robot " + winner.getColor().toString() + "!!" );
		
	}
	
	
	/**
	 * A játékban lévő játékosrobotok lekérdezését szolgáló metódus
	 * @return a játékban lévő játékosrobotok
	 */
	public List<PlayerRobot> getPlayerRobots(){
		return playerRobots;
	}
	
	
	/**
	 * A játékban lévő takarítórobotok lekérdezését szolgáló metódus
	 * @return a játékban lévő takarítórobotok
	 */
	public List<CleanerRobot> getCleaningRobots() {
		return cleanerRobots;
	}
	
	
	/**
	 * A játékban lévő robotok lekérdezését szolgáló metódus
	 * @return a játékban lévő robotok
	 */
	public List<Robot> getRobots() {
		// Létrehozunk egy listát a játékosrobotokkal
		List<Robot> robots = new ArrayList<Robot>(playerRobots);
		
		// Hozzáadjuk a listához a ttakaítórobotokat is
		for(CleanerRobot cr : cleanerRobots) {
			robots.add(cr);
		}
		
		return robots;
	}
	
	
	/**
	 * Visszaadja az itt tárolt pályát
	 * @return Pálya
	 */
	public Map getMap() {
		return map;
	}
	
	
	/**
	 * Visszaadja az itt tárolt robotvezérlőt
	 * @return Robotvezérlő
	 */
	public RobotController getRobotController() {
		return robotController;
	}
	
}
