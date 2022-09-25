import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBigTwo {
    BigTwo game;
    Player[] players;

    Player player0a = new Player(0);
    Player player1a = new Player(1);
    Player player2a = new Player(2);
    Player player3a = new Player(3);


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
    }

    @Test
    public void testCanPlay() {
        setPlayer(player0a);
        setPlayer(player1a);
        setPlayer(player2a);
        setPlayer(player3a);
        players[0].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        assertTrue("Can play", game.canPlay(players[0].selected()));
    }

    @Test
    public void testPlay() {
        setPlayer(player0a);
        setPlayer(player1a);
        setPlayer(player2a);
        setPlayer(player3a);
        players[0].setSelection(new Card(Value.FOUR, Suit.DIAMONDS), true);
        game.play();
        assertEquals("Number of last played cards", 1, game.lastPlayed().size());
        assertEquals("Last played cards", List.of(new Card(Value.FOUR, Suit.DIAMONDS)), game.lastPlayed());
        assertTrue("No cards selected", players[0].selected().isEmpty());
        assertEquals("Played card removed from hand", 1, players[0].cards().size());
        players[1].setSelection(new Card(Value.FIVE, Suit.DIAMONDS), true);
        game.play();
        assertEquals("Number of last played cards", 1, game.lastPlayed().size());
        assertEquals("Last played cards", List.of(new Card(Value.FIVE, Suit.DIAMONDS)), game.lastPlayed());
        assertTrue("No cards selected", players[1].selected().isEmpty());
        assertEquals("Played card removed from hand", 1, players[1].cards().size());
        players[2].setSelection(new Card(Value.K, Suit.SPADES), true);
        game.play();
        assertEquals("Number of last played cards", 1, game.lastPlayed().size());
        assertEquals("Last played cards", List.of(new Card(Value.K, Suit.SPADES)), game.lastPlayed());
        assertTrue("No cards selected", players[2].selected().isEmpty());
        assertEquals("Played card removed from hand", 1, players[2].cards().size());
        players[3].setSelection(new Card(Value.ACE, Suit.SPADES), true);
        game.play();
        assertEquals("Number of last played cards", 1, game.lastPlayed().size());
        assertEquals("Last played cards", List.of(new Card(Value.ACE, Suit.SPADES)), game.lastPlayed());
        assertTrue("No cards selected", players[3].selected().isEmpty());
        assertTrue("Played card removed from hand", players[3].cards().isEmpty());
        assertEquals("Player 3 won", players[3], game.winner());
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
}
