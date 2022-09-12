import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing static methods to analyze card combinations.
 * Rules are based on <a href="https://appsrv.cse.cuhk.edu.hk/~cscs/event/oldEventSites/cscup2012/games/6.html">...</a>
 */
public class Combination {
    /**
     * Compares two combinations of five cards.
     *
     * @param fiveCards1 The first combination of five cards.
     * @param fiveCards2 The second combination of five cards.
     * @return THe first combination of five cards compared to the second combination of five cards.
     */
    public static int compareFiveCards(List<Card> fiveCards1, List<Card> fiveCards2) {
        int rank1 = rankOfFiveCards(fiveCards1);
        int rank2 = rankOfFiveCards(fiveCards2);
        if (rank1 != rank2) {
            return Integer.compare(rank1, rank2);
        }
        return switch (rank1) {
            case 1 -> compareStraights(fiveCards1, fiveCards2);
            case 2 -> compareFlushes(fiveCards1, fiveCards2);
            case 3 -> compareFullHouses(fiveCards1, fiveCards2);
            case 4 -> compareFourOfAKinds(fiveCards1, fiveCards2);
            case 5 -> compareRoyalFlushes(fiveCards1, fiveCards2);
            default -> -1; // When combination is invalid
        };
    }
    /**
     * Returns the relative rank of the combination of five cards.
     *
     * @param fiveCards The combination of five cards.
     * @return The relative rank of the combination of five cards.
     */
    private static int rankOfFiveCards(List<Card> fiveCards) {
        if (fiveCards.size() != 5) {
            throw new IllegalArgumentException("Number of cards selected must be five.");
        }
        if (isStraight(fiveCards)) return 1;
        else if (isFlush(fiveCards)) return 2;
        else if (isFullHouse(fiveCards)) return 3;
        else if (isFourOfAKind(fiveCards)) return 4;
        else if (isRoyalFlush(fiveCards)) return 5;
        else return 0; // Invalid combination of five cards
    }

