
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame {
	
	private static final long serialVersionUID=1L;

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
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setForeground(new Color(27, 94, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Track • Improve • Sustain 🌍");
        subtitle.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 18));
        subtitle.setForeground(new Color(56, 142, 60));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startBtn = new RoundedButton("Start Journey");
        JButton aboutBtn = new RoundedButton("About Project");
        JButton exitBtn = new RoundedButton("Exit");

        // Set dimensions for consistency
        Dimension btnDim = new Dimension(350, 50);
        startBtn.setMaximumSize(btnDim);
        aboutBtn.setMaximumSize(btnDim);
        exitBtn.setMaximumSize(btnDim);

        JLabel loginLabel = new JLabel("Welcome Back");
        loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        loginLabel.setForeground(new Color(27, 94, 32));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField("Username");
        username.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        username.setForeground(Color.GRAY);
        username.setMaximumSize(new Dimension(350, 45));
        username.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                    username.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().isEmpty()) {
                    username.setText("Username");
                    username.setForeground(Color.GRAY);
                }
            }
        });

        JPasswordField password = new JPasswordField("Password");
        password.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        password.setForeground(Color.GRAY);
        password.setMaximumSize(new Dimension(350, 45));
        password.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        password.setEchoChar((char) 0);

        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(password.getPassword()).equals("Password")) {
                    password.setText("");
                    password.setForeground(Color.BLACK);
                    password.setEchoChar('•');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(password.getPassword()).isEmpty()) {
                    password.setText("Password");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0);
                }
            }
        });


        JButton signInBtn = new RoundedButton("Sign In");
        signInBtn.setMaximumSize(new Dimension(350, 50));
        signInBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusMsg = new JLabel("");
        statusMsg.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        statusMsg.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signup = new JLabel("Don't have an account? Sign up");
        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signup.setForeground(new Color(33, 150, 243));
        signup.setAlignmentX(Component.CENTER_ALIGNMENT);

        GlassPanel content = new GlassPanel();
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        content.setMaximumSize(new Dimension(500, 650));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Center alignment for all buttons
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(startBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(aboutBtn);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(exitBtn);

        buttonPanel.setVisible(loggedIn);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        if (loggedIn) {
            username.setVisible(false);
            password.setVisible(false);
            signInBtn.setVisible(false);
            loginLabel.setVisible(false);
            signup.setVisible(false);
        }

        signInBtn.addActionListener(e -> {
            String pass = new String(password.getPassword());

            if (pass.equals("1234")) {
                statusMsg.setText("Login Successful ✅");
                statusMsg.setForeground(new Color(46, 125, 50));
                
                Timer timer = new Timer(800, ev -> {
                    buttonPanel.setVisible(true);
                    username.setVisible(false);
                    password.setVisible(false);
                    signInBtn.setVisible(false);
                    loginLabel.setVisible(false);
                    signup.setVisible(false);
                    statusMsg.setVisible(false);
                    content.revalidate();
                    content.repaint();
                });
                timer.setRepeats(false);
                timer.start();

            } else {
                statusMsg.setText("Invalid Password ❌ Try again");
                statusMsg.setForeground(Color.RED);
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
        content.add(Box.createVerticalStrut(10));
        content.add(statusMsg);
        content.add(Box.createVerticalStrut(10));
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


    class ImagePanel extends JPanel {
    	
    	private static final long serialVersionUID=1L;
    	
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
        private static final long serialVersionUID = 1L;

        public GlassPanel() {
            setOpaque(false);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Glass background
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

            // Subtle inner glow/border
            g2.setColor(new Color(255, 255, 255, 200));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 40, 40);

            // Outer border for depth
            g2.setColor(new Color(0, 0, 0, 20));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);

            g2.dispose();
            super.paintComponent(g);
        }
    }

}

