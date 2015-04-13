package phoebe.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import phoebe.Log;
import phoebe.basic.Line;
import phoebe.basic.Vector;

public class Map {

	private List<Line> lines;
	private double lineWidth;
	private int rounds;
	private List<Smudge> smudges;

	/**
	 * Beolvassa és feldolgozza a térképfájlt.
	 * @param map a map fájl helye
	 */
	public Map(String map) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "Map", map);
		
		lines = new ArrayList<Line>();
		smudges = new ArrayList<Smudge>();
		Document dom = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		// Megpróbálja megnyitni a pályafájlt és végigparszolni
		try {
			db = dbf.newDocumentBuilder();
			dom = (Document) db.parse( map );

			Element docEle = dom.getDocumentElement();
			lineWidth = Double.parseDouble(docEle.getAttribute("lineWidth"))*10;
			rounds = Integer.parseInt(docEle.getAttribute("roundNum"));
			
			NodeList nl = docEle.getElementsByTagName("line");
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					Line e = getLine(el);
					lines.add(e);
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * XML feldolgozó, az adatból Line-t készít
	 * @param el XML element
	 * @return Line
	 */
	private Line getLine(Element el) {
		String[] numsSplit = el.getTextContent().split(" ");
		double[] nums = new double[4];
		for (int i = 0; i < 4; ++i) {
			// az xml fájl normálva tárolja a koordinátákat, 10x10es pályára alakítjuk
			nums[i] = Double.parseDouble(numsSplit[i]) * 10;
		}
		return new Line( nums[0], nums[1], nums[2], nums[3] );
	}

	/**
	 * Beteszi a foltot a smudges listába.
	 * @param s az új folt
	 */
	public void addSmudge(Smudge s) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "addSmudge", s.toString());
		
		smudges.add(s);
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Minden foltot egyel öregít. Azokat a foltokat, amelyeknek 0 lesz az
	 * élettartama, törli a smudges listából.
	 */
	public void nextRound() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "nextRound");
					
		for (Smudge s : smudges) {
			if (s.makeOlder() == 0) {
				smudges.remove(s);
			}
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
	}

	/**
	 * Visszaadja az összes foltot.
	 * @return foltok
	 */
	public List<Smudge> getSmudges() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getSmudges");
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
				
		return smudges;
	}

	/**
	 * Visszaadja az adott pontban hatást kifejtõ foltokat
	 * @param v pont
	 * @return itt ható foltok
	 */
	public List<Smudge> getSmudgesAt(Vector v) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getSmudgesAt", v.toString());
				
		List<Smudge> smudgesAt = new ArrayList<Smudge>();
		for (Smudge s : smudges) {
			if ( s.isEffectiveAt( v )) {
				smudgesAt.add( s );
			}
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction();
		
		return smudgesAt;
	}
	
	/**
	 * http://prog.hu/tudastar/84433/Szakasz+es+pont+tavolsaga+3Dben.html
	 * @param l szakasz
	 * @param v pont
	 * @return pont és szakasz távolsága
	 */
	private double pointAndLineDist(Line l, Vector v) {
		Vector p1 = l.getVector1();
		Vector p2 = l.getVector2();
		// 
		double d = Math.abs( (p2.getX()-p1.getX())*(p1.getY()-v.getY()) -
				(p1.getX()-v.getX())*(p2.getY()-p1.getY()) ) /
				Math.sqrt( Math.pow(p2.getX()-p1.getX(),2) + Math.pow(p1.getY()-p2.getY(), 2) );
		double sectionLen = l.length();
		double sectionA = Math.sqrt( Math.pow(p1.distance(v), 2) - Math.pow(d, 2) );
		double sectionB = Math.sqrt( Math.pow(p2.distance(v), 2) - Math.pow(d, 2) );
		if (Math.abs(sectionA + sectionB - sectionLen) < 0.01) {
			return d;
		}
		else if (sectionA < sectionB) {
			return p1.distance( v );
		}
		else {
			return p2.distance( v );
		}
	}

	/**
	 * Visszaadja, hogy az adott pont rajta van-e a pályán.
	 * @param v Vizsgált pont
	 * @return igaz, ha az úton van
	 */
	public boolean isOnRoad(Vector v) {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "isOnRoad", v.toString());
		
		boolean result = false;
				
		// Egyenként végignézi, elég közel van-e bármelyik pályaelemhez.
		for (Line l : lines) {
			if ( pointAndLineDist(l, v) < lineWidth/2 ) {
				result = true;
				break;
			}
		}
		
		//Metódusból kilépés kiírása
		Log.exitFunction(result);
				
		return result;
	}

	/**
	 * Visszaadja az utak listáját
	 * @return utak listája
	 */
	public List<Line> getLines() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getLines");
		
		//Metódusból kilépés kiírása
		Log.exitFunction("List<Line>");
				
		return lines;
	}

	/**
	 * Visszaadja az utak szélességét
	 * @return utak szélessége
	 */
	public double getLineWidth() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getLineWidth");
		
		//Metódusból kilépés kiírása
		Log.exitFunction(lineWidth);
				
		return lineWidth;
	}

	/**
	 * Visszaadja a pályán játszható körök számát
	 * @return játszható körök száma
	 */
	public int getRounds() {
		// Függvénybe lépéskor kiírjuk az osztály nevét, a függvényt és a paraméterlistát
		Log.enterFunction(Map.class, "getRounds");
		
		//Metódusból kilépés kiírása
		Log.exitFunction(rounds);
		
		return rounds;
	}
	
	/**
	 * Visszaadja a legközelebbi foltot
	 * @param v Innen adja a legközelebbi foltot
	 * @return A vektorhoz legközelebbi folt. Ha nincs folt, null
	 */
	public Smudge nearestSmudgeTo(Vector v) {
		Smudge nearestSmudge = null;
		double minDistance = 100;
		for (Smudge s : smudges) {
			if ( v.distance(s.getPosition()) < minDistance ) {
				nearestSmudge = s;
			}
		}
		return nearestSmudge;
	}

}
