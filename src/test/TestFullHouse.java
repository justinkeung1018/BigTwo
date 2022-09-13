import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFullHouse {
    TestCombination testCombination;

    List<Card> referenceFullHouse;
    List<Card> largerFullHouse;
    List<Card> smallerFullHouse;
    List<Card> unsortedFullHouse;
    List<Card> invalid;

    @Before
    public void setUp() {
        testCombination = new TestCombination();

        // Compare: reference
        referenceFullHouse = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        );

        // Compare: larger
        largerFullHouse = Arrays.asList(
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.K, Suit.SPADES),
                new Card(Value.K, Suit.CLUBS)
        );

        // Compare: smaller (three cards smaller, two cards larger)
        smallerFullHouse = Arrays.asList(
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.J, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS)
        );

        // Valid: unsorted
        unsortedFullHouse = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.ACE, Suit.SPADES)
        );

        // Invalid: three cards equal, two different cards
        invalid = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.DEUCE, Suit.HEARTS),
                new Card(Value.THREE, Suit.DIAMONDS)
        );
    }

    @Test
    public void testIsValidFiveCards() {
        assertTrue("Check isValid", Combination.isValidFiveCards(referenceFullHouse));
        assertTrue("Check isValid", Combination.isValidFiveCards(unsortedFullHouse));
        assertFalse("Check isValid", Combination.isValidFiveCards(invalid));
    }

    @Test
    public void testCompareFullHouses() {
        assertEquals("Larger", 1, Combination.compareFiveCards(largerFullHouse, referenceFullHouse));
        assertEquals("Smaller", -1, Combination.compareFiveCards(smallerFullHouse, referenceFullHouse));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Straight vs Full House", -1, Combination.compareFiveCards(testCombination.straight(), referenceFullHouse));
        assertEquals("Flush vs Full House", -1, Combination.compareFiveCards(testCombination.flush(), referenceFullHouse));
        assertEquals("Four of a Kind vs Full House", 1, Combination.compareFiveCards(testCombination.fourOfAKind(), referenceFullHouse));
        assertEquals("Royal Flush vs Full House", 1, Combination.compareFiveCards(testCombination.royalFlush(), referenceFullHouse));
    }
}
