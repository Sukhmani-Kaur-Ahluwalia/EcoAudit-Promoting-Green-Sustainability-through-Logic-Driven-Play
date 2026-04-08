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

        panel.setLayout(null); // absolute positioning
        GlassPanel glass = new GlassPanel();
        glass.setBounds(80, 220, 1150, 240);
        panel.add(glass);
        int[][] positions = {
                { 120, 320 }, // 1
                { 220, 350 }, // 2
                { 340, 300 }, // 3
                { 460, 260 }, // 4
                { 600, 220 }, // 5
                { 740, 240 }, // 6
                { 860, 270 }, // 7
                { 960, 320 }, // 8
                { 1040, 360 }, // 9
                { 1120, 320 } // 10
        };

        for (int i = 0; i < 10; i++) {

            JButton levelBtn = new CircleButton("" + (i + 1));

            int x = positions[i][0] - 80;
            int y = positions[i][1] - 220;

            levelBtn.setBounds(x, y, 70, 70);

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
    	
    	private static final long serialVersionUID=1L;
        public GlassPanel() {
            setOpaque(false);
            setLayout(null); // IMPORTANT for absolute positioning
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // semi-transparent white blur effect
            g2.setColor(new Color(255, 255, 255, 200));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

            super.paintComponent(g);
        }
    }
}