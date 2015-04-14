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
	 * @param c a robot sz�ne
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
	 * A met�dus eld�nti, hogy a 0.45 egys�gnyi sugar� robot az adott poz�ci�n tal�lhat�-e
	 * @param p a megadott poz�ci�
	 * @return	logikai �rt�k, mely megadja, hogy a robot az adott poz�ci�n van-e
	 */
	@Override
	public boolean isAt(Vector p) {
		return (position.distance(p) < 0.45);
	}
	
	
	/**
	 * Lerak egy olajfoltot
	 * @return a l�trehozott olajfolt referenci�j�val t�r vissza
	 */
	@Override
	public Oil createOil(){
		Oil o = new Oil(position); 
		--oilNumber;
		return o;
	}
	
	/**
	 * Lerak egy ragacsfoltot
	 * @return a l�trehozott ragacsfolt referenci�j�val t�r vissza
	 */
	public Glue createGlue(){
		Glue g = new Glue(position); 
		--glueNumber;
		return g;
	}
	
	/**
	 * Be�ll�tja a sebess�gvektort
	 * @param v az �j sebess�gvektor
	 */
	public void setSpeedVector(Vector v){
		speedVector = v;
	}
	
	/**
	 * V�grehajtja az ugr�st
	 */
	public void jump(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "jump");
		
		// TODO folt lehelyez�se
		
		// Megtett �t n� az ugr�ssal
		distance += speedVector.length();
		
		// Ugr�skor a hely m�dos�t�sa a sebess�gvektorral
		position = new Vector(position.getX() + speedVector.getX(),
				position.getY() + speedVector.getY());
		
		// K�vetkez� k�rre felk�sz�t�s
		speedHalved = false;
		speedModificationDisabled = false;
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction();
	}
	
	
	/**
	 * Megfelezi a robot sebess�g�t
	 */
	public void halveSpeed(){
		// Csak abban az esetben felez, ha ez m�g nem t�rt�nt meg a fordul�ban
		if ( speedHalved == false ) {
			speedVector = speedVector.multiply(0.5);
			speedHalved = true;
		}
	}
	
	/**
	 * Letiltja a sebess�gm�dos�t�st
	 */
	public void disableSpeedModification(){
		speedModificationDisabled = true;
	}
	
	
	/**
	 * Visszat�r a robot aktu�lis sebess�g�vel
	 * @return a robot sebess�gvektor�val t�r vissza
	 */
	public Vector getSpeedVector(){
		return speedVector;
	}
	
	/**
	 *Visszat�r a t�rolt olajfoltok sz�m�val
	 * @return olajfoltok sz�ma
	 */
	public int getOilNumber(){
		return oilNumber;
	}
	
	/**
	 * Visszat�r a t�rolt ragacsfoltok sz�m�val
	 * @return ragacsfoltok sz�ma
	 */
	public int getGlueNumber(){
		return glueNumber;
	}
	
	/**
	 * Visszat�r a robot �ltal megtett t�vols�ggal
	 * @return megtett t�vols�g
	 */
	public double getDistance(){
		return distance;
	}
	

	/**
	 * Megadja, hogy m�dos�thatja-e a felhaszn�l� a a robot sbess�g�t
	 * @return m�dos�that�-e vagy nem
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
