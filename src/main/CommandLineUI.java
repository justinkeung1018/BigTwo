import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CommandLineUI implements UI {
    BigTwo game;

    public CommandLineUI(BigTwo game) {
        this.game = game;
    }

    @Override
    public void start() {
        Player winner = game.winner();

        while (winner == null) {
            Player player = game.currentPlayer();
            System.out.println("Player " + player.id() + "'s turn");

            List<Card> lastPlayed = game.lastPlayed();
            System.out.println("Last played cards:");
            for (Card card : lastPlayed) {
                System.out.println(card);
            }
            System.out.println();

            List<Card> cards = player.cards();
            System.out.println("You have " + cards.size() + " cards:");
            for (Card card : cards) {
                System.out.println(card);
            }
            System.out.println();

            System.out.println("Type the cards you want to play one by one, starting a new line after each card.");
            System.out.println("Press [Enter] on an empty line to play the cards.");
            System.out.println("Type \"Abandon\" and press [Enter] to restart your selection.");
            System.out.println("Type \"Pass\" to forfeit your turn.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    boolean hasPlayed = tryPlay(player);
                    if (hasPlayed) {
                        break;
                    }
                } else if (input.equals("Abandon")) {
                    cls();
                } else if (input.equals("Pass")) {
                    System.out.println("You passed your turn.");
                    break;
                } else {
                    Card card = searchCardFromString(cards, input);
                    if (card == null) {
                        System.out.println("Error: not a valid card.");
                        continue;
                    } else {
                        player.toggleSelection(card);
                        System.out.println(card);
                    }
                }
            }
            winner = game.winner();
        }
        System.out.println("Player " + winner.id() + " won!");
    }

    @Override
    public void setup() {

    }

    public void redraw() {

    }

    @Override
    public void toggleSelection(Card card) {

    }

    @Override
    public void play() {

    }

    @Override
    public void pass() {

    }

    public void toggleSelection() {

    }

    private Card searchCardFromString(List<Card> cards, String string) {
        String trimmed = string.trim(); // To remove the \n after each entry
        for (Card card : cards) {
            if (card.toString().equals(string.trim())) {
                return card;
            }
        }
        return null;
    }

    /**
     * Tries to play the selected cards of the specified player.
     * @param player The player.
     * @return Whether the player successfully played their selected cards.
     */
    private boolean tryPlay(Player player) {
        List<Card> selected = player.selected();
        if (game.canPlay(selected)) {
            System.out.println("You played:");
            for (Card selectedCard : selected) {
                System.out.println(selectedCard);
            }
            game.play();
            return true;
        } else {
            System.out.println("Error: your cards cannot be played. Please enter your cards again.");
            return false;
        }
    }

    private void cls(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
