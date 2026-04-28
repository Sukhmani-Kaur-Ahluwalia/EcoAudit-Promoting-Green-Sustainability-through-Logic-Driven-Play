// WelcomePage is the first screen the player sees
// it shows the login form, and after login shows the main navigation buttons
// password is hardcoded as "1234" for now (should use a real system later)

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame {

    private static final long serialVersionUID = 1L;

    public WelcomePage(boolean loggedIn) {

             setTitle("EcoAudit - Promoting Green Sustainability");
             setExtendedState(JFrame.MAXIMIZED_BOTH);
              setDefaultCloseOperation(EXIT_ON_CLOSE);
System.out.println("Setting the screen");

        // outer background panel - muted green color
        JPanel outerBg = new JPanel(new BorderLayout());
        outerBg.setBackground(new Color(220, 230, 230));

        // map image wallpaper behind the card
        MapBgPanel mapWallpaper = new MapBgPanel("/assets/map_bg.png");
        mapWallpaper.setLayout(new BoxLayout(mapWallpaper, BoxLayout.Y_AXIS));
mapWallpaper.setOpaque(false);
        mapWallpaper.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        

        // big title at the top of the card
        JLabel titleLbl = new JLabel("EcoAudit Adventure");
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
   titleLbl.setFont(new Font("Serif", Font.BOLD, 36));
        titleLbl.setForeground(new Color(60, 30, 10));
                             System.out.println("We have set up the card successfully.");

        // tagline under the title
    JLabel taglineLbl = new JLabel("Track  Improve  Sustain");
        taglineLbl.setFont(new Font("Arial", Font.PLAIN, 16));
                     taglineLbl.setForeground(new Color(80, 60, 40));
        taglineLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ------- navigation buttons (shown only after login) -------

        JButton startBtn  = makeBtn("Start Journey", new Color(76, 175, 80));
        JButton aboutBtn  = makeBtn("About Project", new Color(66, 133, 244));
        JButton exitBtn   = makeBtn("Exit", new Color(219, 68, 55));

        // panel that holds the 3 nav buttons stacked vertically
        JPanel navBtnPanel = new JPanel();
        navBtnPanel.setOpaque(false);
        navBtnPanel.setLayout(new BoxLayout(navBtnPanel, BoxLayout.Y_AXIS));
        navBtnPanel.add(startBtn);
        navBtnPanel.add(Box.createVerticalStrut(15));
        navBtnPanel.add(aboutBtn);
        navBtnPanel.add(Box.createVerticalStrut(15));
        navBtnPanel.add(exitBtn);

        // ------- login form fields -------

        JLabel loginHdrLbl = new JLabel("Login");
        loginHdrLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // username text field with gray placeholder
        JTextField userField = new JTextField("Enter your username");
        userField.setMaximumSize(new Dimension(400, 40));
        userField.setForeground(Color.GRAY);
        userField.setCaretPosition(0);

        // clear the placeholder when user starts typing
        userField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // check if the field still shows the placeholder text
                boolean stillPlaceholder = userField.getText().equals("Enter your username");
                if (stillPlaceholder == true) {
                    userField.setText("");
                    userField.setForeground(Color.BLACK);
                }
            }
        });
        //issues to be resolved in login and get tested by Sejal

        // restore placeholder if user leaves the field empty
        userField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                boolean isEmpty = userField.getText().isEmpty();
                if (isEmpty == true) {
                    userField.setText("Enter your username");
                    userField.setForeground(Color.GRAY);
                }
            }
        });

        // password field - echo char 0 so placeholder text is visible
        JPasswordField passField = new JPasswordField("Enter your password");
        passField.setMaximumSize(new Dimension(400, 40));
        passField.setForeground(Color.GRAY);
        passField.setEchoChar((char) 0);

        // clear placeholder when player starts typing their password
        passField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String currentVal = String.valueOf(passField.getPassword());
                boolean isPlaceholder = currentVal.equals("Enter your password");
                if (isPlaceholder == true) {
                           passField.setText("");
              passField.setForeground(Color.BLACK);
                    passField.setEchoChar('*');
                }
            }
        });

        // restore placeholder if player leaves password field blank
        passField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String currentVal = String.valueOf(passField.getPassword());
                boolean isEmpty = currentVal.isEmpty();
                if (isEmpty == true) {
                    passField.setText("Enter your password");
                    passField.setForeground(Color.GRAY);
                    passField.setEchoChar((char) 0);
                }
            }
        });

        JButton signInBtn = makeBtn("Sign In", new Color(76, 175, 80));

        // link-style label for sign up (not functional yet)
        JLabel signUpLbl = new JLabel("Don't have an account? Sign up >");
        signUpLbl.setForeground(new Color(66, 133, 244));
        signUpLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ------- glass content card -------

        EcoGlassCard glassCard = new EcoGlassCard();
        glassCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        glassCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        glassCard.setMaximumSize(new Dimension(550, 650));

        // if player is already logged in, skip the form and show nav directly
        if (loggedIn == true) {
            navBtnPanel.setVisible(true);
            userField.setVisible(false);
            passField.setVisible(false);
            signInBtn.setVisible(false);
            loginHdrLbl.setVisible(false);
            // System.out.println("user already logged in, skipping login form");
        } else {
            // show the login form, hide the nav buttons for now
            navBtnPanel.setVisible(false);
        }

        // ------- sign in button logic -------
        signInBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // grab what the player typed as their password
                String typedPass = new String(passField.getPassword());

                // System.out.println("typed password: " + typedPass);

                // check against the hardcoded correct password
                boolean correct = false;
                if (typedPass.equals("1234")) {
                    correct = true;
                }

                if (correct == true) {
                    JOptionPane.showMessageDialog(null, "Login Successful! Welcome to EcoAudit!");

                    // hide login fields and show nav buttons
                    navBtnPanel.setVisible(true);
                    userField.setVisible(false);
                    passField.setVisible(false);
                    signInBtn.setVisible(false);
                    loginHdrLbl.setVisible(false);

                    // refresh the card so the layout updates
                    glassCard.revalidate();
                    glassCard.repaint();

                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password. Hint: it's 1234");
                }
            }
        });

        // ------- nav button actions -------

        // start journey - go to level map
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LevelPage lp = new LevelPage();
            }
        });

        // about - go to about page
        aboutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                AboutPage ap = new AboutPage();
            }
        });

        // exit - close the whole application
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ------- add everything to the glass card -------

        glassCard.add(titleLbl);
        glassCard.add(Box.createVerticalStrut(12));
        glassCard.add(taglineLbl);
        glassCard.add(Box.createVerticalStrut(30));
        glassCard.add(navBtnPanel);
        glassCard.add(Box.createVerticalStrut(30));
        glassCard.add(loginHdrLbl);
        glassCard.add(Box.createVerticalStrut(15));
        glassCard.add(userField);
        glassCard.add(Box.createVerticalStrut(15));
        glassCard.add(passField);
        glassCard.add(Box.createVerticalStrut(20));
        glassCard.add(signInBtn);
        glassCard.add(Box.createVerticalStrut(15));
        glassCard.add(signUpLbl);
