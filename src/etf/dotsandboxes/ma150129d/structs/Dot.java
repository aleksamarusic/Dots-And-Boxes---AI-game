package etf.dotsandboxes.ma150129d.structs;


public class Dot {
	private int x, y;
	
	public Dot(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Dot dot) {
		return this.x == dot.x && this.y == dot.y;
	}
	
	public String toString() {
		return "" + x + ", " + y;
	}
}
