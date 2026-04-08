import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizPage extends JFrame {

	private static final long serialVersionUID=1L;
    java.util.List<Question> questions;
    int index = 0;
    int level;
    ScoreManager sm = new ScoreManager();

    JLabel questionLabel;
    JButton[] optionBtns;

    public QuizPage(int level) {
    	
        this.level=level;
        this.questions=TestDB.getQuestions(level);
        setTitle("Eco Quiz 🌍");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ❌ remove plain background
        // getContentPane().setBackground(new Color(232, 245, 233));

        /*questions = new Question[] {
                new Question("Do you recycle waste?",
                        new String[] { "Always", "Sometimes", "Rarely", "Never" },
                        new int[] { 1, 1, 1, 1 }),

                new Question("How do you save water?",
                        new String[] { "Turn off tap", "Use bucket", "Ignore", "Waste" },
                        new int[] { 1, 1, 1, 1 })
        };*/

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 26));
        questionLabel.setForeground(new Color(60, 30, 10)); // 🔥 better for board

        JPanel optionPanel = new JPanel();
        optionPanel.setOpaque(false); // 🔥 important (transparent)
        optionPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridx = 0;

        optionBtns = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionBtns[i] = new RoundedButton("");
            optionBtns[i].setPreferredSize(new Dimension(420, 65));

            gbc.gridy = i;
            optionPanel.add(optionBtns[i], gbc);
        }

        // 🔥 WRAPPER PANEL
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false); // 🔥 transparent
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        optionPanel.setMaximumSize(new Dimension(450, 500));

        wrapperPanel.add(questionLabel);
        wrapperPanel.add(Box.createVerticalStrut(40));
        wrapperPanel.add(optionPanel);

        // 🔥 CENTER PANEL
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // 🔥 transparent
        centerPanel.add(wrapperPanel);

        // 🏴‍☠️ BACKGROUND PANEL
        ImagePanel bgPanel = new ImagePanel("assets/quizboard.png");
        bgPanel.setLayout(new GridBagLayout());

        bgPanel.add(centerPanel);

        // ✅ FINAL ADD
        add(bgPanel);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (index < questions.size()) {

            questionLabel.setText(questions.get(index).question);

            java.util.List<Integer> order = new java.util.ArrayList<>();
            for (int i = 0; i < 4; i++)
                order.add(i);

            java.util.Collections.shuffle(order);

            for (int i = 0; i < 4; i++) {

                int shuffledIndex = order.get(i);

                optionBtns[i].setText(questions.get(index).options[shuffledIndex]);

                int score = questions.get(index).scores[shuffledIndex];

                for (ActionListener al : optionBtns[i].getActionListeners()) {
                    optionBtns[i].removeActionListener(al);
                }

                optionBtns[i].addActionListener(e -> {
                    sm.addScore(score);
                    index++;
                    loadQuestion();
                });
            }

        } else {
            dispose();
            new ResultPage(level,sm.getScore());
        }
    }
}

// 🔥 BACKGROUND CLASS (same as yours)
class ImagePanel extends JPanel {
	private static final long serialVersionUID=1L;
    private Image image;

    public ImagePanel(String path) {
        image = new ImageIcon(path).getImage();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}