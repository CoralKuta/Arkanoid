// ID: 208649186

package sprites;

import biuoop.DrawSurface;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Coral Kuta.
 * A class for holding all the sprites of the game.
 */
public class SpriteCollection {
    private final List<Sprite> list;

    /**
     * Constructor of sprite collection - creates a list of objects.
     * We create a linked list because it's easier to manage.
     */
    public SpriteCollection() {
        this.list = new LinkedList<>();
    }


    /**
     * Add the sprite to the game.
     *
     * @param s - new sprite
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * Removes the sprite to the game.
     *
     * @param s - the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }


    /**
     * Notify all the sprites time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new LinkedList<>(this.list);

        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }


    /**
     * Call drawOn on all sprites.
     *
     * @param d - draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.list) {
            sprite.drawOn(d);
        }
    }
}