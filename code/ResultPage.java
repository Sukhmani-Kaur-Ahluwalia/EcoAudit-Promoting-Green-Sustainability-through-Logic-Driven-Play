import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ResultPage extends JFrame {

    double totalPossible = 50.0;
    double percent;
    int max = 10;

    public ResultPage(int lvl, int score) {

        setTitle("Eco Scoreboard 🌍");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        percent = (score / totalPossible) * 100.0;
        boolean pass = percent >= 70.0;

        // ================= BACKGROUND =================
        ImagePanel bg = new ImagePanel("scroll.png");
        bg.setLayout(new GridBagLayout());

        // ================= MAIN PANEL =================
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        // ================= TITLE =================
        JLabel title = new JLabel("🌿 Your Eco Impact Report");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        title.setForeground(new Color(0, 100, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= SCORE =================
        JLabel scoreLabel = new JLabel("🌱 Eco Score: " + score);
        scoreLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel percentLabel = new JLabel("🌍 Green Impact: " + String.format("%.2f", percent) + "%");
        percentLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= IMAGE =================
        ImageIcon mascot = new ImageIcon("pirate.jpeg");
        Image scaled = mascot.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel mascotLabel = new JLabel(new ImageIcon(scaled));
        mascotLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= MESSAGE =================
        String msg;

        if (percent >= 90) {
            msg = "🌍 Planet Saver! You protected nature brilliantly!";
        } else if (percent >= 70) {
            msg = "🌱 Eco Hero! Great sustainable choices!";
        } else {
            msg = "🌿 Good Start! Keep building eco habits!";
        }

        JLabel msgLabel = new JLabel(msg);
        msgLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        msgLabel.setForeground(new Color(0, 90, 90));
        msgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= BUTTON =================
        JButton btn = new RoundedButton(pass ? "Continue Eco Journey 🌱" : "Try Again 🌿");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addActionListener(e -> {

            dispose();

            if (pass) {

                try {
                    FileWriter fw = new FileWriter("unlocked.txt");
                    fw.write(String.valueOf(Math.min(lvl + 1, max)));
                    fw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                new ResultSucess(lvl, score, percent);

            } else {
                new WelcomePage(true);
            }
        });

        // ================= LAYOUT =================
        content.add(Box.createVerticalStrut(20));
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(scoreLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(percentLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(mascotLabel);
        content.add(Box.createVerticalStrut(20));
        content.add(msgLabel);
        content.add(Box.createVerticalStrut(30));
        content.add(btn);

        bg.add(content);
        setContentPane(bg);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ResultPage(1, 35);
    }
}