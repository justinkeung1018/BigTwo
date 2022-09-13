public class Card implements Comparable<Card> {
    private final Value value;
    private final Suit suit;
//    private final ImageIcon image;
//    private final int x;
//    private final int y;

    // Constructor
    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
//        this.x = 100; // Width of the card
//        this.y = (int) (100 * 1.452); // Height of the card
//
//        ImageIcon tempImage = new ImageIcon("src/playing_cards/" + value.getValueString().toLowerCase() + "_of_" + suit.getSuitString().toLowerCase() + ".png");
//        Image resizedImage = tempImage.getImage().getScaledInstance(this.x, this.y, java.awt.Image.SCALE_SMOOTH);
//        this.image = new ImageIcon(resizedImage);
    }

    // Public methods
    public Value value() {
        return value;
    }

    public Suit suit() {
        return suit;
    }

//    public ImageIcon getImageIcon() {
////        System.out.println("src/playing_cards/" + value.getValueString().toLowerCase() + "_of_" + suit.getSuitString().toLowerCase() + ".png");
//        return image;
//    }

//    public String toString() {
//        return value.getValueString() + " of " + suit.getSuitString();
//    }

    @Override
    // Note that since all cards are unique in the deck, returning 0 implies an error
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
