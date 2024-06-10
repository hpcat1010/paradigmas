package unoV1;

public class ReverseCard extends SpecialCard{
    public ReverseCard(String aColor, String aValue) {
        super(aColor, aValue);
    }


    public void effect() {
        UnoGame.changeDirection();
        UnoGame.nextPlayer(UnoGame.direction);

    }

    @Override
    public Card playMeAs(String color) {
        return null;
    }

    @Override
    public void amIWild() {
        UnoGame.canIPlayCard(this);

    }

}
