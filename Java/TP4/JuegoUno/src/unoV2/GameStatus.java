package unoV2;

public abstract class GameStatus {

    public static final String NotYourTurn = "Not your turn";


    public abstract void playCard(Card aCard);

    public abstract GameStatus nextTurn();

    public abstract char getCurrentPlayer();

    public abstract void drawCard( Card aCard);



}


