import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RoyalFlushTester {

    Card[] cards;

    RoyalFlush refRoyalFlush;
    RoyalFlush largerSuitRoyalFlush;
    RoyalFlush largerValueRoyalFlush;
    RoyalFlush largerValueSuitRoyalFlush;

    Straight straight;
    Flush flush;
    FullHouse fullHouse;
    FourOfAKind fourOfAKind;

    RoyalFlush validUnsortedRoyalFlush;
    RoyalFlush invalidRandomRoyalFlush;
    RoyalFlush invalidStraightRoyalFlush;
    RoyalFlush invalidFlushRoyalFlush;

    @Before
    public void setUp() {
        // Compare: reference
        cards = new Card[] {
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS)
        };
        ArrayList<Card> refCards = new ArrayList<Card>(Arrays.asList(cards));
        refRoyalFlush = new RoyalFlush(refCards);

        // Compare: same value, larger suit
        cards = new Card[] {
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.J, Suit.HEARTS),
                new Card(Value.Q, Suit.HEARTS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS)
        };
        ArrayList<Card> largerSuitCards = new ArrayList<Card>(Arrays.asList(cards));
        largerSuitRoyalFlush = new RoyalFlush(largerSuitCards);

        // Compare: larger value, same suit
        cards = new Card[] {
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.CLUBS)
        };
        ArrayList<Card> largerValueCards = new ArrayList<Card>(Arrays.asList(cards));
        largerValueRoyalFlush = new RoyalFlush(largerValueCards);

        // Compare: larger value, larger suit
        cards = new Card[] {
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES)
        };
        ArrayList<Card> largerValueSuitCards = new ArrayList<Card>(Arrays.asList(cards));
        largerValueSuitRoyalFlush = new RoyalFlush(largerValueSuitCards);

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

        // Compare: smaller (Full House)
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> fullHouseCards = new ArrayList<Card>(Arrays.asList(cards));
        fullHouse = new FullHouse(fullHouseCards);

        // Compare: smaller (Four of a Kind)
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS)
        };
        ArrayList<Card> fourOfAKindCards = new ArrayList<Card>(Arrays.asList(cards));
        fourOfAKind = new FourOfAKind(fourOfAKindCards);

        // Valid: unsorted
        cards = new Card[] {
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS)
        };
        ArrayList<Card> validUnsortedCards = new ArrayList<Card>(Arrays.asList(cards));
        validUnsortedRoyalFlush = new RoyalFlush(validUnsortedCards);

        // Invalid: random
        cards = new Card[] {
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS)
        };
        ArrayList<Card> invalidRandomCards = new ArrayList<Card>(Arrays.asList(cards));
        invalidRandomRoyalFlush = new RoyalFlush(invalidRandomCards);

        // Invalid: Straight
        invalidStraightRoyalFlush = new RoyalFlush(straightCards);

        // Invalid: Flush
        invalidFlushRoyalFlush = new RoyalFlush(flushCards);
    }

    @Test
    public void testIsValid() {
        assertEquals("Check isValid", true, refRoyalFlush.isValid());
        assertEquals("Check isValid", true, validUnsortedRoyalFlush.isValid());
        assertEquals("Check isValid", false, invalidRandomRoyalFlush.isValid());
        assertEquals("Check isValid", false, invalidStraightRoyalFlush.isValid());
        assertEquals("Check isValid", false, invalidFlushRoyalFlush.isValid());
    }

    @Test
    public void testCompareTo() {
        assertEquals("Same value, larger suit", 1, largerSuitRoyalFlush.compareTo(refRoyalFlush));
        assertEquals("Larger value, same suit", 1, largerValueRoyalFlush.compareTo(refRoyalFlush));
        assertEquals("Larger value, larger suit", 1, largerValueSuitRoyalFlush.compareTo(refRoyalFlush));
        assertEquals("Smaller: Straight", -1, straight.compareTo(refRoyalFlush));
        assertEquals("Smaller: Flush", -1, flush.compareTo(refRoyalFlush));
        assertEquals("Smaller: Full House", -1, fullHouse.compareTo(refRoyalFlush));
        assertEquals("Smaller: Four of a kind", -1, fourOfAKind.compareTo(refRoyalFlush));
    }
}
