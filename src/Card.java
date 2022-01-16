public class Card {
    private final Value value;
    private final Suit suit;

    enum Value {
        /* Ace is 14 instead of 1 and deuce is 15 instead of 2 to preserve:
        * 1. Hierarchy (2 > A > 3 > 4...)
        * 2. Intuition for the remaining cards (3, 4, 5...)
        */
        ACE(14, "Ace"),
        DEUCE(15, "Deuce"),
        THREE(3, "3"),
        FOUR(4, "4"),
        FIVE(5, "5"),
        SIX(6, "6"),
        SEVEN(7, "7"),
        EIGHT(8, "8"),
        NINE(9, "9"),
        TEN(10, "10"),
        J(11, "Jack"),
        Q(12, "Queen"),
        K(13, "King");

        private final int value;
        private final String valueString;

        Value(int value, String valueString) {
            this.value = value;
            this.valueString = valueString;
        }

        public int getValue() {
            return this.value;
        }

        public String getValueString() {
            return this.valueString;
        }
    }

    enum Suit {
        HEARTS("Hearts"),
        SPADES("Spades"),
        DIAMONDS("Diamonds"),
        CLUBS("Clubs");

        private final String suit;
        Suit(String suit) {
            this.suit = suit;
        }

        public String getSuit() {
            return suit;
        }
    }

    // Constructor
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    // Public methods
    public Value getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        return value.getValueString() + " of " + suit.getSuit();
    }
}
