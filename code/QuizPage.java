import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class QuizPage extends JFrame{
 ArrayList<Question> qlist;
int qindex=0;
 int currlvl;
 int Earned=0;
 Question currq;
 ScoreManager scoreManager= new ScoreManager();
JLabel qlLabel;
JButton opButtons[];
 private static final long serialVersionUID=1L;
       
 public QuizPage(int lvl){
    setTitle("EcoAudit Quiz - Level "+ lvl);
    setExtendedState(MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    System.out.println("ques frame setting");

    this.currlvl=lvl;
    // qlist= (ArrayList<Question>) TestDB.getQuestions(lvl);  
    qlLabel = new JLabel(" ",JLabel.CENTER);
    //dark forest colour
    qlLabel.setForeground(new Color(255, 255, 255));
    // qlLabel.setBackground(new Color.);
    qlLabel.setFont(new Font("SansSerif",Font.BOLD,24));
    GridBagConstraints gbc= new GridBagConstraints();
    gbc.insets= new Insets(15,0,15,0);//jagah between options
    gbc.gridx=0; //colum =0 means options done in vertical format
    JPanel buttons= new JPanel();
    buttons.setOpaque(false);
    buttons.setLayout(new GridBagLayout());

    opButtons= new JButton[4];

    for(int i=0;i<4;i++){
        int index=i;
        opButtons[i]= new RoundedButton("");
        opButtons[i].setPreferredSize(new Dimension(420, 65));
        opButtons[i].addActionListener(e->{
            Earned= currq.greenScores[index];
            scoreManager.Add(Earned);
            System.out.println("user chose op "+(index+1)+" points earned = "+Earned);
            qindex++;
            loadQuestion();
        });
        gbc.gridy=i;
        buttons.add(opButtons[i], gbc);
    }

    JPanel wrappingJPanel= new JPanel();
    wrappingJPanel.setLayout(new BoxLayout(wrappingJPanel,BoxLayout.Y_AXIS));
    qlLabel.setAlignmentX(CENTER_ALIGNMENT);
    buttons.setAlignmentX(CENTER_ALIGNMENT);
    wrappingJPanel.add(qlLabel);
    wrappingJPanel.add(Box.createVerticalStrut(50));
    wrappingJPanel.add(buttons);
    wrappingJPanel.setOpaque(false);

    //centre panel to make wraapingjpanel in between 
    JPanel centrecard= new JPanel(new GridBagLayout());
    centrecard.setOpaque(false);
    centrecard.setBackground(new Color(0, 0, 0, 0));
    centrecard.add(wrappingJPanel);

    qlist=(ArrayList<Question>) TestDB.getQuestions(lvl);
   
    ImagePanel bgPanel = new ImagePanel("quizboard3.png",0.8f);
    bgPanel.setLayout(new GridBagLayout());
    bgPanel.add(centrecard);
    add(bgPanel);
    loadQuestion();
 setVisible(true);

}//constructor over
int score= scoreManager.getTotalScore();
    void loadQuestion(){
    if(qindex<qlist.size()){
          currq= qlist.get(qindex);
      //wrap whole text
      qlLabel.setText("<html><center>"+currq.QuestionString+"</center></html");

        for(int i=0;i<4;i++){
        //same whole text wrap 
        String text="<html><center>"+currq.answerChoices[i]+"</html></center>";
    
        opButtons[i].setText(text);        
            this.revalidate();//ui refresh
            this.repaint();

    }
        }
        else{System.out.println("q ovver");
JOptionPane.showMessageDialog(this, "Great , Thankyou for answering dude");

this.dispose();
new DYKpage(currlvl,scoreManager.getTotalScore());
//result page call up 
      }


     }
     
     public static void main(String[] args) {
         new QuizPage(1);
     }//this func ends
}    //quizpage class over bracket
 