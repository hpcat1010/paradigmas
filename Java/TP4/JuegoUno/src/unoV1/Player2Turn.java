package unoV1;

class Player2Turn extends GameStatus {
    public UnoGame game;

    public Player2Turn(UnoGame game) {
        this.game = game;
    }

    public void PlayCardsAsPlayer2(Card aCard) {
        game.methodPlayCard('B', aCard);
    }

    @Override
    public void playCard(Card aCard) {
        PlayCardsAsPlayer2(aCard);
    }

    public GameStatus nextTurn() {
        return new Player1Turn(game);
    }

    @Override
    public char getCurrentPlayer() {
        return 'B';
    }

    @Override
    public void drawCards(char player, int numberCards) {
        drawCardsAsPlayer2(player, numberCards);
    }

    public void drawCardsAsPlayer2(char player, int numberCards) {
        game.methodDrawCardS('B', numberCards);
    }
}
