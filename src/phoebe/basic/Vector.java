package phoebe.basic;

public class Vector {
	private double x;
	private double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}	
	
	public Vector(double x1, double y1, double x2, double y2){
		this.x = x2 - x1;
		this.y = y2 - y1;
	}
	
	public Vector(Vector v1, Vector v2){
		this(v1.x, v1.y, v2.x, v2.y);
	}
	
	public Vector add (Vector v){
		return new Vector(x + v.x, y + v.y);
	}
	
	public double getX(){ 
		return x;
	}
	
	public double getY(){ 
		return y; 
	}
	
	public double length(){
		return Math.sqrt(x * x + y * y);
	}
	
	public Vector normalized() {
		return new Vector(x / this.length(), y / this.length());
	}
	
	public double distance(Vector v){
		return (new Vector(x, y, v.x, v.y)).length();
	}
	
}
