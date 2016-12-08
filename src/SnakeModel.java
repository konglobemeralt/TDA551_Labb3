import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeModel extends GameModel{

    private int score;

    /** Graphical representation of a coin. */
    private static final IGameTile BODY_TILE = new RoundTileI(new Color(139, 215,
            61), new Color(139, 215, 61), 2.0);

    /** Graphical representation of a blank tile. */
    private static final IGameTile HEAD_TILE = new RoundTileI(new Color(0, 126, 17),
            new Color(0, 126, 17), 2.0);

    /** Graphical representation of the collector */
    private static final IGameTile APPLE_TILE = new RoundTileI(new Color(215, 3,
            0), new Color(215, 3, 0), 2.0);

    /** Graphical representation of a blank tile. */
    private static final IGameTile BLANK_TILE = new IGameTile();

    /** The direction of the collector. */
    private GoldModel.Directions direction = GoldModel.Directions.NORTH;

    /** The Snake object that is the snake in the snake game */
    private Snake snake;


    /**
     * Constructor for the game. Initialize the gameboard,  snake and the apple.
     */
    public SnakeModel(){

        Dimension size = getGameboardSize();

        // Blank out the whole gameboard
        for (int i = 0; i < size.width; i++) {
            for (int j = 0; j < size.height; j++) {
                setGameboardState(i, j, BLANK_TILE);
            }
        }

        // Insert the snake head in the middle of the gameboard.
        this.snake = new Snake(new Point(size.width / 2, size.height / 2));
        //this.snake = new Snake(new Point(3, 1));


        int bodyX = (int)this.snake.getHeadPosition().getX();
        int bodyY = (int)this.snake.getHeadPosition().getY();
        setGameboardState(bodyX, bodyY, HEAD_TILE);

        for(int i=0; i<this.snake.getSnake().size();i++){
            Point offset = this.snake.getSnake().get(i);
            bodyX  += offset.getX();
            bodyY  += offset.getY();
            setGameboardState(bodyX, bodyY, BODY_TILE);
        }

        // Insert apple into the gameboard.
        spawnNewApple();
    }

    /**
     * Return whether the specified position is empty.
     *
     * @param pos
     *            The position to test.
     * @return true if position is empty.
     */
    private boolean isPositionEmpty(final Position pos) {
        return (getGameboardState(pos) == BLANK_TILE);
    }

    /**
     * Update the direction of the collector
     * according to the user's keypress.
     */
    private void updateDirection(final int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.snake.rotateLeft();
                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                this.snake.rotateRight();
                System.out.println("right");
                break;
            default:
                // Don't change direction if another key is pressed
                break;
        }
    }

    /**
     * Update game on tick and handle game logic and events.
     * @param lastKey
     * @throws GameOverException
     */
    public void gameUpdate(final int lastKey) throws GameOverException{
        Position oldHeadPos = new Position((int)this.snake.getHeadPosition().getX(), (int)this.snake.getHeadPosition().getY());

        // Update direction of Snake
        this.updateDirection(lastKey);

        // First clear the tail tile.
        this.setGameboardState(new Position((int)this.snake.getTailPosition().getX(),
                (int)this.snake.getTailPosition().getY()), BLANK_TILE);

        setGameboardState(oldHeadPos, BODY_TILE);

        // Move the snake
        this.snake.move();

        Position newHeadpos = new Position((int)this.snake.getHeadPosition().getX(), (int)this.snake.getHeadPosition().getY());

        //Check if move will make the snake out of bounds then throw exception.
        if (isOutOfBounds(newHeadpos)) {
            throw new GameOverException(this.score);
        }

        //Check collisions
        this.handleCollisions(newHeadpos);

        // Set the new head tile.
        this.setGameboardState(newHeadpos, HEAD_TILE);

    }

    /**
     *  Spawn a new apple, method makes sure that there will be no collisions with the snake.
     */
    public void spawnNewApple(){
        Position pos;
        Random random = new Random();
        do {
            pos = new Position(random.nextInt((int)getGameboardSize().getWidth()),
                    random.nextInt((int)getGameboardSize().getHeight()));
        }while(isSnakeCollision(pos) &&
                !((getGameboardSize().width*getGameboardSize().height) == this.snake.getSnake().size()+1));
        setGameboardState(pos, APPLE_TILE);
    }

    /**
     *
     * @param pos The position to test.
     * @return <code>false</code> if the position is outside the playing field, <code>true</code> otherwise.
     */
    private boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
                || pos.getY() < 0 || pos.getY() >= getGameboardSize().height;
    }

    /**
     * Check all collision relevant for the snake game and react to them.
     * @param pos
     * @throws GameOverException
     */

    private void handleCollisions(Position pos) throws GameOverException {
        if(this.isAppleCollision(pos)) {
            this.snake.grow();
            this.score++;
            this.spawnNewApple();
        }else if(this.isSnakeCollision(pos)){
            throw new GameOverException(this.score);
        }
    }

    /**
     * Check for position for apple collision
     * @param pos For position to test
     * @return boolean
     */
    private boolean isAppleCollision(Position pos) {
        return this.getGameboardState(pos.getX(), pos.getY()) == APPLE_TILE;
    }

    /**
     * Check for position for snake collision
     * @param pos For position to test
     * @return boolean
     */
    private boolean isSnakeCollision(Position pos) {
        return this.getGameboardState(pos.getX(), pos.getY()) == BODY_TILE;
    }

}
