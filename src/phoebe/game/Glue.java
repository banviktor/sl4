package phoebe.game;

import phoebe.basic.Vector;

public class Glue extends Smudge {
	
	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Glue(Vector p){ super(p); }

	
	/**
	 * A folt hatását valósítja meg, az átadott robotnak felezi a sebességét
	 * @param r a robot, aminek felezi a sebességét
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
