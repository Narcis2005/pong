package pong;

public class Vector2 {
	double x = 0, y = 0;
	Vector2() {
		
	}
	Vector2(double x,double y) {
		this.x = x;
		this.y = y;
	}
	public Vector2 multiply (double n) {
		return new Vector2(x * n, y * n);
	}
	public Vector2 add (double n) {
		return new Vector2(x + n, y + n);
	}
	public Vector2 add (Vector2 n) {
		return new Vector2((double)x + n.x, (double)y + n.y);
	}
	public double speed () {
		return Math.sqrt(x * x + y * y);
	}
}
