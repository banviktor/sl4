package phoebe.game;

import java.util.List;

import phoebe.basic.Line;
import phoebe.basic.Vector;

public class Map {

	public Map(String map){}
	public void addSmudge(Smudge s){}
	public void nextRound(){}
	public List<Smudge> getSmudges(){ return null; }
	public List<Smudge> getSmudgesAt(){ return null; }
	public boolean isOnRoad(Vector v){ return true; }
	public List<Line> getLines(){ return null; }	
	public double getLineWidth(){ return 0; }	
	
}
