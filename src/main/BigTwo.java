import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigTwo {
    int currentPlayerId;
    List<Card> lastPlayed;
    Player[] players;
    int numPlayersPassed;

    public BigTwo() {
        currentPlayerId = 0;
        lastPlayed = new ArrayList<>();
        players = new Player[4];
        numPlayersPassed = 0;

        for (int id = 0; id < 4; id++) {
            Player player = new Player(id);
            players[id] = player;
        }

        distribute();
    }

    /**
     * Plays the selected cards of the current player.
     * @return Whether the player has successfully played their selected cards.
     */
    public boolean play() {
        Player player = players[currentPlayerId];
        List<Card> selected = player.selected();
        if (!canPlay(selected)) {
            return false;
        }

        lastPlayed.clear();
        for (Card card : selected) {
            lastPlayed.add(new Card(card));
        }
        player.play();
        currentPlayerId = (currentPlayerId + 1) % 4;
        numPlayersPassed = 0;
        return true;
    }

    /**
     * Determines whether the player can play their selected cards.
     *
     * @param selected The selected cards.
     * @return Whether the player can play their selected cards.
     */
    private boolean canPlay(List<Card> selected) {
        if (lastPlayed.size() != 0 && lastPlayed.size() != selected.size()) {
            return false;
        }
        if (selected.size() == 1) {
            if (lastPlayed.size() == 0) {
                return true;
            }
            Card selectedCard = selected.get(0);
            Card lastPlayedCard = lastPlayed.get(0);
            return selectedCard.compareTo(lastPlayedCard) > 0;
        }
        if (Combination.isValidPair(selected)) {
            return lastPlayed.size() == 0 || Combination.comparePairs(selected, lastPlayed) > 0;
        } else if (Combination.isValidTriple(selected)) {
            return lastPlayed.size() == 0 || Combination.compareTriples(selected, lastPlayed) > 0;
        } else if (Combination.isValidFiveCards(selected)) {
            return lastPlayed.size() == 0 || Combination.compareFiveCards(selected, lastPlayed) > 0;
        } else {
            return false;
        }
    }

    /**
     * Passes one round.
     */
    public void pass() {
        currentPlayerId = (currentPlayerId + 1) % 4;
        numPlayersPassed++;
        if (numPlayersPassed == 3) {
            numPlayersPassed = 0;
            lastPlayed.clear();
        }
    }

    /**
     * Determines the winner of the game. Null if there are no winners, i.e. the game has not ended.
     * @return Winner of the game.
     */
    public Player winner() {
        for (Player player : players) {
            if (player.cards().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    /**
     * Returns an array containing all players.
     * @return An array containing all players.
     */
    public Player[] players() { return players; }

    /**
     * Returns the current player.
     * @return The current player.
     */
    public Player currentPlayer() {
        return players[currentPlayerId];
    }

    /**
     * Returns the cards played in the previous round.
     * @return The cards played in the previous round.
     */
    public List<Card> lastPlayed() {
        return lastPlayed;
    }

    /**
     * Generates a shuffled deck of 52 cards.
     * @return A shuffled deck.
     */
    private List<Card> deck() {
        List<Card> deck = new ArrayList<>();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

    /**
     * Distributes a deck of 52 cards evenly among four players.
     */
    private void distribute() {
        List<Card> deck = deck();
        int cardsPerPlayer = deck.size() / players.length;
        int distributed = 0;
        for (Player player : players) {
            player.setCards(new ArrayList<Card>(deck.subList(distributed, distributed + cardsPerPlayer)));
            distributed += cardsPerPlayer;
        }
    }
}
