// ID: 208649186

package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Coral Kuta.
 * The class that is responsable for the key press.
 * If the player pressed pause it will stop the screen.
 */
public class KeyPressStoppableAnimation implements Animation {
    private static final String SPACE = KeyboardSensor.SPACE_KEY;

    private final Animation animation;
    private final KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param keyboard - the keyboard.
     * @param animation - the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, Animation animation) {
        this.keyboard = keyboard;
        this.animation = animation;
        this.stop = animation.shouldStop();
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(SPACE)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
