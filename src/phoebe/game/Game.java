package phoebe.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
				Vector startingVector = new Vector(rand.nextDouble() * Map.size, rand.nextDouble() * Map.size);
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
	 * A sorrendben következő robotot adja vissza. Ha csak egy robot maradt, a játék véget ér.
	 * Ha az utolsó robot lépett, növeli a körök számát (ami ha eléri a maximális számot a 
	 * játék véget ér), létrehozza esetlegesen az új takarítórobotot, továbbá az összes takarítórobotot dolgoztatja.
	 * @return A sorrendben következő robot
	 */
	public synchronized PlayerRobot getNextPlayerRobot(){
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
		
		//UserIO.println("Ez a(z) " + (round) + ". kör.");
		//UserIO.println("A " + playerRobots.get(actualRobotNumber).getColor().toString() + " Robot következik.");
		
		return playerRobots.get(actualRobotNumber);
	}
	
	
	
	
	private synchronized void spawnCleanerRobot() {
		Random r = new Random();
		
		// Spawnolási esély: ([aktuális foltok száma] - [aktuális takarítók száma]) * 20%
		double chance = (double) (map.getSmudges().size() - cleanerRobots.size()) * r.nextDouble();
		
		// Ha új robotot spawnolunk
		if(chance <= 0.2 && chance > 0){
			double x = r.nextInt(2) * 10;
			double y = r.nextInt(2) * 10;
			Vector p = new Vector(x, y);
			cleanerRobots.add(new CleanerRobot(p, map, this));
			//UserIO.println("Takarítórobot indul útnak innen: " + p.getX() + ", " + p.getY());
		}
	}
	
	
	
	/**
	 * Törli az aktuális robotot
	 */
	public synchronized void deleteActualRobot(){
		// Kitörli a robotot a listából 
		playerRobots.remove( actualRobotNumber );
		// Visszalép, hogy a következő robot helyes legyen
		actualRobotNumber--;
	}
	
	
	/**
	 * Megsemmisíti az adott pozícion lévő takarítórobotokat
	 * @param p az adott pozíció vektora
	 */
	public synchronized List<CleanerRobot> collideCleanerRobotsWithActual(){
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
	public synchronized List<PlayerRobot> collidePlayerRobotsWithActual() {
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
					if(playerRobots.indexOf(pr) <= actualRobotNumber)
						actualRobotNumber--;
					playerRobots.remove(pr);					
					robotIterator.remove();
					
					//Az aktuális a kettő átlagával megy tovább, és ütközhet másokkal
					actualRobot.setSpeedVector(avgSpeed);
					
				} else if(pr.getSpeedVector().length() == actualRobot
						.getSpeedVector().length()){
					
					//Ha egyforma gyorsak mindeketten összetörnek
					deleted.add(pr);
					if(playerRobots.indexOf(pr) <= actualRobotNumber)
						actualRobotNumber--;
					playerRobots.remove(pr);							
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
	public synchronized boolean isRobotCollision(Robot robot) {
		for(PlayerRobot r : playerRobots){
			// Önmagával ne ütközzön
			if (r == robot) {
				continue;
			}
			if(r.overlaps(robot)){
				return true;
			}
		}
		for(CleanerRobot cr : cleanerRobots){
			// Önmagával ne ütközzön
			if (cr == robot) {
				continue;
			}
			if(cr.overlaps(robot)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * A játék végetérését megvalósító metódus, kiválasztja a nyertest és leállítja a játékot
	 */
	private synchronized void gameEnd(){		
		//Megnézzük az összes robot közül kinek a legnagyobb a megtett távolsága
		PlayerRobot winner = playerRobots.get(0);
		for (PlayerRobot i : playerRobots) {
			if ( i.getDistance() > winner.getDistance() ) {
				winner = i;
			}
		}
		
		//Értesítjük gameControllert a játék végéről
		gameController.gameEnded(winner);		
	}
	
	
	/**
	 * A játékban lévő játékosrobotok lekérdezését szolgáló metódus
	 * @return a játékban lévő játékosrobotok
	 */
	public synchronized List<PlayerRobot> getPlayerRobots(){
		return playerRobots;
	}
	
	
	/**
	 * A játékban lévő takarítórobotok lekérdezését szolgáló metódus
	 * @return a játékban lévő takarítórobotok
	 */
	public synchronized List<CleanerRobot> getCleanerRobots() {
		return cleanerRobots;
	}
	
	
	/**
	 * A játékban lévő robotok lekérdezését szolgáló metódus
	 * @return a játékban lévő robotok
	 */
	public synchronized List<Robot> getRobots() {
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
	public synchronized Map getMap() {
		return map;
	}
	
	
	/**
	 * Visszaadja az itt tárolt robotvezérlőt
	 * @return Robotvezérlő
	 */
	public synchronized RobotController getRobotController() {
		return robotController;
	}
	
}
