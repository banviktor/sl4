package phoebe.game;

import phoebe.basic.Vector;

public class Oil extends Smudge {

	/**
	 * Konstruktor az olajfolt pozíciójának megadásával
	 * @param p pozíció
	 */
	public Oil(Vector p){ super(p); }

	/**
	 * A folt hatását valósítja meg, az átadott robotra kikapcsolja a sebességváltoztatás
	 * lehetõségét
	 * @param r a robot, aminek kikapcsolja a sebességváltoztatását
	 */
	@Override
	public void action(Robot r){}
		
}
