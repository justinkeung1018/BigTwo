public enum Value {
    ACE("Ace", 12),
    DEUCE("Deuce", 13),
    THREE("3", 1),
    FOUR("4", 2),
    FIVE("5", 3),
    SIX("6", 4),
    SEVEN("7", 5),
    EIGHT("8", 6),
    NINE("9", 7),
    TEN("10", 8),
    J("Jack", 9),
    Q("Queen", 10),
    K("King", 11);

    private final String valueName;
    private final int rank;

    Value(String valueName, int rank) {
        this.valueName = valueName;
        this.rank = rank;
    }

    public String valueName() {
        return valueName;
    }

    public int rank() {
        return rank;
    }
}
