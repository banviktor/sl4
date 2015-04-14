package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Glue extends Smudge {
	
	/**
	 * Konstruktor az olajfolt poz�ci�j�nak megad�s�val
	 * @param p poz�ci�
	 */
	public Glue(Vector p) {
		super(p);
		
		// A ragacsfoltnak 4 az �lettartama szemben az �soszt�lyban alap�rtelmezett 6-al
		duration = 4;
	}
	
	
	/**
	 * A folt hat�s�t val�s�tja meg, az �tadott robotnak felezi a sebess�g�t
	 * @param r a robot, aminek felezi a sebess�g�t
	 */
	@Override
	public int action(PlayerRobot r) {
		r.halveSpeed();
		
		// A ragacsfoltnak r�l�p�skor cs�kken az �lettartama
		return duration--;
	}


	/**
	 * A ragacsfoltra nincs hat�ssal a k�rv�lt�s
	 * @return a folt �lettartama
	 */
	@Override
	public int nextRound() {
		return duration;
	}
	
	
	@Override
	public String toString() {
		return "Ragacs: " + position;
	}	

}
