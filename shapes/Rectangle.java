// ID: 208649186

package shapes;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Coral Kuta.
 * A class for represtetion of a rectangle - The basic collidable object.
 *
 */
public class Rectangle implements Shape {

    // Fields
    private final Point upperLeft;
    private final Point upperRight;
    private final Point lowerLeft;
    private final Point lowerRight;
    private final double width;
    private final double height;
    private final Color color;


    /**
     * Constructor for a new rectangle with location and width/height.
     *
     * @param upperLeft - a point.
     * @param width - width.
     * @param height - height.
     * @param color - the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;

        this.upperRight = new Point((this.upperLeft.getX() + this.width), upperLeft.getY());
        this.lowerLeft = new Point(this.upperLeft.getX(), (this.upperLeft.getY() + this.height));
        this.lowerRight = new Point(getUpperRight().getX(), getLowerLeft().getY());
        this.color = color;
    }


    /**
     * Check if the given line has intersection points with the rectangle and add them to a list.
     *
     * @param line - the line we check if the rectangle has intersections with.
     * @return a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> pointList = new LinkedList<>();

        //Asking the line intersects with any of the rectangle lines.
        if (this.upLine().isIntersecting(line)) {
            pointList.add(this.upLine().intersectionWith(line));
        }

        if (this.downLine().isIntersecting(line)) {
            pointList.add(this.downLine().intersectionWith(line));
        }

        if (this.leftLine().isIntersecting(line)) {
            pointList.add(this.leftLine().intersectionWith(line));
        }

        if (this.rightLine().isIntersecting(line)) {
            pointList.add(this.rightLine().intersectionWith(line));
        }

        return pointList;
    }


    /**
     * Accessor to the width of the rectangle.
     *
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }


    /**
     * Accessor to the height of the rectangle.
     *
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * Accessor to the upper left point of the rectangle.
     *
     * @return the rectangle's upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Accessor to the lower left point of the rectangle.
     *
     * @return the rectangle's lower left point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * Accessor to the upper right point of the rectangle.
     *
     * @return the rectangle's upper right point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Accessor to the lower right point of the rectangle.
     *
     * @return the rectangle's lower right point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * Accessor to the up line of the rectangle.
     *
     * @return the rectangle's up line.
     */
    public Line upLine() {
        return new Line(this.upperLeft, this.upperRight);
    }

    /**
     * Accessor to the down line of the rectangle.
     *
     * @return the rectangle's down line.
     */
    public Line downLine() {
        return new Line(this.lowerLeft, this.lowerRight);
    }

    /**
     * Accessor to the left line of the rectangle.
     *
     * @return the rectangle's left line.
     */
    public Line leftLine() {
        return new Line(this.upperLeft, this.lowerLeft);
    }

    /**
     * Accessor to the right line of the rectangle.
     *
     * @return the rectangle's right line.
     */
    public Line rightLine() {
        return new Line(this.upperRight, this.lowerRight);
    }


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint - where we hit the object.
     * @param currentVelocity - the sprite velocity.
     * @return new Velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double currentDx = currentVelocity.getDx();
        double currentDy = currentVelocity.getDy();

        //If the collision point is the actual points of the rectangle change the angle.
        if (collisionPoint.equals(this.getUpperLeft())) {
            return Velocity.fromAngleAndSpeed(315, currentVelocity.getSpeed());
        } else if (collisionPoint.equals(this.getUpperRight())) {
            return Velocity.fromAngleAndSpeed(45, currentVelocity.getSpeed());
        } else if (collisionPoint.equals(this.getLowerLeft())) {
            return Velocity.fromAngleAndSpeed(225, currentVelocity.getSpeed());
        } else if (collisionPoint.equals(this.getLowerRight())) {
            return Velocity.fromAngleAndSpeed(135, currentVelocity.getSpeed());

            //If the collision point is on the lines we need to change the sign.
        } else if (collisionPoint.isOnLine(this.upLine()) || collisionPoint.isOnLine(this.downLine())) {
            return new Velocity(currentDx, -currentDy);
        } else if (collisionPoint.isOnLine(this.leftLine()) || collisionPoint.isOnLine(this.rightLine())) {
            return new Velocity(-currentDx, currentDy);
        } else {
            return currentVelocity;
        }
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
        // Set the color to the rectangle.
        d.setColor(colorD);

        // Draw.
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);

        // Draw separating lines.
        this.upLine().drawShape(d);
        this.downLine().drawShape(d);
        this.leftLine().drawShape(d);
        this.rightLine().drawShape(d);
    }

    /**
     * Returns if the rectangle contains the point.
     *
     * @param point - a point.
     * @return true if the point is inside the rectangle, false otherwise.
     */
    public boolean containsPoint(Point point) {
        double pointX = point.getX();
        double pointY = point.getY();

        //Return if the point is in the range of the rectangle's lines.
        return (pointX > upperLeft.getX()) && (pointX < upperRight.getX())
                && (pointY > upperRight.getY()) && (pointY < lowerRight.getY());
    }


    @Override
    public void drawShape(DrawSurface d) {
        drawOn(d, this.color);
    }
}

