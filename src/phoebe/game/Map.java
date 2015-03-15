package phoebe.game;

import java.util.List;

import phoebe.basic.Line;
import phoebe.basic.Vector;

public class Map {

	private List<Line> lines;
	private double lineWidth;
	private int rounds;	
	private List<Smudge> smudges;
	
	public Map(String map){}
	public void addSmudge(Smudge s){}
	public void nextRound(){}
	public List<Smudge> getSmudges(){ return smudges; }
	public List<Smudge> getSmudgesAt(){ return null; }
	public boolean isOnRoad(Vector v){ return true; }
	public List<Line> getLines(){ return lines; }	
	public double getLineWidth(){ return lineWidth; }	
	
}
