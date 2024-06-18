package uno;

public class PlayerTurn extends GameStatus{
    private final UnoGame game;
    private final char currentPlayer;
    public PlayerTurn(UnoGame game, char currentPlayer) {
        this.game = game;
        this.currentPlayer = currentPlayer;
    }

    public void drawCard(Card aCard) {
        game.playerHands.get(currentPlayer).add(aCard);
        game.nextPlayer();
    }

    public void playCard(Card aCard) {
        game.playCardForPlayer(currentPlayer, aCard);
    }

    public GameStatus nextTurn() {
        return new PlayerTurn(game, game.nextPlayerChar());
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

}
