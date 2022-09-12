import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingGraphicsHandler extends JFrame implements ActionListener, GraphicsHandler {
    JPanel mainPanel;
    JPanel panelH1 = new JPanel();
    JPanel panelH2 = new JPanel();
    JPanel panelH3 = new JPanel();
    JPanel panelH4 = new JPanel();
    JPanel[] panelsForDifferentHands = {panelH1, panelH2, panelH3, panelH4};
    JButton playButton;
    CardLayout cl = new CardLayout();

    @Override
    public void setup() {
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

    @Override
    public void redraw() {

    }

    @Override
    public void toggleSelection() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
}
