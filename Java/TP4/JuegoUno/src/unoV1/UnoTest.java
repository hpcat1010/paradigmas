package unoV1;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UnoTest {

    static Card Red2 = new NormalCard("Red", 2);
    static Card Blue4 = new NormalCard("Blue", 4);
    static Card Yellow8 = new NormalCard("Yellow", 8);
    static Card Red4 = new NormalCard("Red", 4);
    static Card Green2 = new NormalCard("Green", 2);
    static Card RedPlus2 = new Plus2Card("Red", "Plus 2");
    static Card BluePlus2 = new Plus2Card("Blue", "Plus 2");
    static Card RedSkip = new SkipCard("Red", "Skip");
    static Card RedReverse = new ReverseCard("Red", "Reverse");
    static Card WILD = new WildCard("Wild", "Wild");

    @Before
    public void setUp() {
        mazo = new ArrayList<>(Arrays.asList(Red2, Blue4, Yellow8, Green2, Red4, Red2, Green2, Red4, Red2, Red2, Red2, Red2, RedPlus2, RedSkip, Red2, Red2, Red2, RedReverse));
        specialMazo1 = new ArrayList<>(Arrays.asList(Red2, RedReverse, RedPlus2, BluePlus2, RedSkip, Red2, Green2, Red2, Red2, Red2, Red2, Red2, RedPlus2, RedSkip, Red2, Red2, Red2, RedReverse));
        specialMazo2 = new ArrayList<>(Arrays.asList(Red2, WILD, RedReverse, BluePlus2, RedSkip, Red2, Green2, Blue4, Red4, Red2, Red2, Red2, RedPlus2, RedSkip, Red2, Red2, Red2, RedReverse));
        mazoSkip = new ArrayList<>(Arrays.asList(Red2, RedSkip, RedSkip, RedSkip, RedSkip, Red2, RedSkip, RedSkip, RedSkip, RedSkip, RedSkip, RedSkip, RedPlus2, RedSkip, Red2, Red2, Red2, RedReverse));
        mazoWith12Cards = new ArrayList<>(Arrays.asList(Green2,Red2,Red2,Red2,Red2,Red2,Red2,Red2,Red2,Red2,Red2,Red2));
    }
    private static List<Card> mazo = new ArrayList<>();
    private static List<Card> specialMazo1 = new ArrayList<>();
    private static List<Card> specialMazo2 = new ArrayList<>();
    private static List<Card> mazoSkip = new ArrayList<>();
    private static List<Card> mazoWith12Cards = new ArrayList<>();
    @Test
    public void testTopCardRojo2() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertEquals("Red", game.topCard.getColor());
        assertEquals(2, game.topCard.getValue());
    }
    @Test
    public void testErrorDiferentCard() {
        UnoGame game = new UnoGame(mazo,'A','B');

        assertThrowsLike("Can't play that card", () -> game.playCard('A', Yellow8));
    }
    @Test
    public void testPlayCardSameColor() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A', Red4);
        assertEquals(Red4, UnoGame.topCard);
    }
    @Test
    public void testPlayCardSameValue() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A', Green2);
        assertEquals(Green2, UnoGame.topCard);
    }
    @Test
    public void testPlayCardDifferentColorAndValue() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertThrowsLike("Can't play that card", () -> game.playCard('A', Blue4));
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
        assertThrowsLike("Not your turn", () -> game.playCard('B', Red4));
    }
    @Test
    public void testPlayCardNotYourTurn() {
        UnoGame game = new UnoGame(mazo,'A','B');
        game.playCard('A', Red4);
        assertThrowsLike("Not your turn", () -> game.playCard('A', Blue4));
    }
    @Test
    public void testDrawCardNotYourTurn() {
        UnoGame game = new UnoGame(mazo,'A','B');
        assertThrowsLike("Not your turn", () -> game.drawCardS('B',1));
    }
    @Test
    public void testSpecialCardPSkip() {
        UnoGame game = new UnoGame(specialMazo1,'A','B');
        game.playCard('A', RedSkip);
        assertEquals('A', game.getCurrentPlayer());
    }
    @Test
    public void testSpecialCardPlus2() {
        UnoGame game = new UnoGame(specialMazo1,'A','B');
        game.playCard('A', RedPlus2);
        assertEquals(7, game.playerHands.get('B').size());
        assertEquals('A', game.getCurrentPlayer());

    }
    @Test
    public void testPlus2BlueAfterPLus2Red() {
        UnoGame game = new UnoGame(specialMazo1, 'A', 'B');
        game.playCard('A', RedPlus2);
        game.playCard('A', BluePlus2);
        assertEquals(BluePlus2, game.topCard);
    }
    @Test
    public void testSpecialCardReverse() {
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', RedReverse);
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
    public void testAfterSpecialCardWildHasToBeSameColor(){
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Blue"));
        assertThrowsLike("Can't play that card", () -> game.playCard('B', Red4));
    }
    @Test
    public void testSpecialCardWildIsSameColor() {
        UnoGame game = new UnoGame(specialMazo2, 'A', 'B');
        game.playCard('A', WILD.playMeAs("Blue"));
        game.playCard('B', Blue4);
        assertEquals(Blue4, game.topCard);
    }
    @Test
    public void testPlayerAWins() {
        UnoGame game = new UnoGame(mazoSkip, 'A', 'B');
        game.playCard('A', RedSkip);
        game.playCard('A', RedSkip);
        game.playCard('A', RedSkip);
        game.playCard('A', RedSkip);
        game.playCard('A', Red2);
        assertEquals('A', game.getWinner());
    }
    @Test
    public void testPlayerBWins() {
        UnoGame game = new UnoGame(mazoSkip, 'A', 'B');
        game.playCard('A', Red2);
        game.playCard('B', RedSkip);
        game.playCard('B', RedSkip);
        game.playCard('B', RedSkip);
        game.playCard('B', RedSkip);
        game.playCard('B', RedSkip);
        assertEquals('B', game.getWinner());
    }

    @Test
    public void testPlayCardWhenDeckIsEmptyDiscardPileGetsShufledButNotTopCard() {
        UnoGame game = new UnoGame(mazoWith12Cards, 'A', 'B');
        game.playCard('A', Red2);
        game.drawCardS('B', 1);
        game.drawCardS('A', 1);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals(0, game.discardPile.size());
        assertEquals(0, game.deck.size());
        assertEquals(Red2, game.topCard);
    }


    @Test
    public void testPlayCardNotInHand() {
        UnoGame game = new UnoGame(mazo, 'A', 'B');
        Card cardNotInHand = new NormalCard("Green", 5);
        assertThrowsLike("Card not in hand", () -> game.playCard('A', cardNotInHand));
    }


    @Test
    public void testPlayCardAfterGameEnd() {

    }
    







    private void assertThrowsLike(String message, Executable codeBlock) {
        Assertions.assertEquals(message, Assertions.assertThrows(Throwable.class, codeBlock).getMessage());
    }
}
