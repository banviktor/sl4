package phoebe.basic;

public class Line {
	private Vector p1;
	private Vector p2;
	
	public Line(Vector p1, Vector p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public double length(){
		return (new Vector(p1, p2)).length();
	}
}