System.out.println("Glass card made successfully.");
        // center the card vertically on the map
        mapWallpaper.add(Box.createVerticalGlue());
        mapWallpaper.add(glassCard);
        mapWallpaper.add(Box.createVerticalGlue());

        outerBg.add(mapWallpaper, BorderLayout.CENTER);
        add(outerBg);
        setVisible(true);
    }

    // helper method to build a colored nav button quickly
    // i got tired of repeating the same 6 lines every time
    private JButton makeBtn(String txt, Color col) {
        JButton b = new JButton(txt);
        b.setMaximumSize(new Dimension(400, 55));
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBackground(col);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        return b;
    }

    // this inner class draws the map image as the page background
    class MapBgPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Image mapImg;

        public MapBgPanel(String imgPath) {
            mapImg = new ImageIcon(getClass().getResource(imgPath)).getImage();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(mapImg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    int Leader()
    {
       return 100;
    }
    // this inner class is the frosted glass card that holds the login/nav content
    class EcoGlassCard extends JPanel {

        private static final long serialVersionUID = 1L;

        public EcoGlassCard() {
            setOpaque(false);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
       System.out.println("Glass Card Setup:"+ Leader();

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            // draw a semi-transparent white rounded rectangle
            g2.setColor(new Color(255, 255, 255, 200));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
        }
    }
}
