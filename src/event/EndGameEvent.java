package event;

import gameEngine.MainGameLoop;
import geometry.RectangleFactory;
import render.Color;
import render.Effect;

public class EndGameEvent extends Event{

    public EndGameEvent() {
        super();
        this.generator = MainGameLoop.player;
    }

    public boolean execute() {
        RectangleFactory rf = new RectangleFactory();
        Effect gameOverEffect = new Effect(rf.createRectangle(50, 50, 800, 800), new Color(255, 0, 0, 0), -1);
        return true;
    }
    public boolean revert() {
        return false;
    }

}
