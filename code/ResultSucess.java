import java.awt.*;
import javax.swing.*;

public class ResultSucess extends JFrame {

    int max = 10;

    public ResultSucess(int lvl, int score, double percent) {

        System.out.println("ResultSuccess opened");

        setTitle("LEVEL CLEARED");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
// 🌿 BACKGROUND PANEL
        ImagePanel bg = new ImagePanel("scoreboard.jpg");
        bg.setLayout(new GridBagLayout());
 JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);

      JLabel title = new JLabel("🌍 Eco Hero! Level Cleared");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        title.setForeground(Color.darkGray);
        title.setBackground(new Color(124,252,0));
        title.setOpaque(true);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

       JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
JLabel xp = new JLabel("🌟 Eco XP Earned: 10x+");
        xp.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        xp.setAlignmentX(Component.CENTER_ALIGNMENT);
        String msg;
        if (percent >= 90) {
            msg = "🌍 Planet Saver Mode Activated!";
        } else if (percent >= 70) {
            msg = "🌱 Eco Hero in Progress!";
        } else {
            msg = "🌿 You’re learning to be eco-aware!";
    }

 JLabel message = new JLabel(msg);
        message.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
         message.setForeground(Color.white);
        message.setBackground(new Color(0,139,139));
        message.setOpaque(true);

  ImageIcon icon = new ImageIcon("badge1.png");
        Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(scaled));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

      
 JButton back = new RoundedButton("Back To Home");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        back.addActionListener(e -> {
            dispose();
            new LevelPage();
        });

       
    main.add(Box.createVerticalStrut(30));
    main.add(title);
     main.add(Box.createVerticalStrut(15));
        main.add(scoreLabel);
        main.add(Box.createVerticalStrut(10));
      main.add(xp);
        main.add(Box.createVerticalStrut(20));
        main.add(image);
      main.add(Box.createVerticalStrut(20));
        main.add(message);
      main.add(Box.createVerticalStrut(30));
        main.add(back);

        
     bg.add(main);
        setContentPane(bg);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ResultSucess(1, 23, 85);
    }
}