import java.util.Arrays;
import java.util.List;

public class TestCombination {
    private final List<Card> straight = Arrays.asList(
            new Card(Value.ACE, Suit.DIAMONDS),
            new Card(Value.DEUCE, Suit.SPADES),
            new Card(Value.THREE, Suit.CLUBS),
            new Card(Value.FOUR, Suit.SPADES),
            new Card(Value.FIVE, Suit.CLUBS)
    );

    private final List<Card> flush = Arrays.asList(
            new Card(Value.THREE, Suit.SPADES),
            new Card(Value.FIVE, Suit.SPADES),
            new Card(Value.SIX, Suit.SPADES),
            new Card(Value.SEVEN, Suit.SPADES),
            new Card(Value.DEUCE, Suit.SPADES)
    );

    private final List<Card> fullHouse = Arrays.asList(
            new Card(Value.ACE, Suit.DIAMONDS),
            new Card(Value.ACE, Suit.SPADES),
            new Card(Value.ACE, Suit.CLUBS),
            new Card(Value.K, Suit.HEARTS),
            new Card(Value.K, Suit.DIAMONDS)
    );

    private final List<Card> fourOfAKind = Arrays.asList(
            new Card(Value.THREE, Suit.DIAMONDS),
            new Card(Value.THREE, Suit.SPADES),
            new Card(Value.THREE, Suit.CLUBS),
            new Card(Value.THREE, Suit.SPADES),
            new Card(Value.DEUCE, Suit.CLUBS)
    );

    private final List<Card> royalFlush = Arrays.asList(
            new Card(Value.THREE, Suit.DIAMONDS),
            new Card(Value.FOUR, Suit.DIAMONDS),
            new Card(Value.FIVE, Suit.DIAMONDS),
            new Card(Value.SIX, Suit.DIAMONDS),
            new Card(Value.SEVEN, Suit.DIAMONDS)
    );

    public List<Card> straight() {
        return straight;
    }

    public List<Card> flush() {
        return flush;
    }

    public List<Card> fullHouse() {
        return fullHouse;
    }

    public List<Card> fourOfAKind() {
        return fourOfAKind;
    }

    public List<Card> royalFlush() {
        return royalFlush;
    }
}
