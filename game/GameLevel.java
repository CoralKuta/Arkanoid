// ID: 208649186

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Collidable;
import gamelevels.LevelInformation;
import gamelogic.*;
import sprites.SpriteCollection;
import sprites.Sprite;


/**
 * @author Coral Kuta.
 * A class for running each level of game - create blocks, balls and paddle and move them.
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int COUNT_FROM = 3;
    public static final int NUM_OF_SECONDS = 2;

    //Fields
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final SpritesFactory factory;
    private final BlockRemover blockRemover;
    private final Counter remainedBlocks;
    private final BallRemover ballRemover;
    private final Counter remainedBalls;
    private final ScoreTrackingListener scoreTracking;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInfo;
    private final Counter lives;
    private boolean isAlreadyPressed;


    /**
     * Constructor for the game level.
     *
     * @param info - the level information.
     * @param keyboard - the keyboard of the entire game.
     * @param animationRunner - the animation runner.
     * @param score - the score counter of the entire game.
     * @param lives - the lives counter of the entire game.
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard, AnimationRunner animationRunner,
                     Counter score, Counter lives) {
        this.environment = new GameEnvironment(WIDTH, HEIGHT);
        this.sprites = new SpriteCollection();
        this.remainedBlocks = new Counter();
        this.blockRemover = new BlockRemover(this, this.remainedBlocks);
        this.remainedBalls = new Counter();
        this.ballRemover = new BallRemover(this,  this.remainedBalls);
        this.score = score;
        this.scoreTracking = new ScoreTrackingListener(this.score);
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.levelInfo = info;
        this.factory = new SpritesFactory(this, info);
        this.lives = lives;
        this.isAlreadyPressed = false;
    }

    /**
     * Initialize a new game: create the blocks, borders and paddle and add them to the game.
     */
    public void initialize() {
        // SPRITES & COLLIDABLES
        this.factory.createPaddle();
        this.factory.createBlocks();

        //Adding borders.
        environment.getUp().addToGame(this);
        environment.getRight().addToGame(this);
        environment.getLeft().addToGame(this);

        //Adding the death block.
        environment.getDown().addToGame(this);

        //Adding the score indicator.
        this.factory.createScoreIndicator(this, this.score, this.lives);

    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() throws ExitException {
        //The balls are created here in order to allow re-run of the game while there are still lives.
        this.factory.createBalls();


        //First run the count down animation - 3, 2, 1...
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites, this.levelInfo));
        this.running = true;

        //Use our runner to run the current animation of the game - one of a lot.
        this.runner.run(this);
        if (!running) {
            //If all balls fell out the screen, one less life.
            if (this.remainedBalls.getValue() == 0) {
                this.lives.decrease(1);
            }
        }
    }


    @Override
    public void doOneFrame(DrawSurface d) throws ExitException {
        //Draw the background and the sprites.
        this.levelInfo.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //If the player pressed "p" it means he wants to pause, so we create new pause animation.
        if (this.keyboard.isPressed("p")) {
            if (!this.isAlreadyPressed) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, new Pause(this.levelInfo)));
                this.isAlreadyPressed = true;
            }
        } else {
                this.isAlreadyPressed = false;
        }

        // If there are no blocks left, the player won.
        if (this.remainedBlocks.getValue() == 0) {
            // +100 points.
            this.score.increase(100);
            this.running = false;
        }

        // if there are no balls left, the player lost.
        if (this.remainedBalls.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Add collidable to the game.
     *
     * @param c - collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     *
     * @param s - sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Remove collidable to the game.
     *
     * @param c - collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite to the game.
     *
     * @param s - sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Getter.
     *
     * @return the ball remover
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }


    /**
     * Getter.
     *
     * @return the block remover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }


    /**
     * Getter.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }


    /**
     * Getter.
     *
     * @return the score tracker
     */
    public ScoreTrackingListener getScoreTracking() {
        return this.scoreTracking;
    }

    /**
     * Getter.
     * @return the keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }
}

