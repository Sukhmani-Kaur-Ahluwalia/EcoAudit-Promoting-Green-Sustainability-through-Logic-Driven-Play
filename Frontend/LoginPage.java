import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("EcoAudit 🌱");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to EcoAudit", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton startBtn = new JButton("Start Quiz ▶");

        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new QuizPage();
            }
        });

        add(title, BorderLayout.CENTER);
        add(startBtn, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}