import javax.swing.*;
import java.awt.*;

public class ResultPage extends JFrame {

    public ResultPage(int score) {

        setTitle("Result 🌟");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(232, 245, 233));

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🏴‍☠️ Pirate Image
        ImageIcon pirateIcon = new ImageIcon("assets/pirate.jpeg");

        if (pirateIcon.getIconWidth() == -1) {
            System.out.println("Image not found!");
        }

        Image img = pirateIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel pirateLabel = new JLabel(new ImageIcon(img));
        pirateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel badge = new JLabel(
                score >= 2 ? "Captain of Sustainability!"
                        : score >= 1 ? "Eco Explorer!"
                                : "Land in Danger!");
        badge.setFont(new Font("Arial", Font.PLAIN, 18));
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitBtn = new RoundedButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(e -> System.exit(0));

        // 🔥 CENTER PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(232, 245, 233));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(pirateLabel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(badge);
        mainPanel.add(Box.createVerticalStrut(35));
        mainPanel.add(exitBtn);

        mainPanel.add(Box.createVerticalGlue());

        ImagePanel bgPanel = new ImagePanel("assets/scroll.png");
        bgPanel.setLayout(new GridBagLayout());

        mainPanel.setOpaque(false); // 🔥 important

        bgPanel.add(mainPanel);

        add(bgPanel);
        setVisible(true); // 🔥 THIS WAS MISSING
    }
}
