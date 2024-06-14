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
    public void playCard(char aPlayer, Card aCard) {
        if (aPlayer == 'A')
            PlayCardsAsPlayer1(aCard);
        else
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
    public void drawCard(char player, Card aCard) {
        if (player == 'A') {
            drawCardsAsPlayer2(aCard);
        } else {
            drawCardsAsPlayer1(aCard);

        }
    }

    public void drawCardsAsPlayer2(Card aCard) {
        game.playerHands.get('A').add(aCard);
        game.nextPlayer();
    }



}
