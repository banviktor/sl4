package phoebe.game;

import phoebe.basic.Vector;

public abstract class Smudge {
	
	protected Vector position;
	protected int remainingRounds;
	
	public Smudge(Vector p){}
	public boolean isEffectiveAt(Vector p){ return false; }
	public int makeOlder(){ return 0; }
	public Vector getPosition(){ return null; }
	public abstract void action(Robot r);	
	
}
