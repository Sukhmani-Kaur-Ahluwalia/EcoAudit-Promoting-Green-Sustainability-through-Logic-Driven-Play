// CircleButton is a custom round button used on the level map screen
// i had to override paintComponent to draw it manually because JButton is square by default

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleButton extends JButton {

    private static final long serialVersionUID = 1L;

    // tracks if the mouse is hovering so we can change how it looks
    private boolean mouseOnBtn = false;
    
    int Me()
    {
      return 0;
    }

    public CircleButton(String levelNum) {
        super(levelNum);

        // turn off default button painting
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        Me();

        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));
        setHorizontalAlignment(SwingConstants.CENTER);

        // listen for hover so the button lights up
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                mouseOnBtn = true;
                repaint();
                Me();
            }
            public void mouseExited(MouseEvent e) {
                mouseOnBtn = false;
                repaint();
            }
        });
    }

    // this method draws the button ourselves instead of using default swing drawing
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // button moves up a little when hovered to look interactive
        int lift = 0;
        if (mouseOnBtn == true) {
            lift = -3;
        }


        // draw the main white circle
        g2.setColor(Color.WHITE);
        g2.fillOval(lift, lift, getWidth(), getHeight());

        // draw the shadow circle behind the button
        g2.setColor(new Color(0, 0, 0, 70));
        g2.fillOval(6, 10, getWidth() - 8, getHeight() - 8);

        
        // draw the border ring
        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(lift, lift, getWidth() - 1, getHeight() - 1);


        // draw a semi-transparent white oval on the top half for the glossy effect
        g2.setColor(new Color(255, 255, 255, 120));
        g2.fillOval(lift + 5, lift + 5, getWidth() - 10, getHeight() / 2);

        // draw the level number text in the center of the circle
        FontMetrics fm = g2.getFontMetrics();
        String txt = getText();
        int tx = (getWidth() - fm.stringWidth(txt)) / 2;
        int ty = (getHeight() + fm.getAscent()) / 2 - 3;
        g2.setColor(Color.BLACK);
        g2.drawString(txt, tx, ty);
 
        
        // extra glow ring when mouse is hovering
        if (mouseOnBtn == true) {
            g2.setColor(new Color(255, 255, 255, 150));
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(lift, lift, getWidth() - 1, getHeight() - 1);
        }

    }

    @Override
    protected void paintBorder(Graphics g) {
        // don't draw default border, we handle it above
    }

    // only count a click if it's inside the circle shape, not the square area around it
    @Override
    public boolean contains(int x, int y) {
        int r = getWidth() / 2;
        int cx = r;
        int cy = r;

        // use distance formula to check if point is inside the circle
        double dist = Math.pow(x - cx, 2) + Math.pow(y - cy, 2);
        double rSquared = Math.pow(r, 2);

        if (dist <= rSquared) {
            return true;
        } else {
            return false;
        }
    }
}
