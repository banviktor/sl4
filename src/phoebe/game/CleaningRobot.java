package phoebe.game;

import phoebe.basic.Vector;

public class CleaningRobot extends Robot {

	public CleaningRobot(Vector p) {
		super(p);
	}

	public Oil createOil() {
		return new Oil(position);
	}

	public boolean isAt(Vector p) {
		if(position.getX() == p.getX() && position.getY() == p.getY()){
			return true;
		}
		return false;
	}

}
