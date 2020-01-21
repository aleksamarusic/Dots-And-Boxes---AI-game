package etf.dotsandboxes.ma150129d.bots;

import java.util.ArrayList;
import java.util.Random;

import etf.dotsandboxes.ma150129d.structs.Box;
import etf.dotsandboxes.ma150129d.structs.Dot;
import etf.dotsandboxes.ma150129d.structs.Line;
import etf.dotsandboxes.ma150129d.structs.Start;
import etf.dotsandboxes.ma150129d.structs.State;

public class PlayerEasy extends Player {
    @Override
    public State playMove(State state) {
    	Box boxToComplete = Start.getBoxWithThreeLines();
    	
    	if (boxToComplete != null){
    		//TODO: ispraviti kada se bude znalo kako se
    		//dohvata ime igraca i potez!!!
    		boxToComplete.completeBox("Aleksa", 14);
    	}
    	else{
    		Line line;
    		Random random = new Random();
    		
    		if (!Start.isGameOver()){ //just to prevent infinite loop
	    		while(true) {
	    			line = Start.getLine(generateRandomDots(random));
	    			if (line != null && !line.isDrawn())
	    				break;
	    		}
	    		
	    		//TODO: ispraviti kada se bude znalo kako se
	    		//dohvata ime igraca i potez!!!
	    		//(potez se trenutno generise random samo zbog testiranja)
	    		line.drawLine("Aleksa", random.nextInt(20));
    		}
    	}
    	
        return null;
    }
    
    ArrayList<Dot> generateRandomDots(Random random){
	    int x1 = random.nextInt(Start.getM());
	    int y1 = random.nextInt(Start.getN());
	    int x2, y2;
	    
	    if (random.nextBoolean()){ //x stays the same, y changes
	    	x2 = x1;
	    	
	    	if (y1 == 0)
		    	y2 = 1;
		    else if (y1 == Start.getN() - 1)
		    	y2 = Start.getN() - 2;
		    else
		    	y2 = random.nextBoolean() ? y1 - 1 : y1 + 1;
	    }
	    else { //y stays the same, x changes
	    	y2 = y1;
	    	
	    	if (x1 == 0)
		    	x2 = 1;
		    else if (x1 == Start.getM() - 1)
		    	x2 = Start.getM() - 2;
		    else
		    	x2 = random.nextBoolean() ? x1 - 1 : x1 + 1;
	    }
	    
	    ArrayList<Dot> dots = new ArrayList<Dot>();
	    dots.add(new Dot(x1, y1));
	    dots.add(new Dot(x2, y2));
	    
	    return dots;
    }
}
