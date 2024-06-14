package unoV1;

public abstract class GameStatus {

    public static final String NotYourTurn = "Not your turn";

    public void PlayCardsAsPlayer1(Card aCard) {
        throw new RuntimeException(NotYourTurn);
    }

    public void PlayCardsAsPlayer2(Card aCard) {
        throw new RuntimeException(NotYourTurn);
    }

    public abstract void playCard(char aPlayer, Card aCard);

    public abstract GameStatus nextTurn();

    public abstract char getCurrentPlayer();

    public abstract void drawCard(char player, Card aCard);

    public void drawCardsAsPlayer1(Card aCard) {
        throw new RuntimeException(NotYourTurn);
    }

    public void drawCardsAsPlayer2(Card aCard) {
        throw new RuntimeException(NotYourTurn);
    }

}


