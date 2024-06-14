package unoV1;

class FinishedStatus extends GameStatus {
    public static final String GameOver = "Game is over";
    @Override
    public void playCard(char aPlayer, Card aCard) {
        throw new RuntimeException(GameOver);
    }
    @Override
    public GameStatus nextTurn() {
        return this;
    }
    @Override
    public char getCurrentPlayer() {
        return 0;
    }
    @Override
    public void drawCard(char player, Card aCard) {
        throw new RuntimeException(GameOver);
    }


}
