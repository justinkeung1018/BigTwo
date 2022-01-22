import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FullHouseTester {

    Card[] cards;

    FullHouse refFullHouse;
    FullHouse largerFullHouse;
    FullHouse smallerFullHouse;

    Straight straight;
    Flush flush;
    FourOfAKind fourOfAKind;
    RoyalFlush royalFlush;

    FullHouse validUnsortedFullHouse;
    FullHouse invalidFourOfAKindFullHouse;
    FullHouse invalidDiffFullHouse;

    @Before
    public void setUp() {
        // Compare: reference
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> refCards = new ArrayList<Card>(Arrays.asList(cards));
        refFullHouse = new FullHouse(refCards);

        // Compare: larger
        cards = new Card[] {
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.K, Suit.SPADES),
                new Card(Value.K, Suit.CLUBS)
        };
        ArrayList<Card> largerCards = new ArrayList<Card>(Arrays.asList(cards));
        largerFullHouse = new FullHouse(largerCards);

        // Compare: smaller (three cards smaller, two cards larger)
        cards = new Card[] {
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS)
        };
        ArrayList<Card> smallerCards = new ArrayList<Card>(Arrays.asList(cards));
        smallerFullHouse = new FullHouse(smallerCards);

        // Compare: smaller (Straight)
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS)
        };
        ArrayList<Card> straightCards = new ArrayList<Card>(Arrays.asList(cards));
        straight = new Straight(straightCards);

        // Compare: smaller (Flush)
        cards = new Card[] {
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.DEUCE, Suit.SPADES)
        };
        ArrayList<Card> flushCards = new ArrayList<Card>(Arrays.asList(cards));
        flush = new Flush(flushCards);

        // Compare: larger (Four of a Kind)
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS)
        };
        ArrayList<Card> fourOfAKindCards = new ArrayList<Card>(Arrays.asList(cards));
        fourOfAKind = new FourOfAKind(fourOfAKindCards);

        // Compare: larger (Royal Flush)
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
        };
        ArrayList<Card> royalFlushCards = new ArrayList<Card>(Arrays.asList(cards));
        royalFlush = new RoyalFlush(royalFlushCards);

        // Valid: unsorted
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.ACE, Suit.SPADES)
        };
        ArrayList<Card> validUnsortedCards = new ArrayList<Card>(Arrays.asList(cards));
        validUnsortedFullHouse = new FullHouse(validUnsortedCards);

        // Invalid: Four of a Kind
        invalidFourOfAKindFullHouse = new FullHouse(fourOfAKindCards);

        // Invalid: three cards equal, two different cards
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.THREE, Suit.DIAMONDS)
        };
        ArrayList<Card> invalidDiffCards = new ArrayList<Card>(Arrays.asList(cards));
        invalidDiffFullHouse = new FullHouse(invalidDiffCards);
    }

    @Test
    public void testIsValid() {
        assertEquals("Check isValid", true, refFullHouse.isValid());
        assertEquals("Check isValid", true, validUnsortedFullHouse.isValid());
        assertEquals("Check isValid", false, invalidFourOfAKindFullHouse.isValid());
        assertEquals("Check isValid", false, invalidDiffFullHouse.isValid());
        assertEquals("Check isValid", false, invalidFourOfAKindFullHouse.isValid());
    }

    @Test
    public void testCompareTo() {
        assertEquals("Larger", 1, largerFullHouse.compareTo(refFullHouse));
        assertEquals("Smaller", -1, smallerFullHouse.compareTo(refFullHouse));
        assertEquals("Smaller: Straight", -1, straight.compareTo(refFullHouse));
        assertEquals("Smaller: Flush", -1, flush.compareTo(refFullHouse));
        assertEquals("Larger: Four of a Kind", 1, fourOfAKind.compareTo(refFullHouse));
        assertEquals("Larger: Royal Flush", 1, royalFlush.compareTo(refFullHouse));
    }
}
