package phoebe.game;

import phoebe.Log;
import phoebe.basic.Color;
import phoebe.basic.Vector;

public class Robot {
	
	private Color color;
	private Vector position;
	private double distance;
	private int oilNumber;
	private int glueNumber;
	private Vector speedVector;
	
	private boolean speedHalved;
	private boolean speedModificationDisabled;

	
	/**
	 * a robot konstruktora
	 * 
	 * @param c a robot sz�ne
	 * @param p a robot kezdeti helye
	 */
	public Robot(Color c, Vector p){
		Log.enterFunction(Robot.class, "Robot", c.toString() + p.toString());
		
		this.color = c;
		this.position = p;
		
		this.oilNumber = 3;
		this.oilNumber = 3;
		
		this.speedVector = new Vector(0, 0);
		
		this.speedHalved = false;
		this.speedModificationDisabled = false;
		
		Log.exitFunction();
	}
	
	/**
	 * lerak egy olajfoltot
	 * 
	 * @return a l�trehozott olajfolt referenci�j�val t�r vissza
	 */
	public Oil createOil(){
		Log.enterFunction(Robot.class, "createOil");
		
		Oil o = new Oil(position); //olajfolt l�trehoz�sa
		
		Log.exitFunction("oil"); //TODO Oil.toString() -> Smudge
		return o;
	}
	
	/**
	 * lerak egy ragacsfoltot
	 * 
	 * @return a l�trehozott ragacsfolt referenci�j�val t�r vissza
	 */
	public Glue createGlue(){
		Log.enterFunction(Robot.class, "createGlue");
		
		Glue g = new Glue(position); //ragacsfolt l�trehoz�sa
		
		Log.exitFunction("glue");
		return g;
	}
	
	/**
	 * be�ll�tja a sebess�gvektort
	 * 
	 * @param v az �j sebess�gvektor
	 */
	public void setSpeedVector(Vector v){
		Log.enterFunction(Robot.class, setSpeedVector);
		
		speedVector = v;
		
		Log.exitFunction();
	}
	
	/**
	 * v�grehajtja az ugr�st
	 */
	public void jump(){
		Log.enterFunction(Robot.class, "jump");
		
		Log.exitFunction();
	}
	
	/**
	 * visszat�r a robot aktu�lis poz�ci�j�val
	 * 
	 * @return a robot helyvektor�val t�r vissza
	 */
	public Vector getPosition(){
		Log.enterFunction(Robot.class, "getPosition");
		
		Log.exitFunction();
		return position;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector getSpeedVector(){ return null; }
	
	/**
	 *
	 *
	 * @return
	 */
	public int getOilNumber(){ return 0; }
	
	/**
	 * 
	 * @return
	 */
	public int getGlueNumber(){ return 0; }
	
	/**
	 * 
	 * @return
	 */
	public double getDistance(){ return 0; }

	public void halveSpeed(){}
	public void disableSpeedModification(){}
	public boolean isSpeedModificationDisabled(){ return false; }
	
}
