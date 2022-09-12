import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing static methods to analyze card combinations.
 * Rules are based on <a href="https://appsrv.cse.cuhk.edu.hk/~cscs/event/oldEventSites/cscup2012/games/6.html">...</a>
 */
public class Combination {
    public static int compareFiveCards(List<Card> selected, List<Card> lastPlayed) {
        int selectedRank = rankOfFiveCards(selected);
        int lastPlayedRank = rankOfFiveCards(lastPlayed);
        if (selectedRank != lastPlayedRank) {
            return Integer.compare(selectedRank, lastPlayedRank);
        }
        return switch (selectedRank) {
            case 1 -> compareStraights(selected, lastPlayed);
            case 2 -> compareFlushes(selected, lastPlayed);
            case 3 -> compareFullHouses(selected, lastPlayed);
            case 4 -> compareFourOfAKinds(selected, lastPlayed);
            case 5 -> compareRoyalFlushes(selected, lastPlayed);
            default -> -1; // When combination is invalid
        };
    }
    /**
     * Returns the relative rank of the combination of five cards.
     *
     * @param selected The selected cards.
     * @return The relative rank of the combination of five cards.
     */
    private static int rankOfFiveCards(List<Card> selected) {
        if (selected.size() != 5) {
            throw new IllegalArgumentException("Number of cards selected must be five.");
        }
        if (isStraight(selected)) return 1;
        else if (isFlush(selected)) return 2;
        else if (isFullHouse(selected)) return 3;
        else if (isFourOfAKind(selected)) return 4;
        else if (isRoyalFlush(selected)) return 5;
        else return 0; // Invalid combination of five cards
    }

    /**
     * Determines if all selected cards have the same suit.
     *
     * @param selected The selected cards.
     * @return Whether all selected cards have the same suit.
     */
    private static boolean hasSameSuit(List<Card> selected) {
        if (selected.size() == 0) {
            throw new IllegalArgumentException("At least one card must be selected.");
        }
        Suit suit = selected.get(0).getSuit();
        for (Card card : selected) {
            if  (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the selected cards are in consecutive order.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards are in consecutive order.
     */
    private static boolean areConsecutive(List<Card> selected) {
        Collections.sort(selected);
        int currValue = 0;
        boolean containsJQK = false;
        for (Card card : selected) {
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
     * Useful for determining if the selected cards form a Full House or Four of a Kind.
     *
     * @param selected The selected cards.
     * @return A map with key being the card value and value being the number of cards having that value.
     */
    private static Map<Value, Integer> count(List<Card> selected) {
        Map<Value, Integer> count = new HashMap<>();
        for (Card card : selected) {
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
     * Determines whether the selected cards form a Straight.
     * Note that a Royal Flush (i.e. the selected cards satisfying the criteria for both Straight and Flush)
     * does not form a Straight.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards form a Straight.
     */
    private static boolean isStraight(List<Card> selected) {
        return areConsecutive(selected) && !hasSameSuit(selected);
    }

    /**
     * Determines whether the selected cards form a Flush.
     * Note that a Royal Flush (i.e. the selected cards satisfying the criteria for both Straight and Flush)
     * does not form a Flush.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards form a Flush.
     */
    private static boolean isFlush(List<Card> selected) {
        return hasSameSuit(selected) && !areConsecutive(selected);
    }

    /**
     * Determines whether the selected cards form a Full House.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards form a Full House.
     */
    private static boolean isFullHouse(List<Card> selected) {
        Map<Value, Integer> count = count(selected);
        return count.size() == 2 && count.containsValue(2) && count.containsValue(3);
    }

    /**
     * Determines whether the selected cards form a Four of a Kind.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards form a Four of a Kind.
     */
    private static boolean isFourOfAKind(List<Card> selected) {
        Map<Value, Integer> count = count(selected);
        return count.size() == 2 && count.containsValue(4) && count.containsValue(1);
    }

    /**
     * Determines whether the selected cards form a Royal Flush.
     *
     * @param selected The selected cards.
     * @return Whether the selected cards form a Flush.
     */
    private static boolean isRoyalFlush(List<Card> selected) {
        return areConsecutive(selected) && hasSameSuit(selected);
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
     * @param selected The selected cards.
     * @param numCards The number of cards with a certain value.
     * @return The card value with the specified number of cards. Null if no card value has the specified number of cards.
     */
    private static Value valueWithNumCards(List<Card> selected, int numCards) {
        Map<Value, Integer> count = count(selected);
        for (Map.Entry<Value, Integer> entry : count.entrySet()) {
            if (entry.getValue() == numCards) {
                return entry.getKey();
            }
        }
        return null;
    }
}
