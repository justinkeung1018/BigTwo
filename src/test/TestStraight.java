import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestStraight {
    TestCombination testCombination;

    List<Card> referenceStraight;
    List<Card> smallestStraight;
    List<Card> largestStraight;
    List<Card> largerSuitStraight; // Same values as smallestStraight, but card with the largest value has larger suit
    List<Card> unsortedValidStraight;
    List<Card> notConsecutive;
    List<Card> fourCards;
    List<Card> JQKA2;
    List<Card> QKA23;
    List<Card> KA234;

    @Before
    public void setUp() {
        testCombination = new TestCombination();

        referenceStraight = Arrays.asList(
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS)
        );

        smallestStraight = Arrays.asList(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS)
        );

        largestStraight = Arrays.asList(
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        );

        largerSuitStraight = Arrays.asList(
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.CLUBS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.SPADES)
        );

        unsortedValidStraight = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
        );

        notConsecutive = Arrays.asList(
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.NINE, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS)
        );

        fourCards = Arrays.asList(
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
        );

        JQKA2 = Arrays.asList(
            new Card(Value.J, Suit.DIAMONDS),
            new Card(Value.Q, Suit.DIAMONDS),
            new Card(Value.K, Suit.DIAMONDS),
            new Card(Value.ACE, Suit.SPADES),
            new Card(Value.DEUCE, Suit.DIAMONDS)
        );

        QKA23 = Arrays.asList(
                new Card(Value.Q, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES)
        );

        KA234 = Arrays.asList(
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES)
        );
    }

    @Test
    public void testIsValidFiveCards() {
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(referenceStraight));
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(smallestStraight));
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(largestStraight));
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(largerSuitStraight));
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(unsortedValidStraight));

        assertFalse("Check isValidFiveCards", Combination.isValidFiveCards(notConsecutive));
        assertFalse("Check isValidFiveCards", Combination.isValidFiveCards(fourCards));
        assertFalse("Check isValidFiveCards", Combination.isValidFiveCards(JQKA2));
        assertFalse("Check isValidFiveCards", Combination.isValidFiveCards(QKA23));
        assertFalse("Check isValidFiveCards", Combination.isValidFiveCards(KA234));
    }

    @Test
    public void testCompareByValue() {
        assertEquals("Transitive: middle vs smallest", 1, Combination.compareFiveCards(referenceStraight, smallestStraight));
        assertEquals("Transitive: largest vs middle", 1, Combination.compareFiveCards(largestStraight, referenceStraight));
        assertEquals("Transitive: largest vs smallest", 1, Combination.compareFiveCards(largestStraight, smallestStraight));
        assertEquals("Reflexive", 0, Combination.compareFiveCards(referenceStraight, referenceStraight));
    }

    @Test
    public void testTieBreakerBySuit() {
        assertEquals("Same values, one with larger suit", 1, Combination.compareFiveCards(largerSuitStraight, smallestStraight));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Flush vs Straight", 1, Combination.compareFiveCards(testCombination.flush(), largestStraight));
        assertEquals("Full House vs Straight", 1, Combination.compareFiveCards(testCombination.fullHouse(), largestStraight));
        assertEquals("Four of a Kind vs Straight", 1, Combination.compareFiveCards(testCombination.fourOfAKind(), largestStraight));
        assertEquals("Royal Flush vs Straight", 1, Combination.compareFiveCards(testCombination.royalFlush(), largestStraight));
    }
}
