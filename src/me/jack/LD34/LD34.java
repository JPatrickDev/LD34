package me.jack.LD34;

import me.jack.LD34.States.InGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 12/12/2015.
 */
public class LD34 extends StateBasedGame{

    public LD34(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        gameContainer.setShowFPS(false);
        this.addState(new InGameState());
    }
}
