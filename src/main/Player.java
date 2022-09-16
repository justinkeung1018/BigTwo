import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final int id;
    private List<Card> cards;
    private List<Card> selected;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.cards = new ArrayList<>();
        this.selected = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public int id() {
        return id;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> cards() {
        return cards;
    }

    public List<Card> selected() {
        return selected;
    }

    /**
     * Plays the selected cards.
     */
    public void play() {
        cards.removeAll(selected);
        selected.clear();
    }

    /**
     * Selects a card if the card has not been selected, deselects otherwise.
     * @param card The card to be selected or deselected.
     */
    public void toggleSelection(Card card) {
        if (!cards.contains(card)) {
            throw new IllegalArgumentException("Player does not have the card.");
        }
        if (selected.contains(card)) {
            selected.remove(card);
        } else {
            selected.add(card);
        }
    }
}
