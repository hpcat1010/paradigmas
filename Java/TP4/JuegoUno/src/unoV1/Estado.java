package unoV1;

public class Estado {

    public int currentPlayer;
    public Boolean gameEnded;
    public UnoGame game;
    public Estado(UnoGame game){
        this.game = game;
        this.currentPlayer = game.currentPlayer;
        this.gameEnded = false;
    }
}
