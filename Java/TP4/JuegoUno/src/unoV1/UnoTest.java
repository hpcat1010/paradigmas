package unoV1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UnoTest {

    @Test
    public void testTopCardRojo2() {
        UnoGame game = new UnoGame(UnoGame.mazo,'A','B');
        assertEquals("rojo", game.topCard.color);
        assertEquals(2, game.topCard.value);
    }
//    @Test
//    public void testErrorDiferentCard() {
//        UnoGame game = new UnoGame();
//
//        assertThrows(RuntimeException.class, () -> game.playCard(new Card("azul", 3)));
//    }


}
