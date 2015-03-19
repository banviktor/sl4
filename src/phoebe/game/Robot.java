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
	 * @param c a robot sz�ne
	 * @param p a robot kezdeti helye
	 */
	public Robot(Color c, Vector p){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Robot.class, "Robot", c.toString() + p.toString());
		
		this.color = c;
		this.position = p;
		
		this.oilNumber = 3;
		this.oilNumber = 3;
		this.distance = 0;
		
		this.speedVector = new Vector(0, 0);
		
		this.speedHalved = false;
		this.speedModificationDisabled = false;
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Lerak egy olajfoltot
	 * @return a l�trehozott olajfolt referenci�j�val t�r vissza
	 */
	public Oil createOil(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "createOil");
		
		// olajfolt l�trehoz�sa
		Oil o = new Oil(position); 
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(o.toString()); 
		return o;
	}
	
	/**
	 * Lerak egy ragacsfoltot
	 * @return a l�trehozott ragacsfolt referenci�j�val t�r vissza
	 */
	public Glue createGlue(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "createGlue");
		
		// ragacsfolt l�trehoz�sa
		Glue g = new Glue(position); 
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(g.toString());
		return g;
	}
	
	/**
	 * Be�ll�tja a sebess�gvektort
	 * @param v az �j sebess�gvektor
	 */
	public void setSpeedVector(Vector v){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Robot.class, "setSpeedVector", v.toString());
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction();
	}
	
	/**
	 * V�grehajtja az ugr�st
	 */
	public void jump(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "jump");
		
		// Ugr�skor a hely m�dos�t�sa a sebess�gvektorral
		position = new Vector(position.getX() + speedVector.getX(),
				position.getY() + speedVector.getY());
		
		// TODO folt elhelyez�se
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction();
	}
	
	/**
	 * Visszat�r a robot aktu�lis poz�ci�j�val
	 * @return a robot helyvektor�val t�r vissza
	 */
	public Vector getPosition(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getPosition");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(position);
		return position;
	}
	
	/**
	 * Visszat�r a robot aktu�lis sebess�g�vel
	 * @return a robot sebess�gvektor�val t�r vissza
	 */
	public Vector getSpeedVector(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getSpeedVector");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(speedVector);
		return speedVector;
	}
	
	/**
	 *Visszat�r a t�rolt olajfoltok sz�m�val
	 * @return olajfoltok sz�ma
	 */
	public int getOilNumber(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getOilNumber");
		
		// Megk�rdezz�k a felhaszn�l�t, hogy vab-e m�g olaj a k�szletben
				if (UserInput.getBoolean("Van m�g olaj?", true)) {
					// Ha van, akkor a sz�ma m�r l�nyegtelen, fix 3-ra �ll�tjuk
					oilNumber = 3;
				} else {
					// Ha nincs, akkor a k�szlet�nk 0
					oilNumber = 0;
				}
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(oilNumber);
		return oilNumber;
	}
	
	/**
	 * Visszat�r a t�rolt ragacsfoltok sz�m�val
	 * @return ragacsfoltok sz�ma
	 */
	public int getGlueNumber(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getGlueNumber");
		
		// Megk�rdezz�k a felhaszn�l�t, hogy vab-e m�g ragacs a k�szletben
		if (UserInput.getBoolean("Van m�g ragacs?", true)) {
			// Ha van, akkor a sz�ma m�r l�nyegtelen, fix 3-ra �ll�tjuk
			glueNumber = 3;
		} else {
			// Ha nincs, akkor a k�szlet�nk 0
			glueNumber = 0;
		}
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(glueNumber);
		return glueNumber;
	}
	
	/**
	 * Visszat�r a robot �ltal megtett t�vols�ggal
	 * @return megtett t�vols�g
	 */
	public double getDistance(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "getDistence");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(distance);
		return distance;
	}
	
	/**
	 * Megfelezi a robot sebess�g�t
	 */
	public void halveSpeed(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "halveSpeed");
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}
	
	/**
	 * Letiltja a sebess�gm�dos�t�st
	 */
	public void disableSpeedModification(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "disableSpeedModification");
		
		// Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}

	/**
	 * Megadja, hogy m�dos�thatja-e a felhaszn�l� a a robot sbess�g�t
	 * @return m�dos�that�-e vagy nem
	 */
	public boolean isSpeedModificationDisabled(){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Robot.class, "isSpeedModificationDisabled");
		
		// Megk�rdezz�k a felhaszn�l�t, hogy olajfolton �llunk-e, ami kikapcsolja a 
		// sebess�gm�dos�t�s lehet�s�g�t
		speedModificationDisabled = UserInput.getBoolean("Olajfolton �llunk?", false);
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(Boolean.toString(speedModificationDisabled));
		return speedModificationDisabled;
	}
	
}
