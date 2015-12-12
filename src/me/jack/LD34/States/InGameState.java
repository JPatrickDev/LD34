package me.jack.LD34.States;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jack on 12/12/2015.
 */
public class InGameState extends BasicGameState {

    Level level;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Tile.init();
    }


    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        //  level = new Level(5);
        try {
            level = Level.load("levels/1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if (level != null) {
            level.render(graphics);
            drawButtons(graphics);
        }
    }

    Rectangle upDown, leftRight;

    public void drawButtons(Graphics g) {
        int y = level.d * Tile.tileSize;
        g.fillRect(0, y, 128, 128);

        g.fillRect(272, y, 128, 128);

        AllowedMovementType currentMove = level.currentMove();
        g.setColor(Color.black);
        if (currentMove.getUPDOWN() == 0) {
            g.drawString("UP", 64, y + 64);
        } else {
            g.drawString("DOWN", 64, y + 64);
        }
        if (currentMove.getLEFTRIGHT() == 0) {
            g.drawString("LEFT", 272 + 64, y + 64);
        } else {
            g.drawString("RIGHT", 272 + 64, y + 64);
        }
        g.setColor(Color.white);
        if (upDown == null) {
            upDown = new Rectangle(0, y, 128, 128);
            leftRight = new Rectangle(272, y, 128, 128);
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if (button != 0)
            return;


        AllowedMovementType currentMove = level.currentMove();

        if (upDown.contains(x, y)) {
            if(currentMove.getUPDOWN() == 0){
                level.player.setY(level.player.getY() - Tile.tileSize);
            }else{
                level.player.setY(level.player.getY() +Tile.tileSize);
            }
        } else if (leftRight.contains(x, y)) {
            if(currentMove.getLEFTRIGHT() == 0){
                level.player.setX(level.player.getX() - Tile.tileSize);
            }else{
                level.player.setX(level.player.getX() + Tile.tileSize);
            }
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 1;
    }
}