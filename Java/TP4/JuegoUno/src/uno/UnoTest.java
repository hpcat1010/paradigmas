package uno;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static uno.FinishedStatus.GameOver;
import static uno.UnoGame.*;

public class UnoTest {
    private Card Red2;
    private Card Red1;
    private Card Red3;
    private Card Red5;
    private Card Red6;
    private Card Red7;
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
    private ReverseCard GreenReverse;
    private WildCard WILD;
    private List<Card> playerAHand;
    private List<Card> playerBHand;
    private List<Card> playerCHand;
    private List<Card> playerDHand;
    private List<Card> playerEHand;
    private List<Card> playerFHand;
    private List<Card> playerGHand;

    private List<Card> shortHand;

    @Before
    public void setUp() {
        Red2 = new NormalCard("Red", 2);
        Red1 = new NormalCard("Red", 1);
        Red3 = new NormalCard("Red", 3);
        Red5 = new NormalCard("Red", 5);
        Red6 = new NormalCard("Red", 6);
        Red7 = new NormalCard("Red", 7);
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
        GreenReverse = new ReverseCard("Green", "Reverse");
        WILD = new WildCard("Wild", "Wild");
        playerAHand = new ArrayList<>(Arrays.asList(Red2, Blue4, Yellow8, Red4, Green2));
        playerBHand = new ArrayList<>(Arrays.asList(Red9, Blue4, Yellow8, Red4, Green2));
        shortHand = new ArrayList<>(Arrays.asList(Red4, RedSkip));
        playerCHand = new ArrayList<>(Arrays.asList(Red1, RedSkip, WILD));
        playerDHand = new ArrayList<>(Arrays.asList(Red7, RedSkip, WILD));
        playerEHand = new ArrayList<>(Arrays.asList(Red3, RedSkip, WILD));
        playerFHand = new ArrayList<>(Arrays.asList(Red6, RedSkip, WILD));
        playerGHand = new ArrayList<>(Arrays.asList(Red5, RedSkip, WILD));
    }


    @Test
    public void testTopCardRojo2() {
        UnoGame game = new UnoGame('A', 'B');
        assertEquals("Red", game.topCard.getColor());
        assertEquals(2, game.topCard.getValue());
    }

