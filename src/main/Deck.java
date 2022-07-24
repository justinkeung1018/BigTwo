import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    // Constructor
    public Deck() {
        deck = new ArrayList<Card>();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(value, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public ArrayList<Card> distribute() {
        ArrayList<Card> hand = new ArrayList<Card>(deck.subList(0, 13));
        deck.subList(0, 13).clear();
        return hand;
    }

    // Currently only useful for testing out lastPlayedCards in Game class
    public ArrayList<Card> getDeck() {
        return deck;
    }
}
