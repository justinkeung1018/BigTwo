import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private List<Card> cards;
    private List<Card> selected;

    public Player(int id) {
        this.id = id;
        this.cards = new ArrayList<>();
        this.selected = new ArrayList<>();
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
     * Sets the selected state of a card.
     * @param card The card to be selected or deselected.
     * @param isSelected Whether the card should be selected.
     */
    public void setSelection(Card card, boolean isSelected) {
        if (!cards.contains(card)) {
            throw new IllegalArgumentException("Player does not have the card.");
        }
        if (isSelected && !selected.contains(card)) {
            selected.add(card);
        } else if (!isSelected) {
            selected.remove(card);
        }
    }
}
