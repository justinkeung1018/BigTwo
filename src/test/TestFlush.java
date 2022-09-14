import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFlush {
    TestCombination testCombination;

    List<Card> referenceFlush;
    List<Card> largerSuitFlush;
    List<Card> largerValueFlush;
    List<Card> largerValueSuitFlush;
    List<Card> validUnsortedFlush;
    List<Card> validIllegalRoyalFlush;
    List<Card> invalid;

    @Before
    public void setUp() {
        testCombination = new TestCombination();

        // Compare: reference
        referenceFlush = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.J, Suit.DIAMONDS)
        );

        // Compare: same value, larger suit
        largerSuitFlush = Arrays.asList(
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.K, Suit.SPADES)
        );

        // Compare: larger value, same suit
        largerValueFlush = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS)
        );

        // Compare: larger value, larger suit
        largerValueSuitFlush = Arrays.asList(
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES)
        );

        // Valid: unsorted
        validUnsortedFlush = Arrays.asList(
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS)
        );

        // Valid: illegal Royal Flush (QKA23)
        validIllegalRoyalFlush = Arrays.asList(
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS)
        );

        // Invalid: different suit
        invalid = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.K, Suit.DIAMONDS)
        );
    }

    @Test
    public void testIsValidFiveCards() {
        assertTrue("Check isValid", Combination.isValidFiveCards(referenceFlush));
        assertTrue("Check isValid", Combination.isValidFiveCards(validUnsortedFlush));
        assertTrue("Check isValid", Combination.isValidFiveCards(validIllegalRoyalFlush));
        assertFalse("Check isValid", Combination.isValidFiveCards(invalid));
    }

    @Test
    public void testCompareFlushes() {
        assertEquals("Same value, larger suit", 1, Combination.compareFiveCards(largerSuitFlush, referenceFlush));
        assertEquals("Larger value, same suit", 1, Combination.compareFiveCards(largerValueFlush, referenceFlush));
        assertEquals("Larger value, larger suit", 1, Combination.compareFiveCards(largerValueSuitFlush, referenceFlush));
        assertEquals("Reflexive", 0, Combination.compareFiveCards(referenceFlush, referenceFlush));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Straight vs Flush", -1, Combination.compareFiveCards(testCombination.straight(), referenceFlush));
        assertEquals("Full House vs Flush", 1, Combination.compareFiveCards(testCombination.fullHouse(), referenceFlush));
        assertEquals("Four of a kind vs Flush", 1, Combination.compareFiveCards(testCombination.fourOfAKind(), referenceFlush));
        assertEquals("Royal Flush vs Flush", 1, Combination.compareFiveCards(testCombination.royalFlush(), referenceFlush));
    }
}
