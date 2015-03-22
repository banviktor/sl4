package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
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
	public Smudge(Vector p) {
		// Ezt a f�ggv�nyh�v�st nem logoljuk, mivel csak egy Glue vagy egy Oil konstruktor�ban
		// h�vhat� meg super(p) h�v�ssal (mivel az absztrakt oszt�ly nem p�ld�nyos�that�),
		// azok viszont m�r kezelik ezt a h�v�st
		
		position = p;
		remainingRounds = 3;
	}
	
	
	/** 
	 * Met�dus annak eld�nt�s�re, hogy a folt egy adott helyen fejt-e ki hat�st
	 * @param p a hely, ahol vizsg�ljuk a folt hat�sos�g�t
	 * @return logikai �rt�k, mely megadja, hogy a vizsg�lt helyen fejt-e ki hat�st a folt
	 */
	public boolean isEffectiveAt(Vector p) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Smudge.class, "isEffectiveHere", p.toString());
				
		//Megadjuk, hogy a folt alatta van-e
		boolean isHere = UserInput.getBoolean("Ez a folt a robot alatt van?", false);
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(String.valueOf(isHere));
		return isHere;
	}
	
	
	/** 
	 * A folt �reg�t�s�t megval�s�t� met�dus
	 * @return a folt �j kora
	 */
	public int makeOlder() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Smudge.class, "makeOlder");
		
		
		remainingRounds = UserInput.getInt("A " + this.toString() + " h�tralev� k�reinek sz�ma nulla?", 3);
		if (remainingRounds < 0) {
			remainingRounds = 0;
		}
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(remainingRounds);
		return remainingRounds;
	}
	
	
	/** 
	 * A folt poz�ci�j�t lek�rdez� met�dus
	 * @return a folt poz�ci�ja
	 */
	public Vector getPosition(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Smudge.class, "getPosition");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(position.toString());
		return position;
	}
	
	/** 
	 * Absztrakt met�dus, mely a folt t�pus�t�l f�gg� v�ltoz�st hoz l�tre az �tadott robotban
	 * @param r a robot, amin v�ltoztat�st kell l�trehozni
	 */
	public abstract void action(Robot r);	
	
	public abstract String toString();
	
}
