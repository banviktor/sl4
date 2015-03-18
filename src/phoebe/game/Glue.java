package phoebe.game;

import phoebe.basic.Vector;

public class Glue extends Smudge {
	
	/**
	 * Konstruktor az olajfolt poz�ci�j�nak megad�s�val
	 * @param p poz�ci�
	 */
	public Glue(Vector p){ super(p); }

	
	/**
	 * A folt hat�s�t val�s�tja meg, az �tadott robotnak felezi a sebess�g�t
	 * @param r a robot, aminek felezi a sebess�g�t
	 */
	@Override
	public void action(Robot r){
		r.halveSpeed();
	}


	@Override
	public String toString() {
		return "Glue@" + position;
	}	

}
