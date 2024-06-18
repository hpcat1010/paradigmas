package uno;

public class WildCard extends SpecialCard {
    private String color;
    private String value;
    private String newColor;

    public WildCard(String aColor, String aValue) {
        super(aColor, aValue);
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public void effect(UnoGame aGame) {
        aGame.topCard = new WildCard(newColor, value);

    }

    public Card playMeAs(String color) {
        newColor = color;
        return this;
    }



    public boolean canBePLayedOnTopOf(Card aTopCard) {
        return true;
    }

}
