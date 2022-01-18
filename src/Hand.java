import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private int id;

    // Constructor
    public Hand(int id) {
        cards = new ArrayList<Card>();
        this.id = id;
    }

    // Getters and setters
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getID() {
        return id;
    }

    public String toString() {
        return "Hand " + id;
    }

    /* Methods to implement:
    * Play card(s)
     */

}
