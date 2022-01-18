import javax.swing.*;
import java.awt.*;

public class Card {
    private final Value value;
    private final Suit suit;
    private final ImageIcon image;
    private final int x;
    private final int y;

    // Constructor
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
        this.x = 100;
        this.y = (int) (100 * 1.452);

        ImageIcon tempImage = new ImageIcon("src/playing_cards/" + value.getValueString().toLowerCase() + "_of_" + suit.getSuitString().toLowerCase() + ".png");
        Image resizedImage = tempImage.getImage().getScaledInstance(this.x, this.y, java.awt.Image.SCALE_SMOOTH);
        this.image = new ImageIcon(resizedImage);
    }

    // Public methods
    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public ImageIcon getImageIcon() {
        System.out.println("src/playing_cards/" + value.getValueString().toLowerCase() + "_of_" + suit.getSuitString().toLowerCase() + ".png");
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return value.getValueString() + " of " + suit.getSuitString();
    }
}
