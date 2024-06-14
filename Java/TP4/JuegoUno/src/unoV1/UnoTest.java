package unoV1;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnoTest {
    private Card Red2;
    private Card Blue4;
    private Card Yellow8;
    private Card Red4;
    private Card Red9;
    private Card Green2;
    private Plus2Card RedPlus2;
    private Plus2Card BluePlus2;
    private SkipCard RedSkip;

    private SkipCard BlueSkip;
    private ReverseCard RedReverse;
    private ReverseCard GreedReverse;
    private WildCard WILD;

    private List<Card> playerAHand;
    private List<Card> playerBHand;

    private List<Card> newHand;
    @Before
    public void setUp() {
        Red2 = new NormalCard("Red", 2);
        Blue4 = new NormalCard("Blue", 4);
        Yellow8 = new NormalCard("Yellow", 8);
        Red4 = new NormalCard("Red", 4);
        Green2 = new NormalCard("Green", 2);
        Red9 = new NormalCard("Red", 9);
        RedPlus2 = new Plus2Card("Red", "Plus 2");
        BluePlus2 = new Plus2Card("Blue", "Plus 2");
        RedSkip = new SkipCard("Red", "Skip");
        BlueSkip = new SkipCard("Blue", "Skip");
        RedReverse = new ReverseCard("Red", "Reverse");
        GreedReverse = new ReverseCard("Green", "Reverse");
        WILD = new WildCard("Wild", "Wild");
        playerAHand = new ArrayList<>(Arrays.asList(Red2, Blue4, Yellow8, Red4, Green2));
        playerBHand = new ArrayList<>(Arrays.asList(Red9, Blue4, Yellow8, Red4, Green2));
        newHand = new ArrayList<>(Arrays.asList(Red4,RedSkip));
    }



    @Test
    public void testTopCardRojo2() {
        UnoGame game = new UnoGame( 'A', 'B');
        assertEquals("Red", game.topCard.getColor());
        assertEquals(2, game.topCard.getValue());
    }

    @Test
    public void testErrorDiferentCard() {
        UnoGame game = new UnoGame( 'A', 'B');

        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        assertThrowsLike("Can't play that card", () -> game.playCard('A', Yellow8));
    }

    @Test
    public void testPlayCardSameColor() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', Red4);
        assertEquals(Red4, game.topCard);
    }

    @Test
    public void testPlayCardSameValue() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', Green2);
        assertEquals(Green2, game.topCard);
    }

    @Test
    public void testPlayCardDifferentColorAndValue() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        assertThrowsLike("Can't play that card", () -> game.playCard('A', Blue4));
    }

    @Test
    public void testDrawCard() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.drawCardS('A', Red9);
        assertEquals(6, game.playerHands.get('A').size());
    }

    @Test
    public void testTurnOrder() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        assertThrowsLike("Not your turn", () -> game.playCard('B', Red4));
    }

    @Test
    public void testPlayCardNotYourTurn() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', Red4);
        assertThrowsLike("Not your turn", () -> game.playCard('A', Blue4));
    }

    @Test
    public void testDrawCardNotYourTurn() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        assertThrowsLike("Not your turn", () -> game.drawCardS('B', Red9));
    }

    @Test
    public void testSpecialCardPSkip() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedSkip);
        assertEquals('A', game.getCurrentPlayer());
    }
    @Test
    public void testSpecialCardPSkipAfterAnotherSkip() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedSkip);
        playerAHand.add(BlueSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedSkip);
        game.playCard('A', BlueSkip);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardPlus2() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedPlus2);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedPlus2);
        assertEquals(7, game.playerHands.get('B').size());
        assertEquals('A', game.getCurrentPlayer());

    }

    @Test
    public void testPlus2BlueAfterPLus2Red() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedPlus2);
        playerAHand.add(BluePlus2);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedPlus2);
        game.playCard('A', BluePlus2);
        assertEquals(BluePlus2, game.topCard);
    }

    @Test
    public void testSpecialCardReverse() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedReverse);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedReverse);
        assertEquals('A', game.getCurrentPlayer());
    }
    @Test
    public void testSpecialCardReverseAfterRevese(){
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(RedReverse);
        playerAHand.add(GreedReverse);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedReverse);
        game.playCard('A', GreedReverse);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardWildAsRojo() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', WILD.playMeAs("Red"));
        assertEquals("Red", game.topCard.getColor());

    }

    @Test
    public void testSpecialCardWildAsAzul() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', WILD.playMeAs("Blue"));
        assertEquals("Blue", game.topCard.getColor());

    }

    @Test
    public void testAfterSpecialCardWildHasToBeSameColor() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', WILD.playMeAs("Blue"));
        assertThrowsLike("Can't play that card", () -> game.playCard('B', Red4));
    }

    @Test
    public void testSpecialCardWildIsSameColor() {
        UnoGame game = new UnoGame( 'A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        game.playCard('A', WILD.playMeAs("Blue"));
        game.playCard('B', Blue4);
        assertEquals(Blue4, game.topCard);
    }

    @Test
    public void testPlayerAWins() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A', newHand);
        game.playerHands.put('B', playerBHand);
        game.playCard('A', RedSkip);
        game.shoutUno('A');
        assertTrue(game.oneCardLeft);
        game.playCard('A', Red4);
        assertEquals('A', game.getWinner());
    }

    @Test
    public void testPlayerBWins() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', newHand);
        game.playCard('A', Red4);
        game.playCard('B', RedSkip);
        game.shoutUno('B');
        assertTrue(game.oneCardLeft);
        game.playCard('B', Red4);
        assertEquals('B', game.getWinner());
    }



    @Test
    public void testPlayCardNotInHand() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',playerAHand );
        game.playerHands.put('B', playerBHand);
        Card cardNotInHand = new NormalCard("Green", 5);
        assertThrowsLike("Card not in hand", () -> game.playCard('A', cardNotInHand));
    }
    @Test
    public void testShoutUnoDrawCards() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',newHand );
        game.playerHands.put('B',playerBHand);
        game.playCard('A', Red4);
        game.shoutUno('B');
        assertFalse(game.oneCardLeft);
        assertEquals(3, game.playerHands.get('A').size());
    }
    @Test
    public void testShoutUnoDrawCardsHelp() {
        UnoGame game = new UnoGame( 'A', 'B');
        game.playerHands.put('A',newHand );
        game.playerHands.put('B',playerBHand);
        game.playCard('A', Red4);
        game.shoutUno('B');
        assertFalse(game.oneCardLeft);
        assertEquals(3, game.playerHands.get('A').size());
    }

    @Test
    public void testPlayCardAfterGameEnd() {

    }


    private void assertThrowsLike(String message, Executable codeBlock) {
        Assertions.assertEquals(message, Assertions.assertThrows(Throwable.class, codeBlock).getMessage());
    }
}
