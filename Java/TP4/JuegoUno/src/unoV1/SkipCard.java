package unoV1;

public class SkipCard extends SpecialCard{
    public SkipCard(String aColor, String aValue) {
        super(aColor,aValue);
    }
    public void effect(){
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
