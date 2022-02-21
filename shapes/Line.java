// ID: 208649186

package shapes;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * @author Coral Kuta.
 * A class for representation of a line - a segment.
 * Line has attributes of start Point and end Point.
 * A line knows it's length, it middle Point, if it's intersecting with another line and where, if it equals
 * to another line.
 * A line also knows it's slope, it's intersection Point with y axis, it's infinite line and also to draw itself.
 */
public class Line implements Shape {

    //Fields
    private final Point start;
    private final Point end;

    // Line equation
    private final LineEquation lineEquation;

    private final Color color;


    /**
     * Constructor with a start Point and an end Point.
     *
     * @param start - the start Point of the segment.
     * @param end - the end Point of the segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.lineEquation = new LineEquation(start, end);
        this.color = Color.BLACK;
    }


    /**
     * Constructor with 4 values of x and y for start Point and end Point.
     *
     * @param x1 - the x value of the start Point.
     * @param y1 - the y value of the start Point.
     * @param x2 - the x value of the end Point.
     * @param y2 - the y value of the end Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // Creating a start Point and an end Point with the values.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.lineEquation = new LineEquation(this.start, this.end);
        this.color = Color.BLACK;
    }


    /**
     * Constructor for line shape.
     *
     * @param x1 - the x value of the start Point.
     * @param y1 - the y value of the start Point.
     * @param x2 - the x value of the end Point.
     * @param y2 - the y value of the end Point.
     * @param color - the color of the line
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        // Creating a start Point and an end Point with the values.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.lineEquation = new LineEquation(this.start, this.end);
        this.color = color;
    }

    /**
     * Checks the distance between 2 points, which means the line's length.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }


    /**
     * Calculate the middle point of the line using the mathematical equation.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }


    /**
     * The starting point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }


    /**
     * The ending point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }


    /**
     * Checks the intersection point of both lines considering all the lines possible.
     *
     * @param other - other line.
     * @return the intersection point between this line and the other line if the lines intersect,
     * 'null' otherwise.
     */
    public Point intersectionWith(Line other) {

        // If the lines are the same there is no intersection.
        if (this.equals(other)) {
            return null;
        }

        // If both of the lines are actually points.
        if ((this.length() == 0) && (other.length() == 0)) {
            // Check if they're the same point.
            if (this.start.equals(other.start)) {
                return this.start;
            } else {
                return null;
            }
        }

        // If this line is a point (and other line is an actual line).
        if (this.length() == 0) {

                // Check if this line (=point) is on the actual other line.
                if (this.start.isOnLine(other)) {
                    return this.start;
                } else {
                    return null;
                }
            }


        // If other line is a point (and this line is an actual line).
        if (other.length() == 0) {

            // Check if other line (=point) is on the actual this line.
            if (other.start.isOnLine(this)) {
                return other.start;
            }
        }

        // If both of the lines has the same slope - the only way to intersection is one line continuing the other.
        double thisSlope = this.getSlope();
        double otherSlope = other.getSlope();


        // Edge case - both parallel to y axis and has infinity slope
        if ((thisSlope == Double.POSITIVE_INFINITY)
                && (otherSlope == Double.POSITIVE_INFINITY)) {

            // Find the start and end of both lines
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double otherMinY = Math.min(other.start.getY(), other.end.getY());
            double otherMaxY = Math.max(other.start.getY(), other.end.getY());

            // Check if one continues the other
            if (thisMinY == otherMaxY) {
                return new Point(this.start.getX(), thisMinY);
            } else if (thisMaxY == otherMinY) {
                return new Point(this.start.getX(), thisMaxY);
            } else {
                return null;
            }
        }

        // Else, they are parallel to each other, not to y axis.
        if (thisSlope == otherSlope) {

            // Create temp lines to be sure start point has smaller x value then end point.
            Line tempThis = switchStartEnd(this);
            Line tempOther = switchStartEnd(other);

            // Check if one continues the other.
            if (tempThis.end.equals(tempOther.start)) {
                return tempThis.end;
            } else if (tempThis.start.equals(tempOther.end)) {
                return tempThis.start;
            } else {
                return null;
            }
        }


         // Otherwise, there is an intersection on the INFINITE lines.
        Point intersection = this.lineEquation.findInfIntersection(this, other);


        // Find if the point is on both lines.
        if ((intersection.isOnLine(this)) && (intersection.isOnLine(other))) {
            return intersection;
        }

        // Finally, seems like the intersection is only on the infinite lines.
        return null;
    }


    /**
     * Check if the lines intersect.
     *
     * @param other - other line.
     * @return 'true' if the lines intersect, 'false' otherwise.
     */
    public boolean isIntersecting(Line other) {
        // If the lines are not equal and they don't have intersection they are not intersecting..
        return (!this.equals(other)) && (intersectionWith(other) != null);
    }


    /**
     * Check if the lines are equal noticing that a line is not a point (length > 0).
     *
     * @param other - other line.
     * @return 'true' if both of the lines are equal, 'false' otherwise.
     */
    public boolean equals(Line other) {
        // If one of the lines are actually a point there is no equality.
        if ((this.length() == 0) && (other.length() == 0)) {
            return false;
        }

        // If they have the same start point and end point.
        return (((this.start.equals(other.start)) && (this.end.equals(other.end)))
               || ((this.end.equals(other.start)) && (this.start.equals(other.end))));
    }


    /**
     * Check the closest intersection point of the line with the rectangle.
     *
     * @param rect - the rectangle we check the closest intersection with.
     * @return the closest intersection point with the rectangles, null if there is no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);

        if (pointList.isEmpty()) {
            return null;
        } else {
            double distance = this.start.distance(pointList.get(0));
            Point closestPoint = pointList.get(0);

            for (Point point : pointList) {
                if (this.start.distance(point) < distance) {
                    distance = this.start.distance(point);
                    closestPoint = point;
                }
            }
            return closestPoint;
        }
    }


    /**
     * Switch the start point with the end point if the start x value is grater then the end x value.
     *
     * @param line - a line.
     * @return a new line.
     */
    public Line switchStartEnd(Line line) {
        // Creating 2 temporal start and end points for the line.
        Point tempStart = new Point(line.start.getX(), line.start.getY());
        Point tempEnd = new Point(line.end.getX(), line.end.getY());

        // If the start value is bigger then the end value we switch them.
        if (tempStart.getX() > tempEnd.getX()) {
            Point temp = tempStart;
            tempStart = tempEnd;
            tempEnd = temp;

            // Return a new line with the new start and end.
            return new Line(tempStart, tempEnd);
        }

        // If the x value of start is smaller then x value of end we return the line with no change.
        return line;
    }


    /**
     * Accessor to the slope of a line.
     *
     * @return the slope (a) of the line in the equation y = ax+b
     */
    public double getSlope() {
        return this.lineEquation.getSlope();
    }


    /**
     * Accessor to the B value of the infinite line.
     *
     * @return the B value of the line's equation - y = ax+b
     */
    public double getB() {
        return this.lineEquation.getB();
    }

    @Override
    public void drawShape(DrawSurface d) {
        // Put in variables the casted x and y values of the start and end point.
        int x1 = (int) this.start().getX();
        int y1 = (int) this.start().getY();
        int x2 = (int) this.end().getX();
        int y2 = (int) this.end().getY();

        // Set the color.
        d.setColor(this.color);

        // Draw.
        d.drawLine(x1, y1, x2, y2);
    }
}
