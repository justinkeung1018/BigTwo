import java.util.ArrayList;

public class Player {
    private final String name;
    private final int id;
    private ArrayList<Card> cards;
    private ArrayList<Card> selected;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.cards = new ArrayList<>();
        this.selected = new ArrayList<>();
    }
}
