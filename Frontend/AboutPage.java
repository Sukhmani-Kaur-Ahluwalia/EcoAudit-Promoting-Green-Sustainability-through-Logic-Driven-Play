// AboutPage shows info about the EcoAudit project
// the text types itself out one character at a time using a thread - looks cool

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public AboutPage() {

        setTitle("About EcoAudit");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // main background panel - light gray-green color
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(220, 230, 230));
        bgPanel.setLayout(new GridBagLayout());

        // the wooden board card that holds the text
        BoardPanel boardCard = new BoardPanel();

        // text area where we type the description letter by letter
        JTextArea descArea = new JTextArea();
        
        descArea.setLineWrap(true);
        descArea.setForeground(Color.WHITE);
        descArea.setFont(new Font("Serif", Font.BOLD, 17));
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);

        // wrapper adds padding around the text inside the board image
        JPanel textWrapper = new JPanel(new BorderLayout());
        textWrapper.add(descArea, BorderLayout.CENTER);
        textWrapper.setBorder(BorderFactory.createEmptyBorder(120, 80, 60, 80));
        textWrapper.setOpaque(false);
        
        System.out.println("EcoAudit: About");
        // back button goes back to welcome screen
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                // user already logged in if they got here
                boolean alreadyIn = true;
                WelcomePage wp = new WelcomePage(alreadyIn);
            }
        });

        // panel at the bottom for the back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(backBtn);
        
        boardCard.add(bottomPanel, BorderLayout.SOUTH);
        boardCard.add(textWrapper, BorderLayout.CENTER);

        bgPanel.add(boardCard);
        add(bgPanel);

        // the full project description text we want to display
        String fullDesc = "EcoAudit Adventure is an interactive quiz game designed to promote awareness about "
                + "environmental responsibility in a fun and delighful way. "
                + "Players moves through levels that present real life scenarios and questions related to eco friendly habits and daily choices. "
                + "Based on his responses, users earns scores that reflect their understanding and impact on the environment. "
                + "The project combines data structures with logical thinkings while maintaining a simple and appealing interface. "
                + "It encourages players to adopt more better habits and make good choices, inspiring them to contribution toward protecting the environment.";

        // count the total characters manually so we know when to stop the loop
        int totalChars = 0;
        for (int i = 0; i < fullDesc.length(); i++) {
            totalChars = totalChars + 1;
        }

        System.out.println("total chars to type: " + totalChars);

        // run the typing animation on a separate thread so it doesn't freeze the UI
        Thread typingThread = new Thread(new Runnable() {
            public void run() {
                // go through each character one at a time
                for (int charIdx = 0; charIdx < totalChars; charIdx++) {
                    char c = fullDesc.charAt(charIdx);
                    String oneChar = "" + c;
System.out.println(oneChar);

                    // swing UI updates must happen on the event dispatch thread
                    final String letterToAdd = oneChar;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            descArea.append(letterToAdd);
                        }
                    });

                    // wait 25ms between each character
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
            }
        });

        typingThread.start();
        setVisible(true);
    }

    // this inner class draws the wooden board image as the card background
    class BoardPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Image boardImg;

        public BoardPanel() {
            boardImg = new ImageIcon(getClass().getResource("/assets/board.png")).getImage();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(650, 500));
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // draw the board image stretched to fill the panel
            g.drawImage(boardImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
