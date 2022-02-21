// ID: 208649186

package shapes;


/**
 * @author Coral Kuta.
 * A class for representation of Velocity of the ball.
 * Velocity has attributes of change in position of the x axis - dx, and change in position of the y axis - dy.
 * Knows how to transform a velocity given by angle and speed to velocity in dx dy, also knows how to apply
 * itself to a point.
 */
public class Velocity {
    // Fields

    // The change in position on the x axis.
    private final double dx;

    // The change in position on the y axis.
    private final double dy;

    /**
     * Constructor.
     *
     * @param dx - the change in position on the x axis.
     * @param dy - the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create velocity from angle and speed.
     *
     * @param angle - the angles in which the ball moves.
     * @param speed - the speed of the ball.
     * @return - new velocity from dx dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Convert the angle to radians.
        angle = Math.toRadians(angle);

        // Calculate the dx and dy.
        double newDx = speed * Math.sin(angle);
        double newDy = speed * -Math.cos(angle);

        // Return new velocity with dx and dy that we calculated.
        return new Velocity(newDx, newDy);
    }


    /**
     * @return the speed of the ball.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * Accessor.
     *
     * @return this dx value (the change in position on the x axis).
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accessor.
     *
     * @return this dy value (the change in position on the y axis).
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - the point we want to apply ("move").
     * @return a new "moved" point.
     */
    public Point applyToPoint(Point p) {
        // We move the point by adding to it's x value the dx and to it's y value the dy.
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     *
     * @param rectangle - the rectangle we want to apply ("move").
     * @return a new "moved" rectangle.
     */
    public Rectangle applyToRectangle(Rectangle rectangle) {
        Point upperLeft = rectangle.getUpperLeft();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();

        // We move the rectangle by adding to it's x value the dx and to it's y value the dy.
        Point newPoint = new Point(upperLeft.getX() + this.dx, upperLeft.getY() + this.dy);
        return new Rectangle(newPoint, width, height, rectangle.getColor());
    }

    /**
     *
     * @return the sign of the velocity dx.
     */
    public int getSignDx() {
        int result = 1;
        if (this.dx < 0) {
            result = -1;
        }
        return result;
    }

    /**
     *
     * @return the sign of the velocity dy.
     */
    public int getSignDy() {
        int result = 1;
        if (this.dy < 0) {
            result = -1;
        }
        return result;
    }

}