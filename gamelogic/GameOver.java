// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import game.Counter;
import gamelevels.LevelInformation;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Coral Kuta.
 * A "Game Over" animation.
 * Displayed when out of lives and balls.
 */
public class GameOver implements Animation {
    private final Sprite background;
    private final Counter score;
    private final Color textColor;

    /**
     * Constructor for the screen.
     *
     * @param info - the level information in which the player won.
     * @param score - the current score the player won.
     */
    public GameOver(LevelInformation info, Counter score) {
        this.background = info.getBackground();
        this.textColor = info.textColor();
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);

        //Creating an illusion of 3D font - another color with the same writing.
        Color shade = new Color(130, 130, 130);
        d.setColor(shade);
        d.drawText(d.getWidth() / 3 - 4, d.getHeight() / 2 - 50 - 4, "GAME OVER", 50);
        d.drawText(d.getWidth() / 3 + 40 - 3, d.getHeight() / 2 - 3, "Your score is " + this.score.getValue(), 30);
        d.drawText(d.getWidth() / 3 - 2, d.getHeight() / 2 + 50 - 2, "Press SPACE to menu", 30);

        //The original.
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2 - 50, "GAME OVER", 50);
        d.drawText(d.getWidth() / 3 + 40, d.getHeight() / 2, "Your score is " + this.score.getValue(), 30);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2 + 50 , "Press SPACE to menu", 30);
        d.drawText(d.getWidth() / 3 + 50, d.getHeight() / 2 + 280, "Press Q to exit", 25);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
