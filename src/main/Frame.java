import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends JFrame {
    JPanel mainPanel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JButton playButton;
    Hand hand;
    Game game;


    public Frame(Hand hand, Game game) {
        this.setTitle("Big Two");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1920, 600);
        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.hand = hand;
        this.game = game;

        this.add(mainPanel);
    }

    // JPanel to hold heading which indicates which player is playing
    public void drawHeader(Hand hand) {
        JPanel headingPanel = new JPanel();
        headingPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JLabel headingLabel = new JLabel("Player " + hand.getID() + "'s turn");
        headingPanel.add(headingLabel);
        mainPanel.add(headingPanel);
    }

    // JPanel to show the most recently played card(s)
    public void drawCurr(ArrayList<Card> curr) {
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
    }

    // JPanel to show card(s) the player currently have
    public void drawHand(Hand hand) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setBorder(new EmptyBorder(40, 0, 20, 0));
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
        for (Card card : hand.getCards()) {
            JToggleButton cardButton = new JToggleButton();
            cardButton.setFocusable(false);
            cardButton.setMargin(new Insets(0, 0, 0, 0));
            //cardButton.setBorder(null);
            cardButton.setIcon(card.getImageIcon());
            cardButton.addActionListener(this.game);
            cardButton.putClientProperty("card", card);
            cardButton.putClientProperty("hand", hand);
            cardsPanel.add(cardButton);
        }
        mainPanel.add(cardsPanel);
    }

    // Play button
    public void drawButton() {
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        playButton.addActionListener(this.game);
        buttonPanel.add(playButton);
        mainPanel.add(buttonPanel);
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // If clicked button is a card
//        if (e.getSource() instanceof JToggleButton) {
//            JToggleButton cardButton = (JToggleButton) e.getSource();
//            Card card = (Card) cardButton.getClientProperty("card");
//            if (cardButton.isSelected()) {
//                selected.add(card);
//            } else {
//                selected.remove(card);
//            }
//
//        }
//
//        // If clicked button is the play button
//        if (e.getSource() instanceof JButton) {
//            hand.setSelected(selected);
//            for (Card card : hand.getSelected()) {
//                System.out.println(card);
//            }
//        }
//    }
}
