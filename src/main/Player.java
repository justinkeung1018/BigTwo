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

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
