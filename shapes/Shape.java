// ID: 208649186

package shapes;

import biuoop.DrawSurface;

/**
 * @author Coral Kuta.
 * An interface to hold all shapes of the game.
 * Intended for drawing on the screen.
 */
public interface Shape {
    /**
     * Draw the shape to the screen.
     * @param d - the surface.
     */
    void drawShape(DrawSurface d);
}
