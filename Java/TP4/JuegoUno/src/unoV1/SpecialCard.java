package unoV1;



public class SpecialCard extends Card{
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

    public void effect(){
        if (value.equals("Plus 2")){
            UnoGame.drawCardS(UnoGame.getCurrentPlayer(),2);

        }
        if (value.equals("Skip") || value.equals("Reverse")){
            UnoGame.nextPlayer();
        }

    }


    public Card playMeAs(String red) {
        return new SpecialCard(red, "Wild");
    }
}
