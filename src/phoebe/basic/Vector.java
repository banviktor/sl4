package phoebe.basic;

/**
 * Egy x y pontp�rt t�rol� oszt�ly
 */
public class Vector {
	private final double x;
	private final double y;
	
	/**
	 * Megadott (x,y) koordin�t�kkal l�trehoz egy vektort
	 * @param x X koordin�ta
	 * @param y Y koordin�ta
	 */
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}	
	
	/**
	 * Megadott (x1,y1) pontb�l (x2,y2) pontba mutat� vektort hoz l�tre
	 * @param x1 Az els� pont X koordin�t�ja
	 * @param y1 Az els� pont Y koordin�t�ja
	 * @param x2 A m�sodik pont X koordin�t�ja
	 * @param y2 A m�sodik pont Y koordin�t�ja
	 */
	public Vector(double x1, double y1, double x2, double y2){
		this.x = x2 - x1;
		this.y = y2 - y1;
	}
	
	/**
	 * Megadott v1 pontb�l v2 pontba mutat� vektort hoz l�tre
	 * @param v1 Az els� pont helyvektora
	 * @param v2 A m�sodik pont helyvektora
	 */
	public Vector(Vector v1, Vector v2){
		this(v1.x, v1.y, v2.x, v2.y);
	}
	
	/**
	 * L�trehoz egy �j vektort, ami a param�ter �s az aktu�lis vektor �sszege
	 * @param v A vektor, amit hozz� kell adni az aktu�lis vektorhoz
	 * @return Az �sszegvektor
	 */
	public Vector add(Vector v){
		return new Vector(x + v.x, y + v.y);
	}
	
	/**
	 * K�t megadott vektor �sszeg�t adja vissza
	 * @param v1 Az egyik vektor
	 * @param v2 A m�sik vektor
	 * @return A k�t vektor �sszege
	 */
	public static Vector add(Vector v1, Vector v2){
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}
	
	/**
	 * Visszaadja a vektor X koordin�t�j�t
	 * @return X koordin�ta
	 */
	public double getX(){ 
		return x;
	}
	
	/**
	 * Visszaadja a vektor X koordin�t�j�t
	 * @return Y koordin�ta
	 */
	public double getY(){ 
		return y; 
	}
	
	/**
	 * Visszaadja a vektor hossz�t
	 * @return A vektor hossza
	 */
	public double length(){
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Visszaadja a vektor norm�ltj�t
	 * @return A vektor norm�ltja
	 */
	public Vector normalized() {
		if(x == 0 && y == 0)
			return new Vector(0, 0);
		return new Vector(x / this.length(), y / this.length());
	}
	
	/**
	 * Visszaadja a t�vols�got a vektork�nt megadott m�sik pont �s az aktu�lis vektor k�z�tt
	 * @param v A m�sik pont
	 * @return A k�t pont t�vols�ga
	 */
	public double distance(Vector v){
		return (new Vector(x, y, v.x, v.y)).length();
	}
	
	/**
	 * Visszaadja a k�t megadott helyvektor t�vols�g�t
	 * @param v1 Az egyik vektor
	 * @param v2 A m�sik vektor
	 * @return A k�t helyvektor t�vols�ga
	 */
	public static double distance(Vector v1, Vector v2){
		return (new Vector(v1, v2)).length();
	}
	
	/**
	 * A vektort a megadott skal�rral megszorozza �s az eredm�nyt egy �j vektork�nt adja vissza
	 * @param mul Skal�r
	 * @return Skal�ris szorzat
	 */
	public Vector multiply(double mul){
		return new Vector(x * mul, y * mul);
	}
	
	/**
	 * A megadott vektort a megadott skal�rral megszorozza
	 * @param v Vektor
	 * @param mul Skal�r
	 * @return Skal�ris szorzat
	 */
	public static Vector multiply(Vector v, double mul){
		return new Vector(v.x * mul, v.y * mul);
	}
	
	/**
	 * Visszaadja k�t vektor vektor�tlag�t
	 * @param v1 Az egyik vektor
	 * @param v2 A m�sik vektor
	 * @return Vektor�tlag
	 */
	public static Vector average(Vector v1, Vector v2){
		return new Vector( (v1.x+v2.x)/2, (v1.y+v2.y)/2  );
	}
	
	/**
	 * (x,y) form�tumban visszaadja a pontot
	 */
	public String toString(){
		return x + ", " + y;
	}
	
}
