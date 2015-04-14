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
	 * Beolvassa �s feldolgozza a t�rk�pf�jlt.
	 * @param map a map f�jl helye
	 */
	public Map(String map) {
		lines = new ArrayList<Line>();
		smudges = new ArrayList<Smudge>();
		Document dom = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		// Megpr�b�lja megnyitni a p�lyaf�jlt �s v�gigparszolni
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
		
	}

	/**
	 * XML feldolgoz�, az adatb�l Line-t k�sz�t
	 * @param el XML element
	 * @return Line
	 */
	private Line getLine(Element el) {
		String[] numsSplit = el.getTextContent().split(" ");
		double[] nums = new double[4];
		for (int i = 0; i < 4; ++i) {
			// az xml f�jl norm�lva t�rolja a koordin�t�kat, 10x10es p�ly�ra alak�tjuk
			nums[i] = Double.parseDouble(numsSplit[i]) * 10;
		}
		return new Line( nums[0], nums[1], nums[2], nums[3] );
	}

	/**
	 * Beteszi a foltot a smudges list�ba.
	 * @param s az �j folt
	 */
	public void addSmudge(Smudge s) {
		smudges.add(s);
	}
	
	/**
	 * T�r�l egy foltot a smudges lit�b�l
	 * @param s a t�rlend� folt
	 */
	public void deleteSmudge(Smudge s) {
		smudges.remove(s);
	}

	/**
	 * Minden folton megh�vja a nextRound() f�ggv�nyt. Azokat a foltokat, amelyeknek 0 
	 * lesz az �lettartama, t�rli a smudges list�b�l.
	 */
	public void nextRound() {
		for (Smudge s : smudges) {
			if (s.nextRound() == 0) {
				smudges.remove(s);
			}
		}
	}


	/**
	 * Visszaadja az adott pontban hat�st kifejt� foltokat
	 * @param v pont
	 * @return itt hat� foltok
	 */
	public List<Smudge> getSmudgesAt(Vector p) {		
		List<Smudge> smudgesAt = new ArrayList<Smudge>();
		for (Smudge s : smudges) {
			if ( s.isEffectiveAt( p )) {
				smudgesAt.add( s );
			}
		}
		
		return smudgesAt;
	}
	
	
	/**
	 * Visszaadja a legk�zelebbi foltot a megadott poz�ci�hoz
	 * @param v a megadott poz�ci�
	 * @return a vektorhoz legk�zelebbi folt, ha nincs folt, akkor null
	 */
	public Smudge nearestSmudgeTo(Vector p) {
		if (!smudges.isEmpty()) {
			Smudge nearestSmudge = null;
			double minDistance = p.distance(smudges.get(0).getPosition());
			for (Smudge s : smudges) {
				if ( p.distance(s.getPosition()) < minDistance ) {
					nearestSmudge = s;
				}
			}
			return nearestSmudge;
		}
		return null;
	}
	
	
	/**
	 * Egy szakasz �s egy pont t�vols�g�t meghat�roz� met�dus
	 * A m�dszerr�l: http://prog.hu/tudastar/84433/Szakasz+es+pont+tavolsaga+3Dben.html
	 * @param l szakasz
	 * @param v pont
	 * @return pont �s szakasz t�vols�ga
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
	 * Visszaadja, hogy az adott pont rajta van-e a p�ly�n.
	 * @param v Vizsg�lt pont
	 * @return igaz, ha az �ton van
	 */
	public boolean isOnRoad(Vector p) {
		boolean result = false;
				
		// Egyenk�nt v�gign�zi, el�g k�zel van-e b�rmelyik p�lyaelemhez.
		for (Line l : lines) {
			if ( pointAndLineDist(l, p) < lineWidth/2 ) {
				result = true;
				break;
			}
		}
			
		return result;
	}

	
	/**
	 * Visszaadja az �sszes foltot.
	 * @return foltok
	 */
	public List<Smudge> getSmudges() {	
		return smudges;
	}
	
	
	/**
	 * Visszaadja az utak list�j�t
	 * @return utak list�ja
	 */
	public List<Line> getLines() {
		return lines;
	}

	/**
	 * Visszaadja az utak sz�less�g�t
	 * @return utak sz�less�ge
	 */
	public double getLineWidth() {
		return lineWidth;
	}

	/**
	 * Visszaadja a p�ly�n j�tszhat� k�r�k sz�m�t
	 * @return j�tszhat� k�r�k sz�ma
	 */
	public int getRounds() {
		return rounds;
	}
	
}
