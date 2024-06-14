package unoV1;

import java.util.*;

public class UnoGame {
    public Map<Character, List<Card>> playerHands = new HashMap<>();
    public List<Character> players = new ArrayList<>();
    public Card topCard = new NormalCard("Red", 2);
    public char lastPlayer;
    public GameStatus estado = new Player1Turn(this);
    public boolean oneCardLeft = false;

    public UnoGame(Character aPlayer1, Character aPlayer2) {
        addPlayer(aPlayer1);
        addPlayer(aPlayer2);


    }


    public void nextPlayer() {
//        currentPlayer = (currentPlayer + direction) % players.size();
        estado = estado.nextTurn();
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
//        return players.get(currentPlayer);
        return estado.getCurrentPlayer();

    }


    public void drawCard(char player, Card aCard) {
        estado.drawCard(player, aCard);

    }

    public void playCard(char player, Card aCard) {
        doesPlayerHaveCard(player, aCard);
        canIPlayCard(aCard);
        estado.playCard(player, aCard);

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

    public void methodPlayCard(char aPlayer, Card aCard) {
        topCard = aCard;
        playerHands.get(aPlayer).remove(aCard);
        lastPlayer = aPlayer;
        nextPlayer();
        aCard.effect(this);

    }


}
