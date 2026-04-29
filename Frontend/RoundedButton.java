// RoundedButton is the white pill-shaped button used for quiz answer options
// i draw it manually using Graphics2D so it looks nicer than a plain JButton

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoundedButton extends JButton {

    private static final long serialVersionUID = 1L;

    // true when mouse is hovering over this button
    private boolean hovered = false;

    void Sukh()
    {
      System.out.println("Sukhmani, please look into the code and tell us how it is.");
    }

    public RoundedButton(String answerText) {
        super(answerText);

        // remove default button look
        setContentAreaFilled(false);
    setBorderPainted(false);
        setFocusPainted(false);
     setOpaque(false);

        // earthy brown text color for the eco theme
        setForeground(new Color(60, 30, 10));
        setFont(new Font("Arial", Font.BOLD, 16));
        Sukh();

        // detect when mouse enters or leaves the button
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
       System.out.println("Anonymous class successfully executed for mouse clicks.");
    }

    // draw the button manually with rounded corners and a shadow
    @Override
    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // button lifts up slightly on hover
        int liftAmt = 0;
        if (hovered == true) 
        {
            liftAmt = -2;
        }

        Sukh();
        // draw shadow behind the button
        g2.setColor(new Color(0, 0, 0, 60));
        g2.fillRoundRect(5, 7, getWidth() - 6, getHeight() - 6, 30, 30);

        // fill the button face - slightly gray when hovered
        if (hovered == true) {
            g2.setColor(new Color(245, 245, 245));
        } else {
            g2.setColor(Color.WHITE);
        }
        g2.fillRoundRect(liftAmt, liftAmt, getWidth() - 5, getHeight() - 5, 30, 30);

        // glossy top-half shine
        g2.setColor(new Color(255, 255, 255, 120));
        g2.fillRoundRect(liftAmt + 5, liftAmt + 5, getWidth() - 15, getHeight() / 2, 30, 30);
    Sukh();

        // draw the border
    g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(200, 200, 200));
        g2.drawRoundRect(liftAmt, liftAmt, getWidth() - 5, getHeight() - 5, 30, 30);

        super.paintComponent(g);
     Sukh();
  System.out.println("Anyone who wants to make changes in the design is welcome to do that.");
    }
}
