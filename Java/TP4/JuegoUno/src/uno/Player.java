package uno;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List hand = new ArrayList();

    public List getHand() {
        return hand;
    }

    public void drawCard(Object card) {
        hand.add(card);
    }

    public void playPlayerCard(Object card) {
        hand.remove(card);
        UnoGame.discardPile.add(card);
        if (getHand().isEmpty()) {
            throw new RuntimeException("Player wins!");
        }
    }
}
