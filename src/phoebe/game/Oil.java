package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Oil extends Smudge {
	
	/**
	 * Konstruktor az olajfolt poz�ci�j�nak megad�s�val
	 * @param p poz�ci�
	 */
	public Oil(Vector p) {
		super(p);
		}

	
	/**
	 * A folt hat�s�t val�s�tja meg, az �tadott robotra kikapcsolja a sebess�gv�ltoztat�s
	 * lehet�s�g�t
	 * @param r a robot, aminek kikapcsolja a sebess�gv�ltoztat�s�t
	 */
	@Override
	public int action(PlayerRobot r) {
		r.disableSpeedModification();
		
		// Az olajfoltnak r�l�p�skor nem cs�kken az �lettartama
		return duration;
	}

	
	/**
	 * Az olajfolt az �j k�rben esz�t egyet az �lettartam�b�l
	 * @return a folt �j �lettartama
	 */
	@Override
	public int nextRound() {
		return duration--;
	}
	

	@Override
	public String toString() {
		return "Olaj: " + position;
	}
		
}
