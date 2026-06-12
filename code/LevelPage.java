import java.awt.*;
import java.awt.event.*;
import  java.io.*;
import java.util.Scanner;
import javax.swing.*;
public class LevelPage extends JFrame{

   int[][] levelCoords = {
        { 250, 320 }, // Level 1     
            { 350, 350 }, // Level 2 (+100 X)
        { 450, 400 }, // Level 3
        { 550, 600 }, // Level 4
        { 650, 220 }, // Level 5
        { 750, 440 }, // Level 6
        { 850, 210 }, // Level 7
        { 950, 320 }, // Level 8
        { 1050, 360 },// Level 9
        { 1150, 220} // Level 10 (Screen ke andar ekdum safe zone mein)
    };

    private int getUnlockLvel(){
        try{
            File f= new File("unlocked.txt");
            if(f.exists()){
                Scanner sc= new Scanner(f);
                if(sc.hasNextInt()){
                    int lvl= sc.nextInt();
                    sc.close();
                    return lvl;
                }
            sc.close();
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public LevelPage(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("EcoAudit - Levels playground");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon loader= new ImageIcon(getClass().getResource("map.png"));
        Image maping = loader.getImage();

        JPanel map= new JPanel(){
        protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(maping, 0, 0, getWidth(),getHeight(),this);

}
} ; 
map.setLayout(null); 
int maxunlocked= getUnlockLvel();

for(int i=0;i<10;i++){
    int x= levelCoords[i][0];
    int y= levelCoords[i][1];
    String num= ""+(i+1);
    final int lvlnum=i+1;
      JButton lvlButton= new CircleButton(num);
      lvlButton.setBounds(x,y,70,70);

      if(lvlnum<=maxunlocked){
        lvlButton.setEnabled(true);
        lvlButton.addActionListener((ActionEvent e)->{
dispose();
new QuizPage(lvlnum);

      });
      }else{
        lvlButton.setText("🔒");
        lvlButton.addActionListener((ActionEvent e)->{
            
            JOptionPane.showMessageDialog(null, "This level is locked 🔒 for now .");
        });
      }
      map.add(lvlButton);
      

}    
 JButton backmenubtn= new RoundedButton("BACK TO MENU ");
 
backmenubtn.addActionListener((ActionEvent e)->{
dispose();
new WelcomePage(true);
});


backmenubtn.setBounds(30,30,160,40);
map.add(backmenubtn);
add(map);
setVisible(true);

      

    }
    public static void main(String[] args) {
        LevelPage pg= new LevelPage();
    }
}
//heyaaa