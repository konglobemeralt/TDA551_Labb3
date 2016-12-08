import java.awt.Dimension;
import java.awt.Graphics;

/**
 * A game tile combined of two other tiles.
 * 
 * Whenever the object paints itself, the bottom tile is painted first,
 * then the top tile is painted on top.
 */
public class CompositeTile implements IGameTile {
	private final IGameTile bottomTile;
	private final IGameTile topTile;

	public CompositeTile(final IGameTile bottom, final IGameTile top) {
		this.bottomTile = bottom;
		this.topTile = top;
	}

	public IGameTile getTop() {
		return this.topTile;
	}

	public IGameTile getBottom() {
		return this.bottomTile;
	}

	/**
	 * Draws itself in a given graphics context, position and size.
	 * 
	 * @param g
	 *            graphics context to draw on.
	 * @param x
	 *            pixel x coordinate of the tile to be drawn.
	 * @param y
	 *            pixel y coordinate of the tile to be drawn.
	 * @param d
	 *            size of this object in pixels.
	 */
	@Override
	public void draw(final Graphics g, final int x, final int y,
				final Dimension d) {
		this.bottomTile.draw(g, x, y, d);
		this.topTile.draw(g, x, y, d);
	}
}
