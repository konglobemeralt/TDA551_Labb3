import java.awt.*;
import java.util.ArrayList;

/**
* The Snake class is what defines a snake in this snake game. It should handle
 * all movements and changes of the snake.
*
*/
public class Snake {
    private ArrayList<Point> snakeBody = new ArrayList<Point>();
    private Point headPosition;
    private Point tailPosition;
    private int direction = 0;
    private Point directionVector;
    private Point[] directionList = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};

    /**
     * Rotates the snake to the right.
     */
    public void rotateRight() {
        this.direction++;
        this.direction = Math.floorMod(this.direction, 4);
        updateRotation();
    }

    /**
     * Rotates the snake to the left.
     */
    public void rotateLeft() {
        this.direction--;
        this.direction = Math.floorMod(this.direction, 4);
        updateRotation();
    }

    /**
     * Updates the head / first index of the bodyList to the new direction.
     */
    private void updateRotation() {
        this.directionVector = directionList[this.direction];
    }

    /**
     * The main constructor for the Snake class. Will initialize the body with all the
     * body parts that a snake should have in the beginning.
     * @param headPosition set the position of the head.
     */

    public Snake(Point headPosition) {
        this.headPosition = headPosition;
        this.tailPosition = new Point((int)this.headPosition.getX()-2, (int)this.headPosition.getY());
        this.directionVector = new Point(1, 0);
        this.snakeBody.add(new Point(-1, 0));
        this.snakeBody.add(new Point(-1, 0));
    }

    /**
     * Getter for the snakes body and head.
     * @return bodyList, ArrayList of Points.
     */
    public ArrayList<Point> getSnake(){
        return this.snakeBody;
    }

    /**
     * Getter for the position of the head. A coordinate on the game board.
     * @return headPosition, a Point.
     */
    public Point getHeadPosition(){
        return this.headPosition;
    }

    /**
     * Getter for the position of the tail. A coordinate on the game board.
     * @return tailPosition, a Point.
     */
    public Point getTailPosition(){
        return this.tailPosition;
    }

    /**
     * The move method will insert the tail of the snake at the heads latest position
     * and remove the current tail and thus move the snake in the requested direction.
     *
     * It will also update the snakes internal absolute coordinate.
     * */
    public void move() {
        this.headPosition.x += this.directionVector.getX();
        this.headPosition.y += this.directionVector.getY();

        Point tail = snakeBody.get(snakeBody.size()-1);
        this.tailPosition.x -= tail.getX();
        this.tailPosition.y -= tail.getY();

        // Will add the negation of the this.directionVectors vector.
        snakeBody.add(0, new Point((int)this.directionVector.getX()*-1, (int)this.directionVector.getY()*-1));
        snakeBody.remove(snakeBody.size()-1);
    }

    /**
     * This method will add a new Point to the snake's body at the end of the snake.
     */
    public void grow(){
        this.snakeBody.add(new Point(0, 0));
    }

    /**
     * @return ArrayList of Points, return list of the snake's segments coordinates
     * relative to its head's coordinate. The first position of the list contains the snake direction
     * and not an offset.
     */

}
