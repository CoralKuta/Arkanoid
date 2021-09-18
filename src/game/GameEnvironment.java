// ID: 208649186

package game;

import java.util.LinkedList;
import java.util.List;
import collisiondetection.CollisionInfo;
import shapes.Line;
import shapes.Point;
import collidables.Collidable;
import collidables.Block;


/**
 * @author Coral Kuta.
 * The GameEnvironment is a collection of collidable objects a ball can collide with during the game.
 */
public class GameEnvironment {
    public static final int BORDER_SIZE = 20;
    public static final int UP_BORDER_SIZE = 40;

    //Fields
    private final List<Collidable> list;
    private final Block up;
    private final Block down;
    private final Block left;
    private final Block right;


    /**
     * Constructor of game environment with border blocks include.
     *
     * @param width - the width of the screen for creating borders blocks.
     * @param height - the height of the screen for creating borders blocks.
     */
    public GameEnvironment(int width, int height) {
        //A list which holds all the collidable objects of the game.
        this.list = new LinkedList<>();

        //Create borders.
        this.left = new Block(new Point(0, 0), BORDER_SIZE, height);
        this.right = new Block(new Point(width - BORDER_SIZE, 0), BORDER_SIZE, height);
        this.up = new Block(new Point(0, 0), width, UP_BORDER_SIZE);

        //Create the death border, outside the screen borders.
        this.down = new Block(new Point(-width, height + 5 * BORDER_SIZE), width * 3, BORDER_SIZE);
    }


    /**
     * Add the given collidable to the environment.
     *
     * @param c - collidable.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }


    /**
     * Remove the given collidable to the environment.
     *
     * @param c - collidable.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }


    /**
     * Accessor to the collidables list.
     *
     * @return this collidables list.
     */
    public List<Collidable> getCollidables() {
        return this.list;
    }

    /**
     * Get the closest collision to the collidable.
     *
     * @param trajectory - the line that simulate the trajectory of the ball.
     * @return collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //Initialize collidable and point to null.
        Point closestCollision = null;
        Collidable closest = null;

        //Going through every collidable.
        for (Collidable collidable : this.list) {
            //Calculate the closest intersection.
            Point current = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (current != null) {
                // first we assign closest collision point.
                if (closestCollision == null) {
                    closest = collidable;
                    closestCollision = current;
                    continue;
                }
                //Make sure the closest one is taken.
                if (current.distance(trajectory.start()) < closestCollision.distance(trajectory.start())) {
                    closestCollision = current;
                    closest = collidable;
                }
            }
        }
        return new CollisionInfo(closest, closestCollision);
    }


    /**
     * Accessor to the down line of the block.
     *
     * @return down line.
     */
    public Block getDown() {
        return this.down;
    }

    /**
     * Accessor to the left line of the block.
     *
     * @return left line.
     */
    public Block getLeft() {
        return this.left;
    }

    /**
     * Accessor to the right line of the block.
     *
     * @return right line.
     */
    public Block getRight() {
        return this.right;
    }

    /**
     * Accessor to the up line of the block.
     *
     * @return up line.
     */
    public Block getUp() {
        return this.up;
    }

}