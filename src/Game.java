import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    Hand h1 = new Hand(1);
    Hand h2 = new Hand(2);
    Hand h3 = new Hand(3);
    Hand h4 = new Hand(4);
    Hand[] hands = {h1, h2, h3, h4};
    Deck deck;

    ArrayList<Card> curr;

    public Game() {
        deck = new Deck();

        // Tester method for curr
        curr = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
            curr.add(deck.getDeck().get(i));
        }

        deck.shuffle();
        for (Hand hand : hands) {
            hand.setCards(deck.distribute());
            for (Card card : hand.getCards()) {
            }
        }
        draw(h1);
    }

    public void draw(Hand hand) {

        JFrame frame = new JFrame();
        frame.setTitle("Big Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1920, 600);
        ImageIcon icon = new ImageIcon("src/icon.png");
        frame.setIconImage(icon.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // JPanel to hold heading which indicates which player is playing
        JPanel headingPanel = new JPanel();
        headingPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JLabel headingLabel = new JLabel("Player " + hand.getID() + "'s turn");
        headingPanel.add(headingLabel);
        mainPanel.add(headingPanel);

        // JPanel to show the most recently played card(s)
        JPanel currPanel = new JPanel();
        if (curr.isEmpty()) {
            JLabel emptyLabel = new JLabel("You can play anything this round.");
            currPanel.add(emptyLabel);
        } else {
            currPanel.setLayout(new BoxLayout(currPanel, BoxLayout.X_AXIS));
            for (Card currCard : curr) {
                JLabel currCardLabel = new JLabel();
                currCardLabel.setIcon(currCard.getImageIcon());
                currPanel.add(currCardLabel);
            }
        }
        mainPanel.add(currPanel);

        // JPanel to show card(s) the player currently have
        JPanel cardsPanel = new JPanel();
        cardsPanel.setBorder(new EmptyBorder(40, 0, 20, 0));
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
        for (Card card : hand.getCards()) {
            JButton cardButton = new JButton();
            cardButton.setFocusable(false);
            cardButton.setMargin(new Insets(0, 0, 0, 0));
            //cardButton.setBorder(null);
            cardButton.setIcon(card.getImageIcon());
            cardsPanel.add(cardButton);
        }
        mainPanel.add(cardsPanel);

        // Play button
        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        buttonPanel.add(playButton);
        mainPanel.add(buttonPanel);

        changeFont(mainPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Helper method to change font of all components
    private void changeFont(Container parent) {
        for (Component component : parent.getComponents()) {
            if (component instanceof JPanel) {
                System.out.println(component);
                changeFont((Container) component);
            }
            component.setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    /* Methods to implement
    * Calls out the person with 3 of Diamonds to play first
    * Checks if play is valid
    * Determines if game is over (whoever's hand is empty)
     */
}
