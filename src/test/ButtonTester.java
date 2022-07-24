import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ButtonTester {
    Game game;

    @Before
    public void setup() {
        game = new Game();
        game.setUp();
    }


    @Test
    public void testPlayButtonClicked() {
        ActionEvent playButtonClicked = new ActionEvent(this, 1, "Clicked play button");
        game.selected = new ArrayList<Card>();
        game.selected.add(new Card(Value.K, Suit.HEARTS));

        game.lastPlayedCards = new ArrayList<Card>();
        game.lastPlayedCards.add(new Card(Value.EIGHT, Suit.HEARTS));

        game.playButtonClicked(playButtonClicked);

        ArrayList lastPlayedCardsAfter = new ArrayList<Card>();
        lastPlayedCardsAfter.add(new Card(Value.K, Suit.HEARTS));
        assertEquals("lastPlayedCards", lastPlayedCardsAfter, game.lastPlayedCards);

        System.out.println("Before:");
        System.out.println("currHand.getSelected()");
        for (Card card : game.currHand.getSelected()) {
            System.out.println(card);
        }
        System.out.println("this.selected");
        for (Card card : game.selected) {
            System.out.println(card);
        }
        System.out.println("lastPlayedCards");
        for (Card card : game.lastPlayedCards) {
            System.out.println(card);
        }


        System.out.println("After:");
        System.out.println("currHand.getSelected()");
        for (Card card : game.currHand.getSelected()) {
            System.out.println(card);
        }
        System.out.println("this.selected");
        for (Card card : game.selected) {
            System.out.println(card);
        }
        System.out.println("lastPlayedCards");
        for (Card card : game.lastPlayedCards) {
            System.out.println(card);
        }
        System.out.println(game.lastPlayedCards.size());
    }
}
