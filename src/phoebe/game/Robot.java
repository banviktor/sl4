package phoebe.game;

import phoebe.Log;
import phoebe.basic.Vector;

/**
 * Az �ltal�nos robotokat megval�s�t� absztrakt oszt�ly. T�rolja a robot hely�t
 * �s el�rhet�v� teszi az �ltal�nos funkci�it.
 */
public abstract class Robot {

	protected Vector position;
	
	public Robot(Vector p) {
		this.position = p;
	}
	
	/**
	 * Visszat�r a robot aktu�lis poz�ci�j�val
	 * @return a robot helyvektor�val t�r vissza
	 */
	public Vector getPosition(){
		return position;
	}
	
	/**
	 * Absztrakt met�dus annak eld�nt�s�re, hogy a robot az adott poz�ci�n tal�lhat�-e
	 * @param p az adott poz�ci�
	 * @return logikai �rt�k, mely megadja, hogy a robot az adott poz�ci�n van-e
	 */
	public abstract boolean isAt(Vector p);
	
	
	/**
	 * Absztrakt met�dus, melyben a robot l�trehoz egy olajfoltot az aktu�lis poz�ci�j�n
	 * @return a l�trehozott olajfolt
	 */
	public abstract Oil createOil();
	
}
