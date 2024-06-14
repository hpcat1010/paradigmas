package unoV1;

class Player1Turn extends GameStatus {
    private UnoGame game;

    public Player1Turn(UnoGame game) {
        this.game = game;
    }
    @Override
    public void drawCard(char player, Card aCard) {
        if (player == 'A') {
            drawCardsAsPlayer1(aCard);
        } else {
            drawCardsAsPlayer2(aCard);

        }
    }
    public void drawCardsAsPlayer1(Card aCard) {
        game.playerHands.get('A').add(aCard);
        game.nextPlayer();
    }
    public void PlayCardsAsPlayer1(Card aCard) {
        game.methodPlayCard('A', aCard);
    }

    public void playCard(char aPlayer, Card aCard) {
        if (aPlayer == 'A')
            PlayCardsAsPlayer1(aCard);
        else
            PlayCardsAsPlayer2(aCard);

    }

    public GameStatus nextTurn() {
        return new Player2Turn(game);
    }

    @Override
    public char getCurrentPlayer() {
        return 'A';
    }



}
