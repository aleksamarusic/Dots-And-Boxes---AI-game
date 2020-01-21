package etf.dotsandboxes.ma150129d.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import etf.dotsandboxes.ma150129d.structs.Dot;
import etf.dotsandboxes.ma150129d.structs.Line;
import etf.dotsandboxes.ma150129d.structs.Start;

public class FileManager {
	public static void writeGameToFile(){
		ArrayList<Line> lines = Start.getLines();
		ArrayList<Line> sortedLines = new ArrayList<Line>();
		
		for(Line l: lines){
			sortedLines.add(l);
		}
		
		Collections.sort(sortedLines, Line.chronologicalComparator);
		
		Path path = new File("game.txt").toPath();
		Charset charset = Charset.forName("US-ASCII");
		
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			
			String dimensions = "" + Start.getM() + " " + Start.getN();
		    writer.write(dimensions, 0, dimensions.length());
		    writer.newLine();
		    
		    for(Line l: sortedLines){
		    	if (l.isDrawn()){
		    		String lineString = lineToString(l);
		    		writer.write(lineString, 0, lineString.length());
		    		writer.newLine();
		    	}
		    }
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public static Boolean readGameFromFile(){
		Path path = new File("game.txt").toPath();
		Charset charset = Charset.forName("US-ASCII");
		
		ArrayList<Line> newLines = new ArrayList<Line>();
		
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
		    String str = null;
		    
		    str = reader.readLine();
		    if (str == null){
		    	return false;
		    }
		    else{
		    	String[] strings = str.split(" ");
		    	int m = Integer.parseInt(strings[0]);
		    	int n = Integer.parseInt(strings[1]);
		    	
		    	if (m > 0 && n > 0){
			    	Start.setM(m);
			    	Start.setN(n);
		    	}
		    	else{
		    		return false;
		    	}
		    }
		    
		    while ((str = reader.readLine()) != null) {
		    	Line l = stringToLine(str);
		    	
		    	if (l != null)
		    		newLines.add(l);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		    return false;
		}
		catch (NumberFormatException e) {
		    System.err.format("NumberFormatException: %s%n", e);
		    return false;
		}
		
		ArrayList<Line> lines = Start.getLines();
		
		for (Line newL: newLines){
			for(Line l: lines){
				if (newL.equals(l))
					l.drawLine(newL.getPlayer(), newL.getMoveNum());
			}
		}
		
		return true;
	}

	private static String lineToString(Line l){
		List<Dot> dots = l.getDots();
		
		String lineString = "";
		
		if (dots.get(0).getX() == dots.get(1).getX()){ //horizontal line
			int y;
			
			if (dots.get(0).getY() < dots.get(1).getY())
				y = dots.get(0).getY();
			else
				y = dots.get(1).getY();
			
			lineString += dots.get(0).getX();
			lineString += (char) (y + 65);
		}
		else { //vertical line
			int x;
			
			if (dots.get(0).getX() < dots.get(1).getX())
				x = dots.get(0).getX();
			else
				x = dots.get(1).getX();
			
			lineString += (char) (x + 65);
			lineString += dots.get(0).getY();
		}
		
		lineString += " " + l.getPlayer();
		
		return lineString;
	}
	
	private static Line stringToLine(String str) {
		// TODO Auto-generated method stub
		return null;
	}
}
