package phoebe.basic;

public class Line {
	private final Vector p1;
	private final Vector p2;
	
	/**
	 * L�trehoz egy szakaszt k�t, helyvektorral megadott pont k�z�tt
	 * @param p1 Az els� pont
	 * @param p2 A m�sodik pont
	 */
	public Line(Vector p1, Vector p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * L�trehoz egy szakaszt k�t, koordin�t�ival megadott pont k�z�tt
	 * @param x1 Az els� pont X koordin�t�ja
	 * @param y1 Az els� pont Y koordin�t�ja
	 * @param x2 A m�sodik pont X koordin�t�ja
	 * @param y2 A m�sodik pont Y koordin�t�ja
	 */
	public Line(double x1, double y1, double x2, double y2){
		this.p1 = new Vector(x1, y1);
		this.p2 = new Vector(x2, y2);
	}
	
	/**
	 * Visszaadja a szakasz hossz�t
	 * @return A szakasz hossza
	 */
	public double length(){
		//return (new Vector(p1, p2)).length();
		return p1.distance(  p2 );
	}
	
	/**
	 * Visszaadja az els� vektort
	 * @return Els� vektor
	 */
	public Vector getVector1() {
		return p1;
	}
	
	/**
	 * Visszaadja a m�sodik vektort
	 * @return M�sodik vektor
	 */
	public Vector getVector2() {
		return p2;
	}
}
