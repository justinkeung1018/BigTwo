import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private ArrayList<Card> selected;
    private int id;

    // Constructor
    public Hand(int id) {
        cards = new ArrayList<Card>();
        selected = new ArrayList<Card>();
        this.id = id;
    }

    // Getters and setters
    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getSelected() {
        return selected;
    }

    public void setSelected(ArrayList<Card> selected) {
        this.selected = selected;
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
