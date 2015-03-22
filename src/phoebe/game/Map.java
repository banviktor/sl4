package phoebe.game;

import java.util.ArrayList;
import java.util.List;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Line;
import phoebe.basic.Vector;

public class Map {

	private List<Line> lines;
	private double lineWidth;
	private int rounds;
	private List<Smudge> smudges;

	/**
	 * Beolvassa �s feldolgozza a t�rk�pf�jlt.
	 * @param map a map f�jl helye
	 */
	public Map(String map) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "Map", map);
		
		lineWidth = 0;
		rounds = 3;
		smudges = new ArrayList<Smudge>();
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}

	/**
	 * Beteszi a foltot a smudges list�ba.
	 * @param s az �j folt
	 */
	public void addSmudge(Smudge s) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "addSmudge", s.toString());
		
		smudges.add(s);
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}

	/**
	 * Minden foltot egyel �reg�t. Azokat a foltokat, amelyeknek 0 lesz az
	 * �lettartama, t�rli a smudges list�b�l.
	 */
	public void nextRound() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "nextRound");
					
		for (Smudge s : smudges) {
			UserInput.getInt("A " + s + " h�tralev� k�reinek sz�ma nulla?", 3);
		}
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}

	/**
	 * Visszaadja az �sszes foltot.
	 * @return foltok
	 */
	public List<Smudge> getSmudges() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t �s a f�ggv�nyt
		Log.enterFunction(Map.class, "getSmudges");
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Smudge>");
				
		return smudges;
	}

	/**
	 * Visszaadja az adott pontban hat�st kifejt� foltokat
	 * @param v pont
	 * @return itt hat� foltok
	 */
	public List<Smudge> getSmudgesAt(Vector v) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "getSmudgesAt", v.toString());
				
		List<Smudge> smudgesAt = new ArrayList<Smudge>();
		for (Smudge s : smudges) {
			if ( s.isEffectiveAt( v )) {
				smudgesAt.add( s );
			}
		}
		
		// Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Smudge>");
		
		return smudgesAt;
	}
	
	/**
	 * Visszaadja, hogy az adott pont rajta van-e a p�ly�n.
	 * @param v Vizsg�lt pont
	 * @return igaz, ha az �ton van
	 */
	public boolean isOnRoad(Vector v) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "isOnRoad", v.toString());

		// Bek�rj�k a j�t�kost�l, hogy az adott pont a t�rk�pen van-e
		boolean result = UserInput.getBoolean("A t�rk�pen van ez a pont?", true);
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(result);
				
		return result;
	}

	/**
	 * Visszaadja az utak list�j�t
	 * @return utak list�ja
	 */
	public List<Line> getLines() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "getLines");
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction("List<Line>");
				
		return lines;
	}

	/**
	 * Visszaadja az utak sz�less�g�t
	 * @return utak sz�less�ge
	 */
	public double getLineWidth() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "getLineWidth");
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(lineWidth);
				
		return lineWidth;
	}

	/**
	 * Visszaadja a p�ly�n j�tszhat� k�r�k sz�m�t
	 * @return j�tszhat� k�r�k sz�ma
	 */
	public int getRounds() {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Map.class, "getRounds");
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(rounds);
		
		return rounds;
	}

}
