package etf.dotsandboxes.ma150129d.bots;

import java.util.ArrayList;
import java.util.Random;

import etf.dotsandboxes.ma150129d.structs.Box;
import etf.dotsandboxes.ma150129d.structs.Dot;
import etf.dotsandboxes.ma150129d.structs.Line;
import etf.dotsandboxes.ma150129d.structs.State;

public class PlayerEasy extends Player {
	public PlayerEasy(String name) {
		this.name = name;
	}

	@Override
    public State playMove(State state) {
    	Box boxToComplete = state.getBoxWithThreeLines();
    	if (boxToComplete != null) {
			state.setNextMoveNumber(state.getNextMoveNumber() + 1);
    		boxToComplete.completeBox(name, state.getNextMoveNumber());
    	}
    	else{
    		Line line;
    		Random random = new Random();
    		if (!state.isGameOver()) { //just to prevent infinite loop
	    		while(true) {
	    			line = state.getLine(generateRandomDots(random, state));
	    			if (line != null && !line.isDrawn())
	    				break;
	    		}
				state.setNextMoveNumber(state.getNextMoveNumber() + 1);
	    		line.drawLine(name, state.getNextMoveNumber());
    		}
    	}
    	
        return null;
    }
    
    private ArrayList<Dot> generateRandomDots(Random random, State state){
	    int x1 = random.nextInt(state.getNumberOfRows());
	    int y1 = random.nextInt(state.getNumberOfCols());
	    int x2, y2;
	    
	    if (random.nextBoolean()){ //x stays the same, y changes
	    	x2 = x1;
	    	
	    	if (y1 == 0)
		    	y2 = 1;
		    else if (y1 == state.getNumberOfCols() - 1)
		    	y2 = state.getNumberOfCols() - 2;
		    else
		    	y2 = random.nextBoolean() ? y1 - 1 : y1 + 1;
	    }
	    else { //y stays the same, x changes
	    	y2 = y1;
	    	
	    	if (x1 == 0)
		    	x2 = 1;
		    else if (x1 == state.getNumberOfRows() - 1)
		    	x2 = state.getNumberOfRows() - 2;
		    else
		    	x2 = random.nextBoolean() ? x1 - 1 : x1 + 1;
	    }
	    
	    ArrayList<Dot> dots = new ArrayList<Dot>();
	    dots.add(new Dot(x1, y1));
	    dots.add(new Dot(x2, y2));
	    
	    return dots;
    }
}
