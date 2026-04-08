import javax.swing.*;
import java.awt.*;

public class ResultPage extends JFrame {

	private static final long serialVersionUID=1L;
	
    public ResultPage(int level,int score) {

        setTitle("Result 🌟");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(232, 245, 233));
        
        double percent=(score/50.0)*100.0;
        boolean clear=percent>=70;

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel percentLabel = new JLabel("Percentage: " + percent + "%");
        percentLabel.setFont(new Font("Arial", Font.BOLD, 22));
        percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // 🏴‍☠️ Pirate Image
        ImageIcon pirateIcon = new ImageIcon("assets/pirate.jpeg");

        if (pirateIcon.getIconWidth() == -1) {
            System.out.println("Image not found!");
        }

        Image img = pirateIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel pirateLabel = new JLabel(new ImageIcon(img));
        pirateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*JLabel badge = new JLabel(
                score >= 2 ? "Captain of Sustainability!"
                        : score >= 1 ? "Eco Explorer!"
                                : "Land in Danger!");*/
        JLabel badge=new JLabel(clear ? "Level Cleared! Next Level Unlocked": "Score below 70%. Try Again");
        badge.setFont(new Font("Arial", Font.PLAIN, 18));
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton Btn = new RoundedButton(clear ? "Next Level":"Retry Level");
        Btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        Btn.addActionListener(e -> 
        {
        	dispose();
        
        if(clear)
        {
           if(level<10)
        	  new QuizPage(level+1);
           else
           {
             JOptionPane.showMessageDialog(this,"All Levels Completed!");
             new LevelPage();
           }
        }
        else
        {
          new QuizPage(level);
        }
        });
        
        JButton exitBtn = new RoundedButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(e -> System.exit(0));

        // 🔥 CENTER PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(232, 245, 233));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(percentLabel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(pirateLabel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(badge);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(Btn);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(exitBtn);

        mainPanel.add(Box.createVerticalGlue());

        ImagePanel bgPanel = new ImagePanel("assets/scroll.png");
        bgPanel.setLayout(new GridBagLayout());

        mainPanel.setOpaque(false); // 🔥 important

        bgPanel.add(mainPanel);

        add(bgPanel);
        setVisible(true); // 🔥 THIS WAS MISSING
    }
}
