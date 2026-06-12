import java.awt.*;
import javax.swing.*;

public class CircleButton extends JButton{

    public CircleButton(String num){
        super(num);
        //square box things removed
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
//circle convert button

    }
    protected  void paintComponent(Graphics g){
        
Graphics2D g2= (Graphics2D) g;
//smooth edges ke liye
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g2.setColor(Color.DARK_GRAY);
g2.fillOval(0, 0, getWidth(), getHeight());
setForeground(Color.white);
setFont(new Font("ariel", Font.BOLD,18));


super.paintComponent(g);

    }
    public static void main(String args[]){
        CircleButton cb= new CircleButton("1");
    }
}

