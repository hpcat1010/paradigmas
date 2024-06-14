package unoV1;

public abstract class GameStatus {

    public void PlayCardsAsPlayer1(Card aCard){
        throw new RuntimeException("Can't play that card");
    }
    public void PlayCardsAsPlayer2(Card aCard){
        throw new RuntimeException("Can't play that card");
    }
    public abstract void playCard(Card aCard);
    public abstract GameStatus nextTurn();

    public abstract char getCurrentPlayer();

    public abstract void drawCards(char player, int numberCards);

    public void drawCardsAsPlayer1(char player, int numberCards) {
        throw new RuntimeException("Can't draw cards");
    }
    public void drawCardsAsPlayer2(char player, int numberCards) {
        throw new RuntimeException("Can't draw cards");
    }
}


