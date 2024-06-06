package unoV1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UnoTest {

    static Card R2 = new NormalCard("Red", 2);
    static Card B4 = new NormalCard("Blue", 4);
    static Card Y8 = new NormalCard("Yellow", 8);
    static Card R4 = new NormalCard("Red", 4);
    static Card B6 = new NormalCard("Blue", 6);
    static Card Y1 = new NormalCard("Yellow", 1);
    static Card G2 = new NormalCard("Green", 2);
    static Card G4 = new NormalCard("Green", 4);
    static Card R1 = new NormalCard("Red", 1);
    static Card R3 = new NormalCard("Red", 3);
    static Card R9 = new NormalCard("Red", 9);
    static Card Y7 = new NormalCard("Yellow", 7);
    static Card RP2 = new SpecialCard("Red", "Plus 2");
    static Card BP2 = new SpecialCard("Blue", "Plus 2");
    static Card RS = new SpecialCard("Red", "Skip");
    static Card RREV = new SpecialCard("Red", "Reverse");
    static Card WILD = new WildCard("Wild", "Wild");

    @Before
    public void setUp() {
        mazo = new ArrayList<>(Arrays.asList(R2, B4, Y8, G2, R4, Y1, G2, G4, R1, R3, R9, Y7,RP2,RS,R2,R2,R2,RREV));
        specialMazo1 = new ArrayList<>(Arrays.asList(R2, RREV, RP2, BP2, RS, Y1, G2, G4, R1, R3, R9, Y7,RP2,RS,R2,R2,R2,RREV));
        specialMazo2 = new ArrayList<>(Arrays.asList(R2, WILD, RREV, BP2, RS, Y1, G2, B4, R1, R3, R9, Y7,RP2,RS,R2,R2,R2,RREV));

    }
    private static List<Card> mazo = new ArrayList<>();
    private static List<Card> specialMazo1 = new ArrayList<>();
    private static List<Card> specialMazo2 = new ArrayList<>();

    @Test
    public void testTopCardRojo2() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertEquals("Red", game.topCard.getColor());
        assertEquals(2, game.topCard.getValue());
    }
    @Test
    public void testErrorDiferentCard() {
        UnoGame game = new UnoGame(mazo,'A','B');

        assertThrows(RuntimeException.class, () -> game.playCard('A',B4));
    }
    @Test
    public void testPlayCardSameColor() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A',R4);
        assertEquals(R4, UnoGame.topCard);
    }
    @Test
    public void testPlayCardSameValue() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A',G2);
        assertEquals(G2, UnoGame.topCard);
    }
    @Test
    public void testPlayCardDifferentColorAndValue() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertThrows(RuntimeException.class, () -> game.playCard('A',Y8));
    }

    @Test
    public void testDrawCard() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.drawCardS('A',1);
        assertEquals(6, game.playerHands.get('A').size());
    }
    @Test
    public void testTurnOrder() {
        UnoGame game = new UnoGame(mazo, 'A', 'B');
        assertThrows(RuntimeException.class, () -> game.playCard('B', R4));
    }
    @Test
    public void testPlayCardNotYourTurn() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A',R4);
        assertThrows(RuntimeException.class, () -> game.playCard('A',R3));
    }
    @Test
    public void testDrawCardNotYourTurn() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertThrows(RuntimeException.class, () -> game.drawCardS('B',1));
    }
    @Test
    public void testSpecialCardPSkip() {
        UnoGame game = new UnoGame(specialMazo1,'A','B');
        game.playCard('A',RS);
        assertEquals('A', game.getCurrentPlayer());
    }
    @Test
    public void testSpecialCardPlus2() {
        UnoGame game = new UnoGame(specialMazo1,'A','B');
        game.playCard('A',RP2);
        assertEquals(7, game.playerHands.get('B').size());
        assertEquals('A', game.getCurrentPlayer());

    }
    @Test
    public void testPlus2BlueAfterPLus2Red() {
        UnoGame game = new UnoGame(specialMazo1, 'A', 'B');
        game.playCard('A', RP2);
        game.playCard('A', BP2);
        assertEquals(BP2, game.topCard);
    }
    @Test
    public void testSpecialCardReverse() {
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', RREV);
        assertEquals('A', game.getCurrentPlayer());
    }
    @Test
    public void testSpecialCardWildAsRojo(){
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Red"));
        assertEquals("Red", game.topCard.getColor());

    }
    @Test
    public void testSpecialCardWildAsAzul(){
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Blue"));
        assertEquals("Blue", game.topCard.getColor());

    }
    @Test
    public void testSpecialCardWildHasToBeSameColor(){
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Blue"));
        assertThrows(RuntimeException.class, () -> game.playCard('B', R2));
    }
    @Test
    public void testSpecialCardWildIsSameColor() {
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Blue"));
        game.playCard('B',B4);
        assertEquals(B4, game.topCard);
    }
}
