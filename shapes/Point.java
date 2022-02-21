// ID: 208649186

package shapes;


/**
 * @author Coral Kuta.
 * A class for representation of a Point.
 * Point has attributes of x value and y value.
 * A points knows the distance between itself to another point, if it equals to another point and if it's on a
 * certain line.
 */
public class Point {
    static final double EPSILON = Math.pow(10, -2);

    // The Point's x value.
    private final double x;

    // The Point's y value.
    private final double y;


    /**
     * Constructor of a point.
     *
     * @param x - the x value of the point.
     * @param y - the y value ot the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Checks the distance between this point to other point.
     *
     * @param other - another point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        // Using the equation of the distance between 2 points.
        double xDifference = Math.pow((other.getX() - this.x), 2);
        double yDifference = Math.pow((other.getY() - this.y), 2);
        return Math.sqrt(xDifference + yDifference);
    }


    /**
     * Checks if this point is equal to other point.
     *
     * @param other - another point.
     * @return 'true' if the points are equal and 'false' otherwise.
     */
    public boolean equals(Point other) {
        // Declare a very small number.
        double epsilon = Math.pow(10, -2);

        // Check if the point are 'epsilon' equal.
        if ((Math.abs(other.getX() - this.getX()) < epsilon) && (Math.abs(other.getY() - this.getY()) < epsilon)) {
            return true;
        }


        // check if the points are the same.
        return (other.getX() == this.x) && (other.getY() == this.y);
    }


    /**
     * Accessor for the x value of the point.
     *
     * @return this point's x value.
     */
    public double getX() {
        return this.x;
    }


    /**
     * Accessor for the y value of the point.
     *
     * @return this point's y value.
     */
    public double getY() {
        return this.y;
    }


    /**
     * Checks if the point is on the line.
     *
     * @param line - an infinite line
     * @return 'true' if the point is on the infinite line, and 'false' otherwise.
     */
    public boolean isOnInfLine(Line line) {
        //if the line is parallel to y axis we need to have the same x
        if (line.getSlope() == Double.POSITIVE_INFINITY) {
            return ((line.start().getX() == this.getX()));
        } else {
            //otherwise the line has an equation
            return this.getY() - (line.getSlope() * this.getX()) - line.getB() < EPSILON;
        }
    }

    /**
     * Check if the point is on the line by checking if the point's x value is between the max and min values of x,
     * and if the point's y values is between the max and min values of y.
     *
     * @param line - A line
     * @return 'true' if the point is on the line, 'false' otherwise.
     */
    public boolean isOnLine(Line line) {
        return ((this.isOnInfLine(line)) && (this.isInSegment(line)));
    }


    /**
     * Check if the point is on the line by checking if the point's x value is between the max and min values of x,
     * and if the point's y values is between the max and min values of y.
     *
     * @param line - A line
     * @return 'true' if the point is on the line, 'false' otherwise.
     */
    private boolean isInSegment(Line line) {
        // Calculating the min and max x value.
        double minX = Math.min(line.start().getX(), line.end().getX());
        double maxX = Math.max(line.start().getX(), line.end().getX());

        // Calculating the min and max y value.
        double minY = Math.min(line.start().getY(), line.end().getY());
        double maxY = Math.max(line.start().getY(), line.end().getY());

        // If the point's x is between the max x and the min x and the point's y is between the max y and min y.
        return ((this.getX() <= maxX) && (this.getX() >= minX))
                && ((this.getY() <= maxY) && (this.getY() >= minY));
    }

}