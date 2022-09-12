public enum Suit {
    DIAMONDS("Diamonds", 1),
    CLUBS("Clubs", 2),
    HEARTS("Hearts", 3),
    SPADES("Spades", 4);

    private final String suitName;
    private final int rank;

    Suit(String suitName, int rank) {
        this.suitName = suitName;
        this.rank = rank;
    }

    public String suitName() {
        return suitName;
    }

    public int rank() {
        return rank;
    }
}
