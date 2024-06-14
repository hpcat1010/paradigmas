package unoV2;

class Player2Turn extends GameStatus {
    private final UnoGame game;

    public Player2Turn(UnoGame game) {
        this.game = game;
    }

    @Override
    public void drawCard(Card aCard) {
        game.playerHands.get('B').add(aCard);
        game.nextPlayer();
    }

    public void playCard(Card aCard) {
        game.playCardForPlayer('B', aCard);

    }
    public GameStatus nextTurn() {
        return new Player1Turn(game);
    }

    @Override
    public char getCurrentPlayer() {
        return 'B';
    }


}
