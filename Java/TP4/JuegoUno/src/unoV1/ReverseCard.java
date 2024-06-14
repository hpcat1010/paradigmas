package unoV1;

public class ReverseCard extends SpecialCard{
    public ReverseCard(String aColor, String aValue) {
        super(aColor, aValue);
    }


    public void effect(UnoGame aGame) {
        aGame.nextPlayer();

    }

    @Override
    public Card playMeAs(String color) {
        return null;
    }



    @Override
    public void canBePLayedOnTopOf(Card aTopCard) {
        if (aTopCard.getValue() != this.getValue() && !aTopCard.getColor().equals(this.getColor())) {
            throw new RuntimeException("Can't play that card");
        }

    }

}
