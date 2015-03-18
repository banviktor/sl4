package phoebe.game;

import phoebe.Log;
import phoebe.UserInput;
import phoebe.basic.Vector;

public class Oil extends Smudge {
	
	/**
	 * Konstruktor az olajfolt poz�ci�j�nak megad�s�val
	 * @param p poz�ci�
	 */
	public Oil(Vector p) {
		super(p);
		
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Glue.class, "Glue", p.toString());
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}

	
	/** 
	 * Met�dus annak eld�nt�s�re, hogy az olajfolt egy adott helyen fejt-e ki hat�st
	 * Csak a szkeletonba kell fel�l�rni a sz�l� f�ggv�ny�t, itt a k�rd�s felt�tele miatt
	 * @param p a hely, ahol vizsg�ljuk a folt hat�sos�g�t
	 * @return logikai �rt�k, mely megadja, hogy a vizsg�lt helyen fejt-e ki hat�st a folt
	 */
	@Override
	public boolean isEffectiveAt(Vector p) {
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Oil.class, "isEffectiveAt", p.toString());
				
		//Megadjuk, hogy a folt alatta van-e
		boolean isHere = UserInput.getBoolean("Ez az olajfolt a robot alatt van?", false);
		
		//Met�dusb�l kil�p�s ki�r�sa a visszat�r�si �rt�kkel
		Log.exitFunction(String.valueOf(isHere));
		return isHere;
	}
	
	
	/**
	 * A folt hat�s�t val�s�tja meg, az �tadott robotra kikapcsolja a sebess�gv�ltoztat�s
	 * lehet�s�g�t
	 * @param r a robot, aminek kikapcsolja a sebess�gv�ltoztat�s�t
	 */
	@Override
	public void action(Robot r){
		// F�ggv�nybe l�p�skor ki�rjuk az oszt�ly nev�t, a f�ggv�nyt �s a param�terlist�t
		Log.enterFunction(Oil.class, "action", r.toString());
		
		r.disableSpeedModification();
		
		//Met�dusb�l kil�p�s ki�r�sa
		Log.exitFunction();
	}


	@Override
	public String toString() {
		return "Oil@" + position;
	}
		
}
