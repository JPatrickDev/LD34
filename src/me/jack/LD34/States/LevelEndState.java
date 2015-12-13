package me.jack.LD34.States;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

/**
 * Created by Jack on 12/12/2015.
 */
public class LevelEndState extends BasicGameState {


    public static int movesTaken, minMoves, timeTaken,pos;
    public static String cat;

    private int stars = 0;

    private Image star, noStar;
    public static InGameState state;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        star = ImageUtil.loadImage("res/star.png");
        noStar = ImageUtil.loadImage("res/noStar.png");
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {

        if(minMoves > movesTaken)
            minMoves = movesTaken;
        if (movesTaken <= minMoves) {
            stars = 3;
        } else {
            int percentDiff = ((movesTaken-minMoves)/minMoves) * 100;
            System.out.println(percentDiff);
            if (percentDiff <= 5) {
                stars = 2;
            } else
                stars = 1;
        }

        int catI = -1;
        if(cat.equals("intro"))
            catI = 0;
        else if(cat.equals("easy"))
            catI = 1;
        else if(cat.equals("medium"))
            catI = 2;
        LevelSelectState.instance.setScore(catI,pos,stars);
        LevelSelectState.instance.setScore(catI,pos+1,0);
        next = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        int starsToRender = stars;
        for (int i = 0; i != 3; i++) {
            if(starsToRender> 0){
                graphics.drawImage(star,102 + (i*64),168);
                starsToRender--;
            }else {
                graphics.drawImage(noStar,102 + (i*64),168);
            }
        }
        graphics.setColor(Color.white);
        graphics.drawString("Moves taken: " + movesTaken, 140, 168 + 68);
        graphics.drawString("Minimum: " + minMoves, 154, 168+68+16);
        graphics.drawString("Click to continue...",120,168+68+32);


    }

    boolean next = false;
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(next) {
      //      state.nextLevel();
            stateBasedGame.enterState(1);
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        next = true;
    }

    @Override
    public int getID() {
        return 2;
    }
}
