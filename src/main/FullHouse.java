import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FullHouse extends FiveCards {

    public FullHouse(ArrayList<Card> selected) {
        super(3, selected);
    }

    @Override
    public int compareTo(FiveCards curr) {
        if (rank > curr.rank) {
            return 1;
        }
        if (rank < curr.rank) {
            return -1;
        }
        ArrayList<Card> currSelected = curr.getSelected();
        Collections.sort(selected);
        Collections.sort(currSelected);

        // Third card (i.e. median) in sorted cards is the card value that appears three times
        Card middleSelectedCard = selected.get(2);
        Card middleCurrCard = currSelected.get(2);
        return middleSelectedCard.compareTo(middleCurrCard);
    }

    // Assume trySelected is sorted
    public boolean isValid() {
        HashMap<Value, Integer> count = new HashMap<Value, Integer>();
        for (Card card : selected) {
            Value value = card.getValue();
            if (count.containsKey(value)) {
                int valueCount = count.get(value);
                valueCount++;
                count.put(value, valueCount);
            } else {
                count.put(value, 1);
            }
        }
        return count.size() == 2 && count.containsValue(2) && count.containsValue(3);
    }
}
