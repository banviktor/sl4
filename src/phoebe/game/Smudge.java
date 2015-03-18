package phoebe.game;

import phoebe.basic.Vector;

/* Az �ltal�nos foltokat megval�s�t� absztarkt oszt�ly. T�rolja a folt hely�t,
 * h�tral�v� �lettartam�t, �s megval�s�tja a foltok �ltal�nos met�dusait
 */
public abstract class Smudge {
	
	protected Vector position;
	protected int remainingRounds;

	
	/** 
	 * Konstruktor a folt poz�ci�j�nak megad�s�val
	 * @param p a folt poz�ci�ja
	 */
	public Smudge(Vector p){}
	
	
	/** 
	 * Met�dus annak eld�nt�s�re, hogy a folt egy adott helyen fejt-e ki hat�st
	 * @param p a hely, ahol vizsg�ljuk a folt hat�sos�g�t
	 * @return logikai �rt�k, mely megadja, hogy a vizsg�lt helyen fejt-e ki hat�st a folt
	 */
	public boolean isEffectiveAt(Vector p){ return false; }
	
	
	/** 
	 * A folt �reg�t�s�t megval�s�t� met�dus
	 * @return a folt �j kora
	 */
	public int makeOlder(){ return 0; }
	
	
	/** 
	 * A folt poz�ci�j�t lek�rdez� met�dus
	 * @return a folt poz�ci�ja
	 */
	public Vector getPosition(){ return null; }
	
	
	/** 
	 * Absztrakt met�dus, mely a folt t�pus�t�l f�gg� v�ltoz�st hoz l�tre az �tadott robotban
	 * @param r a robot, amin v�ltoztat�st kell l�trehozni
	 */
	public abstract void action(Robot r);	
	
}
