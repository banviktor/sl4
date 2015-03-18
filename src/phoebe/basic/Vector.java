package phoebe.basic;

public class Vector {
	private double x;
	private double y;
	
	/**
	 * Megadott (x,y) koordinátákkal létrehoz egy vektort
	 * @param x X koordináta
	 * @param y Y koordináta
	 */
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}	
	
	/**
	 * Megadott (x1,y1) pontból (x2,y2) pontba mutató vektort hoz létre
	 * @param x1 Az elsõ pont X koordinátája
	 * @param y1 Az elsõ pont Y koordinátája
	 * @param x2 A második pont X koordinátája
	 * @param y2 A második pont Y koordinátája
	 */
	public Vector(double x1, double y1, double x2, double y2){
		this.x = x2 - x1;
		this.y = y2 - y1;
	}
	
	/**
	 * Megadott v1 pontból v2 pontba mutató vektort hoz létre
	 * @param v1 Az elsõ pont helyvektora
	 * @param v2 A második pont helyvektora
	 */
	public Vector(Vector v1, Vector v2){
		this(v1.x, v1.y, v2.x, v2.y);
	}
	
	/**
	 * Létrehoz egy új vektort, ami a paraméter és az aktuális vektor összege
	 * @param v A vektor, amit hozzá kell adni az aktuális vektorhoz
	 * @return Az összegvektor
	 */
	public Vector add (Vector v){
		return new Vector(x + v.x, y + v.y);
	}
	
	/**
	 * Visszaadja a vektor X koordinátáját
	 * @return X koordináta
	 */
	public double getX(){ 
		return x;
	}
	
	/**
	 * Visszaadja a vektor X koordinátáját
	 * @return Y koordináta
	 */
	public double getY(){ 
		return y; 
	}
	
	/**
	 * Visszaadja a vektor hosszát
	 * @return A vektor hossza
	 */
	public double length(){
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Visszaadja a vektor normáltját
	 * @return A vektor normáltja
	 */
	public Vector normalized() {
		if(x == 0 && y == 0)
			return new Vector(0, 0);
		return new Vector(x / this.length(), y / this.length());
	}
	
	/**
	 * Visszaadja a távolságot a vektorként megadott másik pont és az aktuális vektor között
	 * @param v A másik pont
	 * @return A két pont távolsága
	 */
	public double distance(Vector v){
		return (new Vector(x, y, v.x, v.y)).length();
	}
	
}
