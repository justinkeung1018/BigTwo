import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourOfAKind extends FiveCards {

    public FourOfAKind(ArrayList<Card> selected) {
        super(4, selected);
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
        Collections.sort(curr.getSelected());

        // Third card (i.e. median) in sorted cards is the card value that appears four times
        Card middleSelectedCard = selected.get(2);
        Card middleCurrCard = currSelected.get(2);
        return middleSelectedCard.compareTo(middleCurrCard);
    }

    public boolean isValid() {
        HashMap<Value, Integer> count = countCards(selected);
        return count.size() == 2 && count.containsValue(4) && count.containsValue(1);
    }
}
