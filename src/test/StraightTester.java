import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StraightTester {

    Card[] cards;

    Straight straight1;
    Straight straight2;
    Straight straight3;
    Straight straight4;

    Flush flush;
    FullHouse fullHouse;
    FourOfAKind fourOfAKind;
    RoyalFlush royalFlush;

    Straight validStraight;
    Straight invalidStraight1;
    Straight invalidStraight2;
    Straight invalidStraight3;
    Straight invalidSpecialStraight1;
    Straight invalidSpecialStraight2;
    Straight invalidSpecialStraight3;

    @Before
    public void setUp() {
        // Compare: smallest
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card(Value.THREE, Suit.DIAMONDS));
        cards1.add(new Card(Value.FOUR, Suit.SPADES));
        cards1.add(new Card(Value.FIVE, Suit.CLUBS));
        cards1.add(new Card(Value.SIX, Suit.DIAMONDS));
        cards1.add(new Card(Value.SEVEN, Suit.DIAMONDS));
        straight1 = new Straight(cards1);

        // Compare by value: middle
        ArrayList<Card> cards2 = new ArrayList<Card>();
        cards2.add(new Card(Value.FOUR, Suit.SPADES));
        cards2.add(new Card(Value.FIVE, Suit.CLUBS));
        cards2.add(new Card(Value.SIX, Suit.DIAMONDS));
        cards2.add(new Card(Value.SEVEN, Suit.DIAMONDS));
        cards2.add(new Card(Value.EIGHT, Suit.DIAMONDS));
        straight2 = new Straight(cards2);

        // Compare by value: largest
        ArrayList<Card> cards3 = new ArrayList<Card>();
        cards3.add(new Card(Value.ACE, Suit.SPADES));
        cards3.add(new Card(Value.DEUCE, Suit.CLUBS));
        cards3.add(new Card(Value.THREE, Suit.DIAMONDS));
        cards3.add(new Card(Value.FOUR, Suit.DIAMONDS));
        cards3.add(new Card(Value.FIVE, Suit.DIAMONDS));
        straight3 = new Straight(cards3);

        // Compare by suit: larger
        ArrayList<Card> cards4 = new ArrayList<Card>();
        cards4.add(new Card(Value.THREE, Suit.DIAMONDS));
        cards4.add(new Card(Value.FOUR, Suit.SPADES));
        cards4.add(new Card(Value.FIVE, Suit.CLUBS));
        cards4.add(new Card(Value.SIX, Suit.DIAMONDS));
        cards4.add(new Card(Value.SEVEN, Suit.SPADES));
        straight4 = new Straight(cards4);

        // Compare: larger (Flush)
        cards = new Card[] {
                new Card(Value.THREE, Suit.SPADES),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.SIX, Suit.SPADES),
                new Card(Value.SEVEN, Suit.SPADES),
                new Card(Value.DEUCE, Suit.SPADES)
        };
        ArrayList<Card> flushCards = new ArrayList<Card>(Arrays.asList(cards));
        flush = new Flush(flushCards);

        // Compare: larger (Full House)
        cards = new Card[] {
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.ACE, Suit.CLUBS),
                new Card(Value.K, Suit.HEARTS),
                new Card(Value.K, Suit.DIAMONDS)
        };
        ArrayList<Card> fullHouseCards = new ArrayList<Card>(Arrays.asList(cards));
        fullHouse = new FullHouse(fullHouseCards);

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
        ArrayList<Card> validCards = new ArrayList<Card>();
        validCards.add(new Card(Value.THREE, Suit.DIAMONDS));
        validCards.add(new Card(Value.FOUR, Suit.SPADES));
        validCards.add(new Card(Value.FIVE, Suit.CLUBS));
        validCards.add(new Card(Value.SIX, Suit.DIAMONDS));
        validCards.add(new Card(Value.SEVEN, Suit.DIAMONDS));
        validStraight = new Straight(validCards);

        // Invalid: values not in consecutive order
        ArrayList<Card> invalidCards1 = new ArrayList<Card>();
        invalidCards1.add(new Card(Value.FOUR, Suit.SPADES));
        invalidCards1.add(new Card(Value.FIVE, Suit.CLUBS));
        invalidCards1.add(new Card(Value.SIX, Suit.DIAMONDS));
        invalidCards1.add(new Card(Value.NINE, Suit.DIAMONDS));
        invalidCards1.add(new Card(Value.EIGHT, Suit.DIAMONDS));
        invalidStraight1 = new Straight(invalidCards1);

        // Invalid: Royal Flush
        ArrayList<Card> invalidCards2 = new ArrayList<Card>();
        invalidCards2.add(new Card(Value.FOUR, Suit.DIAMONDS));
        invalidCards2.add(new Card(Value.FIVE, Suit.DIAMONDS));
        invalidCards2.add(new Card(Value.SIX, Suit.DIAMONDS));
        invalidCards2.add(new Card(Value.SEVEN, Suit.DIAMONDS));
        invalidCards2.add(new Card(Value.EIGHT, Suit.DIAMONDS));
        invalidStraight2 = new Straight(invalidCards2);

        // Invalid: four cards only
        ArrayList<Card> invalidCards3 = new ArrayList<Card>();
        invalidCards3.add(new Card(Value.FOUR, Suit.SPADES));
        invalidCards3.add(new Card(Value.FIVE, Suit.CLUBS));
        invalidCards3.add(new Card(Value.SIX, Suit.DIAMONDS));
        invalidCards3.add(new Card(Value.SEVEN, Suit.DIAMONDS));
        invalidStraight3 = new Straight(invalidCards3);

        // Invalid: special case 1 (JQKA2)
        cards = new Card[] {
                new Card(Value.J, Suit.DIAMONDS),
                new Card(Value.Q, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.DIAMONDS),
        };
        ArrayList<Card> invalidSpecialCards1 = new ArrayList<Card>(Arrays.asList(cards));
        invalidSpecialStraight1 = new Straight(invalidSpecialCards1);

        // Invalid: special case 2 (QKA23)
        cards = new Card[] {
                new Card(Value.Q, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES),
        };
        ArrayList<Card> invalidSpecialCards2 = new ArrayList<Card>(Arrays.asList(cards));
        invalidSpecialStraight2 = new Straight(invalidSpecialCards2);

        // Invalid: special case 3 (KA234, unsorted)
        cards = new Card[] {
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.K, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES),
        };
        ArrayList<Card> invalidSpecialCards3 = new ArrayList<Card>(Arrays.asList(cards));
        invalidSpecialStraight3 = new Straight(invalidSpecialCards3);
    }

    @Test
    public void testConstructor() {
        System.out.println(straight1);
        System.out.println("selected: " + straight1.getSelected());
        System.out.println("selected size:  " + straight1.getSelected().size());
    }

    @Test
    public void testIsValid() {
        assertEquals("Check isValid", true, straight1.isValid());
        assertEquals("Check isValid", true, validStraight.isValid());
        assertEquals("Check isValid", false, invalidStraight1.isValid());
        assertEquals("Check isValid", false, invalidStraight2.isValid());
        assertEquals("Check isValid", false, invalidStraight3.isValid());
        assertEquals("Check isValid", false, invalidSpecialStraight1.isValid());
        assertEquals("Check isValid", false, invalidSpecialStraight2.isValid());
        assertEquals("Check isValid", false, invalidSpecialStraight3.isValid());
    }

    @Test
    public void testValueCompare() {
        assertEquals("Middle vs smallest", true, straight2.compareTo(straight1) > 0);
        assertEquals("Largest vs middle", true, straight3.compareTo(straight2) > 0);
        assertEquals("Largest vs smallest", true, straight3.compareTo(straight1) > 0);
    }

    @Test
    public void testSuitCompare() {
        assertEquals("Larger vs smaller", true, straight4.compareTo(straight1) > 0);
    }

    @Test
    public void testFiveCardsCompare() {
        assertEquals("Larger: Flush", 1, flush.compareTo(straight1));
        assertEquals("Larger: Full House", 1, fullHouse.compareTo(straight1));
        assertEquals("Larger: Four of a Kind", 1, fourOfAKind.compareTo(straight1));
        assertEquals("Larger: Royal Flush", 1, royalFlush.compareTo(straight1));
    }


}
