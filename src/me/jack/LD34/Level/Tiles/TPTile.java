package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by Jack on 12/12/2015.
 */
public class TPTile extends Tile{

    private Point target;
    private Image customTile = null;
    Color darkerC = Color.decode("#57007F");
    Color lighterC = Color.decode("#AE00FF");
    public TPTile(Point target, Color color) {
        super(TileType.TP,AllowedMovementType.NONE);
        this.target = target;
        try {
            customTile = createCustomTile(color);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    private Image createCustomTile(Color colour) throws SlickException {
        Image tileTemplate = this.tpTile.copy();
        Color drk = colour.darker().darker();
        Graphics g = tileTemplate.getGraphics();

        for(int x = 0;x!= tileTemplate.getWidth();x++){
            for(int y = 0;y!= tileTemplate.getHeight();y++){
                if(tileTemplate.getColor(x,y).equals(lighterC)){
                    g.setColor(colour);
                    g.fillRect(x, y, 1, 1);
                }else if(tileTemplate.getColor(x,y).equals(darkerC)){
                    g.setColor(drk);
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
        g.flush();
        return tileTemplate;
    }

    @Override
    public void render(org.newdawn.slick.Graphics g, int x, int y) {
        g.drawImage(customTile, x * Tile.tileSize, y * Tile.tileSize);
    }

    @Override
    public void steppedOn(Level level) {
        System.out.println("Teleport " + target);
        level.tpPlayer(target);
    }
}
