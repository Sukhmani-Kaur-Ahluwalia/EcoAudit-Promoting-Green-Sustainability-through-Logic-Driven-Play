import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class RoundedButton extends JButton {

    private static final long serialVersionUID = 1L;

    private boolean isHovered = false;
    private Color normalColor = new Color(46, 204, 113); // Fresh Mint Green
    private Color hoverColor = new Color(39, 174, 96);  // Deeper Green
    private float scale = 1.0f;

    public RoundedButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                animateScale(1.05f);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                animateScale(1.0f);
                repaint();
            }
        });
    }

    private void animateScale(float target) {
        scale = target;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Apply scaling for hover effect
        double xOffset = (width * (1 - scale)) / 2;
        double yOffset = (height * (1 - scale)) / 2;
        g2.translate(xOffset, yOffset);
        g2.scale(scale, scale);

        // Shadow - softer and more natural
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRoundRect(4, 6, width - 8, height - 8, 25, 25);

        // Background Gradient
        GradientPaint gradient = new GradientPaint(0, 0, isHovered ? hoverColor : normalColor,
                0, height, isHovered ? normalColor : hoverColor);
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, width - 8, height - 8, 25, 25);

        // White border glow
        if (isHovered) {
            g2.setColor(new Color(255, 255, 255, 100));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, width - 10, height - 10, 25, 25);
        }

        // Text
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D textBounds = fm.getStringBounds(getText(), g2);
        int textX = (int) ((width - 8 - textBounds.getWidth()) / 2);
        int textY = (int) ((height - 8 - textBounds.getHeight()) / 2 + fm.getAscent());

        g2.setColor(Color.WHITE);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }
}