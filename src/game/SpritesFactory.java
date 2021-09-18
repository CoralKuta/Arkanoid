// ID: 208649186

package game;

import collidables.Block;
import collidables.Paddle;
import gamelevels.LevelInformation;
import shapes.Ball;
import shapes.Point;
import shapes.Velocity;


/**
 * @author Coral Kuta.
 * A class for representation of a Sprite factory.
 * Here we create all the sprites for the game by the level information given.
 */
public class SpritesFactory {
    //Fields
    private final GameLevel game;
    private final LevelInformation levelInfo;


    /**
     * Constructor.
     *
     * @param game - the game.
     * @param info - the level information.
     */
    public SpritesFactory(GameLevel game, LevelInformation info) {
        this.game = game;
        this.levelInfo = info;
    }


    /**
     * The creation of the balls for each level.
     * We receive a list of balls and velocities created by the level info, set their environment
     * and add them to the game.
     */
    public void createBalls() {
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            //Get a ball from the information's list of balls.
            Ball ball = this.levelInfo.levelBalls().get(i);

            //Set it the suitable velocity and set the game environment.
            Velocity velocity = this.levelInfo.initialBallVelocities().get(i);
            ball.setGameEnvironment(this.game.getEnvironment());
            ball.setVelocity(velocity.getDx(), velocity.getDy());

            //Add the ball to the game.
            game.getBallRemover().getRemaining().increase(1);
            ball.addHitListener(this.game.getBallRemover());
            ball.addToGame(this.game);
        }
    }

    /**
     * The creation of the blocks for each level.
     * We receive a list of blocks created by the level info, and add them to the game.
     */
    public void createBlocks() {
        for (int i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            //Get a block from the list of the information.
            Block block = this.levelInfo.blocks().get(i);

            //Add the block to the game.
            game.getBlockRemover().getRemaining().increase(1);
            block.addHitListener(this.game.getBlockRemover());
            block.addHitListener(this.game.getScoreTracking());
            block.addToGame(this.game);
        }
    }

    /**
     * Paddle creation.
     */
    public void createPaddle() {
        //Create the paddle always in the middle.
        int paddleX = GameLevel.WIDTH / 2 - this.levelInfo.paddleWidth() / 2;
        int paddleY = GameLevel.HEIGHT - GameEnvironment.BORDER_SIZE - Paddle.PADDLE_HEIGHT;
        Paddle paddle = new Paddle(game.getKeyboard(), new Point(paddleX, paddleY),
                this.levelInfo.paddleWidth(), Paddle.PADDLE_HEIGHT);

        //Add it to the game.
        paddle.addToGame(game);
    }

    /**
     * Score indicator creation.
     *
     * @param gameLevel  - the gameLevel.
     * @param score - the counter of score.
     * @param lives - the counter of lives.
     */
    public void createScoreIndicator(GameLevel gameLevel, Counter score, Counter lives) {
        //Create a new score indicator with the information - score, lives left and level name.
        ScoreIndicator scoreIndicator = new ScoreIndicator(lives, score, this.levelInfo.levelName());

        //Add it to the game.
        scoreIndicator.addToGame(gameLevel);
    }

}
