package unoV1;

import java.util.*;
import java.util.stream.IntStream;

public class UnoGame {
    public Map<Character, List<Card>> playerHands = new HashMap<>();
    public List<Character> players = new ArrayList<>();
    public Card topCard = new NormalCard("Red", 2);
    public int direction = 1;
    public int currentPlayer = 0;
    public char lastPlayer;

    public boolean oneCardLeft = false;

    public UnoGame(Character aPlayer1, Character aPlayer2) {
        addPlayer(aPlayer1);
        addPlayer(aPlayer2);


    }


    public void nextPlayer(int direction) {
        currentPlayer = (currentPlayer + direction) % players.size();
    }


    public char getWinner() {
        return players.stream()
                .filter(player -> playerHands.get(player).isEmpty())
                .findFirst()
                .orElse((char) 0);
    }


    public void addPlayer(char player) {
        players.add(player);
        playerHands.put(player, new ArrayList<>());

    }

    public char getCurrentPlayer() {
        return players.get(currentPlayer);

    }

    public void isMyTurn(char player) {
        if (player != players.get(currentPlayer)) {
            throw new RuntimeException("Not your turn");
        }
    }

    public void drawCardS(char player, Card... aCard) {
        isMyTurn(player);
        List<Card> playerHand = playerHands.get(player);
        playerHand.addAll(Arrays.asList(aCard));


    }

    public void playCard(char player, Card aCard) {
        doesPlayerHaveCard(player, aCard);
        isMyTurn(player);
        canIPlayCard(aCard);
        nextPlayer(direction);
        playerHands.get(player).remove(aCard);
        topCard = aCard;
        lastPlayer = player;
        aCard.effect(this);
    }


    public void doesPlayerHaveCard(char player, Card card) {
        if (!playerHands.get(player).contains(card)) {
            throw new RuntimeException("Card not in hand");
        }
    }

    public void canIPlayCard(Card card) {
        card.canBePLayedOnTopOf(topCard);
    }

    public void shoutUno(char aPlayer) {
        if (lastPlayer == aPlayer) {
            oneCardLeft = true;
        } else {
            playerHands.get(lastPlayer).add(new NormalCard("Blue", 7));
            playerHands.get(lastPlayer).add(new NormalCard("Blue", 8));

        }
    }

    public void methodPlayCard(char b, Card aCard) {
        //todo
    }

    public void methodDrawCardS(char a, int numberCards) {
        //todo
    }
}
