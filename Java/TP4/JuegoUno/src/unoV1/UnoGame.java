package unoV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnoGame {
    private static List deck = new ArrayList();
    public static List<Card> discardPile = new ArrayList<>();
    public static Map<Character, List<Card>> playerHands = new HashMap<>();
    public static List<Character> players = new ArrayList<>();
    public static Card topCard;
    public static int direction;
    public static int currentPlayer = 0;

    public UnoGame(List mazo, Character aPlayer1, Character aPlayer2) {
        deck.clear();
        discardPile.clear();
        playerHands.clear();
        players.clear();
        topCard = null;
        currentPlayer = 0;

        deck = mazo;
        topCard = (Card) deck.remove(0);
        direction = 1;
        addPlayer(aPlayer1);

        addPlayer(aPlayer2);


        drawCardS(aPlayer1, 5);
        drawCardS(aPlayer2, 5);


    }

    public static void nextPlayer(int direction) {
        currentPlayer = (currentPlayer + direction) % players.size();
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

    public static void drawCardS(char player, int numberCards) {
        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
        if (deck.isEmpty()) {
            deck = discardPile;
            discardPile.clear();
        } for (int i = 0; i < numberCards; i++) {
            playerHands.get(player).add((Card) deck.remove(0));
        }
        nextPlayer(direction);
    }

    public void playCard(char player, Card card) {
        List<Card> playerHand = playerHands.get(player);
        if (!playerHand.contains(card)) {
            throw new RuntimeException("Card not in hand");
        }
        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
        if (card.getValue() != "Wild") {
            if (topCard.getValue().equals("Wild")) {
                if (card.getColor() != topCard.getColor()) {
                    throw new RuntimeException("Invalid card 1");
                }
            } else {
                if (card.getColor() != topCard.getColor() && !card.getValue().equals(topCard.getValue())) {
                    throw new RuntimeException("Invalid card 2");
                }
            }
        }
        nextPlayer(direction);
        discardPile.add(card);
        playerHand.remove(card);
        topCard = card;
        card.effect();
    }

}
