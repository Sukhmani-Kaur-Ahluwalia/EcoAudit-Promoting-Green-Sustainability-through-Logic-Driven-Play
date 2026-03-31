
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleButton extends JButton {

    private boolean isHovered = false;

    public CircleButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));

        setHorizontalAlignment(SwingConstants.CENTER);

        // Hover detection
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int lift = isHovered ? -3 : 0; // slight upward movement

        // 🔥 Shadow (depth)
        g2.setColor(new Color(0, 0, 0, 70));
        g2.fillOval(6, 10, getWidth() - 8, getHeight() - 8);

        // 🤍 Pure white button
        g2.setColor(Color.WHITE);
        g2.fillOval(lift, lift, getWidth(), getHeight());

        // ✨ Glossy highlight
        g2.setColor(new Color(255, 255, 255, 120));
        g2.fillOval(lift + 5, lift + 5, getWidth() - 10, getHeight() / 2);

        // Border
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(lift, lift, getWidth() - 1, getHeight() - 1);

        // Glow on hover
        if (isHovered) {
            g2.setColor(new Color(255, 255, 255, 150));
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(lift, lift, getWidth() - 1, getHeight() - 1);
        }

        // 📝 Centered text
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 3;

        g2.setColor(Color.BLACK);
        g2.drawString(getText(), x, y);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // no default border
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        int centerX = radius;
        int centerY = radius;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }
}