// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import game.Counter;
import gamelevels.LevelInformation;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Coral Kuta.
 * A "You Win" animation.
 * Displayed when all levels are compleated.
 */
public class YouWin implements Animation {
    private final Sprite background;
    private final Counter score;
    private final Color textColor;

    /**
     * Constructor for the screen.
     *
     * @param info - the level information in which the player won.
     * @param score - the current score the player won.
     */
    public YouWin(LevelInformation info, Counter score) {
        this.background = info.getBackground();
        this.textColor = info.textColor();
        this.score = score;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);

        //Creating an illusion of 3D font - another color with the same writing.
        Color shade = new Color(113, 84, 120);
        d.setColor(shade);
        d.drawText(d.getWidth() / 3 + 30 - 3, d.getHeight() / 2 - 50 - 3, "You Win!", 55);
        d.drawText(d.getWidth() / 3 + 30 - 2,  d.getHeight() / 2 - 2, "Your score is " + this.score.getValue(), 30);
        d.drawText(d.getWidth() / 3 + 50 - 1, d.getHeight() / 2 + 50 - 1, "Press SPACE to menu", 20);

        //The original.
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 3 + 30, d.getHeight() / 2 - 50, "You Win!", 55);
        d.drawText(d.getWidth() / 3 + 30, d.getHeight() / 2, "Your score is " + this.score.getValue(), 30);
        d.drawText(d.getWidth() / 3 + 50, d.getHeight() / 2 + 50, "Press SPACE to menu", 20);
        d.drawText(d.getWidth() / 3 + 50, d.getHeight() / 2 + 280, "Press Q to exit", 25);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
