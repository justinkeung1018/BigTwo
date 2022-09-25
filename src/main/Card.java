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

    /**
     * Returns the value of the card.
     * @return The value of the card.
     */
    public Value value() {
        return value;
    }

    /**
     * Returns the suit of the card.
     * @return The suit of the card.
     */
    public Suit suit() {
        return suit;
    }

    /**
     * Returns the string representation of the card.
     * The string is in the format of "[value] of [suit]".
     * @return The string representation of the card.
     */
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

    @Override
    public boolean equals(Object card) {
        if (card == this) return true;
        if (card == null) return false;
        if (card.getClass() != this.getClass()) return false;
        Card that = (Card) card;
        return this.compareTo(that) == 0;
    }
}
