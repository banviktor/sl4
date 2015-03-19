package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
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
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Robot.class, "Robot", c.toString() + p.toString());
		
		this.color = c;
		this.position = p;
		
		this.oilNumber = 3;
		this.oilNumber = 3;
		this.distance = 0;
		
		this.speedVector = new Vector(0, 0);
		
		this.speedHalved = false;
		this.speedModificationDisabled = false;
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Lerak egy olajfoltot
	 * @return a létrehozott olajfolt referenciájával tér vissza
	 */
	public Oil createOil(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "createOil");
		
		// olajfolt létrehozása
		Oil o = new Oil(position); 
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(o.toString()); 
		return o;
	}
	
	/**
	 * Lerak egy ragacsfoltot
	 * @return a létrehozott ragacsfolt referenciájával tér vissza
	 */
	public Glue createGlue(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "createGlue");
		
		// ragacsfolt létrehozása
		Glue g = new Glue(position); 
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(g.toString());
		return g;
	}
	
	/**
	 * Beállítja a sebességvektort
	 * @param v az új sebességvektor
	 */
	public void setSpeedVector(Vector v){
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Robot.class, "setSpeedVector", v.toString());
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction();
	}
	
	/**
	 * Végrehajtja az ugrást
	 */
	public void jump(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "jump");
		
		// Ugráskor a hely módosítása a sebességvektorral
		position = new Vector(position.getX() + speedVector.getX(),
				position.getY() + speedVector.getY());
		
		// TODO folt elhelyezése
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction();
	}
	
	/**
	 * Visszatér a robot aktuális pozíciójával
	 * @return a robot helyvektorával tér vissza
	 */
	public Vector getPosition(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getPosition");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(position);
		return position;
	}
	
	/**
	 * Visszatér a robot aktuális sebességével
	 * @return a robot sebességvektorával tér vissza
	 */
	public Vector getSpeedVector(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getSpeedVector");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(speedVector);
		return speedVector;
	}
	
	/**
	 *Visszatér a tárolt olajfoltok számával
	 * @return olajfoltok száma
	 */
	public int getOilNumber(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getOilNumber");
		
		// Megkérdezzük a felhasználót, hogy vab-e még olaj a készletben
				if (UserInput.getBoolean("Van még olaj?", true)) {
					// Ha van, akkor a száma már lényegtelen, fix 3-ra állítjuk
					oilNumber = 3;
				} else {
					// Ha nincs, akkor a készletünk 0
					oilNumber = 0;
				}
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(oilNumber);
		return oilNumber;
	}
	
	/**
	 * Visszatér a tárolt ragacsfoltok számával
	 * @return ragacsfoltok száma
	 */
	public int getGlueNumber(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getGlueNumber");
		
		// Megkérdezzük a felhasználót, hogy vab-e még ragacs a készletben
		if (UserInput.getBoolean("Van még ragacs?", true)) {
			// Ha van, akkor a száma már lényegtelen, fix 3-ra állítjuk
			glueNumber = 3;
		} else {
			// Ha nincs, akkor a készletünk 0
			glueNumber = 0;
		}
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(glueNumber);
		return glueNumber;
	}
	
	/**
	 * Visszatér a robot által megtett távolsággal
	 * @return megtett távolság
	 */
	public double getDistance(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "getDistence");
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(distance);
		return distance;
	}
	
	/**
	 * Megfelezi a robot sebességét
	 */
	public void halveSpeed(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "halveSpeed");
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}
	
	/**
	 * Letiltja a sebességmódosítást
	 */
	public void disableSpeedModification(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "disableSpeedModification");
		
		// Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Megadja, hogy módosíthatja-e a felhasználó a a robot sbességét
	 * @return módosítható-e vagy nem
	 */
	public boolean isSpeedModificationDisabled(){
		// Függvénybe lépéskor kiírjuk az osztály nevét és a függvényt
		Log.enterFunction(Robot.class, "isSpeedModificationDisabled");
		
		// Megkérdezzük a felhasználót, hogy olajfolton állunk-e, ami kikapcsolja a 
		// sebességmódosítás lehetõségét
		speedModificationDisabled = UserInput.getBoolean("Olajfolton állunk?", false);
		
		// Metódusból kilépés kiírása a visszatérési értékkel
		Log.exitFunction(Boolean.toString(speedModificationDisabled));
		return speedModificationDisabled;
	}
	
}
