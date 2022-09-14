import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFourOfAKind {
    TestCombination testCombination;

    List<Card> referenceFourOfAKind;
    List<Card> largerFourOfAKind;
    List<Card> smallerFourOfAKind;
    List<Card> validUnsortedFourOfAKind;
    List<Card> validReversedFourOfAKind;
    List<Card> invalid;

    @Before
    public void setUp() {
        testCombination = new TestCombination();

        // Compare: reference
        referenceFourOfAKind = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        );

        // Compare: larger
        largerFourOfAKind = Arrays.asList(
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.THREE, Suit.DIAMONDS)
        );

        // Compare: smaller
        smallerFourOfAKind = Arrays.asList(
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.J, Suit.HEARTS),
                new Card(Value.DEUCE, Suit.DIAMONDS)
        );

        // Valid: unsorted
        validUnsortedFourOfAKind = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        );

        // Valid: reversed
        validReversedFourOfAKind = Arrays.asList(
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        );

        // Invalid: random
        invalid = Arrays.asList(
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.ACE, Suit.HEARTS)
        );
    }

    @Test
    public void testIsValidFiveCards() {
        assertTrue("Check isValid", Combination.isValidFiveCards(referenceFourOfAKind));
        assertTrue("Check isValid", Combination.isValidFiveCards(validUnsortedFourOfAKind));
        assertTrue("Check isValid", Combination.isValidFiveCards(validReversedFourOfAKind));
        assertFalse("Check isValid", Combination.isValidFiveCards(invalid));
    }

    @Test
    public void testCompareFourOfAKinds() {
        assertEquals("Larger", 1, Combination.compareFiveCards(largerFourOfAKind, referenceFourOfAKind));
        assertEquals("Smaller", -1, Combination.compareFiveCards(smallerFourOfAKind, referenceFourOfAKind));
        assertEquals("Reflexive", 0, Combination.compareFiveCards(referenceFourOfAKind, referenceFourOfAKind));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Straight vs Four of a Kind", -1, Combination.compareFiveCards(testCombination.straight(), referenceFourOfAKind));
        assertEquals("Flush vs Four of a Kind", -1, Combination.compareFiveCards(testCombination.flush(), referenceFourOfAKind));
        assertEquals("Full House vs Four of a Kind", -1, Combination.compareFiveCards(testCombination.fullHouse(), referenceFourOfAKind));
        assertEquals("Royal Flush vs Four of a Kind", 1, Combination.compareFiveCards(testCombination.royalFlush(), referenceFourOfAKind));
    }
}
