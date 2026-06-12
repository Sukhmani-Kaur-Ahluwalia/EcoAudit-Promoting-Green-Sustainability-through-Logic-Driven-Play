// this page is an older login page - not really used anymore
// WelcomePage handles login now but i kept this file just in case

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public LoginPage() 
{
setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("EcoAudit Login");
        setUndecorated(true);
        setLayout(new BorderLayout());

        // label at the top
        JLabel welcomeTxt = new JLabel("Welcome to EcoAudit", JLabel.CENTER);
     welcomeTxt.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));

        // button to go to level page
        JButton goBtn = new JButton("Start Quiz");
        System.out.println("GUI Setup Done");
        // when button is clicked go to the level selection screen
        goBtn.addActionListener(new ActionListener() 
       {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.out.println("going to level page");
                LevelPage lp = new LevelPage();
            }
        });

    add(goBtn, BorderLayout.SOUTH);
        add(welcomeTxt, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
