import java.util.ArrayList;
import java.util.Collections;

public class Game {
    Hand h1 = new Hand(1);
    Hand h2 = new Hand(2);
    Hand h3 = new Hand(3);
    Hand h4 = new Hand(4);
    Hand[] hands = {h1, h2, h3, h4};
    Deck deck;
    ArrayList<Card> curr;
    FiveCards currObject;

    public Game() {
        deck = new Deck();

        // Tester for curr
        curr = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
            curr.add(deck.getDeck().get(i));
        }

        deck.shuffle();
        for (Hand hand : hands) {
            hand.setCards(deck.distribute());
            for (Card card : hand.getCards()) {
            }
        }
        draw(h1);

    }

    public void draw(Hand hand) {
        Frame frame = new Frame(hand);
        frame.drawHeader(hand);
        frame.drawCurr(curr);
        frame.drawHand(hand);
        frame.drawButton();
        frame.changeFont(frame.mainPanel);
        frame.setVisible(true);
    }

    public boolean isValid(ArrayList<Card> curr, ArrayList<Card> selected) {

        if (curr.size() == selected.size() || curr.size() == 0) {
            if (curr.size() == 1 || curr.size() == 3) {
                Card selectedCard = selected.get(0);
                Card currCard = curr.get(0);
                return selectedCard.compareTo(currCard) > 0;
            }

            if (curr.size() == 2) {
                Collections.sort(selected);
                Collections.sort(curr);
                Card largestSelectedCard = selected.get(curr.size() - 1);
                Card largestCurrCard = curr.get(curr.size() - 1);
                return largestSelectedCard.compareTo(largestCurrCard) > 0;
            }

            if (selected.size() == 4) {
                return false;
            }

            if (selected.size() == 5) {
                FiveCards[] combinations = {new RoyalFlush(selected), new FourOfAKind(selected), new FullHouse(selected), new Flush(selected), new Straight(selected)};
                for (FiveCards combination : combinations) {
                    if (combination.isValid()) {
                        // TODO: turn curr into the appropriate FiveCards object and implement compareTo
                        return combination.compareTo(currObject) > 0;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Game();
    }


    /* Methods to implement
    * Calls out the person with 3 of Diamonds to play first
    * Checks if play is valid
    * Determines if game is over (whoever's hand is empty)
     */
}
