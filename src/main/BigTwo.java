import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BigTwo {
    int currentPlayerId;
    ArrayList<Card> lastPlayed;
    Player[] players;
    GraphicsHandler graphicsHandler;

    public BigTwo(GraphicsHandler graphicsHandler) {
        currentPlayerId = 1;
        lastPlayed = new ArrayList<>();
        players = new Player[5]; // Size 5 for easier access by 1-indexing
        this.graphicsHandler = graphicsHandler;

        Scanner scanner = new Scanner(System.in);
        for (int id = 1; id <= 4; id++) {
            String name;
            while (!scanner.hasNext()) {
                System.out.print("Player " + id + ", enter your name: " );
            }
            name = scanner.next();
            Player player = new Player(name, id);
            players[id] = player;
        }
        scanner.close();

        graphicsHandler.setup();
    }

    public static void main(String[] args) {
        GraphicsHandler graphicsHandler = new SwingGraphicsHandler();
        new BigTwo(graphicsHandler);
    }

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

    private void distribute() {
        List<Card> deck = deck();
        int cardsPerPlayer = deck.size() / (players.length - 1);
        int cardsDistributed = 0;
        for (int id = 1; id < players.length; id++) {
            players[id].setCards(deck.subList(cardsDistributed, cardsDistributed + cardsPerPlayer));
            cardsDistributed += cardsPerPlayer;
        }
    }

    /**
     * Determines whether the player can play their selected cards.
     *
     * @param selected The selected cards.
     * @return Whether the player can play their selected cards.
     */
    private boolean canPlay(List<Card> selected) {
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

    /* Methods to implement
    * Calls out the person with 3 of Diamonds to play first
    * Checks if play is valid
    * Determines if game is over (whoever's hand is empty)
     */
}
