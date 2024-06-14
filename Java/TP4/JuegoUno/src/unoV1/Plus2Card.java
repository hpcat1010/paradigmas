package unoV1;

public class Plus2Card extends SpecialCard{
    public Plus2Card(String aColor, String aValue) {
        super(aColor, aValue);
    }
    public void effect(UnoGame aGame){
        aGame.drawCardS(aGame.getCurrentPlayer(),new NormalCard("Blue",7),new NormalCard("Blue",8));
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
