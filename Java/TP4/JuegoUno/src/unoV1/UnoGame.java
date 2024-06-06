package unoV1;

import java.util.ArrayList;
import java.util.List;

public class UnoGame {

    private static List deck = new ArrayList();
    public static List<Object> discardPile = new ArrayList<>();

    public static List<Card> player1Hand = new ArrayList<>();
    public static List<Card> player2Hand = new ArrayList<>();
    public static List<Character> players = new ArrayList<>();
    public static Card topCard;
    public String direction;
    public static int currentPlayer = 0;

    public UnoGame(List mazo, Character aPlayer1, Character aPlayer2) {
        deck.clear();
        discardPile.clear();
        player1Hand.clear();
        player2Hand.clear();
        players.clear();
        topCard = null;
        direction = null;
        currentPlayer = 0;

        deck = mazo;
        topCard = (Card) deck.remove(0);
        direction = "clockwise";
        addPlayer(aPlayer1);

        addPlayer(aPlayer2);


        drawCardS(aPlayer1, 5);
        drawCardS(aPlayer2, 5);


    }

    public static void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }


    public List getDeck() {
        return deck;
    }

    public List getPlayers() {
        return players;
    }

    public void addPlayer(char player) {
        players.add(player);


    }

    public static char getCurrentPlayer() {
        return players.get(currentPlayer);

    }

    public static void drawCardS(char player, int numberCards) {
        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
        if (deck.isEmpty()) {
            deck = discardPile;
            discardPile.clear();
        }
        if (player == players.get(0)) {
            for (int i = 0; i < numberCards; i++) {
                player1Hand.add((Card) deck.remove(0));
            }

        } else {
            for (int i = 0; i < numberCards; i++) {
                player2Hand.add((Card) deck.remove(0));
            }

        }

        nextPlayer();
    }

    public void playCard(char player, Card card) {

        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
        if (card.getColor() != topCard.getColor() && card.getValue() != topCard.getValue()) {
            throw new RuntimeException("Invalid card");
        }

        nextPlayer();
        card.effect();
        topCard = card;

    }
}
