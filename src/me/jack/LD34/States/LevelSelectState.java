package me.jack.LD34.States;

import me.jack.LD34.LD34;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Jack on 12/12/2015.
 */
public class LevelSelectState extends BasicGameState {


    LinkedHashMap<Level, Integer> introLevelStatus = new LinkedHashMap<Level, Integer>();
    LinkedHashMap<Level, Integer> easyLevelStatus = new LinkedHashMap<Level, Integer>();
    LinkedHashMap<Level, Integer> mediumLevelStatus = new LinkedHashMap<Level, Integer>();
    LinkedHashMap<Level, Integer> hardLevelStatus = new LinkedHashMap<Level, Integer>();
    private boolean startLevel = false;

    public static LevelSelectState instance;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

      /*  String line;
        try {
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =1;i!= 6;i++){
                    if(levelExists("levels/intro/" + i + ".txt")){
                        Level level = null;
                        try {
                            level = Level.load("levels/intro/" + i + ".txt",true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int status = -1;
                        if (i == 1 || i == 2)
                            status = 0;
                        introLevelStatus.put(level, status);
                    }
                    if(levelExists("levels/easy/" + i + ".txt")){
                        Level level = null;
                        try {
                            level = Level.load("levels/easy/" + i + ".txt",true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int status = -1;
                        if (i == 1 || i == 2)
                            status = 0;
                        easyLevelStatus.put(level, status);
                    }
                    if(levelExists("levels/medium/" + i + ".txt")){
                        Level level = null;
                        try {
                            level = Level.load("levels/medium/" + i + ".txt",true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int status = -1;
                        if (i == 1 || i == 2)
                            status = 0;
                        mediumLevelStatus.put(level, status);
                    }
                    if(levelExists("levels/hard/" + i + ".txt")){
                        Level level = null;
                        try {
                            level = Level.load("levels/hard/" + i + ".txt",true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int status = -1;
                        if (i == 1 || i == 2)
                            status = 0;
                        hardLevelStatus.put(level, status);
                    }
                }

            }
        }).start();

        LevelSelectState.instance = this;
        SoundEngine.getInstance().addSound("locked", new Sound("res/Sound/locked.wav"));
    }

    private boolean levelExists(String path){
        return Level.class.getResourceAsStream(path) != null;
    }
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        startLevel = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Intro Levels", 0, 30);
        int x = 8;
        int y = 45;
        int i = 1;
        for (Level level : introLevelStatus.keySet()) {
            String status = "";
            int statusCode = introLevelStatus.get(level);
            if (statusCode == -1) {
                status = "Locked";
            } else if (statusCode == 0) {
                status = "Unlocked";
            } else {
                status = statusCode + " Stars";
            }
            graphics.drawString(i + "-" + status, x, y);
            y += 30;
            i++;
        }
        graphics.drawString("Easy Levels", 120, 30);
        x = 128;
        y = 45;
        i = 1;
        for (Level level : easyLevelStatus.keySet()) {
            String status = "";
            int statusCode = easyLevelStatus.get(level);
            if (statusCode == -1) {
                status = "Locked";
            } else if (statusCode == 0) {
                status = "Unlocked";
            } else {
                status = statusCode + " Stars";
            }
            graphics.drawString(i + "-" + status, x, y);
            y += 30;
            i++;
        }

        graphics.drawString("Medium Levels", 240, 30);
        x = 248;
        y = 45;
        i = 1;
        for (Level level : mediumLevelStatus.keySet()) {
            String status = "";
            int statusCode = mediumLevelStatus.get(level);
            if (statusCode == -1) {
                status = "Locked";
            } else if (statusCode == 0) {
                status = "Unlocked";
            } else {
                status = statusCode + " Stars";
            }
            graphics.drawString(i + "-" + status, x, y);
            y += 30;
            i++;
        }

        graphics.drawString("Hard Levels", 8, 195);
        x = 8;
        y = 210;
        i = 1;
        for (Level level : hardLevelStatus.keySet()) {
            String status = "";
            int statusCode = hardLevelStatus.get(level);
            if (statusCode == -1) {
                status = "Locked";
            } else if (statusCode == 0) {
                status = "Unlocked";
            } else {
                status = statusCode + " Stars";
            }
            graphics.drawString(i + "-" + status, x, y);
            y += 30;
            i++;
        }

    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == 0) {
            if (x > 8 && y > 45 && x < 92 && y < 195) {
                int yT = (y - 45) / 30;
                Level level = getLevelAt(introLevelStatus.keySet(), yT);
                if (introLevelStatus.get(level) == -1) {
                    SoundEngine.getInstance().play("locked");
                    return;
                }
                try {
                    InGameState.instance.setLevel(Level.load("levels/intro/" + (yT + 1) + ".txt",false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startLevel = true;
                InGameState.instance.levelCat = "intro";
                InGameState.instance.levelPos = yT + 1;
            }

            if (x > 120 && y > 45 && x < 92 + 158 && y < 195) {
                int yT = (y - 45) / 30;
                Level level = getLevelAt(easyLevelStatus.keySet(), yT);
                if (easyLevelStatus.get(level) == -1) {
                    SoundEngine.getInstance().play("locked");
                    return;
                }
                try {
                    InGameState.instance.setLevel(Level.load("levels/easy/" + (yT + 1) + ".txt",false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startLevel = true;
                InGameState.instance.levelCat = "easy";
                InGameState.instance.levelPos = yT + 1;
            }

            if (x > 248 && y > 45 && x < 92 + 158 + 158 && y < 195) {
                int yT = (y - 45) / 30;
                Level level = getLevelAt(mediumLevelStatus.keySet(), yT);
                if (mediumLevelStatus.get(level) == -1) {
                    SoundEngine.getInstance().play("locked");
                    return;
                }
                try {
                    InGameState.instance.setLevel(Level.load("levels/medium/" + (yT + 1) + ".txt",false));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startLevel = true;
                InGameState.instance.levelCat = "medium";
                InGameState.instance.levelPos = yT + 1;
            }
        }

        if (x > 8 && y > 210 && x < 92 && y < 360) {
            int yT = (y - 210) / 30;
            Level level = getLevelAt(hardLevelStatus.keySet(), yT);
            if (hardLevelStatus.get(level) == -1) {
                SoundEngine.getInstance().play("locked");
                return;
            }
            try {
                InGameState.instance.setLevel(Level.load("levels/hard/" + (yT + 1) + ".txt",false));
            } catch (IOException e) {
                e.printStackTrace();
            }
            startLevel = true;
            InGameState.instance.levelCat = "hard";
            InGameState.instance.levelPos = yT + 1;
        }
    }

    public Level getLevelAt(Collection<Level> levels, int pos) {
        int i = 0;
        for (Level level : levels) {
            if (i == pos)
                return level;
            i++;
        }
        return null;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (startLevel)
            stateBasedGame.enterState(1);
    }

    public void setScore(int cat, int pos, int score) {
        if (score == 0) {
            if (cat == 0) {
                Level level = getLevelAt(introLevelStatus.keySet(), pos);
                if (level == null)
                    return;
                int cScore = introLevelStatus.get(level);
                if (cScore == -1) {
                    introLevelStatus.put(level, score);
                }
            } else if (cat == 1) {
                Level level = getLevelAt(easyLevelStatus.keySet(), pos);
                if (level == null)
                    return;
                int cScore = easyLevelStatus.get(level);
                if (cScore == -1) {
                    easyLevelStatus.put(level, score);
                }
            } else if (cat == 2) {
                Level level = getLevelAt(mediumLevelStatus.keySet(), pos);
                if (level == null)
                    return;
                int cScore = mediumLevelStatus.get(level);
                if (cScore == -1) {
                    mediumLevelStatus.put(level, score);
                }
            } else if (cat == 3) {
                Level level = getLevelAt(hardLevelStatus.keySet(), pos);
                if (level == null)
                    return;
                int cScore = hardLevelStatus.get(level);
                if (cScore == -1) {
                    hardLevelStatus.put(level, score);
                }
            }
        } else {
            if (cat == 0) {
                Level level = getLevelAt(introLevelStatus.keySet(), pos);
                introLevelStatus.put(level, score);
            } else if (cat == 1) {
                Level level = getLevelAt(easyLevelStatus.keySet(), pos);
                easyLevelStatus.put(level, score);
            } else if (cat == 2) {
                Level level = getLevelAt(mediumLevelStatus.keySet(), pos);
                mediumLevelStatus.put(level, score);
            } else if (cat == 3) {
                Level level = getLevelAt(hardLevelStatus.keySet(), pos);
                hardLevelStatus.put(level, score);
            }
        }
    }

    @Override
    public int getID() {
        return 3;
    }
}
