import java.awt.Dimension;

/**
 * Common superclass for all game model classes.
 * 
 * Constructors of subclasses should initiate matrix elements and additional,
 * game-dependent fields.
 */
public interface GameModel {

	/** A Matrix containing the state of the gameboard. */
	/*private final IGameTile[][] gameboardState;

	*//** The size of the state matrix. *//*
	private final Dimension gameboardSize = Constants.getGameSize();

	*//**
	 * Create a new game model. As GameModel is an abstract class, this is only
	 * intended for subclasses.
	 *//*
	public GameModel() {
		this.gameboardState =
				new IGameTile[this.gameboardSize.width][this.gameboardSize.height];
	}*/

	/**
	 * Set the tile on a specified position in the gameboard.
	 * 
	 * @param pos
	 *            The position in the gameboard matrix.
	 * @param tile
	 *            The type of tile to paint in specified position
	 */
	public void setGameboardState(final Position pos, final IGameTile tile);

	/**
	 * Set the tile on a specified position in the gameboard.
	 * 
	 * @param x
	 *            Coordinate in the gameboard matrix.
	 * @param y
	 *            Coordinate in the gameboard matrix.
	 * @param tile
	 *            The type of tile to paint in specified position
	 */
	public void setGameboardState(final int x, final int y,
			final IGameTile tile);

	/**
	 * Returns the IGameTile in logical position (x,y) of the gameboard.
	 * 
	 * @param pos
	 *            The position in the gameboard matrix.
	 */
	public IGameTile getGameboardState(final Position pos);

	/**
	 * Returns the IGameTile in logical position (x,y) of the gameboard.
	 * 
	 * @param x
	 *            Coordinate in the gameboard matrix.
	 * @param y
	 *            Coordinate in the gameboard matrix.
	 */
	public IGameTile getGameboardState(final int x, final int y);

	/**
	 * Returns the size of the gameboard.
	 */
	public Dimension getGameboardSize();

	/**
	 * This method is called repeatedly so that the game can update it's state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	public void gameUpdate(int lastKey) throws GameOverException;
}
