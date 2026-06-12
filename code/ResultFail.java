
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultFail extends JFrame {
    public ResultFail(int lvl, int score, double percent){
        System.out.println("ResultPage opened");
        setTitle("Try Again 🌿");
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel title= new JLabel("😅 Level Not Cleared");
        title.setFont(new Font("Segoe UI Emoji",Font.BOLD,24));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cornerMsg = new JLabel("\"Don’t worry, try again & save the planet 🌱\"");
        cornerMsg.setAlignmentX(CENTER_ALIGNMENT);
         JLabel scorLabel= new JLabel("Your Score "+score);
        scorLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton retyButton= new RoundedButton("RETRY LEVEL");
        retyButton.setAlignmentX(CENTER_ALIGNMENT);
        retyButton.addActionListener((ActionEvent e) -> {
            dispose();
            new LevelPage();
        });

        JButton exitbutton= new RoundedButton("HOME");
        exitbutton.setAlignmentX(CENTER_ALIGNMENT);
        exitbutton.addActionListener((ActionEvent e) -> {
            dispose();
            new WelcomePage(true);
        });
add(Box.createVerticalStrut(30));
        add(title);
add(Box.createVerticalStrut(20));
        add(scorLabel);
add(Box.createVerticalStrut(30));
        add(cornerMsg);
add(Box.createVerticalStrut(20));
        add(retyButton);
add(Box.createVerticalStrut(10));
       add(exitbutton);
setVisible(true);
    }

    public static void main(String[] args) {
        new ResultFail(1, 23, 66);
    }
}