    @Test
    public void testErrorDiferentCard() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        assertThrowsLike(CantPlayThatCard, () -> game.playCard(Yellow8));
    }

    @Test
    public void testPlayCardSameColor() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        assertEquals(Red2.getColor(), game.topCard.getColor());
        assertEquals(Red2.getValue(), game.topCard.getValue());
        game.playCard(Red4);
        assertEquals(Red4, game.topCard);
    }

    @Test
    public void testPlayCardSameValue() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(Green2);
        assertEquals(Green2, game.topCard);
    }

    @Test
    public void testPlayCardDifferentColorAndValue() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        assertThrowsLike(CantPlayThatCard, () -> game.playCard(Blue4));
    }

    @Test
    public void testDrawCard() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.drawCard(Red9);
        assertEquals(6, game.playerHands.get('A').size());
    }

    @Test
    public void testTurnOrderWhenYouPlayCard() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        assertEquals('A', game.getCurrentPlayer());
        game.playCard(Red4);
        assertEquals('B', game.getCurrentPlayer());
    }

    @Test
    public void testTurnOrderWhenYouDrawCard() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.drawCard(Red4);
        assertEquals(6, game.playerHands.get('A').size());
        assertEquals('B', game.getCurrentPlayer());
        game.drawCard(Blue4);
        assertEquals(6, game.playerHands.get('B').size());
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardPSkip() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardPSkipAfterAnotherSkip() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedSkip);
        playerAHand.add(BlueSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        game.playCard(BlueSkip);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testCardSkipFollowsTurnOrder() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardPlus2() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedPlus2);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedPlus2);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals(7, game.playerHands.get('B').size());
        assertEquals('A', game.getCurrentPlayer());

    }

    @Test
    public void testPlus2BlueAfterPLus2Red() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedPlus2);
        playerAHand.add(BluePlus2);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedPlus2);
        game.playCard(BluePlus2);
        assertEquals(BluePlus2, game.topCard);
    }

    @Test
    public void testSpecialCardReverse() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedReverse);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedReverse);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardReverseAfterRevese() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(RedReverse);
        playerAHand.add(GreenReverse);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedReverse);
        game.playCard(GreenReverse);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardWildAsRed() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Red"));
        assertEquals("Red", game.topCard.getColor());

    }

    @Test
    public void testSpecialCardWildAsBlue() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Blue"));
        assertEquals("Blue", game.topCard.getColor());

    }

    @Test
    public void testSpecialCardWildAsGreen() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Green"));
        assertEquals("Green", game.topCard.getColor());

    }

    @Test
    public void testSpecialCardWildAsYellow() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Yellow"));
        assertEquals("Yellow", game.topCard.getColor());

    }


    @Test
    public void testAfterSpecialCardWildHasToBeSameColor() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Blue"));
        assertThrowsLike(CantPlayThatCard, () -> game.playCard(Red4));
    }

    @Test
    public void testSpecialCardWildIsSameColor() {
        UnoGame game = new UnoGame('A', 'B');
        playerAHand.add(WILD);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(WILD.playMeAs("Blue"));
        game.playCard(Blue4);
        assertEquals(Blue4, game.topCard);
    }

    @Test
    public void testPlayerAWins() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        game.shoutUno('A');
        assertTrue(game.oneCardLeft);
        game.playCard(Red4);
        assertEquals('A', game.getWinner());
    }

    @Test
    public void testPlayerBWins() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', shortHand);
        game.playCard(Red4);
        game.playCard(RedSkip);
        game.shoutUno('B');
        assertTrue(game.oneCardLeft);
        game.playCard(Red4);
        assertEquals('B', game.getWinner());
    }

    @Test
    public void testPlayCardNotInHand() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        assertThrowsLike(CardNotInHand, () -> game.playCard(new NormalCard("Green", 5)));
    }

    @Test
    public void testShoutUnoDrawCards() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(Red4);
        game.shoutUno('B');
        assertFalse(game.oneCardLeft);
        assertEquals(3, game.playerHands.get('A').size());
    }

    @Test
    public void testShoutUnoDrawCardsHelp() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(Red4);
        game.shoutUno('B');
        assertFalse(game.oneCardLeft);
        assertEquals(3, game.playerHands.get('A').size());
    }


    @Test
    public void testDrawCardAfterGameEnd() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        game.shoutUno('A');
        assertTrue(game.oneCardLeft);
        game.playCard(Red4);
        assertEquals('A', game.getWinner());
        assertThrowsLike(GameOver, () -> game.drawCard(Green2));
    }

    @Test
    public void testPlayCardAfterGameEnd() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        game.playCard(RedSkip);
        game.shoutUno('A');
        assertTrue(game.oneCardLeft);
        game.playCard(Red4);
        assertEquals('A', game.getWinner());
        assertThrowsLike(GameOver, () -> game.playCard(Red4));
    }

    @Test
    public void testNoWinner() {
        UnoGame game = new UnoGame('A', 'B');
        game.playerHands.put('A', shortHand);
        game.playerHands.put('B', playerBHand);
        assertNotEquals('A', game.getWinner());
        assertNotEquals('B', game.getWinner());
    }

    @Test
    public void test3Players() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        assertEquals('A', game.getCurrentPlayer());
        game.playCard(Red4);
        assertEquals('B', game.getCurrentPlayer());
        game.playCard(Red9);
        assertEquals('C', game.getCurrentPlayer());
        game.playCard(Red1);
        assertEquals('A', game.getCurrentPlayer());
    }

    @Test
    public void test7Players() {
        UnoGame game = new UnoGame('A', 'B', 'C', 'D', 'E', 'F', 'G');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.playerHands.put('D', playerDHand);
        game.playerHands.put('E', playerEHand);
        game.playerHands.put('F', playerFHand);
        game.playerHands.put('G', playerGHand);
        assertEquals('A', game.getCurrentPlayer());
        game.playCard(Red4);
        assertEquals('B', game.getCurrentPlayer());
        game.playCard(Red9);
        assertEquals('C', game.getCurrentPlayer());
        game.playCard(Red1);
        assertEquals('D', game.getCurrentPlayer());
        game.playCard(Red7);
        assertEquals('E', game.getCurrentPlayer());
        game.playCard(Red3);
        assertEquals('F', game.getCurrentPlayer());
        game.playCard(Red6);
        assertEquals('G', game.getCurrentPlayer());
        game.playCard(Red5);
        assertEquals('A', game.getCurrentPlayer());

    }

    @Test
    public void testLesThanTwoPlayers() {
        assertThrowsLike(atLeastTwoPlayers, () -> new UnoGame('A'));
    }

    @Test
    public void testNoPlayers() {
        assertThrowsLike(atLeastTwoPlayers, () -> new UnoGame());
    }

    @Test
    public void testHandSizeWith3PlayersWhenPlayed() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.playCard(Red4);
        assertEquals(4, game.playerHands.get('A').size());
        game.playCard(Red9);
        assertEquals(4, game.playerHands.get('B').size());
        game.playCard(Red1);
        assertEquals(2, game.playerHands.get('C').size());
        game.playCard(Red2);
        assertEquals(3, game.playerHands.get('A').size());
    }

    @Test
    public void testHandSizeWith3PlayersWhenDraw() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.drawCard(Blue4);
        assertEquals(6, game.playerHands.get('A').size());
        game.drawCard(Green2);
        assertEquals(6, game.playerHands.get('B').size());
        game.drawCard(Red9);
        assertEquals(4, game.playerHands.get('C').size());
        game.drawCard(Red1);
        assertEquals(7, game.playerHands.get('A').size());
    }

    @Test
    public void testSpecialCardSkipWith3Players() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        playerAHand.add(RedSkip);
        playerCHand.add(RedSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.playCard(RedSkip);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals('C', game.getCurrentPlayer());
        game.playCard(RedSkip);
        assertEquals(3, game.playerHands.get('C').size());
        assertEquals('B', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardPlus2With3Players() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        playerAHand.add(RedPlus2);
        playerCHand.add(RedPlus2);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.playCard(RedPlus2);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals(7, game.playerHands.get('B').size());
        assertEquals('C', game.getCurrentPlayer());
        game.playCard(RedPlus2);
        assertEquals(3, game.playerHands.get('C').size());
        assertEquals(7, game.playerHands.get('A').size());
        assertEquals('B', game.getCurrentPlayer());
    }

    @Test
    public void testSpecialCardReverseWith3Players() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        playerAHand.add(RedReverse);
        playerBHand.add(RedReverse);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', playerCHand);
        game.playCard(RedReverse);
        assertEquals(5, game.playerHands.get('A').size());
        assertEquals('C', game.getCurrentPlayer());
        game.playCard(Red1);
        assertEquals(2, game.playerHands.get('C').size());
        assertEquals('B', game.getCurrentPlayer());
        game.playCard(RedReverse);
        assertEquals(5, game.playerHands.get('B').size());
        assertEquals('C', game.getCurrentPlayer());
    }

    @Test
    public void testPlayerCWin() {
        UnoGame game = new UnoGame('A', 'B', 'C');
        playerAHand.add(RedSkip);
        game.playerHands.put('A', playerAHand);
        game.playerHands.put('B', playerBHand);
        game.playerHands.put('C', shortHand);
        game.playCard(RedSkip);//A
        game.playCard(RedSkip);//C
        game.shoutUno('C');
        game.playCard(Red9);//b
        game.playCard(Red4);//C
        assertEquals('C', game.getWinner());
        assertThrowsLike(GameOver, () -> game.drawCard(Red4));
    }

    private void assertThrowsLike(String message, Executable codeBlock) {
        assertEquals(message, assertThrows(Throwable.class, codeBlock).getMessage());
    }
}
