package phoebe.basic;

public class Line {
	private Vector p1;
	private Vector p2;
	
	/**
	 * Létrehoz egy szakaszt két, helyvektorral megadott pont között
	 * @param p1 Az elsõ pont
	 * @param p2 A második pont
	 */
	public Line(Vector p1, Vector p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Létrehoz egy szakaszt két, koordinátáival megadott pont között
	 * @param x1 Az elsõ pont X koordinátája
	 * @param y1 Az elsõ pont Y koordinátája
	 * @param x2 A második pont X koordinátája
	 * @param y2 A második pont Y koordinátája
	 */
	public Line(double x1, double y1, double x2, double y2){
		this.p1 = new Vector(x1, y1);
		this.p2 = new Vector(x2, y2);
	}
	
	/**
	 * Visszaadja a szakasz hosszát
	 * @return A szakasz hossza
	 */
	public double length(){
		return (new Vector(p1, p2)).length();
	}
}
