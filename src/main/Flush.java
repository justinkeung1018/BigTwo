import java.util.ArrayList;
import java.util.Collections;

public class Flush extends FiveCards {

    public Flush(ArrayList<Card> selected) {
        super(2, selected);
    }

    @Override
    public int compareTo(FiveCards curr) {
        if (rank > curr.rank) {
            return 1;
        }
        if (rank < curr.rank) {
            return -1;
        }
        Collections.sort(selected);
        Collections.sort(curr.getSelected()); // Necessary? Will curr already be sorted?
        Card largestSelectedCard = selected.get(selected.size() - 1);
        Card largestCurrCard = curr.getSelected().get(curr.getSelected().size() - 1);
        return largestSelectedCard.compareTo(largestCurrCard);
    }

    public boolean isValid() {
        return sameSuit(selected) && !consecutiveValues(selected);
    }
}
