package uno;


public class Plus2Card extends SpecialCard{
    public Plus2Card(String aColor, String aValue) {
        super(aColor, aValue);
    }
    public void effect(UnoGame aGame){
        aGame.nextPlayer();
        aGame.playerHands.get(aGame.getCurrentPlayer()).add(new NormalCard("Blue", 7));
        aGame.playerHands.get(aGame.getCurrentPlayer()).add(new NormalCard("Blue", 8));

    }

    @Override
    public Card playMeAs(String color) {
        return null;
    }



}
