package me.jack.LD34.Level.Tiles;

import me.jack.LD34.Level.AllowedMovementType;
import me.jack.LD34.Level.Level;
import me.jack.LD34.Level.Tile;
import me.jack.LD34.Level.TileType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

/**
 * Created by Jack on 12/12/2015.
 */
public class FlingTile extends Tile {
    private int direction;

    private Animation anim;
    int rot = 0;

    public FlingTile(int direction) {
        super(TileType.FLING, AllowedMovementType.NONE);
        this.direction = direction;
        SpriteSheet animSheet = new SpriteSheet(ImageUtil.loadImage("res/fling_anim.png"), 32, 32);
        anim = new Animation();
        if (direction == 0)
            rot = 90;
        if (direction == 1)
            rot = 180;
        if(direction == 2)
            rot = 270;
            for (int x = 0; x != animSheet.getHorizontalCount(); x++) {
                for (int y = 0; y != animSheet.getVerticalCount(); y++) {
                    Image sprite = animSheet.getSprite(x, y);
                    sprite.rotate(rot);
                    anim.addFrame(sprite, 10);
                }
            }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        //g.drawImage(fTiles[direction], x * Tile.tileSize, y * Tile.tileSize);
        anim.draw(x * Tile.tileSize, y * Tile.tileSize);
    }

    @Override
    public void steppedOn(Level level) {
        SoundEngine.getInstance().play("fling");
        level.player.fling(direction);
    }
}
