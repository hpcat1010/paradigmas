package uno;

public class NormalCard extends Card{

    private String color;
    private int value;


    public NormalCard(String aColor, int aValue) {
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public Integer getValue() {
        return value;
    }


    public void effect(UnoGame aGame) {

    }

    @Override
    public Card playMeAs(String red) {
      return this;
    }





}
