package phoebe.game;

import phoebe.basic.Color;
import phoebe.basic.Vector;

public class Robot {

	public Robot(Color c, Vector p){}
	public Oil createOil(){ return null; }
	public Glue createGlue(){ return null; }
	public void setSpeedVector(Vector v){}
	public void jump(){}
	public Vector getPosition(){ return null; }
	public Vector getSpeedVector(){ return null; }
	public int getOilNumber(){ return 0; }
	public int getGlueNumber(){ return 0; }
	public double getDistance(){ return 0; }

	public void halveSpeed(){}
	public void disableSpeedModification(){}
	public boolean isSpeedModificationDisabled(){ return false; }
	
}
