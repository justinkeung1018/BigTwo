import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestStraight {
    List<Card> smallestStraight;
    List<Card> middleStraight;
    List<Card> largestStraight;
    List<Card> largerSuitStraight; // Same values as smallestStraight, but card with the largest value has larger suit
    List<Card> flush;
    List<Card> fullHouse;
    List<Card> fourOfAKind;
    List<Card> royalFlush;
    List<Card> unsortedValidStraight;
    List<Card> notConsecutive;
    List<Card> fourCards;
    List<Card> JQKA2;
    List<Card> QKA23;
    List<Card> KA234;

    @Before
    public void setUp() {
        smallestStraight = Arrays.asList(
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.DIAMONDS)
        );

        middleStraight = Arrays.asList(
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS),
                new Card(Value.EIGHT, Suit.DIAMONDS)
        );

        largestStraight = Arrays.asList(
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS),
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS)
        );

        largerSuitStraight = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.SPADES),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.SPADES)
        );

        flush = Arrays.asList(
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.DEUCE, Suit.SPADES)
        );

        fullHouse = Arrays.asList(
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        );

        fourOfAKind = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.THREE, Suit.CLUBS),
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.CLUBS)
        );

        royalFlush = Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.SIX, Suit.DIAMONDS),
                new Card(Value.SEVEN, Suit.DIAMONDS)
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
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(smallestStraight));
        assertTrue("Check isValidFiveCards", Combination.isValidFiveCards(middleStraight));
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
        assertEquals("Transitive: middle vs smallest", 1, Combination.compareFiveCards(middleStraight, smallestStraight));
        assertEquals("Transitive: largest vs middle", 1, Combination.compareFiveCards(largestStraight, middleStraight));
        assertEquals("Transitive: largest vs smallest", 1, Combination.compareFiveCards(largestStraight, smallestStraight));
        assertEquals("Reflexive", 0, Combination.compareFiveCards(middleStraight, middleStraight));
    }

    @Test
    public void testTieBreakerBySuit() {
        assertEquals("Same values, one with larger suit", 1, Combination.compareFiveCards(largerSuitStraight, smallestStraight));
    }

    @Test
    public void testCompareOtherFiveCards() {
        assertEquals("Flush vs Straight", 1, Combination.compareFiveCards(flush, largestStraight));
        assertEquals("Full House vs Straight", 1, Combination.compareFiveCards(fullHouse, largestStraight));
        assertEquals("Four of a Kind vs Straight", 1, Combination.compareFiveCards(fourOfAKind, largestStraight));
        assertEquals("Royal Flush vs Straight", 1, Combination.compareFiveCards(royalFlush, largestStraight));
    }
}
