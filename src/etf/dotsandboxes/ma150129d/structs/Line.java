package etf.dotsandboxes.ma150129d.structs;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private List<Dot> dots;
	private Boolean isDrawn;
	
	public Line(Dot d1, Dot d2){
		dots = new ArrayList<Dot>();
		dots.add(d1);
		dots.add(d2);
		
		isDrawn = false;
	}

	public List<Dot> getDots() {
		return dots;
	}

	public Boolean isDrawn() {
		return isDrawn;
	}

	public void setIsDrawn(Boolean isDrawn) {
		this.isDrawn = isDrawn;
	}
	
	public String toString() {
		return "" + dots.get(0) + " / " + dots.get(1);
	}
}
