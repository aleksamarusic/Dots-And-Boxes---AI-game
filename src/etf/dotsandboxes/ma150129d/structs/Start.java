package etf.dotsandboxes.ma150129d.structs;

import java.util.ArrayList;
import java.util.List;

import etf.dotsandboxes.ma150129d.bots.PlayerEasy;

public class Start {
	
	private static ArrayList<Line> lines;
	private static int m, n;
	//private ArrayList<ArrayList<Box>> boxes;
	public static Box[][] boxes;
	
	public static void initLinesAndBoxes(){
		lines = new ArrayList<Line>();
		boxes = new Box[m-1][n-1];
		
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				
				if (j < n - 1){
					lines.add(new Line(new Dot(i,  j), new Dot(i, j + 1)));
					System.out.println("Dodata je linija " + lines.get(lines.size()-1));
				}
				
				if (i < m - 1){
					lines.add(new Line(new Dot(i,  j), new Dot(i + 1, j)));
					System.out.println("Dodata je linija " + lines.get(lines.size()-1));
				}
				
				if (i < m - 1 && j < n - 1){
					boxes[i][j] = new Box(new Dot(i, j), new Dot(i, j+1), new Dot(i+1, j+1), new Dot(i+1, j));
					System.out.println("Dodat je kvadrat " + boxes[i][j]);
				}
			}
		}
		
		for (Line l: lines){
			List<Dot> dots = l.getDots();
			
			int iBox1, jBox1, iBox2, jBox2;
			
			if (dots.get(0).getX() == dots.get(1).getX()){
				iBox1 = dots.get(0).getX() - 1;
				iBox2 = dots.get(0).getX();
				
				if (dots.get(0).getY() < dots.get(1).getY())
					jBox1 = jBox2 = dots.get(0).getY();
				else
					jBox1 = jBox2 = dots.get(1).getY();
			}
			else{
				jBox1 = dots.get(0).getY() - 1;
				jBox2 = dots.get(0).getY();
				
				if (dots.get(0).getX() < dots.get(1).getX())
					iBox1 = iBox2 = dots.get(0).getX();
				else
					iBox1 = iBox2 = dots.get(1).getX();
			}
			
			if (iBox1 >= 0 && iBox1 < m - 1 && jBox1 >= 0 && jBox1 < n - 1)
				boxes[iBox1][jBox1].addLine(l);
			
			if (iBox2 >= 0 && iBox2 < m - 1 && jBox2 >= 0 && jBox2 < n - 1)
				boxes[iBox2][jBox2].addLine(l);
		}
	}
	
	public static Box getBoxWithTreeLines(){
		for(int i=0; i<m-1; i++){
			for(int j=0; j<n-1; j++){
				if (boxes[i][j].numLinesDrawn() == 3)
					return boxes[i][j];
			}
		}
		
		return null;
	}

	public static int getM() {
		return m;
	}

	public static int getN() {
		return n;
	}

	public static Line getLine(ArrayList<Dot> dots) {
		if (dots.get(0).equals(dots.get(1)))
			return null;
		
		for (Line l: lines){
			List<Dot> curr = l.getDots();
			
			if (!dots.get(0).equals(curr.get(0)) && !dots.get(0).equals(curr.get(1)))
				continue;
			
			if (!dots.get(1).equals(curr.get(0)) && !dots.get(0).equals(curr.get(1)))
				continue;
			
			return l;
		}
		
		return null;
	}
	
	public static void main(String[] args){
		m = 8;
		n = 10;
		
		initLinesAndBoxes();
		
		PlayerEasy playerEasy = new PlayerEasy();
		
		for(int i=0; i<20; i++)
			playerEasy.playMove(new State());
		
		lines.get(0).setIsDrawn(true);
		lines.get(1).setIsDrawn(true);
		lines.get(3).setIsDrawn(true);
		
		playerEasy.playMove(new State());
	}
}
