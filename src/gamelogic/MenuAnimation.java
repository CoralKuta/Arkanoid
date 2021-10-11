package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import sprites.Background;

import java.awt.*;
import java.util.ArrayList;

public class MenuAnimation implements Animation{

    private final Background background;

    public MenuAnimation() {
        this.background = createBackground();
    }

    private Background createBackground() {
        Color sky = new Color(23, 135, 206);
        Rectangle background = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT, sky);
        ArrayList<Shape> list = new ArrayList<>();

        //Creating three colors of the clouds.
        Color light = new Color(202, 202, 202);
        Color mid = new Color(186, 186, 186);
        Color dark = new Color(169, 169, 169);

        //Creating the left cloud.
        Circle cloud1 = new Circle(new Point(90, 400), 30, light, light);
        Circle cloud2 = new Circle(new Point(120, 420), 35, light, light);
        Circle cloud3 = new Circle(new Point(140, 380), 35, mid, mid);
        Circle cloud4 = new Circle(new Point(170, 420), 30, dark, dark);
        Circle cloud5 = new Circle(new Point(190, 380), 35, dark, dark);
        list.add(cloud1);
        list.add(cloud2);
        list.add(cloud3);
        list.add(cloud4);
        list.add(cloud5);

        return new Background(background, list);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2 - 50, "Start a New Game", 50);
        d.drawText(d.getWidth() / 3 + 40, d.getHeight() / 2, "Score Board", 50);
        d.drawText(d.getWidth() / 3 + 10, d.getHeight() / 2 + 50, "Exit", 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}
