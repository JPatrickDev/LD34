package me.jack.LD34.States;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

import javax.swing.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jack on 12/12/2015.
 */
public class InGameState extends BasicGameState {

    Level level;

    int levelPos = 0;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Tile.init();
        buttons[0] = ImageUtil.loadImage("res/upbutton.png");
        buttons[1] = ImageUtil.loadImage("res/downbutton.png");
        buttons[2] = ImageUtil.loadImage("res/leftbutton.png");
        buttons[3] = ImageUtil.loadImage("res/rightbutton.png");
    }


    Image[] buttons = new Image[4];

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        nextLevel();
    }

    public void nextLevel() {
        levelPos++;
        try {
            level = Level.load("levels/" + levelPos + ".txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Level " + levelPos + " not found, exiting");
            System.exit(0);
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
        int y = 260;
        AllowedMovementType currentMove = level.currentMove();
        g.setColor(Color.black);
        if(currentMove == AllowedMovementType.NONE){
            g.drawString("-", 64, y + 64);
            g.drawString("-", 272 + 64, y + 64);
        }else {
            g.drawImage(buttons[currentMove.getUPDOWN()],0,400-64);
            g.drawImage(buttons[currentMove.getLEFTRIGHT() + 2],96,400-64);
            if (upDown == null) {
                upDown = new Rectangle(0,400-64, 64, 64);
                leftRight = new Rectangle(96,400-64,64,64);
            }
        }
        g.setColor(Color.white);
        g.drawString("Moves: " + level.moves,0,400-80);
        g.drawString("Level: " + levelPos,300,400-80);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if (button != 0)
            return;


        AllowedMovementType currentMove = level.currentMove();

        if (upDown.contains(x, y)) {
            if(level.player.state != 0)
                return;
            if (currentMove.getUPDOWN() == 0) {
                if(level.player.getY() - Tile.tileSize <0){
                    return;
                }
            //    level.moves++;
                level.player.moveTo(level.player.getX(),level.player.getY()-Tile.tileSize);
            } else {
                if((level.player.getY() + Tile.tileSize)/Tile.tileSize > level.d-1){
                    return;
                }
                level.player.moveTo(level.player.getX(),level.player.getY()+Tile.tileSize);
             //   level.moves++;
            }
        } else if (leftRight.contains(x, y)) {
            if(level.player.state != 0)
                return;
            if (currentMove.getLEFTRIGHT() == 0) {
                if(level.player.getX() - Tile.tileSize <0){
                    return;
                }
                level.player.moveTo(level.player.getX()-Tile.tileSize,level.player.getY());
             //   level.moves++;
            } else {
                if((level.player.getX() + Tile.tileSize)/Tile.tileSize > level.d-1){
                    return;
                }
                //level.moves++;
                level.player.moveTo(level.player.getX()+Tile.tileSize,level.player.getY());
            }
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (level != null) {
            level.update(this);
        }
    }

    @Override
    public int getID() {
        return 1;
    }
}
