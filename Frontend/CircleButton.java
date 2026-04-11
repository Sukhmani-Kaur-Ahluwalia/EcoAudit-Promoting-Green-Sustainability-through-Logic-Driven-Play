import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleButton extends JButton {

    private static final long serialVersionUID = 1L;

    private boolean isHovered = false;
    private float scale = 1.0f;
    private Color primaryColor = new Color(39, 174, 96); // Emerald Green
    private Color secondaryColor = new Color(123, 237, 159); // Mint Tea

    public CircleButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                scale = 1.1f;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                scale = 1.0f;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Scaling effect
        double xOffset = (width * (1 - scale)) / 2;
        double yOffset = (height * (1 - scale)) / 2;
        g2.translate(xOffset, yOffset);
        g2.scale(scale, scale);

        // Subtle Shadow
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillOval(4, 6, width - 8, height - 8);

        // Gradient Background
        GradientPaint gradient = new GradientPaint(0, 0, isHovered ? secondaryColor : primaryColor,
                0, height, isHovered ? primaryColor : secondaryColor);
        g2.setPaint(gradient);
        g2.fillOval(0, 0, width - 8, height - 8);

        // Glossy Highlight
        g2.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255, 100), 
                                      0, height / 2, new Color(255, 255, 255, 0)));
        g2.fillOval(width / 4, 4, width / 2, height / 3);

        // White Border
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(2, 2, width - 12, height - 12);

        // Centered Text
        FontMetrics fm = g2.getFontMetrics();
        int textX = (width - 8 - fm.stringWidth(getText())) / 2;
        int textY = (height - 8 + fm.getAscent()) / 2 - 2;

        g2.setColor(Color.WHITE);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        return Math.pow(x - radius, 2) + Math.pow(y - radius, 2) <= Math.pow(radius, 2);
    }
}