    /**
     * Determines if all cards have the same suit.
     *
     * @param cards The cards.
     * @return Whether all cards have the same suit.
     */
    private static boolean hasSameSuit(List<Card> cards) {
        if (cards.size() == 0) {
            throw new IllegalArgumentException("At least one card must be selected.");
        }
        Suit suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if  (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the cards are in consecutive order.
     *
     * @param cards The cards.
     * @return Whether the cards are in consecutive order.
     */
    private static boolean areConsecutive(List<Card> cards) {
        Collections.sort(cards);
        int currValue = 0;
        boolean containsJQK = false;
        for (Card card : cards) {
            if (currValue == 0) {
                currValue = card.getValueInt();
            } else {
                int value = card.getValueInt();
                if (value % 13 != (currValue + 1) % 13) {
                    return false;
                }
                currValue++;
            }

            // JQKA2, QKA23, and KA234 are illegal
            Value value = card.getValue();
            if (value == Value.J || value == Value.Q || value == Value.K) {
                containsJQK = true;
            }

            if (containsJQK && value == Value.DEUCE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Counts the number of cards with each unique card value among the selected cards.
     * Useful for determining if the cards form a Full House or Four of a Kind.
     *
     * @param cards The cards.
     * @return A map with key being the card value and value being the number of cards having that value.
     */
    private static Map<Value, Integer> count(List<Card> cards) {
        Map<Value, Integer> count = new HashMap<>();
        for (Card card : cards) {
            Value value = card.getValue();
            if (count.containsKey(value)) {
                int valueCount = count.get(value);
                valueCount++;
                count.put(value, valueCount);
            } else {
                count.put(value, 1);
            }
        }
        return count;
    }

    /**
     * Determines whether the cards form a Straight.
     * Note that a Royal Flush (i.e. the cards satisfying the criteria for both Straight and Flush)
     * does not form a Straight.
     *
     * @param cards The cards.
     * @return Whether the cards form a Straight.
     */
    private static boolean isStraight(List<Card> cards) {
        return areConsecutive(cards) && !hasSameSuit(cards);
    }

    /**
     * Determines whether the cards form a Flush.
     * Note that a Royal Flush (i.e. the cards satisfying the criteria for both Straight and Flush)
     * does not form a Flush.
     *
     * @param cards The cards.
     * @return Whether the cards form a Flush.
     */
    private static boolean isFlush(List<Card> cards) {
        return hasSameSuit(cards) && !areConsecutive(cards);
    }

    /**
     * Determines whether the cards form a Full House.
     *
     * @param cards The cards.
     * @return Whether the cards form a Full House.
     */
    private static boolean isFullHouse(List<Card> cards) {
        Map<Value, Integer> count = count(cards);
        return count.size() == 2 && count.containsValue(2) && count.containsValue(3);
    }

    /**
     * Determines whether the cards form a Four of a Kind.
     *
     * @param cards The cards.
     * @return Whether the cards form a Four of a Kind.
     */
    private static boolean isFourOfAKind(List<Card> cards) {
        Map<Value, Integer> count = count(cards);
        return count.size() == 2 && count.containsValue(4) && count.containsValue(1);
    }

    /**
     * Determines whether the cards form a Royal Flush.
     *
     * @param cards The cards.
     * @return Whether the cards form a Flush.
     */
    private static boolean isRoyalFlush(List<Card> cards) {
        return areConsecutive(cards) && hasSameSuit(cards);
    }

    /**
     * Compares two Straights.
     *
     * @param straight1 The first Straight.
     * @param straight2 The second Straight.
     * @return The first Straight compared to the second Straight.
     */
    private static int compareStraights(List<Card> straight1, List<Card> straight2) {
        assert(isStraight(straight1));
        assert(isStraight(straight2));
        Card largestStraight1Card = Collections.max(straight1);
        Card largestStraight2Card = Collections.max(straight2);
        return largestStraight1Card.compareTo(largestStraight2Card);
    }

    /**
     * Compares two Flushes.
     * (Note to self: the value, instead of the suit, of the largest card is used to compare two Flushes.)
     *
     * @param flush1 The first Flush.
     * @param flush2 The second Flush.
     * @return The first Flush compared to the second Flush.
     */
    private static int compareFlushes(List<Card> flush1, List<Card> flush2) {
        assert(isFlush(flush1));
        assert(isFlush(flush2));
        Collections.sort(flush1);
        Collections.sort(flush2);
        for (int i = 4; i >= 0; i--) {
            int largestFlush1ValueRank = flush1.get(i).getValue().rank();
            int largestFlush2ValueRank = flush2.get(i).getValue().rank();
            if (largestFlush1ValueRank != largestFlush2ValueRank) {
                return Integer.compare(largestFlush1ValueRank, largestFlush2ValueRank);
            }
        }
        int flush1SuitRank = flush1.get(0).getSuit().rank();
        int flush2SuitRank = flush2.get(0).getSuit().rank();
        return Integer.compare(flush1SuitRank, flush2SuitRank);
    }

    /**
     * Compares two Full Houses.
     *
     * @param fullHouse1 The first Full House.
     * @param fullHouse2 The second Full House.
     * @return The first Full House compared to the second Full House.
     */
    private static int compareFullHouses(List<Card> fullHouse1, List<Card> fullHouse2) {
        assert(isFullHouse(fullHouse1));
        assert(isFullHouse(fullHouse2));
        Value triple1Value = valueWithNumCards(fullHouse1, 3);
        assert(triple1Value != null);
        Value triple2Value = valueWithNumCards(fullHouse2, 3);
        assert(triple2Value != null);
        return Integer.compare(triple1Value.rank(), triple2Value.rank());
    }

    /**
     * Compares two Four of a Kinds.
     *
     * @param fourOfAKind1 The first Four of a Kind.
     * @param fourOfAKind2 The second Four of a Kind.
     * @return The first Four of a Kind compared to the second Four of a Kind.
     */
    private static int compareFourOfAKinds(List<Card> fourOfAKind1, List<Card> fourOfAKind2) {
        assert(isFourOfAKind(fourOfAKind1));
        assert(isFourOfAKind(fourOfAKind2));
        Value quadruple1Value = valueWithNumCards(fourOfAKind1, 4);
        assert(quadruple1Value != null);
        Value quadruple2Value = valueWithNumCards(fourOfAKind2, 4);
        assert(quadruple2Value != null);
        return Integer.compare(quadruple1Value.rank(), quadruple2Value.rank());
    }

    /**
     * Compares two Royal Flushes.
     *
     * @param royalFlush1 The first Royal Flush.
     * @param royalFlush2 The second Royal Flush.
     * @return The first Royal Flush compared to the second Royal Flush.
     */
    private static int compareRoyalFlushes(List<Card> royalFlush1, List<Card> royalFlush2) {
        assert(isStraight(royalFlush1));
        assert(isStraight(royalFlush2));
        return compareStraights(royalFlush1, royalFlush2);
    }

    /**
     * Finds the card value with the specified number of cards.
     * Useful for comparing Full Houses and Four of a Kinds.
     *
     * @param cards The selected cards.
     * @param numCards The number of cards with a certain value.
     * @return The card value with the specified number of cards. Null if no card value has the specified number of cards.
     */
    private static Value valueWithNumCards(List<Card> cards, int numCards) {
        Map<Value, Integer> count = count(cards);
        for (Map.Entry<Value, Integer> entry : count.entrySet()) {
            if (entry.getValue() == numCards) {
                return entry.getKey();
            }
        }
        return null;
    }
}
