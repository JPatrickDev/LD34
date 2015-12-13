package me.jack.LD34.States;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

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
    String levelCat;

    public static InGameState instance;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        InGameState.instance = this;
        Tile.init();
        buttons[0] = ImageUtil.loadImage("res/upbutton.png");
        buttons[1] = ImageUtil.loadImage("res/downbutton.png");
        buttons[2] = ImageUtil.loadImage("res/leftbutton.png");
        buttons[3] = ImageUtil.loadImage("res/rightbutton.png");

        SoundEngine.getInstance().addSound("badmove", new Sound("res/Sound/badmove.wav"));
        SoundEngine.getInstance().addSound("tp", new Sound("res/Sound/tp.wav"));
        SoundEngine.getInstance().addSound("fling",new Sound("res/Sound/fling.wav"));
        SoundEngine.getInstance().addSound("end",new Sound("res/Sound/end.wav"));
        SoundEngine.getInstance().addSound("start",new Sound("res/Sound/start.wav"));
    }


    Image[] buttons = new Image[4];
    boolean levelOver = false;

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        levelOver = false;
        if (level == null && !backToLevelSelect)
            nextLevel();
        if (backToLevelSelect)
            game.enterState(3);
    }

    public void levelOver() {
        levelOver = true;
    }


    boolean backToLevelSelect = false;

    public void nextLevel() {
        System.out.println("Loading: " + (levelPos + 1));
        levelPos++;
        try {
            level = Level.load("levels/" + levelCat + "/" + levelPos + ".txt");
        } catch (NullPointerException  e) {
            backToLevelSelect = true;
            return;
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


    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Keyboard.KEY_ESCAPE) {
            backToLevelSelect = true;
        }
    }

    Rectangle upDown, leftRight;

    public void drawButtons(Graphics g) {
        int y = 260;
        AllowedMovementType currentMove = level.currentMove();
        g.setColor(Color.black);
        if (currentMove == AllowedMovementType.NONE) {
            g.drawString("-", 64, y + 64);
            g.drawString("-", 272 + 64, y + 64);
        } else {
            g.drawImage(buttons[currentMove.getUPDOWN()], 0, 400 - 64);
            g.drawImage(buttons[currentMove.getLEFTRIGHT() + 2], 96, 400 - 64);
            if (upDown == null) {
                upDown = new Rectangle(0, 400 - 64, 64, 64);
                leftRight = new Rectangle(96, 400 - 64, 64, 64);
            }
        }
        g.setColor(Color.white);
        g.drawString("Moves: " + level.moves, 0, 400 - 80);
        g.drawString("Level: " + levelPos, 300, 400 - 80);
        g.drawString("Cat: " + levelCat, 300, 400 - 60);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if (button != 0)
            return;


        AllowedMovementType currentMove = level.currentMove();

        if (upDown.contains(x, y)) {
            if (level.player.state != 0)
                return;
            if (currentMove.getUPDOWN() == 0) {
                if (level.player.getY() - Tile.tileSize < 0) {
                    SoundEngine.getInstance().play("badmove");
                    return;
                }
                //    level.moves++;
                level.player.moveTo(level.player.getX(), level.player.getY() - Tile.tileSize);
            } else {
                if ((level.player.getY() + Tile.tileSize) / Tile.tileSize > level.d - 1) {
                    SoundEngine.getInstance().play("badmove");
                    return;
                }
                level.player.moveTo(level.player.getX(), level.player.getY() + Tile.tileSize);
                //   level.moves++;
            }
        } else if (leftRight.contains(x, y)) {
            if (level.player.state != 0)
                return;
            if (currentMove.getLEFTRIGHT() == 0) {
                if (level.player.getX() - Tile.tileSize < 0) {
                    SoundEngine.getInstance().play("badmove");
                    return;
                }
                level.player.moveTo(level.player.getX() - Tile.tileSize, level.player.getY());
                //   level.moves++;
            } else {
                if ((level.player.getX() + Tile.tileSize) / Tile.tileSize > level.d - 1) {
                    SoundEngine.getInstance().play("badmove");
                    return;
                }
                //level.moves++;
                level.player.moveTo(level.player.getX() + Tile.tileSize, level.player.getY());
            }
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (level != null) {
            level.update(this);
            if (levelOver) {
                LevelEndState.movesTaken = level.moves;
                LevelEndState.minMoves = level.minMoves;
                LevelEndState.state = this;
                LevelEndState.pos = levelPos - 1;
                LevelEndState.cat = levelCat;
                level = null;
                stateBasedGame.enterState(2);
            }
        }
        if (backToLevelSelect) {
            stateBasedGame.enterState(3);
            backToLevelSelect = false;
        }
    }

    @Override
    public int getID() {
        return 1;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
