package uno;

public class Card {
    private String color;
    private String value;

    public Card(String aColor, String aValue) {
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }
}
