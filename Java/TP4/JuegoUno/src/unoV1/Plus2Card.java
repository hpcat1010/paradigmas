package unoV1;

import java.util.List;



public class Plus2Card extends SpecialCard{
    public Plus2Card(String aColor, String aValue) {
        super(aColor, aValue);
    }
    public void effect(UnoGame aGame){
        aGame.playerHands.get(aGame.getCurrentPlayer()).add(new NormalCard("Blue", 7));
        aGame.playerHands.get(aGame.getCurrentPlayer()).add(new NormalCard("Blue", 8));
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
