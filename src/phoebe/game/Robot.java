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
	 * A robot konstruktora
	 * @param c a robot színe
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
	 * Lerak egy olajfoltot
	 * @return a létrehozott olajfolt referenciájával tér vissza
	 */
	public Oil createOil(){
		Log.enterFunction(Robot.class, "createOil");
		//olajfolt létrehozása
		Oil o = new Oil(position); 
		
		Log.exitFunction(o.toString()); 
		return o;
	}
	
	/**
	 * Lerak egy ragacsfoltot
	 * @return a létrehozott ragacsfolt referenciájával tér vissza
	 */
	public Glue createGlue(){
		Log.enterFunction(Robot.class, "createGlue");
		//ragacsfolt létrehozása
		Glue g = new Glue(position); 
		
		Log.exitFunction(g.toString());
		return g;
	}
	
	/**
	 * Beállítja a sebességvektort
	 * @param v az új sebességvektor
	 */
	public void setSpeedVector(Vector v){
		Log.enterFunction(Robot.class, "setSpeedVector");
		
		speedVector = v;
		
		Log.exitFunction();
	}
	
	/**
	 * Végrehajtja az ugrást
	 */
	public void jump(){
		Log.enterFunction(Robot.class, "jump");
		
		Log.exitFunction();
	}
	
	/**
	 * Visszatér a robot aktuális pozíciójával
	 * @return a robot helyvektorával tér vissza
	 */
	public Vector getPosition(){
		Log.enterFunction(Robot.class, "getPosition");
		
		Log.exitFunction(position);
		return position;
	}
	
	/**
	 * Visszatér a robot aktuális sebességével
	 * @return a robot sebességvektorával tér vissza
	 */
	public Vector getSpeedVector(){
		Log.enterFunction(Robot.class, "getSpeedVector");
		
		Log.exitFunction(speedVector);
		return speedVector;
	}
	
	/**
	 *Visszatér a tárolt olajfoltok számával
	 * @return olajfoltok száma
	 */
	public int getOilNumber(){
		Log.enterFunction(Robot.class, "getOilNumber");
		
		Log.exitFunction(oilNumber);
		return oilNumber;
	}
	
	/**
	 * Visszatér a tárolt ragacsfoltok számával
	 * @return ragacsfoltok száma
	 */
	public int getGlueNumber(){
		Log.enterFunction(Robot.class, "getGlueNumber");
		
		Log.exitFunction(glueNumber);
		return glueNumber;
	}
	
	/**
	 * Visszatér a robot által megtett távolsággal
	 * @return megtett távolság
	 */
	public double getDistance(){
		Log.enterFunction(Robot.class, "getDistence");
		
		Log.exitFunction(distance);
		return distance;
	}
	
	/**
	 * Megfelezi a robot sebességét
	 */
	public void halveSpeed(){
		Log.enterFunction(Robot.class, "halveSpeed");
		
		Log.exitFunction();
	}
	
	/**
	 * Letiltja a sebességmódosítást
	 */
	public void disableSpeedModification(){
		Log.enterFunction(Robot.class, "disableSpeedModification");

		speedModificationDisabled = true;
		
		Log.exitFunction();
	}

	/**
	 * Megadja, hogy módosíthatja-e a felhasználó a a robot sbességét
	 * @return módosítható-e vagy nem
	 */
	public boolean isSpeedModificationDisabled(){
		Log.enterFunction(Robot.class, "isSpeedModificationDisabled");
		
		Log.exitFunction(Boolean.toString(speedModificationDisabled));
		return speedModificationDisabled;
	}
	
}
