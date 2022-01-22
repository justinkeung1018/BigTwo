import java.util.ArrayList;
import java.util.Collections;

public class RoyalFlush extends FiveCards {
    public RoyalFlush(ArrayList<Card> selected) {
        super(5, selected);
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
        Collections.sort(currSelected); // Necessary? Will curr already be sorted?
        Card largestSelectedCard = selected.get(selected.size() - 1);
        Card largestCurrCard = currSelected.get(currSelected.size() - 1);
        return largestSelectedCard.compareTo(largestCurrCard);
    }

    public boolean isValid() {
        return consecutiveValues(selected) && sameSuit(selected);
    }
}
