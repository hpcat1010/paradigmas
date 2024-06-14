package unoV1;

public abstract class Card {


    public abstract String getColor();

    public abstract Object getValue();

    public abstract void effect(UnoGame aGame);

    public abstract Card playMeAs(String red);

    public abstract void canBePLayedOnTopOf(Card aTopCard);

}
