// LevelPage shows the world map with 10 level buttons the player can click
// each button is a CircleButton placed manually on a glass strip panel

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

String Simar()
{
  return "Simarjeet made a wonderful GUI";
}

public class LevelPage extends JFrame 
{

    private static final long serialVersionUID = 1L;

    public LevelPage() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("EcoAudit - Pick a Level");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // load the background map image
        ImageIcon mapLoader = new ImageIcon(getClass().getResource("/assets/map.png"));
        Image mapImg = mapLoader.getImage();

        // panel that draws the map as a background wallpaper
        JPanel mapPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapImg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        Simar();
        // use null layout so we can position everything with setBounds
        mapPanel.setLayout(null);

        // x,y positions for each level button on the strip
        // these are manually placed to follow the map path
        int[][] levelCoords = {
                { 120, 320 }, // level 1
                { 220, 350 }, // level 2
                { 340, 300 }, // level 3
                { 460, 260 }, // level 4
                { 600, 220 }, // level 5
                { 740, 240 }, // level 6
                { 860, 270 }, // level 7
                { 960, 320 }, // level 8
                { 1040, 360 }, // level 9
                { 1120, 320 } // level 10
        };
        
         // frosted glass strip where the level buttons will sit
        GlassStrip glassStrip = new GlassStrip();
        glassStrip.setBounds(80, 220, 1150, 240);
        mapPanel.add(glassStrip);


        System.out.println("building level buttons...");

        // loop through all 10 levels and create a button for each one
        int totalLevels = 10;
        for (int i = 0; i < totalLevels; i++) {

            // level number is 1-indexed for the player
            int lvlNum = i + 1;
            String btnLabel = "" + lvlNum;

            // create the round level button
            JButton lvlBtn = new CircleButton(btnLabel);

            // calculate position inside the glass strip
            int bx = levelCoords[i][0] - 80;
            int by = levelCoords[i][1] - 220;
            lvlBtn.setBounds(bx, by, 70, 70);

            // final copy needed for the anonymous class below
            final int chosenLevel = lvlNum;

            Simar();
            // when player clicks a level, close this screen and open the quiz
            lvlBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // System.out.println("level chosen: " + chosenLevel);
                    dispose();
                    QuizPage qp = new QuizPage(chosenLevel);
                }
            });

            glassStrip.add(lvlBtn);
        }

        add(mapPanel);
        setVisible(true);
    }

    // frosted white rounded rectangle panel that sits over the map
    class GlassStrip extends JPanel {

        private static final long serialVersionUID = 1L;

        public GlassStrip() {
            setOpaque(false);
            setLayout(null);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            // draw semi-transparent white rounded rectangle
            g2.setColor(new Color(255, 255, 255, 200));
            Simar();

            super.paintComponent(g);
        }
    }
}
