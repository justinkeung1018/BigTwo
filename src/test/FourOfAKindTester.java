import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FourOfAKindTester {

    Card[] cards;

    FourOfAKind refFourOfAKind;
    FourOfAKind largerFourOfAKind;
    FourOfAKind smallerFourOfAKind;

    Straight straight;
    Flush flush;
    FullHouse fullHouse;
    RoyalFlush royalFlush;

    FourOfAKind validUnsortedFourOfAKind;
    FourOfAKind validReversedFourOfAKind;
    FourOfAKind invalidRandomFourOfAKind;
    FourOfAKind invalidFullHouseFourOfAKind;

    @Before
    public void setUp() {
        // Compare: reference
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> refCards = new ArrayList<Card>(Arrays.asList(cards));
        refFourOfAKind = new FourOfAKind(refCards);

        // Compare: larger
        cards = new Card[] {
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.THREE, Suit.DIAMONDS)
        };
        ArrayList<Card> largerCards = new ArrayList<Card>(Arrays.asList(cards));
        largerFourOfAKind = new FourOfAKind(largerCards);

        // Compare: smaller
        cards = new Card[] {
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.J, Suit.HEARTS),
                new Card(Value.DEUCE, Suit.DIAMONDS)
        };
        ArrayList<Card> smallerCards = new ArrayList<Card>(Arrays.asList(cards));
        smallerFourOfAKind = new FourOfAKind(smallerCards);

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
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        };
        ArrayList<Card> validUnsortedCards = new ArrayList<Card>(Arrays.asList(cards));
        validUnsortedFourOfAKind = new FourOfAKind(validUnsortedCards);

        // Valid: reversed
        cards = new Card[] {
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        };
        ArrayList<Card> validReversedCards = new ArrayList<Card>(Arrays.asList(cards));
        validReversedFourOfAKind = new FourOfAKind(validReversedCards);

        // Invalid: random
        cards = new Card[] {
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        };
        ArrayList<Card> invalidRandomCards = new ArrayList<Card>(Arrays.asList(cards));
        invalidRandomFourOfAKind = new FourOfAKind(invalidRandomCards);

        // Invalid: Full House
        invalidFullHouseFourOfAKind = new FourOfAKind(fullHouseCards);
    }

    @Test
    public void testIsValid() {
        assertEquals("Check isValid", true, refFourOfAKind.isValid());
        assertEquals("Check isValid", true, validUnsortedFourOfAKind.isValid());
        assertEquals("Check isValid", true, validReversedFourOfAKind.isValid());
        assertEquals("Check isValid", false, invalidRandomFourOfAKind.isValid());
        assertEquals("Check isValid", false, invalidFullHouseFourOfAKind.isValid());
    }

    @Test
    public void testCompareTo() {
        assertEquals("Larger", 1, largerFourOfAKind.compareTo(refFourOfAKind));
        assertEquals("Smaller", -1, smallerFourOfAKind.compareTo(refFourOfAKind));
        assertEquals("Smaller: Straight", -1, straight.compareTo(refFourOfAKind));
        assertEquals("Smaller: Flush", -1, flush.compareTo(refFourOfAKind));
        assertEquals("Smaller: Full House", -1, fullHouse.compareTo(refFourOfAKind));
        assertEquals("Larger: Royal Flush", 1, royalFlush.compareTo(refFourOfAKind));
    }
}
