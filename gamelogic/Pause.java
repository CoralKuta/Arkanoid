// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import gamelevels.LevelInformation;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Coral Kuta.
 * A PAUSE screen when the player asked to rest.
 */
public class Pause implements Animation {
    private final Sprite background;
    private final Color textColor;


    /**
     * Constructor.
     * @param info - the level information.
     */
    public Pause(LevelInformation info) {
        this.background = info.getBackground();
        this.textColor = info.textColor();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);

        //Creating an illusion of 3D font - another color with the same writing.
        Color shade = new Color(84, 120, 112);
        d.setColor(shade);
        d.drawText(d.getWidth() / 3 - 3, d.getHeight() / 2 - 3, "PAUSED", 55);
        d.drawText(d.getWidth() / 3 - 50 - 2, d.getHeight() / 2 + 50 - 2, "Press SPACE to resume", 30);

        //The original.
        d.setColor(this.textColor);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2, "PAUSED", 55);
        d.drawText(d.getWidth() / 3 - 50 , d.getHeight() / 2 + 50, "Press SPACE to resume", 30);
        d.drawText(d.getWidth() / 3 + 10 , d.getHeight() / 2 + 100, "Press Q to quit", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}