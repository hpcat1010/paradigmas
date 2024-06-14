package unoV1;

public class SkipCard extends SpecialCard{
    public SkipCard(String aColor, String aValue) {
        super(aColor,aValue);
    }
    public void effect(UnoGame aGame){
        aGame.nextPlayer(aGame.direction);
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
