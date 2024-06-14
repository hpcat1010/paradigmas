package unoV2;

class Player1Turn extends GameStatus {
    private final UnoGame game;

    public Player1Turn(UnoGame game) {
        this.game = game;
    }

    @Override
    public void drawCard(Card aCard) {
        game.playerHands.get('A').add(aCard);
        game.nextPlayer();
    }

    public void playCard(Card aCard) {
        game.playCardForPlayer('A', aCard);
    }

    public GameStatus nextTurn() {
        return new Player2Turn(game);
    }

    @Override
    public char getCurrentPlayer() {
        return 'A';
    }


}
