// ID: 208649186

package shapes;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class for a simple circle shape.
 * It is not a point, it has dimension. It is not a ball, it doesn't move.
 */
public class Circle implements Shape {
    // fields:
    private final Point center;
    private final int radius;
    private final Color borderColor;
    private final Color mainColor;


    /**
     * Constructor.
     * @param center - the center of the circle.
     * @param radius - the radius of the circle.
     * @param border - the circle's border color.
     * @param main - the circle's main color.
     */
    public Circle(Point center, int radius, Color border, Color main) {
        this.center = center;
        this.radius = radius;
        this.borderColor = border;
        this.mainColor = main;
    }

    /**
     * Accessor to the x value of the ball's center point.
     *
     * @return the center x value of the circle.
     */
    public int getX() {
        return (int) this.center.getX();
    }


    /**
     * Accessor to the y value of the ball's center point.
     *
     * @return the center y value of the circle.
     */
    public int getY() {
        return (int) this.center.getY();
    }


    /**
     * Accessor to the radius of the ball.
     *
     * @return the radius (size) of the circle.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Accessor to the center of the ball.
     *
     * @return the center Point of the circle.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Getter.
     * @return the main color of the circle.
     */
    public Color getMainColor() {
        return mainColor;
    }

    /**
     * Getter.
     * @return the border color of the circle.
     */
    public Color getBorderColor() {
        return borderColor;
    }


    @Override
    public void drawShape(DrawSurface d) {
        // Converting to int the circle's center values.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();

        // Draw the circle
        d.setColor(this.borderColor);
        d.drawCircle(x, y, this.radius);

        // Draw the circle's border
        d.setColor(this.mainColor);
        d.fillCircle(x, y, this.radius);
    }
}
