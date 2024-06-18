package uno;

public class ReverseCard extends SpecialCard{
    public ReverseCard(String aColor, String aValue) {
        super(aColor, aValue);
    }


    public void effect(UnoGame aGame) {
        aGame.reverseDirection();

    }

    @Override
    public Card playMeAs(String color) {
        return null;
    }





}
