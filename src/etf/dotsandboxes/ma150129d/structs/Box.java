package etf.dotsandboxes.ma150129d.structs;

import java.util.ArrayList;
import java.util.List;

public class Box {
	private List<Dot> dots;
	private List<Line> lines;
	
	public Box(Dot ul, Dot ur, Dot lr, Dot ll){
		dots = new ArrayList<Dot>();
		lines = new ArrayList<Line>();
		
		dots.add(ul);
		dots.add(ur);
		dots.add(lr);
		dots.add(ll);
	}
	
	public void addLine(Line l){
		if (lines.size() < 4)
			lines.add(l);
	}
	
	public int numLinesDrawn(){
		int numDrawn = 0;
		
		for (Line l: lines){
			if (l.isDrawn())
				numDrawn++;
		}
		
		return numDrawn;
	}
	
	public void completeBox(String player, int moveNum){
		if (numLinesDrawn() < 3)
			return;
		System.out.println("Iscrtan je kvadrat " + this);
		for (Line l: lines){
			if (! l.isDrawn())
				l.drawLine(player, moveNum);
		}
	}

	public List<Dot> getDots() {
		return dots;
	}
	
	public String toString() {
		return dots.get(0) + " / " + dots.get(1) + " / " + dots.get(2) + " / " + dots.get(3);
	}
}
