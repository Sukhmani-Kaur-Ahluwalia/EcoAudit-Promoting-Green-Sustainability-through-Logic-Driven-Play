import javax.swing.*;
import java.awt.*;

public class ResultPage extends JFrame {

	private static final long serialVersionUID=1L;
	
    public ResultPage(int level,int score) {

        setTitle("Result 🌟");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(232, 245, 233));
        
        double percent = (score / 50.0) * 100.0;
        boolean clear = percent >= 70;

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        scoreLabel.setForeground(new Color(27, 94, 32));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel percentLabel = new JLabel("Accuracy: " + percent + "%");
        percentLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        percentLabel.setForeground(new Color(56, 142, 60));
        percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon pirateIcon = new ImageIcon(getClass().getResource("/assets/pirate.jpeg"));
        Image img = pirateIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel pirateLabel = new JLabel(new ImageIcon(img));
        pirateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel badge = new JLabel("<html><center>" + (clear ? "Level Cleared! 🎉<br>Next Destination Unlocked" : "Commitment is Key!<br>Score below 70%. Try Again") + "</center></html>");
        badge.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        badge.setForeground(new Color(60, 30, 10));
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextBtn = new RoundedButton(clear ? "Continue Adventure" : "Retry Mission");
        nextBtn.setMaximumSize(new Dimension(300, 50));
        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextBtn.addActionListener(e -> {
            dispose();
            if (clear) {
                if (level < 10) new QuizPage(level + 1);
                else {
                    JOptionPane.showMessageDialog(this, "Master of Sustainability! All Levels Complete! 🌱");
                    new LevelPage();
                }
            } else {
                new QuizPage(level);
            }
        });

        JButton exitBtn = new RoundedButton("Exit to Hub");
        exitBtn.setMaximumSize(new Dimension(300, 50));
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(e -> {
            dispose();
            new WelcomePage(true);
        });

        // Score Card (Plain Transparent Panel now)
        JPanel scoreCard = new JPanel();
        scoreCard.setOpaque(false);
        scoreCard.setLayout(new BoxLayout(scoreCard, BoxLayout.Y_AXIS));
        scoreCard.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        scoreCard.setMaximumSize(new Dimension(500, 600));

        // Center labels horizontally
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        percentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        badge.setHorizontalAlignment(SwingConstants.CENTER);

        scoreCard.add(scoreLabel);
        scoreCard.add(Box.createVerticalStrut(10));
        scoreCard.add(percentLabel);
        scoreCard.add(Box.createVerticalStrut(20));
        scoreCard.add(pirateLabel);
        scoreCard.add(Box.createVerticalStrut(20));
        scoreCard.add(badge);
        scoreCard.add(Box.createVerticalStrut(30));
        scoreCard.add(nextBtn);
        scoreCard.add(Box.createVerticalStrut(15));
        scoreCard.add(exitBtn);

        // Background Panel
        ImagePanel bgPanel = new ImagePanel("/assets/scroll.png");
        bgPanel.setLayout(new GridBagLayout());
        bgPanel.add(scoreCard);

        add(bgPanel);
        setVisible(true);
    }

    class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image image;
        public ImagePanel(String path) {
            image = new ImageIcon(getClass().getResource(path)).getImage();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}


