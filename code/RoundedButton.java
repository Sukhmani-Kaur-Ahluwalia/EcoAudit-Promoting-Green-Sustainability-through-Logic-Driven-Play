import java.awt.*;
import javax.swing.*;
public class RoundedButton  extends  JButton{
    private  static final long serialVersionUID= 1L;
    private int radicorner=30;
    public RoundedButton(String label){
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setVisible(true);
        setForeground(Color.WHITE);
        setFont(new Font("TIMES NEW ROMAN ", Font.BOLD,20));
    }
        protected void paintComponent(Graphics g){
            Graphics2D g2= (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //transparent 3d effect
            g2.setColor(new Color(46,204,113,30));
            g2.fillRoundRect(3, 3, getWidth(), getHeight(), radicorner, radicorner);
           
            if(getModel().isPressed()){
                g2.setColor(new Color(41, 128, 185));
            g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radicorner, radicorner);
            }else{
                g2.fillRoundRect(3, 3, getWidth()-3, getHeight()-3, radicorner,radicorner );
                g2.setColor(new Color(0,0,0,30));

                g2.setColor(new Color(26,102,26));
                g2.fillRoundRect(0,0,getWidth()-3,getHeight()-3,radicorner,radicorner);

            }
            super.paintComponent(g2);
            g2.dispose();

            }
        }
    
  

