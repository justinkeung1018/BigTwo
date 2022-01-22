public enum Suit {
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuitString() {
        return suit;
    }

    public int getRank() {
        return ordinal();
    }
}
