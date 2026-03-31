
import javax.swing.*;
import java.awt.*;

public class AboutPage extends JFrame {

    public AboutPage() {

        setTitle("About EcoAudit 🌿");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel bg = new JPanel();
        bg.setBackground(new Color(220, 230, 230));
        bg.setLayout(new GridBagLayout());

        BoardPanel card = new BoardPanel();

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 17));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);

        JPanel textWrapper = new JPanel(new BorderLayout());
        textWrapper.setOpaque(false);
        textWrapper.setBorder(BorderFactory.createEmptyBorder(120, 80, 60, 80));
        textWrapper.add(textArea, BorderLayout.CENTER);

        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> {
            dispose();
            new WelcomePage(true); // 🔥 FIXED
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backBtn);

        card.add(textWrapper, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        bg.add(card);
        add(bg);

        String text = "EcoAudit Adventure is an interactive quiz game designed to promote awareness about environmental responsibility in a fun and engaging way. "
                + "Players move through levels that present real life scenarios and questions related to eco friendly habits and daily choices. "
                + "Based on their responses, users earn scores that reflect their understanding and impact on the environment. "
                + "The project combines data structures with logical thinking while maintaining a simple and visually appealing interface. "
                + "It encourages players to adopt better habits and make sustainable choices, inspiring them to contribute positively toward protecting the environment.";

        new Thread(() -> {
            for (int i = 0; i < text.length(); i++) {
                final int index = i;
                SwingUtilities.invokeLater(() -> {
                    textArea.append(String.valueOf(text.charAt(index)));
                });

                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        setVisible(true);
    }

    class BoardPanel extends JPanel {
        private Image image;

        public BoardPanel() {
            image = new ImageIcon(getClass().getResource("/assets/board.png")).getImage();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(650, 500));
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
