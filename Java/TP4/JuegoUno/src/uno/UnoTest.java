package uno;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UnoTest {

    @Test
    public void testInitializeGame() {
        UnoGame game = new UnoGame();
        game.initializeGame(new Player(), new Player());
        assertEquals(89, game.getDeck().size());
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testAddPlayer() {
        UnoGame game = new UnoGame();
        game.initializeGame(new Player(), new Player(), new Player());
        assertEquals(3, game.getPlayers().size());
        assertEquals(82, game.getDeck().size());
    }

    @Test
    public void testDrawCard() {
        UnoGame game = new UnoGame();
        Player player = new Player();
        game.initializeGame(player, new Player());
        game.drawCard();
        assertEquals(8, player.getHand().size());
    }

    @Test
    public void testPlayCard() {
        UnoGame game = new UnoGame();
        Player player = new Player();
        game.initializeGame(player, new Player());
        Object card =  player.getHand().get(0);
        game.playCard(card);
        assertEquals(6, player.getHand().size());
        assertEquals(1, UnoGame.discardPile.size());
        assertEquals(card, UnoGame.topCard);
    }

    @Test
    public void testPlayCardWin() {
        UnoGame game = new UnoGame();
        Player player = new Player();
        game.initializeGame(player, new Player());
        for (int i = 0; i < 12; i++) {
            game.playCard(player.getHand().get(0));
        }
        assertThrows(RuntimeException.class, () -> game.playCard(player.getHand().get(0)));
    }

    @Test
    public void testPlayCardDirectionWith3Players() {
        UnoGame game = new UnoGame();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        game.initializeGame(player1, player2, player3);
        assertEquals(player1, game.getCurrentPlayer());
        game.playCard(player1.getHand().get(0));
        assertEquals(player2, game.getCurrentPlayer());
        game.playCard(player2.getHand().get(0));
        assertEquals(player3, game.getCurrentPlayer());
        game.playCard(player3.getHand().get(0));
        assertEquals(player1, game.getCurrentPlayer());
    }

}
