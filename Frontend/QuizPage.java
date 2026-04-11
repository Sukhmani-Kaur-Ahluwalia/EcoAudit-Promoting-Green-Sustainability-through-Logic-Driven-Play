import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizPage extends JFrame {

    private static final long serialVersionUID = 1L;
    java.util.List<Question> questions;
    int index = 0;
    int level;
    ScoreManager sm = new ScoreManager();

    JLabel questionLabel;
    JButton[] optionBtns;

    public QuizPage(int level) {

        this.level = level;
        this.questions = TestDB.getQuestions(level);
        setTitle("Eco Quiz 🌍");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ❌ remove plain background
        // getContentPane().setBackground(new Color(232, 245, 233));

        /*
         * questions = new Question[] {
         * new Question("Do you recycle waste?",
         * new String[] { "Always", "Sometimes", "Rarely", "Never" },
         * new int[] { 1, 1, 1, 1 }),
         * 
         * new Question("How do you save water?",
         * new String[] { "Turn off tap", "Use bucket", "Ignore", "Waste" },
         * new int[] { 1, 1, 1, 1 })
         * };
         */

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        questionLabel.setForeground(new Color(27, 94, 32));

        JPanel optionPanel = new JPanel();
        optionPanel.setOpaque(false);
        optionPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0); // Balanced vertical gap
        gbc.gridx = 0;

        optionBtns = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionBtns[i] = new RoundedButton("");
            optionBtns[i].setPreferredSize(new Dimension(450, 60));

            gbc.gridy = i;
            optionPanel.add(optionBtns[i], gbc);
        }
        optionPanel.setMaximumSize(new Dimension(800, 320)); // Constrain height

        GlassPanel questionCard = new GlassPanel();
        questionCard.setLayout(new BorderLayout());
        questionCard.setBorder(BorderFactory.createEmptyBorder(15, 30, 5, 30)); // Less bottom padding
        questionCard.add(questionLabel, BorderLayout.CENTER);
        questionCard.setMaximumSize(new Dimension(800, 110)); // Slimmer height

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false);
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

        questionCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        wrapperPanel.add(Box.createVerticalStrut(90)); 
        wrapperPanel.add(questionCard);
        wrapperPanel.add(Box.createVerticalStrut(35)); // Shifted options down for better gap
        wrapperPanel.add(optionPanel);

        wrapperPanel.add(Box.createVerticalGlue());

        // Background Panel
        ImagePanel bgPanel = new ImagePanel("/assets/quizboard.png");
        bgPanel.setLayout(new BorderLayout());
        bgPanel.add(wrapperPanel, BorderLayout.CENTER);

        add(bgPanel);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (index < questions.size()) {
            questionLabel.setText("<html><center>" + questions.get(index).question + "</center></html>");

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
            new ResultPage(level, sm.getScore());
        }
    }

    class GlassPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public GlassPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.setColor(new Color(255, 255, 255, 220));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 30, 30);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image image;

        public ImagePanel(String path) {
            image = new ImageIcon(getClass().getResource(path)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
