// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class WelcomePage extends JFrame {
   JLabel title,usernamLabel,passJLabel;
   JTextField username;
   JPasswordField password;
   JButton loginbtn,exibtn;
   JButton   startbtn, abtbtn,backbtn;
   JPanel menu;

   public WelcomePage(boolean log) {
    //IMAGE SET
    ImagePanel bg= new ImagePanel("map_bg.png");
    bg.setLayout(new GridBagLayout());
    setContentPane(bg);
    setTitle("EcoAudit -green Sustainbility ");
   setExtendedState(MAXIMIZED_BOTH);
   setDefaultCloseOperation(EXIT_ON_CLOSE);

     
   // main = new JPanel();
   // main.setLayout(null);
   menu= new JPanel();
   menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));

      title = new JLabel("EcoAudit Adventure");
      title.setFont(new Font("TIMES NEW ROMAN ", Font.BOLD, 40));
      title.setForeground(Color.DARK_GRAY);
      title.setAlignmentX(CENTER_ALIGNMENT);

      usernamLabel=new JLabel("Enter your username :");
      username = new JTextField();
      username.setMaximumSize(new Dimension(300,40));
      passJLabel=new JLabel("Enter your password:");
      password= new JPasswordField();
      password.setMaximumSize(new Dimension(300,40));
     
    
     //CONTAINER PANEL MAIN
        JPanel MapbgJPanel= new JPanel();
    MapbgJPanel.setLayout(new BoxLayout(MapbgJPanel,BoxLayout.Y_AXIS));
    MapbgJPanel.setOpaque(false);
   usernamLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
passJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginbtn= new RoundedButton("LOGIN");
    loginbtn.setAlignmentX(CENTER_ALIGNMENT);
    exibtn= new RoundedButton("EXIT");
    exibtn.setAlignmentX(RIGHT_ALIGNMENT);
    

     add(MapbgJPanel);
      
     
      //MENU BUTTONS
      startbtn= new RoundedButton("START JOURNEY ");
      abtbtn= new RoundedButton("ABOUT PROJECT");
      backbtn= new RoundedButton("LOGOUT");

      //MENU PANEL
      JPanel eg= new JPanel();
      menu.add(startbtn);
      menu.add(Box.createVerticalStrut(15));
      menu.add(abtbtn);
      menu.add(Box.createVerticalStrut(15));
      menu.add(backbtn);
      menu.add(Box.createVerticalStrut(15)); 
     
      menu.setVisible(false);
  
      eg.setLayout(new BoxLayout(eg,BoxLayout.Y_AXIS));
      eg.setBackground(new Color(255,206,27,200));//transpaernt colour
      eg.setOpaque(true);
eg.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(0,0,0), 2),
    BorderFactory.createEmptyBorder(30, 30, 30, 30) 
));
MapbgJPanel.add(Box.createVerticalGlue());
      MapbgJPanel.add(eg);
MapbgJPanel.add(Box.createVerticalGlue());

      eg.add(title);
      eg.add(Box.createVerticalStrut(20));

    startbtn.setAlignmentX(CENTER_ALIGNMENT);
    startbtn.setBackground(new Color(99,141,187));
    backbtn.setAlignmentX(CENTER_ALIGNMENT);
    backbtn.setBackground(new Color(125,192,121));
    abtbtn.setAlignmentX(CENTER_ALIGNMENT);
    exibtn.setAlignmentX(CENTER_ALIGNMENT);
    exibtn.setBackground(new Color(47,161,112));


loginbtn.addActionListener((ActionEvent e) ->{
String pass= new String(password.getPassword());
String useString= username.getText();
 
if(pass.isEmpty() || useString.isEmpty()) JOptionPane.showMessageDialog(null, "Feild can't be left empty!");
   
else if(pass.equals("1234") && !useString.isEmpty()){
    System.out.println("succesfull login ");

    JOptionPane.showMessageDialog(null, "LOGIN SUCCESFULL !");
    //hiding other elemnts
    username.setVisible(false);
    usernamLabel.setVisible(false);
    passJLabel.setVisible(false);
    password.setVisible(false);
    loginbtn.setVisible(false);
   //MENU SHOW
    menu.setVisible(true);
  
menu.setOpaque(true); 
menu.setBackground(new Color(0, 200, 200, 150)); 
menu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
// Har button ke beech 15px ka gap
    revalidate();
    repaint();
}else {
    System.out.println("WRONG ");
    JOptionPane.showMessageDialog(null, "WRONG PASSWORD ");
    
}

});

//action listereners
startbtn.addActionListener((ActionEvent e) ->{
dispose();
new LevelPage();
});
exibtn.addActionListener(e->System.exit(0));

abtbtn.addActionListener((ActionEvent e) ->{
  dispose();
new AboutPage();
});
backbtn.addActionListener((ActionEvent e) -> {
   dispose();
   new WelcomePage(false);
});
//log condition chck
      if(log==true){
        eg.add(menu);
        menu.setVisible(true);
        eg.add(Box.createVerticalStrut(20));
         eg.add(exibtn);

      }else{
        eg.add(usernamLabel);
        eg.add(Box.createVerticalStrut(5));
       eg.add(username);
        eg.add(Box.createVerticalStrut(15));
         eg.add(passJLabel);
        eg.add(Box.createVerticalStrut(5));
        eg.add(password);
        eg.add(Box.createVerticalStrut(20));
        eg.add(menu);
        eg.add(Box.createVerticalStrut(20));
        eg.add(loginbtn);
      }
       
      setVisible(true);
   }
   

   public static void main(String[] var0) {
      WelcomePage wp=new WelcomePage(false);
   }
}
 