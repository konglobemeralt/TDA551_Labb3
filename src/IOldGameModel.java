public interface IOldGameModel extends IGameModel {
    public void setGameboardState(final Position pos, final IGameTile tile);


    public void setGameboardState(final int x, final int y,
                                  final IGameTile tile);
}
