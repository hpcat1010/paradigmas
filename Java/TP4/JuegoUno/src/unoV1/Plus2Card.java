package unoV1;

public class Plus2Card extends SpecialCard{
    public Plus2Card(String aColor, String aValue) {
        super(aColor, aValue);
    }
    public void effect(){
        UnoGame.drawCardS(UnoGame.getCurrentPlayer(),2);
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
