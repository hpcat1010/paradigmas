package unoV1;

class Player1Turn extends GameStatus {
    private UnoGame game;


    public Player1Turn(UnoGame game) {
        this.game = game;
    }

    public void PlayCardsAsPlayer1(Card aCard) {
        game.methodPlayCard('A', aCard);
    }

    @Override
    public void playCard(Card aCard) {
        PlayCardsAsPlayer1(aCard);
    }

    public GameStatus nextTurn() {
        return new Player2Turn(game);
    }

    @Override
    public char getCurrentPlayer() {
        return 'A';
    }

    @Override
    public void drawCards(char player, int numberCards) {
        drawCardsAsPlayer1(player, numberCards);
    }

    public void drawCardsAsPlayer1(char player, int numberCards) {
        game.methodDrawCardS('A', numberCards);
    }
}
