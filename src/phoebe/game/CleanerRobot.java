package phoebe.game;

import phoebe.basic.Vector;

/**
 * A takar�t�robotokat megval�s�t� oszt�ly, megval�s�tja azok mozg�s�t,
 * takar�t�s�t �s �tk�z�s�t
 */
public class CleanerRobot extends Robot {

	private Map map;
	private Game game;
	
	/**
	 * Az oszt�ly konstruktora
	 * @param p a robot poz�ci�ja
	 * @param m	az aktu�lis t�rk�p, melyen a robot szerepel
	 * @param g az aktu�lis j�t�k, melyben a robot szerepel
	 */
	public CleanerRobot(Vector p, Map m, Game g) {
		super(p);
		map = m;
		game = g;
	}

	/**
	 * A met�dus eld�nti, hogy a 0.3 egys�gnyi sugar� robot az adott poz�ci�n tal�lhat�-e
	 * @param p a megadott poz�ci�
	 * @return	logikai �rt�k, mely megadja, hogy a robot az adott poz�ci�n van-e
	 */
	public boolean isAt(Vector p) {
		return (position.distance(p) < 0.3);
	}

	/**
	 * Met�dus, melyben a takar�t�robot l�trehoz egy olajfoltot az aktu�lis poz�ci�j�n
	 * @return	a l�trehozott olajfolt
	 */
	public Oil createOil() {
		return new Oil(position);
	}
	
	/**
	 * A takar�t�robot egy k�rbeli cselekv�seit megval�s�t� met�dus
	 */
	public void clear() {
		
	}
}
