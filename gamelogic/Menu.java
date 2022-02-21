// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import collidables.Block;
import game.Counter;
import game.GameLevel;
import game.ScoreTrackingListener;
import gamelevels.LevelInformation;
import shapes.*;
import sprites.Background;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Coral Kuta.
 * A "You Win" animation.
 * Displayed when all levels are compleated.
 */
public class Menu implements Animation {
    private final Sprite background;
    private final Color textColor;
    private int maxScore;

    /**
     * Constructor for the screen.
     */
    public Menu(Counter score) {
        //Creating the blue sky color of the background.
        Color sky = new Color(115, 177, 210);
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, sky);
        ArrayList<Shape> list = new ArrayList<>();

        //Creating three colors of the clouds.
        Color light = new Color(202, 202, 202);
        Color mid = new Color(186, 186, 186);
        Color dark = new Color(169, 169, 169);

        //Creating the left cloud.
        Circle cloud1 = new Circle(new Point(90, 100), 30, light, light);
        Circle cloud2 = new Circle(new Point(120, 120), 35, light, light);
        Circle cloud3 = new Circle(new Point(140, 80), 35, mid, mid);
        Circle cloud4 = new Circle(new Point(170, 120), 30, dark, dark);
        Circle cloud5 = new Circle(new Point(190, 80), 35, dark, dark);

        //Creating the right cloud.
        Circle cloud6 = new Circle(new Point(650, 130), 30, light, light);
        Circle cloud7 = new Circle(new Point(670, 100), 35, light, light);
        Circle cloud8 = new Circle(new Point(699, 120), 35, mid, mid);
        Circle cloud9 = new Circle(new Point(720, 80), 30, dark, dark);
        Circle cloud10 = new Circle(new Point(740, 120), 35, dark, dark);

        //Creating the middle cloud.
        Circle cloud11 = new Circle(new Point(420, 180), 30, light, light);
        Circle cloud12 = new Circle(new Point(400, 140), 35, light, light);
        Circle cloud13 = new Circle(new Point(449, 120), 35, mid, mid);
        Circle cloud14 = new Circle(new Point(480, 140), 30, dark, dark);
        Circle cloud15 = new Circle(new Point(450, 170), 35, dark, dark);


        //Adding all.
        list.add(cloud1);
        list.add(cloud2);
        list.add(cloud3);
        list.add(cloud4);
        list.add(cloud5);

        list.add(cloud6);
        list.add(cloud7);
        list.add(cloud8);
        list.add(cloud9);
        list.add(cloud10);

        list.add(cloud11);
        list.add(cloud12);
        list.add(cloud13);
        list.add(cloud14);
        list.add(cloud15);

        Color tree = new Color(33, 115, 4);
        Triangle tree1 = new Triangle(new Point(100, 500), new Point(150, 460), new Point(200, 500), tree);
        Triangle tree2 = new Triangle(new Point(70, 530), new Point(150, 480), new Point(230, 530), tree);
        Triangle tree3 = new Triangle(new Point(50, 560), new Point(150, 510), new Point(250, 560), tree);

        Triangle tree4 = new Triangle(new Point(500, 420), new Point(600, 340), new Point(700, 420), tree);
        Triangle tree5 = new Triangle(new Point(450, 480), new Point(600, 370), new Point(750, 480), tree);
        Triangle tree6 = new Triangle(new Point(400, 540), new Point(600, 410), new Point(800, 540), tree);


        Color trunk = new Color(103, 69, 56);
        Rectangle rec1 = new Rectangle(new Point(140, 540), 20, 80, trunk);
        Rectangle rec2 = new Rectangle(new Point(590, 410), 30, 300, trunk);

        list.add(rec1);
        list.add(rec2);
        list.add(tree1);
        list.add(tree2);
        list.add(tree3);
        list.add(tree4);
        list.add(tree5);
        list.add(tree6);


        this.background =  new Background(background, list);
        this.textColor = Color.BLACK;
        this.maxScore = score.getMax();
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);

        Color blue = new Color(84, 114, 124);
        d.setColor(blue);
        d.drawText(d.getWidth() / 2 - 100 - 3, d.getHeight() / 2 - 50 - 3, "Menu", 80);
        // Creating menu
        d.setColor(this.textColor);
        d.drawText(10, 25, "Coral Kuta", 20);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 - 50, "Menu", 80);
        d.drawText(d.getWidth() / 3 - 65, d.getHeight() / 2, "Your max score until now is " + this.maxScore, 30);
        d.drawText(d.getWidth() / 3 + 30, d.getHeight() / 2 + 50, "Press SPACE to start", 25);
        d.drawText(d.getWidth() / 3 + 50, d.getHeight() / 2 + 280, "Press Q to exit", 25);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
