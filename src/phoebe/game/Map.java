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
		lines = new ArrayList<Line>();
		Document dom = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// Using factory get an instance of document builder
		DocumentBuilder db;
		// parse using builder to get DOM representation of the XML file
		try {
			db = dbf.newDocumentBuilder();
			dom = (Document) db.parse( map );

			// get the root element
			Element docEle = dom.getDocumentElement();
			lineWidth = Double.parseDouble(docEle.getAttribute("lineWidth"));
			rounds = Integer.parseInt(docEle.getAttribute("roundNum"));
			
			// get a nodelist of elements
			NodeList nl = docEle.getElementsByTagName("line");
			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {

					// get the employee element
					Element el = (Element) nl.item(i);

					// get the Employee object
					Line e = getLine(el);

					// add it to list
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
			nums[i] = Double.parseDouble(numsSplit[i]);
		}
		return new Line( nums[0], nums[1], nums[2], nums[3] );
	}

	/**
	 * Beteszi a foltot a smudges listába.
	 * @param s az új folt
	 */
	public void addSmudge(Smudge s) {
		smudges.add(s);
	}

	/**
	 * Minden foltot egyel öregít. Azokat a foltokat, amelyeknek 0 lesz az
	 * élettartama, törli a smudges listából.
	 */
	public void nextRound() {
		for (Smudge s : smudges) {
			if (s.makeOlder() == 0) {
				smudges.remove(s);
			}
		}
	}

	/**
	 * Visszaadja az összes foltot.
	 * @return foltok
	 */
	public List<Smudge> getSmudges() {
		return smudges;
	}

	/**
	 * Visszaadja az adott pontban hatást kifejtõ foltokat
	 * @param v pont
	 * @return itt ható foltok
	 */
	public List<Smudge> getSmudgesAt(Vector v) {
		List<Smudge> smudgesAt = new ArrayList<Smudge>();
		for (Smudge s : smudges) {
			if ( s.isEffectiveAt( v )) {
				smudgesAt.add( s );
			}
		}
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
		for (Line l : lines) {
			if ( pointAndLineDist(l, v) < lineWidth/2 ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Visszaadja az utak listáját
	 * @return utak listája
	 */
	public List<Line> getLines() {
		return lines;
	}

	/**
	 * Visszaadja az utak szélességét
	 * @return utak szélessége
	 */
	public double getLineWidth() {
		return lineWidth;
	}

	/**
	 * Visszaadja a pályán játszható körök számát
	 * @return játszható körök száma
	 */
	public int getRounds() {
		return rounds;
	}

}
