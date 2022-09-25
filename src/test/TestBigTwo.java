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
    }

    @Test
    public void testPlay() {
        setPlayer(player0a);
        setPlayer(player1a);
        setPlayer(player2a);
        setPlayer(player3a);
        players[0].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        game.play();
        assertGameState(0, 1, List.of(new Card(Value.FOUR, Suit.DIAMONDS)), 0, 1);
        players[1].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        game.play();
        assertGameState(1, 1, List.of(new Card(Value.FIVE, Suit.DIAMONDS)), 0, 1);
        players[2].setSelection(new Card(Value.K, Suit.SPADES), true);
        game.play();
        assertGameState(2, 1, List.of(new Card(Value.K, Suit.SPADES)), 0, 1);
        players[3].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        game.play();
        assertGameState(3, 1, List.of(new Card(Value.ACE, Suit.SPADES)), 0, 0);
        assertEquals("Player 3 won", players[3], game.winner());

        game = new BigTwo();
        players = game.players();

        setPlayer(player0b);
        setPlayer(player1b);
        setPlayer(player2b);
        setPlayer(player3b);
        players[0].setSelection(new Card(Value.DEUCE, Suit.SPADES), true);
        game.play();
        assertGameState(0, 1, List.of(new Card(Value.DEUCE, Suit.SPADES)), 0, 1);
        players[1].setSelection(new Card(Value.THREE, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(1, 1, List.of(new Card(Value.DEUCE, Suit.SPADES)), 1, 1);
        players[2].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(2, 1, List.of(new Card(Value.DEUCE, Suit.SPADES)), 1, 1);
        players[3].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        assertFalse("Cannot play", game.play());
        game.pass();
        assertGameState(3, 0, null, 1, 1);
        players[0].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        game.play();
        assertGameState(0, 1, List.of(new Card(Value.ACE, Suit.SPADES)), 0, 0);
        assertEquals("Player 0 wins", players[0], game.winner());
    }

    private void setPlayer(Player player) {
        int id = player.id();
        Player reference = players[id];
        reference.selected().clear();
        reference.setCards(player.cards());
        for (Card card : player.selected()) {
            reference.setSelection(card, true);
        }
    }

    private void assertGameState(int id, int numLastPlayedCards, List<Card> lastPlayedCards, int numSelectedCards, int numCards) {
        Player player = players[id];
        assertEquals("Number of last played cards", numLastPlayedCards, game.lastPlayed().size());
        if (lastPlayedCards != null) {
            assertEquals("Last played cards", lastPlayedCards, game.lastPlayed());
        }
        assertEquals("Number of selected cards", numSelectedCards, player.selected().size());
        assertEquals("Played card removed from hand", numCards, player.cards().size());
    }
}
