package uno;

public class Card {
    private String color;
    private String value;

    public Card(String aColor, String aValue) {
        color = aColor;
        value = aValue;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }


//    public void initializeDeck() {
//        deck.clear();
//        String[] colors = {"Red", "Green", "Yellow", "Blue"};
//        String[] specialCards = {"Draw Two", "Skip", "Reverse"};
//
//        // Add number cards
//        for (String color : colors) {
//            deck.add(new Card(color, "0")); // Add one 0 card for each color
//            for (int i = 1; i <= 9; i++) {
//                deck.add(new Card(color, Integer.toString(i))); // Add two 1-9 cards for each color
//                deck.add(new Card(color, Integer.toString(i)));
//            }
//            for (String specialCard : specialCards) {
//                deck.add(new Card(color, specialCard)); // Add two special cards for each color
//                deck.add(new Card(color, specialCard));
//            }
//        }
//
//        // Add wild cards
//        for (int i = 0; i < 4; i++) {
//            deck.add(new Card("Wild", "Color"));
//        }
//    }
    //por si lo usamos

}
