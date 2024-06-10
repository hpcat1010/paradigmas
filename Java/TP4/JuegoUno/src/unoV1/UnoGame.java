package unoV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class UnoGame {
    private static List deck = new ArrayList();
    public static List<Card> discardPile = new ArrayList<>();
    public static Map<Character, List<Card>> playerHands = new HashMap<>();
    public static List<Character> players = new ArrayList<>();
    public static Card topCard;
    public static int direction;
    public static int currentPlayer = 0;

    public UnoGame(List mazo, Character aPlayer1, Character aPlayer2) {
        clearGame();


        deck = mazo;
        topCard = (Card) deck.remove(0);
        direction = 1;
        addPlayer(aPlayer1);

        addPlayer(aPlayer2);


        drawCardS(aPlayer1, 5);
        drawCardS(aPlayer2, 5);


    }


    private void clearGame() {
        deck.clear();
        discardPile.clear();
        playerHands.clear();
        players.clear();
        topCard = null;
        currentPlayer = 0;
    }

    public static void nextPlayer(int direction) {
        currentPlayer = (currentPlayer + direction) % players.size();
    }

    public static void changeDirection() {
        direction = -direction;
    }

    public char getWinner() {
        return players.stream()
                .filter(player -> playerHands.get(player).isEmpty())
                .findFirst()
                .orElse((char) 0);
    }

    public List getDeck() {
        return deck;
    }

    public List getPlayers() {
        return players;
    }

    public void addPlayer(char player) {
        players.add(player);
        playerHands.put(player, new ArrayList<>());

    }

    public static char getCurrentPlayer() {
        return players.get(currentPlayer);

    }

    public static void isMyTurn(char player) {
        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
    }

    public static void drawCardS(char player, int numberCards) {
        isMyTurn(player);
        if (deck.isEmpty()) {
            deck = discardPile;
            discardPile.clear();
        }
        List<Card> drawnCards = IntStream.range(0, numberCards)
                .mapToObj(i -> (Card) deck.remove(0))
                .toList();
        playerHands.get(player).addAll(drawnCards);
        nextPlayer(direction);
    }

    public void playCard(char player, Card aCard) {
        doesPlayerHaveCard(player, aCard);
        isMyTurn(player);

        canThisCardBePlayed(aCard);
        nextPlayer(direction);
        discardPile.add(aCard);
        playerHands.get(player).remove(aCard);
        topCard = aCard;
        aCard.effect();
    }



    private void canThisCardBePlayed(Card aCard) {
        aCard.amIWild();
    }

    public static void doesPlayerHaveCard(char player, Card card) {
        if (!playerHands.get(player).contains(card)) {
            throw new RuntimeException("Card not in hand");
        }
    }

    public static void canIPlayCard(Card card) {
        card.canBePLayedOnTopOf(topCard);
    }

}
