package etf.dotsandboxes.ma150129d.bots;

import etf.dotsandboxes.ma150129d.game.Game;
import etf.dotsandboxes.ma150129d.structs.State;

public abstract class Player {
    protected String name;
    protected Game game;

    public abstract State playMove(State state);
}
