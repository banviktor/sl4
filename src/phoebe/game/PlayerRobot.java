package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Color;
import phoebe.basic.Vector;

public class PlayerRobot extends Robot {
	
	private Color color;
	
	private double distance;
	private int oilNumber;
	private int glueNumber;
	private Vector speedVector;
	
	private boolean speedHalved;
	private boolean speedModificationDisabled;

	
	/**
	 * A robot konstruktora
	 * @param c a robot színe
	 * @param p a robot kezdeti helye
	 */
	public PlayerRobot(Color c, Vector p){
		super(p);
		
		this.color = c;
		
		this.oilNumber = 3;
		this.glueNumber = 3;
		this.distance = 0;
		
		this.speedVector = new Vector(0, 0);
		
		this.speedHalved = false;
		this.speedModificationDisabled = false;
	}
	
	
	/**
	 * A metódus eldönti, hogy a 0.45 egységnyi sugarú robot az adott pozíción található-e
	 * @param p a megadott pozíció
	 * @return	logikai érték, mely megadja, hogy a robot az adott pozíción van-e
	 */
	@Override
	public boolean isAt(Vector p) {
		return (position.distance(p) < 0.45);
	}
	
	
	/**
	 * Lerak egy olajfoltot
	 * @return a létrehozott olajfolt referenciájával tér vissza
	 */
	@Override
	public Oil createOil(){
		Oil o = new Oil(position); 
		--oilNumber;
		return o;
	}
	
	/**
	 * Lerak egy ragacsfoltot
	 * @return a létrehozott ragacsfolt referenciájával tér vissza
	 */
	public Glue createGlue(){
		Glue g = new Glue(position); 
		--glueNumber;
		return g;
	}
	
	/**
	 * Beállítja a sebességvektort
	 * @param v az új sebességvektor
	 */
	public void setSpeedVector(Vector v){
		speedVector = v;
	}
	
	/**
	 * Végrehajtja az ugrást
	 */
	public void jump(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "jump");
		
		// TODO folt lehelyezése
		
		// Megtett út nõ az ugrással
		distance += speedVector.length();
		
		// Ugráskor a hely módosítása a sebességvektorral
		position = new Vector(position.getX() + speedVector.getX(),
				position.getY() + speedVector.getY());
		
		// Következõ körre felkészítés
		speedHalved = false;
		speedModificationDisabled = false;
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction();
	}
	
	
	/**
	 * Megfelezi a robot sebességét
	 */
	public void halveSpeed(){
		// Csak abban az esetben felez, ha ez még nem történt meg a fordulóban
		if ( speedHalved == false ) {
			speedVector = speedVector.multiply(0.5);
			speedHalved = true;
		}
	}
	
	/**
	 * Letiltja a sebességmódosítást
	 */
	public void disableSpeedModification(){
		speedModificationDisabled = true;
	}
	
	
	/**
	 * Visszatér a robot aktuális sebességével
	 * @return a robot sebességvektorával tér vissza
	 */
	public Vector getSpeedVector(){
		return speedVector;
	}
	
	/**
	 *Visszatér a tárolt olajfoltok számával
	 * @return olajfoltok száma
	 */
	public int getOilNumber(){
		return oilNumber;
	}
	
	/**
	 * Visszatér a tárolt ragacsfoltok számával
	 * @return ragacsfoltok száma
	 */
	public int getGlueNumber(){
		return glueNumber;
	}
	
	/**
	 * Visszatér a robot által megtett távolsággal
	 * @return megtett távolság
	 */
	public double getDistance(){
		return distance;
	}
	

	/**
	 * Megadja, hogy módosíthatja-e a felhasználó a a robot sbességét
	 * @return módosítható-e vagy nem
	 */
	public boolean isSpeedModificationDisabled(){
		return speedModificationDisabled;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return color.toString() + ": " + position;
	}
	
}
