package etf.dotsandboxes.ma150129d.structs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Line {
	private List<Dot> dots;
	private Boolean isDrawn;
	private String player;
	private int moveNum;
	
	public Line(Dot d1, Dot d2){
		dots = new ArrayList<Dot>();
		dots.add(d1);
		dots.add(d2);
		isDrawn = false;
		player = "";
		moveNum = 0;
	}

	public List<Dot> getDots() {
		return dots;
	}

	public Boolean isDrawn() {
		return isDrawn;
	}

	public void drawLine(String player, int moveNum) {
		if (!isDrawn){
			this.player = player;
			this.moveNum = moveNum;
			isDrawn = true;
		}
		System.out.println("Iscrtana je linija " + this);
	}
	
	public String getPlayer() {
		return player;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public String toString() {
		return "" + dots.get(0) + " / " + dots.get(1);
	}
	
	public boolean equals(Line line) {
		if (dots.get(0).equals(line.getDots().get(0)) && dots.get(1).equals(line.getDots().get(1)))
			return true;
		else if (dots.get(0).equals(line.getDots().get(1)) && dots.get(1).equals(line.getDots().get(0)))
			return true;
		else
			return false;
	}
	
	public static Comparator<Line> chronologicalComparator = new Comparator<Line>() {
		public int compare(Line l1, Line l2) {
		   if (l1.moveNum < l2.moveNum)
			   return -1;
		   else if (l1.moveNum == l2.moveNum)
			   return 0;
		   else
			   return 1;
	    }
	};
}
