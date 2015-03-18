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
		//olajfolt l�trehoz�sa
		Oil o = new Oil(position); 
		
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
		//ragacsfolt l�trehoz�sa
		Glue g = new Glue(position); 
		
		Log.exitFunction("glue");
		return g;
	}
	
	/**
	 * be�ll�tja a sebess�gvektort
	 * 
	 * @param v az �j sebess�gvektor
	 */
	public void setSpeedVector(Vector v){
		Log.enterFunction(Robot.class, "setSpeedVector");
		
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
		
		Log.exitFunction(position);
		return position;
	}
	
	/**
	 * visszat�r a robot aktu�lis sebess�g�vel
	 * 
	 * @return a robot sebess�gvektor�val t�r vissza
	 */
	public Vector getSpeedVector(){
		Log.enterFunction(Robot.class, "getSpeedVector");
		
		Log.exitFunction(speedVector);
		return speedVector;
	}
	
	/**
	 *visszat�r a t�rolt olajfoltok sz�m�val
	 *
	 * @return
	 */
	public int getOilNumber(){
		Log.enterFunction(Robot.class, "getOilNumber");
		
		Log.exitFunction(oilNumber);
		return oilNumber;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int getGlueNumber(){
		Log.enterFunction(Robot.class, "getGlueNumber");
		
		Log.exitFunction(glueNumber);
		return glueNumber;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public double getDistance(){
		Log.enterFunction(Robot.class, "getDistence");
		
		Log.exitFunction(distance);
		return distance;
	}
	
	/**
	 * 
	 */
	public void halveSpeed(){
		Log.enterFunction(Robot.class, "halveSpeed");
		
		Log.exitFunction();
	}
	
	/**
	 * 
	 */
	public void disableSpeedModification(){
		Log.enterFunction(Robot.class, "disableSpeedModification");

		speedModificationDisabled = true;
		
		Log.exitFunction();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSpeedModificationDisabled(){
		Log.enterFunction(Robot.class, "isSpeedModificationDisabled");
		
		Log.exitFunction(Boolean.toString(speedModificationDisabled));
		return speedModificationDisabled;
	}
	
}
