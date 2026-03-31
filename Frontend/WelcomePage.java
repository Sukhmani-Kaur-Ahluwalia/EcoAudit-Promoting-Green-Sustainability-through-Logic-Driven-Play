
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame {

    public WelcomePage(boolean loggedIn) {

        setTitle("EcoAudit 🌱");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel bgPanel = new JPanel(new BorderLayout());
        bgPanel.setBackground(new Color(220, 230, 230));

        ImagePanel card = new ImagePanel("/assets/map_bg.png");
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        card.setOpaque(false);

        JLabel title = new JLabel("EcoAudit Adventure");
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(new Color(60, 30, 10));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Track • Improve • Sustain 🌍");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(80, 60, 40));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startBtn = createButton("Start Journey", new Color(76, 175, 80));
        JButton aboutBtn = createButton("About Project", new Color(66, 133, 244));
        JButton exitBtn = createButton("Exit", new Color(219, 68, 55));

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField("Enter your username");
        username.setMaximumSize(new Dimension(400, 40));
        username.setForeground(Color.GRAY);

        username.setText("Enter your username");
        username.setForeground(Color.GRAY);
        username.setCaretPosition(0);

        username.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setText("Enter your username");
                    username.setForeground(Color.GRAY);
                }
            }
        });

        username.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (username.getText().equals("Enter your username")) {
                    username.setText("");
                    username.setForeground(Color.BLACK);
                }
            }
        });

        JPasswordField password = new JPasswordField("Enter your password");
        password.setMaximumSize(new Dimension(400, 40));
        password.setForeground(Color.GRAY);
        password.setEchoChar((char) 0);

        password.setText("Enter your password");
        password.setForeground(Color.GRAY);
        password.setEchoChar((char) 0);
        username.setCaretPosition(0);

        password.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (String.valueOf(password.getPassword()).isEmpty()) {
                    password.setText("Enter your password");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0);
                }
            }
        });

        password.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(password.getPassword()).equals("Enter your password")) {
                    password.setText("");
                    password.setForeground(Color.BLACK);
                    password.setEchoChar('•');
                }
            }
        });
        JButton signInBtn = createButton("Sign In", new Color(76, 175, 80));

        JLabel signup = new JLabel("Don’t have an account? Sign up >");
        signup.setForeground(new Color(66, 133, 244));
        signup.setAlignmentX(Component.CENTER_ALIGNMENT);

        GlassPanel content = new GlassPanel();
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        content.setMaximumSize(new Dimension(550, 650));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(startBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(aboutBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(exitBtn);

        buttonPanel.setVisible(loggedIn);

        if (loggedIn) {
            username.setVisible(false);
            password.setVisible(false);
            signInBtn.setVisible(false);
            loginLabel.setVisible(false);
        }

        signInBtn.addActionListener(e -> {
            String pass = new String(password.getPassword());

            if (pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful ✅");

                buttonPanel.setVisible(true);
                username.setVisible(false);
                password.setVisible(false);
                signInBtn.setVisible(false);
                loginLabel.setVisible(false);

                content.revalidate();
                content.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Password ❌");
            }
        });

        startBtn.addActionListener(e -> {
            dispose();
            new LevelPage();
        });

        aboutBtn.addActionListener(e -> {
            dispose();
            new AboutPage();
        });

        exitBtn.addActionListener(e -> System.exit(0));

        content.add(title);
        content.add(Box.createVerticalStrut(12));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(30));
        content.add(buttonPanel);
        content.add(Box.createVerticalStrut(30));
        content.add(loginLabel);
        content.add(Box.createVerticalStrut(15));
        content.add(username);
        content.add(Box.createVerticalStrut(15));
        content.add(password);
        content.add(Box.createVerticalStrut(20));
        content.add(signInBtn);
        content.add(Box.createVerticalStrut(15));
        content.add(signup);

        card.add(Box.createVerticalGlue());
        card.add(content);
        card.add(Box.createVerticalGlue());

        bgPanel.add(card, BorderLayout.CENTER);
        add(bgPanel);

        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(400, 55));
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(String path) {
            image = new ImageIcon(getClass().getResource(path)).getImage();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    class GlassPanel extends JPanel {
        public GlassPanel() {
            setOpaque(false);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255, 255, 255, 200));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
        }
    }
}
