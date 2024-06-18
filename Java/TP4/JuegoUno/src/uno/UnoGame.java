package uno;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnoGame {
    public static final String CardNotInHand = "Card not in hand";
    public static final String CantPlayThatCard = "Can't play that card";
    public static final String atLeastTwoPlayers = "You need at least two players";
    public Map<Character, List<Card>> playerHands = new HashMap<>();
    public List<Character> players = new ArrayList<>();
    public Card topCard = new NormalCard("Red", 2);
    public int currentPlayerIndex = 0;
    public char lastPlayer;
    public int direction = 1;
    public GameStatus estado;
    public boolean oneCardLeft = false;

    public UnoGame(char... player) {
        for (char aPlayer : player) {
            addPlayer(aPlayer);
        }
        if (players.size() < 2) {
            throw new RuntimeException(atLeastTwoPlayers);
        }
        estado = new PlayerTurn(this, players.get(currentPlayerIndex));
    }

    public void nextPlayer() {
        estado = estado.nextTurn();

    }
    public void reverseDirection() {
        direction *= -1;
    }
    public char nextPlayerChar() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        return players.get(currentPlayerIndex);
    }

    public char getWinner() {
        return players.stream()
                .filter(player -> playerHands.get(player).isEmpty())
                .peek(player -> estado = new FinishedStatus())
                .findFirst()
                .orElse((char) 0);
    }

    public void addPlayer(char player) {
        players.add(player);
        playerHands.put(player, new ArrayList<>());
    }

    public char getCurrentPlayer() {
        return estado.getCurrentPlayer();
    }


    public void drawCard(Card aCard) {
        estado.drawCard(aCard);
    }

    public void playCard(Card aCard) {
        if (!playerHands.get(getCurrentPlayer()).contains(aCard)) {
            throw new RuntimeException(CardNotInHand);
        }
        if (!aCard.canBePLayedOnTopOf(topCard)) {
            throw new RuntimeException(CantPlayThatCard);
        }
        estado.playCard(aCard);
    }

    public void shoutUno(char aPlayer) {
        if (lastPlayer == aPlayer) {
            oneCardLeft = true;
        } else {
            playerHands.get(lastPlayer).add(new NormalCard("Blue", 7));
            playerHands.get(lastPlayer).add(new NormalCard("Blue", 8));
        }
    }

    public void playCardForPlayer(char aPlayer, Card aCard) {
        topCard = aCard;
        playerHands.get(aPlayer).remove(aCard);
        lastPlayer = aPlayer;
        aCard.effect(this);
        nextPlayer();

    }


}
