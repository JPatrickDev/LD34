package me.jack.LD34;

import me.jack.LD34.States.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Jack on 12/12/2015.
 */
public class LD34 extends StateBasedGame{

    public LD34(String name) {
        super(name);
    }

    public static TrueTypeFont font;

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
       gameContainer.setShowFPS(false);
        addState(new MainMenuState());
        this.addState(new LevelSelectState());
        this.addState(new InGameState());
        this.addState(new LevelEndState());
        this.addState(new TutorialState());
        addState(new AboutState());
    }
}
