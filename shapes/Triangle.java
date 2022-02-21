// ID: 208649186

package shapes;

import biuoop.DrawSurface;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Coral Kuta.
 * A class for represtetion of a rectangle - The basic collidable object.
 *
 */
public class Triangle extends Polygon implements Shape {

    // Fields
    private final Point middle;
    private final Point left;
    private final Point right;
    private final Color color;


    /**
     * Constructor for a new triangle from 3 points.
     *
     * @param middle - middle point.
     * @param left - left point.
     * @param right - right point.
     * @param color - the color of the triangle.
     */
    public Triangle(Point left, Point middle, Point right, Color color) {
        this.left = left;
        this.right = right;
        this.middle = middle;
        this.color = color;
    }

    /**
     * Accessor to the middle point of the triangle.
     *
     * @return the triangle's middle point.
     */
    public Point getMiddle() {
        return this.middle;
    }


    /**
     * Accessor to the down line of the triangle.
     *
     * @return the triangle's down line.
     */
    public Line downLine() {
        return new Line(this.left, this.right);
    }

    /**
     * Accessor to the left line of the triangle.
     *
     * @return the triangle's left line.
     */
    public Line leftLine() {
        return new Line(this.middle, this.left);
    }

    /**
     * Accessor to the right line of the triangle.
     *
     * @return the triangle's right line.
     */
    public Line rightLine() {
        return new Line(this.middle, this.right);
    }


    /**
     * Getter.
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Draw all sprites on the screen.
     *
     * @param d - draw surface.
     * @param colorD - color.
     */
    public void drawOn(DrawSurface d, Color colorD) {
        // Draw.
        int[] x = {(int)this.right.getX(), (int)this.left.getX(), (int)this.middle.getX()};
        int[] y = {(int)this.right.getY(), (int)this.left.getY(), (int)this.middle.getY()};
        // Set the color to the triangle.
        d.setColor(color);
        d.fillPolygon(new Polygon(x, y, 3));

    }


    @Override
    public void drawShape(DrawSurface d) {
        drawOn(d, this.color);
    }
}

