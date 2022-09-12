import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BigTwo extends JFrame implements ActionListener {
    // GUI related objects
    JPanel mainPanel;
    JPanel panelH1 = new JPanel();
    JPanel panelH2 = new JPanel();
    JPanel panelH3 = new JPanel();
    JPanel panelH4 = new JPanel();
    JPanel[] panelsForDifferentHands = {panelH1, panelH2, panelH3, panelH4};
    JButton playButton;

    CardLayout cl = new CardLayout();

    int currentPlayerId;
    ArrayList<Card> lastPlayed;
    Player[] players;


    public BigTwo() {
        currentPlayerId = 1;
        lastPlayed = new ArrayList<>();
        players = new Player[5]; // Size 5 for easier access by 1-indexing

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

        // Draws the frame
        this.setTitle("Big Two");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1920, 600);
        ImageIcon icon = new ImageIcon("src/main/icon.png");
        this.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(cl);
        for (int i = 0; i < 4; i++) {
            Hand hand = hands[i];
            JPanel panel = panelsForDifferentHands[i];
            panel.setBorder(new EmptyBorder(40, 0, 20, 0));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            addHeader(hand, panel);
            addLastPlayedCards(lastPlayedCards, panel);
            addHand(hand, panel);
            addPlayButton(panel);

            int handNumber = i + 1; // Converts from 0-based to 1-based
            String handNumberString = Integer.toString(handNumber);
            mainPanel.add(panel, handNumberString);
        }
        this.add(mainPanel);
        this.changeFont(mainPanel);
        this.setVisible(true);
    }

    public boolean isValid(ArrayList<Card> curr, ArrayList<Card> selected) {

        if (curr.size() == selected.size() || curr.size() == 0) {
            if (curr.size() == 1 || curr.size() == 3) {
                Card selectedCard = selected.get(0);
                Card currCard = curr.get(0);
                System.out.println(selectedCard.compareTo(currCard) > 0);
                return selectedCard.compareTo(currCard) > 0;
            }

            if (curr.size() == 2) {
                Collections.sort(selected);
                Collections.sort(curr);
                Card largestSelectedCard = selected.get(curr.size() - 1);
                Card largestCurrCard = curr.get(curr.size() - 1);
                return largestSelectedCard.compareTo(largestCurrCard) > 0;
            }

            if (selected.size() == 4) {
                return false;
            }

            if (selected.size() == 5) {
                FiveCards[] combinations = {new RoyalFlush(selected), new FourOfAKind(selected), new FullHouse(selected), new Flush(selected), new Straight(selected)};
                for (FiveCards combination : combinations) {
                    if (combination.isValid()) {
                        // TODO: turn lastPlayedCards into the appropriate FiveCards object and implement compareTo
                        return combination.compareTo(currObject) > 0;
                    }
                }
            }
        }

        System.out.println(false);
        return false;
    }

    public static void main(String[] args) {
        new BigTwo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If clicked button is a card
        if (e.getSource() instanceof JToggleButton) {
            cardButtonClicked(e);
        }

        // If clicked button is the play button
        if (e.getSource() instanceof JButton) {
            playButtonClicked(e);
        }
    }

    public void cardButtonClicked(ActionEvent e) {
        JToggleButton cardButton = (JToggleButton) e.getSource();
        Card card = (Card) cardButton.getClientProperty("card");
        if (cardButton.isSelected()) {
            selected.add(card);
        } else {
            selected.remove(card);
        }
    }

    public void playButtonClicked(ActionEvent e) {
        this.currHand.setSelected(selected);
        if (isValid(lastPlayedCards, selected)) {
            lastPlayedCards.clear();
            for (Card card : selected) {
                lastPlayedCards.add(card);
            }
            selected.clear();
            currHandIndex += 1;
            currHandIndex %= 3;
            String currHandIndexString = Integer.toString(currHandIndex);
            System.out.println(currHandIndex);
            cl.show(mainPanel, currHandIndexString); // Switches to the next player
        } else {
            System.out.println("Invalid play!");
        }

    }

    public void addHeader(Hand hand, JPanel panel) {
        JPanel headingPanel = new JPanel();
        headingPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JLabel headingLabel = new JLabel("Player " + hand.getID() + "'s turn");
        headingPanel.add(headingLabel);
        panel.add(headingPanel);
    }

    // JPanel to show the most recently played card(s)
    public void addLastPlayedCards(ArrayList<Card> curr, JPanel panel) {
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
        panel.add(currPanel);
    }

    // JPanel to show card(s) the player currently have
    public void addHand(Hand hand, JPanel panel) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setBorder(new EmptyBorder(40, 0, 20, 0));
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
        for (Card card : hand.getCards()) {
            JToggleButton cardButton = new JToggleButton();
            cardButton.setFocusable(false);
            cardButton.setMargin(new Insets(0, 0, 0, 0));
//            cardButton.setBorder(null); // If border is null, it is impossible to tell if a card is selected. But it looks nicer
            cardButton.setIcon(card.getImageIcon());
            cardButton.addActionListener(this);
            cardButton.putClientProperty("card", card);
            cardButton.putClientProperty("hand", hand);
            cardsPanel.add(cardButton);
        }
        panel.add(cardsPanel);
    }

    // Play button
    public void addPlayButton(JPanel panel) {
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        buttonPanel.add(playButton);
        panel.add(buttonPanel);
    }

    // Helper method to change font of all components
    public void changeFont(Container parent) {
        for (Component component : parent.getComponents()) {
            if (component instanceof JPanel) {
                System.out.println(component);
                changeFont((Container) component);
            }
            component.setFont(new Font("Arial", Font.PLAIN, 40));
        }
    }

    private ArrayList<Card> deck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

    private void distribute() {

    }


    /* Methods to implement
    * Calls out the person with 3 of Diamonds to play first
    * Checks if play is valid
    * Determines if game is over (whoever's hand is empty)
     */
}
