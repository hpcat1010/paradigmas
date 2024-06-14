package unoV1;


import java.util.List;

public abstract class SpecialCard extends Card{
    private String color;
    private String value;


    public  SpecialCard(String aColor, String aValue) {
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public abstract void effect(UnoGame aGame);


    public abstract Card playMeAs(String color);

    public abstract void canBePLayedOnTopOf(Card aTopCard);
}
