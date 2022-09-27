import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestBigTwo {
    BigTwo game;
    Player[] players;

    Player player0a = new Player(0);
    Player player1a = new Player(1);
    Player player2a = new Player(2);
    Player player3a = new Player(3);

    Player player0b = new Player(0);
    Player player1b = new Player(1);
    Player player2b = new Player(2);
    Player player3b = new Player(3);

    Player player0c = new Player(0);
    Player player1c = new Player(1);
    Player player2c = new Player(2);
    Player player3c = new Player(3);

    public TestBigTwo() {
        game = new BigTwo();
        players = game.players();

        player0a.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.ACE, Suit.CLUBS))
        ));
        player1a.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.DEUCE, Suit.CLUBS)
        )));
        player2a.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.K, Suit.SPADES),
                new Card(Value.SIX, Suit.DIAMONDS)
        )));
        player3a.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.ACE, Suit.SPADES)
        )));

        player0b.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.DEUCE, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES)
        )));
        player1b.setCards(new ArrayList<Card>(List.of(
                new Card(Value.THREE, Suit.DIAMONDS)
        )));
        player2b.setCards(new ArrayList<Card>(List.of(
                new Card(Value.FOUR, Suit.DIAMONDS)
        )));
        player3b.setCards(new ArrayList<Card>(List.of(
                new Card(Value.FIVE, Suit.DIAMONDS)
        )));

        player0c.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.TEN, Suit.SPADES),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.Q, Suit.SPADES),
                new Card(Value.K, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES),
                new Card(Value.DEUCE, Suit.HEARTS)
        )));
        player1c.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.THREE, Suit.DIAMONDS),
                new Card(Value.THREE, Suit.SPADES)
        )));
        player2c.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.FOUR, Suit.DIAMONDS),
                new Card(Value.FOUR, Suit.CLUBS),
                new Card(Value.FOUR, Suit.SPADES)
        )));
        player3c.setCards(new ArrayList<Card>(Arrays.asList(
                new Card(Value.FIVE, Suit.DIAMONDS),
                new Card(Value.FIVE, Suit.CLUBS),
                new Card(Value.FIVE, Suit.SPADES),
                new Card(Value.ACE, Suit.HEARTS),
                new Card(Value.ACE, Suit.CLUBS)
        )));
    }

    @Test
    public void testPlay() {
        reset();
        setPlayers(player0a, player1a, player2a, player3a);

        players[0].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        game.play();
        assertGameState(0, List.of(new Card(Value.FOUR, Suit.DIAMONDS)), 0, 1);
        players[1].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        game.play();
        assertGameState(1, List.of(new Card(Value.FIVE, Suit.DIAMONDS)), 0, 1);
        players[2].setSelection(new Card(Value.K, Suit.SPADES), true);
        game.play();
        assertGameState(2, List.of(new Card(Value.K, Suit.SPADES)), 0, 1);
        players[3].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        game.play();
        assertGameState(3, List.of(new Card(Value.ACE, Suit.SPADES)), 0, 0);
        assertEquals("Player 3 won", players[3], game.winner());

        reset();
        setPlayers(player0b, player1b, player2b, player3b);

        players[0].setSelection(new Card(Value.DEUCE, Suit.SPADES), true);
        assertTrue("Can play", game.play());
        assertGameState(0, List.of(new Card(Value.DEUCE, Suit.SPADES)), 0, 1);
        players[1].setSelection(new Card(Value.THREE, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(1, List.of(new Card(Value.DEUCE, Suit.SPADES)), 1, 1);
        players[2].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(2, List.of(new Card(Value.DEUCE, Suit.SPADES)), 1, 1);
        players[3].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(3, null, 1, 1);
        players[0].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        assertTrue("Can play", game.play());
        assertGameState(0, List.of(new Card(Value.ACE, Suit.SPADES)), 0, 0);
        assertEquals("Player 0 wins", players[0], game.winner());

        reset();
        setPlayers(player0c, player1c, player2c, player3c);

        players[0].setSelection(new Card(Value.J, Suit.SPADES), true);
        players[0].setSelection(new Card(Value.Q, Suit.SPADES), true);
        players[0].setSelection(new Card(Value.K, Suit.SPADES), true);
        players[0].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        players[0].setSelection(new Card(Value.DEUCE, Suit.HEARTS), true);
        assertFalse("Cannot play: invalid Royal Flush", game.play());
        players[0].setSelection(new Card(Value.DEUCE, Suit.HEARTS), false);
        players[0].setSelection(new Card(Value.TEN, Suit.SPADES), true);
        assertTrue("Can play: valid Royal Flush", game.play());
        assertGameState(0, Arrays.asList(new Card(Value.TEN, Suit.SPADES),
                new Card(Value.J, Suit.SPADES),
                new Card(Value.Q, Suit.SPADES),
                new Card(Value.K, Suit.SPADES),
                new Card(Value.ACE, Suit.SPADES)), 0, 1);
        players[1].setSelection(new Card(Value.THREE, Suit.DIAMONDS), true);
        assertFalse("Cannot play: 5 cards vs 1 card", game.play());
        players[1].setSelection(new Card(Value.THREE, Suit.SPADES), true);
        assertFalse("cannot play: 5 cards vs 2 cards", game.play());
        game.pass();
        players[2].setSelection(new Card(Value.FOUR, Suit.SPADES), true);
        players[2].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        players[2].setSelection(new Card(Value.FOUR, Suit.CLUBS), true);
        assertFalse("Cannot play: 5 cards vs 3 cards", game.play());
        game.pass();
        players[3].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        players[3].setSelection(new Card(Value.FIVE, Suit.CLUBS), true);
        players[3].setSelection(new Card(Value.FIVE, Suit.SPADES), true);
        players[3].setSelection(new Card(Value.ACE, Suit.HEARTS), true);
        players[3].setSelection(new Card(Value.ACE, Suit.CLUBS), true);
        assertFalse("Cannot play: Royal Flush vs Full House", game.play());
        game.pass();
        players[0].setSelection(new Card(Value.DEUCE, Suit.HEARTS), true);
        assertTrue("Can play: everyone passed", game.play());
        assertEquals("Player 0 wins", players[0], game.winner());
    }

    /**
     * Resets the game and its players.
     */
    private void reset() {
        game = new BigTwo();
        players = game.players();
    }

    /**
     * Sets the properties of all four players in the game instance to four manually created players.
     * @param player0 The player containing the desired properties of player 0.
     * @param player1 The player containing the desired properties of player 1.
     * @param player2 The player containing the desired properties of player 2.
     * @param player3 The player containing the desired properties of player 3.
     */
    private void setPlayers(Player player0, Player player1, Player player2, Player player3) {
        assert(player0.id() == 0);
        assert(player1.id() == 1);
        assert(player2.id() == 2);
        assert(player3.id() == 3);
        setPlayer(player0);
        setPlayer(player1);
        setPlayer(player2);
        setPlayer(player3);
    }

    /**
     * Sets the properties of a player in the game instance to be those of a manually created player with the same ID.
     * @param player The player with the desired properties.
     */
    private void setPlayer(Player player) {
        int id = player.id();
        Player reference = players[id];
        reference.selected().clear();
        reference.setCards(player.cards());
        for (Card card : player.selected()) {
            reference.setSelection(card, true);
        }
    }

    /**
     * Asserts that the properties of the game state are as expected.
     * @param id ID of the player to be checked.
     * @param lastPlayedCards The list of last played cards. Pass in null if there are no last played cards, or if the last played cards have no effect anymore.
     * @param numSelectedCards The number of cards selected by the specified player.
     * @param numCards The number of cards held by the specified player.
     */
    private void assertGameState(int id, List<Card> lastPlayedCards, int numSelectedCards, int numCards) {
        Player player = players[id];
        if (lastPlayedCards != null) {
            assertEquals("Last played cards", lastPlayedCards, game.lastPlayed());
        }
        assertEquals("Number of selected cards", numSelectedCards, player.selected().size());
        assertEquals("Played card removed from hand", numCards, player.cards().size());
    }
}
