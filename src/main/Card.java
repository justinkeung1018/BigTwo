public class Card implements Comparable<Card> {
    private final Value value;
    private final Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(Card card) {
        this.value = card.value;
        this.suit = card.suit;
    }

    public Value value() {
        return value;
    }

    public Suit suit() {
        return suit;
    }

    public String toString() {
        return value.valueName() + " of " + suit.suitName();
    }

    @Override
    public int compareTo(Card card) {
        int thisValueRank = value.rank();
        int thisSuitRank = suit.rank();
        int otherValueRank = card.value().rank();
        int otherSuitRank = card.suit().rank();
        if (thisValueRank != otherValueRank) {
            return Integer.compare(thisValueRank, otherValueRank);
        }
        return Integer.compare(thisSuitRank, otherSuitRank);
    }
}
