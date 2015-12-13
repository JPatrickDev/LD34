package me.jack.LD34.States;

import me.jack.LD34.Level.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jack on 12/12/2015.
 */
public class LevelSelectState extends BasicGameState{


    HashMap<Level,Integer> introLevelStatus = new HashMap<Level,Integer>();
    HashMap<Level,Integer> easyLevelStatus = new HashMap<Level,Integer>();
    HashMap<Level,Integer> mediumLevelStatus = new HashMap<Level,Integer>();
    HashMap<Level,Integer> hardLevelStatus = new HashMap<Level,Integer>();
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        File introLevels = new File("levels/intro/");
        File easyLevels = new File("levels/easy/");
        File mediumLevels = new File("levels/medium/");
        File hardLevels = new File("levels/hard/");
        for(String f : introLevels.list()){
            File levelFile = new File(introLevels,f);
            try {
                Level level = Level.load(levelFile.getPath());
                introLevelStatus.put(level,-1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 3;
    }
}
