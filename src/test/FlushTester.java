import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FlushTester {

    Card[] cards;

    Flush refFlush;
    Flush largerSuitFlush;
    Flush largerValueFlush;
    Flush largerValueSuitFlush;

    Flush validUnsortedFlush;
    Flush validIllegalRoyalFlush;
    Flush invalidFlush1;
    Flush invalidFlush2;
    Flush invalidFlush3;

    @Before
    public void setUp() {
        // Compare: reference
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.J, Suit.DIAMONDS)
        };
        ArrayList<Card> refCards = new ArrayList<Card>(Arrays.asList(cards));
        refFlush = new Flush(refCards);

        // Compare: same value, larger suit
        cards = new Card[] {
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.K, Suit.SPADES)
        };
        ArrayList<Card> largerSuitCards = new ArrayList<Card>(Arrays.asList(cards));
        largerSuitFlush = new Flush(largerSuitCards);

        // Compare: larger value, same suit
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> largerValueCards = new ArrayList<Card>(Arrays.asList(cards));
        largerValueFlush = new Flush(largerValueCards);

        // Compare: larger value, larger suit
        cards = new Card[] {
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES)
        };
        ArrayList<Card> largerValueSuitCards = new ArrayList<Card>(Arrays.asList(cards));
        largerValueSuitFlush = new Flush(largerValueSuitCards);

        // Valid: unsorted
        cards = new Card[] {
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS)
        };
        ArrayList<Card> validUnsortedCards = new ArrayList<Card>(Arrays.asList(cards));
        validUnsortedFlush = new Flush(validUnsortedCards);

        // Valid: illegal Royal Flush (QKA23)
        cards = new Card[] {
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS)
        };
        ArrayList<Card> validIllegalRoyalCards = new ArrayList<Card>(Arrays.asList(cards));
        validIllegalRoyalFlush = new Flush(validIllegalRoyalCards);

        // Invalid: different suit
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> invalidCards1 = new ArrayList<Card>(Arrays.asList(cards));
        invalidFlush1 = new Flush(invalidCards1);

        // Invalid: Royal Flush
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        };
        ArrayList<Card> invalidCards2 = new ArrayList<Card>(Arrays.asList(cards));
        invalidFlush2 = new Flush(invalidCards2);

        // Invalid: four cards only
        cards = new Card[] {
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
        };
        ArrayList<Card> invalidCards3 = new ArrayList<Card>(Arrays.asList(cards));
        invalidFlush3 = new Flush(invalidCards3);
    }

    @Test
    public void testIsValid() {
        assertEquals("Check isValid", true, refFlush.isValid());
        assertEquals("Check isValid", true, validUnsortedFlush.isValid());
        assertEquals("Check isValid", true, validIllegalRoyalFlush.isValid());
        assertEquals("Check isValid", false, invalidFlush1.isValid());
        assertEquals("Check isValid", false, invalidFlush2.isValid());
        assertEquals("Check isValid", false, invalidFlush3.isValid());
    }

    @Test
    public void testCompareTo() {
        assertEquals("Same value, larger suit", 1, largerSuitFlush.compareTo(refFlush));
        assertEquals("Larger value, same suit", 1, largerValueFlush.compareTo(refFlush));
        assertEquals("Larger value, larger suit", 1, largerValueSuitFlush.compareTo(refFlush));
    }
}
