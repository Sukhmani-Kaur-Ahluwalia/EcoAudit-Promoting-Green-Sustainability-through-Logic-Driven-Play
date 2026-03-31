import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoundedButton extends JButton {

    private boolean isHovered = false;

    public RoundedButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // 🟤 text color (matches parchment theme)
        setForeground(new Color(60, 30, 10));
        setFont(new Font("Arial", Font.BOLD, 16));

        // 🔥 hover detection
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

        int lift = isHovered ? -2 : 0; // slight hover lift

        // 🌑 shadow
        g2.setColor(new Color(0, 0, 0, 60));
        g2.fillRoundRect(5, 7, getWidth() - 6, getHeight() - 6, 30, 30);

        // 🤍 main button (white)
        g2.setColor(isHovered ? new Color(245, 245, 245) : Color.WHITE);
        g2.fillRoundRect(lift, lift, getWidth() - 5, getHeight() - 5, 30, 30);

        // ✨ glossy top effect
        g2.setColor(new Color(255, 255, 255, 120));
        g2.fillRoundRect(lift + 5, lift + 5, getWidth() - 15, getHeight() / 2, 30, 30);

        // 🔲 border
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(lift, lift, getWidth() - 5, getHeight() - 5, 30, 30);

        super.paintComponent(g);
    }
}