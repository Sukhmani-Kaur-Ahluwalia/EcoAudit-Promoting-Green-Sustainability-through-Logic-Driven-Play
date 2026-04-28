// ResultPage shows the player their final score after finishing a quiz level
// also tells them if they passed (>=70%) or need to retry

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public ResultPage(int lvl, int rawScore) {

        setTitle("EcoAudit - Results");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(232, 245, 233));

        // calculate the percentage out of 50 total points
        double totalPossible = 50.0;
        double pct = (rawScore / totalPossible) * 100.0;

        System.out.println("raw score: " + rawScore + "  percent: " + pct);

        // check if the player passed (he needs atleast 70% to pass)
        boolean passed = false;
        if (pct >= 70.0) {
            passed = true;
        }

        // show the score number
        JLabel scoreLbl = new JLabel("Your Green Score: " + rawScore);
           scoreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLbl.setFont(new Font("Arial", Font.BOLD, 28));

        // show the percentage below the score
        JLabel pctLbl = new JLabel("Percentage: " + pct + "%");
  pctLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pctLbl.setFont(new Font("Arial", Font.BOLD, 22));

        // load the eco mascot image and scale it down
        ImageIcon rawIcon = new ImageIcon("assets/pirate.jpeg");

        if (rawIcon.getIconWidth() == -1) {
            System.out.println("mascot image not found - check assets folder");
        }

        Image scaledMascot = rawIcon.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        JLabel mascotLbl = new JLabel(new ImageIcon(scaledMascot));
        mascotLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // message telling player if they passed or failed
        String resultMsg = "";
        if (passed == true) {
            resultMsg = "Level Cleared! Next Level Unlocked";
        } else {
            resultMsg = "Score below 70%. Try Again";
        }

        JLabel resultLbl = new JLabel(resultMsg);
        resultLbl.setFont(new Font("Arial", Font.PLAIN, 18));
        resultLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // button label is different depending on pass or fail
        String btnTxt = "";
        if (passed == true) {
            btnTxt = "Next Level";
        } else {
            btnTxt = "Retry Level";
        }

        JButton actionBtn = new RoundedButton(btnTxt);
        actionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        System.out.println(" Correcting displacement of GUI items.");
        System.out.println("Please someone else also look into this issue");
        //Issue to be resolved by simar

        // handle what happens when the next/retry button is clicked
        actionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

                if (passed == true) {
                    // check if there are more levels left
                    int maxLvl = 10;
                    if (lvl < maxLvl) {
                        int nextLvl = lvl + 1;
                        // System.out.println("loading next level: " + nextLvl);
                        QuizPage qp = new QuizPage(nextLvl);
                    } else {
                        // all 10 levels done!
                        JOptionPane.showMessageDialog(null, "Congratulations! All Eco Levels Completed!");
                        LevelPage lp = new LevelPage();
                    }
                } else {
                    // failed - reload the same level
                    QuizPage retryQp = new QuizPage(lvl);
                }
            }
        });

        // exit button to close the whole app
        JButton exitBtn = new RoundedButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // main content panel - stack everything vertically
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(232, 245, 233));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(scoreLbl);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(pctLbl);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(mascotLbl);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(resultLbl);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(actionBtn);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(exitBtn);
        contentPanel.add(Box.createVerticalGlue());

        // put everything on the scroll background image
        ImagePanel scrollBg = new ImagePanel("assets/scroll.png");
        scrollBg.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);
        scrollBg.add(contentPanel);

        add(scrollBg);
        setVisible(true);
    }
}
