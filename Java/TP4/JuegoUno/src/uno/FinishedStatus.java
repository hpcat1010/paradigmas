package uno;

class FinishedStatus extends GameStatus {
    public static String GameOver = "Game is over";
    @Override
    public void playCard( Card aCard) {
        throw new RuntimeException(GameOver);
    }
    @Override
    public GameStatus nextTurn() {
        return this;
    }
    @Override
    public char getCurrentPlayer() {
        throw new RuntimeException(GameOver);
    }
    @Override
    public void drawCard( Card aCard) {
        throw new RuntimeException(GameOver);
    }


}
