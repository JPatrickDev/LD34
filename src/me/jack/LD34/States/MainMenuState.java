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
public class MainMenuState extends BasicGameState {

    Image logo, menu;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        logo = ImageUtil.loadImage("res/logo.png");
        menu = ImageUtil.loadImage("res/menu.png");
        this.game = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(menu, 0, 0);
    }

    private StateBasedGame game;

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if (x > 0 && x < 400 && y > 153 && y < 153 + 64)
            game.enterState(1);
        else if (x > 0 && x < 400 && y > 220 && y < 220 + 64)
            game.enterState(4);
        else if (x > 0 && x < 400 && y > 287 && y < 287 + 64)
            game.enterState(5);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 0;
    }

}
