import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestRoyalFlush {
    TestCombination testCombination;

    List<Card> referenceRoyalFlush;
    List<Card> largerSuitRoyalFlush;
    List<Card> largerValueRoyalFlush;
    List<Card> largerValueSuitRoyalFlush;
    List<Card> unsortedRoyalFlush;
    List<Card> invalid;

    @Before
    public void setUp() {
        testCombination = new TestCombination();

        referenceRoyalFlush = Arrays.asList(
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS),
                new Card(Value.ACE, Suit.CLUBS)
        );

        largerSuitRoyalFlush = Arrays.asList(
                new Card(Value.TEN, Suit.HEARTS),
                new Card(Value.J, Suit.HEARTS),
                new Card(Value.Q, Suit.HEARTS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.ACE, Suit.HEARTS)
        );

        largerValueRoyalFlush = Arrays.asList(
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FIVE, Suit.CLUBS)
        );

        largerValueSuitRoyalFlush = Arrays.asList(
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES)
        );

        unsortedRoyalFlush = Arrays.asList(
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS)
        );

        invalid = Arrays.asList(
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.TEN, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.Q, Suit.CLUBS),
                new Card(Value.K, Suit.CLUBS)
        );
    }

    @Test
    public void testIsValidFiveCards() {
        assertTrue("Check isValid", Combination.isValidFiveCards(referenceRoyalFlush));
        assertTrue("Check isValid", Combination.isValidFiveCards(unsortedRoyalFlush));
        assertFalse("Check isValid", Combination.isValidFiveCards(invalid));
    }

    @Test
    public void testCompareRoyalFlushes() {
        assertEquals("Same value, larger suit", 1, Combination.compareFiveCards(largerSuitRoyalFlush, referenceRoyalFlush));
        assertEquals("Larger value, same suit", 1, Combination.compareFiveCards(largerValueRoyalFlush, referenceRoyalFlush));
        assertEquals("Larger value, larger suit", 1, Combination.compareFiveCards(largerValueSuitRoyalFlush, referenceRoyalFlush));
        assertEquals("Reflexive", 0, Combination.compareFiveCards(referenceRoyalFlush, referenceRoyalFlush));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Straight vs Royal Flush", -1, Combination.compareFiveCards(testCombination.straight(), referenceRoyalFlush));
        assertEquals("Flush vs Royal Flush", -1, Combination.compareFiveCards(testCombination.flush(), referenceRoyalFlush));
        assertEquals("Full House vs Royal Flush", -1, Combination.compareFiveCards(testCombination.fullHouse(), referenceRoyalFlush));
        assertEquals("Four of a kind vs Royal Flush", -1, Combination.compareFiveCards(testCombination.fourOfAKind(), referenceRoyalFlush));
    }
}
