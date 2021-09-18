// ID: 208649186

package shapes;

/**
 * @author Coral Kuta.
 * A class for representation of an infinite line - the equation line.
 */
public class LineEquation {
    // Fields
    // The line equation - y = ax + b. a is the slope. b is the constant.
    private final double a;
    private final double b;

    /**
     * Constructor for a line equation.
     * @param start - start point.
     * @param end - end point.
     */
    public LineEquation(Point start, Point end) {

        // y2 - y1.
        double yDiff = end.getY() - start.getY();
        // x2 - x1/
        double xDiff = end.getX() - start.getX();
        // slope = (y2 - y1) / (x2 - x1).
        double slope = yDiff / xDiff;

        // If the line is parallel to y axis we make sure the slope is positive and not negative.
        if (slope == Double.NEGATIVE_INFINITY) {
            slope = -slope;
        }

        this.a = slope;
        this.b = start.getY() - (slope * start.getX());
    }


    /**
     * Accessor to the slope of a line.
     *
     * @return the slope (a) of the line in the equation y = ax+b
     */
    public double getSlope() {
        return this.a;
    }

    /**
     * Accessor to the B value of the infinite line.
     *
     * @return the B value of the line's equation - y = ax+b
     */
    public double getB() {
        return this.b;
    }


    /**
     * Find the intersection between 2 *infinite* lines.
     *
     * @param line1 - one line.
     * @param line2 - other line.
     * @return the intersection point between one infinite line and other infinite line.
     */
    public Point findInfIntersection(Line line1, Line line2) {
        // Setting 2 variables to 0.
        double intersectionX = 0;
        double intersectionY = 0;

        // If this line is parallel to y axis.
        if (this.getSlope() ==  Double.POSITIVE_INFINITY) {

            // It's x value is the same so we choose the end point x value for example.
            intersectionX = line1.end().getX();

            // We place the x value on the other line's line equation.
            intersectionY = (line2.getSlope() * intersectionX) + line2.getB();

            // If other line is parallel to y axis.
        } else if (line2.getSlope() ==  Double.POSITIVE_INFINITY) {

            // It's x value is the same so we choose the end point x value for example.
            intersectionX = line2.end().getX();

            // We place the x value on this line's line equation.
            intersectionY = (line1.getSlope() * intersectionX) + line1.getB();

            // Else they both have a line equation.
        } else {
            double bDiff = line2.getB() - line1.getB();
            double slopeDiff = line1.getSlope() - line2.getSlope();
            intersectionX = bDiff / slopeDiff;
            intersectionY = (line1.getSlope() * intersectionX) + line1.getB();
        }
        // Return the intersection point.
        return new Point(intersectionX, intersectionY);
    }

}
