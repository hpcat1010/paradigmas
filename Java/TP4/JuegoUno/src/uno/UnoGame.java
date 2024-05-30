package uno;

import java.util.ArrayList;
import java.util.List;

public class UnoGame {
    private List deck = new ArrayList();
    public static List<Object> discardPile = new ArrayList<>();
    public List<Player> players = new ArrayList();
    public Object topCard;
    public String direction;
    private int currentPlayer = 0;

    public void initializeGame(Player... players) {
        deck.clear();
        for (int i = 0; i < 104; i++) {
            deck.add(new Object());
        }
        this.players.clear();
        for (Player player : players) {
            addPlayer(player);
        }
        direction = "clockwise";
        topCard = deck.remove(0);
    }

    public List getDeck() {
        return deck;
    }

    public List getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if (players.size() == 14) {
            throw new RuntimeException("Too many players");
        }
        players.add(player);
        for (int i = 0; i < 7; i++) {
            player.drawCard(new Object());
            deck.remove(0);
        }

    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void drawCard() {
        players.get(currentPlayer).drawCard(deck.remove(0));
    }

    public void playCard(Object card) {
        players.get(currentPlayer).playPlayerCard(card);
        currentPlayer = (currentPlayer + 1) % players.size();
        topCard = this;
    }
}
