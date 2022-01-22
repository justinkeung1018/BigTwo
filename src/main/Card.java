import javax.swing.*;
import java.awt.*;

public class Card implements Comparable<Card> {
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
    
    public String getValueString() {
        return value.getValueString();
    }

    public int getValueInt() {
        return value.getValue();
    }

    public Suit getSuit() {
        return suit;
    }

    public int getSuitRank() {
        return suit.getRank();
    }

    public ImageIcon getImageIcon() {
        System.out.println("src/playing_cards/" + value.getValueString().toLowerCase() + "_of_" + suit.getSuitString().toLowerCase() + ".png");
        return image;
    }

    public String toString() {
        return value.getValueString() + " of " + suit.getSuitString();
    }

    @Override
    // Note that since all cards are unique in the deck, returning 0 implies an error
    public int compareTo(Card card) {
        int thisValue = value.ordinal();
        int thisSuitRank = suit.getRank();
        int otherValue = card.getValue().ordinal();
        int otherSuitRank = card.getSuitRank();
        if (thisValue > otherValue) {
            return 1;
        }
        if (thisValue == otherValue) {
            if (thisSuitRank > otherSuitRank) {
                return 1;
            }
            if (thisSuitRank < otherSuitRank) {
                return -1;
            }
            else {
                return 0;
            }
        }
        else {
            return -1;
        }
    }
}
