package etf.dotsandboxes.ma150129d.structs;

import java.util.ArrayList;
import java.util.List;

public class State {

	private ArrayList<Line> lines;
	private int numberOfRows, numberOfCols;
	private Box[][] boxes;
	private int playerOnTheMove;
	private int nextMoveNumber;
	private int playerOneScore;
	private int playerTwoScore;

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}

	public int getPlayerOnTheMove() {
		return playerOnTheMove;
	}

	public void setPlayerOnTheMove(int playerOnTheMove) {
		this.playerOnTheMove = playerOnTheMove;
	}

	public int getNextMoveNumber() {
		return nextMoveNumber;
	}

	public void setNextMoveNumber(int nextMoveNumber) {
		this.nextMoveNumber = nextMoveNumber;
	}

	public State(ArrayList<Line> lines, int numberOfRows, int numberOfCols, Box[][] boxes) {
		this.lines = lines;
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		this.boxes = boxes;
		playerOneScore = 0;
		playerTwoScore = 0;
		playerOnTheMove = 0;
	}

	public State(int numberOfRows, int numberOfCols) {
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		playerOneScore = 0;
		playerTwoScore = 0;
		playerOnTheMove = 0;
		initLinesAndBoxes();
	}

	private void initLinesAndBoxes() {
		lines = new ArrayList<Line>();
		boxes = new Box[numberOfRows -1][numberOfCols -1];
		
		for(int i = 0; i< numberOfRows; i++){
			for(int j = 0; j< numberOfCols; j++){
				if (j < numberOfCols - 1){
					lines.add(new Line(new Dot(i,  j), new Dot(i, j + 1)));
					System.out.println("Dodata je linija " + lines.get(lines.size()-1));
				}
				
				if (i < numberOfRows - 1){
					lines.add(new Line(new Dot(i,  j), new Dot(i + 1, j)));
					System.out.println("Dodata je linija " + lines.get(lines.size()-1));
				}
				
				if (i < numberOfRows - 1 && j < numberOfCols - 1){
					boxes[i][j] = new Box(new Dot(i, j), new Dot(i, j+1), new Dot(i+1, j+1), new Dot(i+1, j));
					System.out.println("Dodat je kvadrat " + boxes[i][j]);
				}
			}
		}

		//add lines to squares (they now have just corresponding dots)
		for (Line line: lines){
			List<Dot> dots = line.getDots();
			
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
			
			if (iBox1 >= 0 && iBox1 < numberOfRows - 1 && jBox1 >= 0 && jBox1 < numberOfCols - 1)
				boxes[iBox1][jBox1].addLine(line);
			
			if (iBox2 >= 0 && iBox2 < numberOfRows - 1 && jBox2 >= 0 && jBox2 < numberOfCols - 1)
				boxes[iBox2][jBox2].addLine(line);
		}
	}
	
	public Box getBoxWithThreeLines(){
		for(int i = 0; i< numberOfRows -1; i++){
			for(int j = 0; j< numberOfCols -1; j++){
				if (boxes[i][j].numLinesDrawn() == 3)
					return boxes[i][j];
			}
		}
		return null;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfCols() {
		return numberOfCols;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public void setNumberOfCols(int numberOfCols) {
		this.numberOfCols = numberOfCols;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public Line getLine(ArrayList<Dot> dots) {
		if (dots.get(0).equals(dots.get(1)))
			return null;
		for (Line line: lines){
			List<Dot> curr = line.getDots();
			if (!dots.get(0).equals(curr.get(0)) && !dots.get(0).equals(curr.get(1)))
				continue;
			if (!dots.get(1).equals(curr.get(0)) && !dots.get(0).equals(curr.get(1)))
				continue;
			return line;
		}
		return null;
	}
	
	public boolean isGameOver() {
		for (Line line: lines){
			if (!line.isDrawn())
				return false;
		}
		return true;
	}

	public int heuristicFunction() {
		return playerOnTheMove % 2 == 0 ? playerOneScore - playerTwoScore : playerTwoScore - playerOneScore;
	}
	
	//Just for testing
	/*public void main(String[] args){
		numberOfRows = 8;
		numberOfCols = 10;
		
		//initLinesAndBoxes();
		
		PlayerEasy playerEasy = new PlayerEasy();
		
		for(int i=0; i<20; i++)
			playerEasy.playMove(new State());
		
		lines.get(0).drawLine("Aleksa", 20);
		lines.get(1).drawLine("Aleksa", 21);
		lines.get(3).drawLine("Aleksa", 22);
		
		playerEasy.playMove(new State());
		
		//FileManager.writeGameToFile();
		
		FileManager.readGameFromFile();
		
		System.out.println("m = " + numberOfRows + ", n = " + numberOfCols);
	}*/
}
