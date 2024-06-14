package unoV1;

public class NormalCard extends Card{

    private String color;
    private int value;


    public NormalCard(String aColor, int aValue) {
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public Integer getValue() {
        return value;
    }


    public void effect(UnoGame aGame) {

    }

    @Override
    public Card playMeAs(String red) {
      return this;
    }


    @Override
    public void canBePLayedOnTopOf(Card aTopCard) {
        if (aTopCard.getValue() != this.getValue() && !aTopCard.getColor().equals(this.getColor())) {
            throw new RuntimeException("Can't play that card");
        }
    }


}
