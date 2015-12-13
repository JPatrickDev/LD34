package me.jack.LD34.States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 13/12/2015.
 */
public class AboutState extends BasicGameState{

    Image about;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        about = ImageUtil.loadImage("res/about.png");
        game = stateBasedGame;
    }

    StateBasedGame game;
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(about,0,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void keyPressed(int key, char c) {
        game.enterState(0);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        game.enterState(0);
    }

    @Override
    public int getID() {
        return 5;
    }

}
