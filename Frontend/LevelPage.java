import javax.swing.*;
import java.awt.*;

public class LevelPage extends JFrame {

	private static final long serialVersionUID=1L;
	
    public LevelPage() {

        setTitle("Select Level 🗺️");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background map
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/map.png"));
        Image bgImg = icon.getImage();
        JPanel panel = new JPanel() {

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);
        GlassPanel glass = new GlassPanel();
        glass.setBounds(100, 200, 1100, 300);
        panel.add(glass);

        JLabel selectTitle = new JLabel("Eco Odyssey Map 🗺️", SwingConstants.CENTER);
        selectTitle.setFont(new Font("Segoe UI", Font.BOLD, 42));
        selectTitle.setForeground(Color.WHITE);
        selectTitle.setBounds(0, 80, 1366, 60); // Shifted down
        panel.add(selectTitle);

        int[][] positions = {
                { 120, 350 }, // 1 (Shifted right)
                { 220, 380 }, // 2
                { 340, 330 }, // 3
                { 460, 290 }, // 4
                { 600, 250 }, // 5
                { 740, 270 }, // 6
                { 860, 300 }, // 7
                { 960, 350 }, // 8
                { 1040, 390 }, // 9
                { 1120, 350 } // 10
        };



        for (int i = 0; i < 10; i++) {
            JButton levelBtn = new CircleButton("" + (i + 1));
            int x = positions[i][0] - 100;
            int y = positions[i][1] - 200;
            levelBtn.setBounds(x, y, 75, 75);
            int level = i + 1;

            levelBtn.addActionListener(e -> {
                dispose();
                new QuizPage(level);
            });
            glass.add(levelBtn);
        }
        add(panel);
        setVisible(true);
    }

    class GlassPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        public GlassPanel() {
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Glass background
            g2.setColor(new Color(255, 255, 255, 160));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

            // Subtle border
            g2.setColor(new Color(255, 255, 255, 200));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 40, 40);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}