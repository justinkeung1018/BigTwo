import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class FiveCards implements Comparable<FiveCards> {
    protected int rank;
    protected ArrayList<Card> selected;
    public FiveCards(int rank, ArrayList<Card> selected) {
        this.rank = rank;
        this.selected = selected;
    }

    // Helper method to check if all cards have the same suit, useful for Flush and Royal Flush
    // Assume trySelected is sorted
    protected static boolean sameSuit(ArrayList<Card> trySelected) {
        if (trySelected.size() != 5) {
            return false;
        }
        Suit suit = null;
        for (Card card : trySelected) {
            if (suit == null) {
                suit = card.getSuit();
            } else {
                if  (card.getSuit() != suit) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method to check if the cards have legal consecutive values, useful for Straight and Royal Flush
    // Assume trySelected is sorted
    protected static boolean consecutiveValues(ArrayList<Card> trySelected) {
        Collections.sort(trySelected);
        if (trySelected.size() != 5) {
            return false;
        }
        int currValue = 0;
        boolean containsJQK = false;
        for (Card card : trySelected) {
            if (currValue == 0) {
                currValue = card.getValueInt();
            } else {
                int value = card.getValueInt();
                if (value % 13 != (currValue + 1) % 13) {
                    return false;
                }
                currValue++;
            }

            // JQKA2, QKA23, and KA234 are illegal
            Value value = card.getValue();
            if (value == Value.J || value == Value.Q || value == Value.K) {
                containsJQK = true;
            }

            if (containsJQK && value == Value.DEUCE) {
                return false;
            }
        }
        return true;
    }

    // Helper method to count number of cards with different values, useful for Full House and Four of a Kind
    // Assume trySelected is sorted
    protected static HashMap<Value, Integer> countCards(ArrayList<Card> trySelected) {
        HashMap<Value, Integer> count = new HashMap<Value, Integer>();
        for (Card card : trySelected) {
            Value value = card.getValue();
            if (count.containsKey(value)) {
                int valueCount = count.get(value);
                valueCount++;
                count.put(value, valueCount);
            } else {
                count.put(value, 1);
            }
        }
        return count;
    }

    public String toString() {
        return selected.toString();
    }

    // Getters and setters
    public int getRank() {
        return rank;
    }

    public ArrayList<Card> getSelected() {
        return selected;
    }

    public abstract boolean isValid();
}
