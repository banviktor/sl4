package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

/* Az �ltal�nos foltokat megval�s�t� absztarkt oszt�ly. T�rolja a folt hely�t,
 * h�tral�v� �lettartam�t, �s megval�s�tja a foltok �ltal�nos met�dusait
 */
public abstract class Smudge {
	
	protected Vector position;
	protected int duration;
	protected int thickness;
	
	
	/** 
	 * Konstruktor a folt poz�ci�j�nak megad�s�val
	 * @param p a folt poz�ci�ja
	 */
	public Smudge(Vector p) {		
		position = p;
		duration = 3;
	}
	
	
	/** 
	 * Met�dus annak eld�nt�s�re, hogy a folt egy adott helyen fejt-e ki hat�st
	 * @param p a hely, ahol vizsg�ljuk a folt hat�sos�g�t
	 * @return logikai �rt�k, mely megadja, hogy a vizsg�lt helyen fejt-e ki hat�st a folt
	 */
	public boolean isEffectiveAt(Vector p) {
	
		
		
		return false;
	}
	
	/** 
	 * A folt vastags�g�t c�kkent� met�dus
	 * @return a folt vastags�ga
	 */
	public int reduceThickness(){
		return thickness--;
	}
	
	/** 
	 * A folt vastags�g�t lek�rdez� met�dus
	 * @return a folt vastags�ga
	 */
	public int getThickness(){
		return thickness;
	}
	
	
	/** 
	 * A folt poz�ci�j�t lek�rdez� met�dus
	 * @return a folt poz�ci�ja
	 */
	public Vector getPosition(){
		return position;
	}
	
	
	/** 
	 * Absztrakt met�dus, mely a folt t�pus�t�l f�gg� v�ltoz�st hoz l�tre az �tadott robotban
	 * @param r a robot, amin v�ltoztat�st kell l�trehozni
	 */
	public abstract void action(PlayerRobot r);	
	

	/** 
	 * Absztark met�dus, ami folt �j k�rbe l�p�s�t megval�s�tja meg
	 * @return a folt kora
	 */
	public abstract int nextRound();
	
	
	public abstract String toString();
	
}
