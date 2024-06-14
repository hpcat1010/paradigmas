package unoV2;

public abstract class Card {


    public abstract String getColor();

    public abstract Object getValue();

    public abstract void effect(UnoGame aGame);

    public abstract Card playMeAs(String red);

    public boolean canBePLayedOnTopOf(Card aTopCard) {
        return aTopCard.getValue().equals(this.getValue()) || aTopCard.getColor().equals(this.getColor());
    }

}
