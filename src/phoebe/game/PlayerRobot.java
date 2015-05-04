package phoebe.game;

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
		this.radius = 0.45;
		
		this.speedVector = new Vector(0, 0);
		
		this.speedHalved = false;
		this.speedModificationDisabled = false;
	
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
		// Megtett út nő az ugrással
		distance += speedVector.length();
		
		// Ugráskor a hely módosítása a sebességvektorral
		Vector destination = position.add(speedVector);
		Vector oldSpeedVector = speedVector;
		speedVector = speedVector.normalized().multiply(0.01);
		double precision = 20;
		Vector fragment = new Vector(position, destination).multiply(1.0/precision);
		for(int i = 0; i < precision; ++i){			
			try {
				position = position.add(fragment);
				radius = 0.6-Math.abs((0.15/(precision/2))*(i-precision/2.0));
				Thread.sleep((int)(400.0/precision));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		radius = 0.45;
		position = destination;
		speedVector = oldSpeedVector;
		
		// Új pozíció kiírása
		//UserIO.println("Új pozíció: " + position.getX() + ", " + position.getY());
		
		// Következő körre felkészítés
		speedHalved = false;
		speedModificationDisabled = false;
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
