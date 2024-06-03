package unoV1;

import java.util.ArrayList;
import java.util.List;

public class UnoGame {
    Card R2 = new Card("Red",2);
    Card B4 = new Card("Blue",4);
    Card Y8 = new Card("Yellow",8);
    Card R4 = new Card("Red",4);
    Card B6 = new Card("Blue",6);
    Card Y1 = new Card("Yellow",1);
    Card G2 = new Card("Green",2);
    Card G4 = new Card("Green",4);
    Card R1 = new Card("Red",1);
    Card R3 = new Card("Red",3);
    Card R9 = new Card("Red",9);
    Card Y7 = new Card("Yellow",7);
    public static List mazo = new ArrayList(R2,B4,Y8,R4,B6,Y1,G2,G4,R1,R3,R9,Y7);
    private List deck = new ArrayList();
    public static List<Object> discardPile = new ArrayList<>();

    public List<Card> player1Hand = new ArrayList<>();
    public List<Card> player2Hand = new ArrayList<>();
    public static Object topCard;
    public String direction;
    private int currentPlayer = 0;

    public UnoGame(List mazo,Character... aPlayer) {
        deck = mazo;
        if (aPlayer.length < 2) {
            throw new RuntimeException("At least two players are required to start the game.");
        }

        topCard = deck.remove(0);
        direction = "clockwise";


    }


    public List getDeck() {
        return deck;
    }

    public List getPlayers() {
        return players;
    }

    public void addPlayer(char player) {
        if (players.size() == 14) {
            throw new RuntimeException("Too many players");


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
        topCard = card;
    }
}
