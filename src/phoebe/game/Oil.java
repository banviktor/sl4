package phoebe.game;

import phoebe.basic.Vector;

public class Oil extends Smudge {

	/**
	 * Konstruktor az olajfolt poz�ci�j�nak megad�s�val
	 * @param p poz�ci�
	 */
	public Oil(Vector p){ super(p); }

	/**
	 * A folt hat�s�t val�s�tja meg, az �tadott robotra kikapcsolja a sebess�gv�ltoztat�s
	 * lehet�s�g�t
	 * @param r a robot, aminek kikapcsolja a sebess�gv�ltoztat�s�t
	 */
	@Override
	public void action(Robot r){}
		
}
