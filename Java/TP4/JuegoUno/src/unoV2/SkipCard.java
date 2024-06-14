package unoV2;

public class SkipCard extends SpecialCard{
    public SkipCard(String aColor, String aValue) {
        super(aColor,aValue);
    }
    public void effect(UnoGame aGame){
        aGame.nextPlayer();
    }

    @Override
    public Card playMeAs(String color) {
        return null;
    }








}
