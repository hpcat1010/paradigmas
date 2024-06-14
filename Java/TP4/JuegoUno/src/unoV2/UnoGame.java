package unoV2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnoGame {
    public static final String CardNotInHand = "Card not in hand";
    public static final String CantPlayThatCard = "Can't play that card";
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
//      currentPlayer = (currentPlayer + direction) % players.size();
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
        nextPlayer();
        aCard.effect(this);
    }


}
