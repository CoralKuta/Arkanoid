// ID: 208649186

package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import shapes.Rectangle;
import shapes.Shape;
import java.util.ArrayList;

/**
 * @author Coral Kuta.
 * A class for holding all the decorations of a level - it's screen.
 * The background that's behind.
 */
public class Background implements Sprite {
    private final ArrayList<Shape> shapes;
    private final Rectangle background;

    /**
     * Constructor.
     * @param background - the color of the background.
     * @param shapes - a list of shapes.
     */
    public Background(Rectangle background, ArrayList<Shape> shapes) {
        this.background = background;
        this.shapes = shapes;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.background.drawShape(d);
        for (Shape shape : this.shapes) {
            shape.drawShape(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {

    }

    @Override
    public void removeFromGame(GameLevel game) {

    }
}
