package unoV1;

public abstract class Card {


    public abstract String getColor();

    public abstract Object getValue();

    public abstract void effect();

    public abstract Card playMeAs(String red);

    public abstract void amIWild();

    public void canBePLayedOnTopOf(Card aTopCard) {
        if (aTopCard.getValue() != this.getValue() && !aTopCard.getColor().equals(this.getColor())) {
            throw new RuntimeException("Can't play that card");
        }
    }
}
