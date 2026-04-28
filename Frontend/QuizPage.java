// QuizPage is the main quiz screen
// it loads questions for the chosen level and shows them one by one
// when the player picks an answer we add the score and move on

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuizPage extends JFrame {

    private static final long serialVersionUID = 1L;

    // list of eco questions for this level
    ArrayList<Question> questionList;

    // which question we are currently showing (0 = first)
    int qIndex = 0;

    // which level the player is on
    int currentLvl;

    // tracks total score
    ScoreManager scoreTracker = new ScoreManager();

    // label that shows the question text
    JLabel questionLbl;

    // the four answer buttons
    JButton[] optBtns;

    public QuizPage(int lvl) {

        this.currentLvl = lvl;

        // load the questions for this level from the database
        questionList = (ArrayList<Question>) TestDB.getQuestions(lvl);

        // System.out.println("loaded " + questionList.size() + " questions for level " + lvl);

        setTitle("EcoAudit Quiz - Level " + lvl);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("Setting question frame");

        // question label in the center - big serif font
        questionLbl = new JLabel("", JLabel.CENTER);
   questionLbl.setForeground(new Color(60, 30, 10));
        questionLbl.setFont(new Font("Serif", Font.BOLD, 26));

      GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridx = 0;

        System.out.println("Displayed question");

        // panel to hold the 4 answer buttons in a column
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.setLayout(new GridBagLayout());


        // create the 4 answer buttons
        optBtns = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optBtns[i] = new RoundedButton("");
            optBtns[i].setPreferredSize(new Dimension(420, 65));
            gbc.gridy = i;
            btnPanel.add(optBtns[i], gbc);
        }

        // wrapper stacks question label and buttons vertically
        JPanel wrapPanel = new JPanel();
        wrapPanel.setOpaque(false);
        wrapPanel.setLayout(new BoxLayout(wrapPanel, BoxLayout.Y_AXIS));

        questionLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(450, 500));

        wrapPanel.add(questionLbl);
        wrapPanel.add(Box.createVerticalStrut(40));
        wrapPanel.add(btnPanel);
        System.out.println("Option boxes drawn");

        // center everything in the screen
        JPanel centerPanel = new JPanel(new GridBagLayout());
                    centerPanel.setOpaque(false);
        centerPanel.add(wrapPanel);

        // quiz board background image
    ImagePanel bgPanel = new ImagePanel("assets/quizboard.png");
        bgPanel.setLayout(new GridBagLayout());
                         bgPanel.add(centerPanel);

        add(bgPanel);

        // load the first question right away
        loadQuestion();

                                                     setVisible(true);
    }

    void loadQuestion() {

        int totalQ = questionList.size();

        // check if there are still questions left to show
        if (qIndex < totalQ) {

            Question currQ = questionList.get(qIndex);

            // set the question text on the label
            questionLbl.setText(currQ.ecoQuestion);

            // build an array of indices 0,1,2,3 then shuffle it manually
            // this randomizes which answer appears in which button slot
            int[] shuffleArr = new int[4];
            for (int i = 0; i < 4; i++) {
                shuffleArr[i] = i;
            }

            // manual Fisher-Yates shuffle - swap random pairs
            for (int i = 3; i > 0; i--) {
                int randIdx = (int) (Math.random() * (i + 1));
                // swap shuffleArr[i] and shuffleArr[randIdx]
                int temp = shuffleArr[i];
                shuffleArr[i] = shuffleArr[randIdx];
                shuffleArr[randIdx] = temp;
            }

            // System.out.println("shuffle order: " + shuffleArr[0] + shuffleArr[1] + shuffleArr[2] + shuffleArr[3]);

            // assign shuffled answers to each button
            for (int btnIdx = 0; btnIdx < 4; btnIdx++) {

                int origIdx = shuffleArr[btnIdx];

                // get the answer text and its green score value
                String answerTxt = currQ.answerChoices[origIdx];
                int val1 = currQ.greenScores[origIdx];

                optBtns[btnIdx].setText(answerTxt);

                // remove old action listeners before adding new ones
                // (otherwise old listeners from the previous question are still attached)
                ActionListener[] oldListeners = optBtns[btnIdx].getActionListeners();
                for (int k = 0; k < oldListeners.length; k++) {
                    optBtns[btnIdx].removeActionListener(oldListeners[k]);
                }

                // store score in a final variable so the anonymous class can use it
                final int pointsForThisAnswer = val1;

                // when player clicks this button: record score, advance to next question
                optBtns[btnIdx].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        scoreTracker.addScore(pointsForThisAnswer);
                        // System.out.println("player scored: " + pointsForThisAnswer);
                        qIndex = qIndex + 1;
                        loadQuestion();
                    }
                });
            }

        } else {
            // all questions done - calculate final score and show results
            int totalCarbonSaved = scoreTracker.getScore();
            // System.out.println("quiz over! final score = " + totalCarbonSaved);
            dispose();
            ResultPage rp = new ResultPage(currentLvl, totalCarbonSaved);
        }
    }
}

// this helper class lets us use any image as a panel background
// i put it here at the bottom of this file so it's easy to find
class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image bgImg;

    public ImagePanel(String path) {
        bgImg = new ImageIcon(path).getImage();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // stretch the image to fill the whole panel
        g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
    }
}
