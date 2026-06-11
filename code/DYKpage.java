import java.awt.*;
import javax.swing.*;
public class DYKpage extends JFrame {
public DYKpage(int currlvl,int score){

    setTitle("DID YOU KNOW ?");
    setExtendedState(MAXIMIZED_BOTH);
    
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    
    String[] facts = {
    	    "  Around 27,000 trees are cut down every day to make toilet paper.",
    	    "🌲 100 acres of rainforest are destroyed every minute.",
    	    "📰 Recycling one run of the Sunday New York Times could save 75,000 trees.",
    	    "🥩 Beef production is one of the biggest causes of deforestation.",
    	    "💧 Only 1% of Earth's water is safe for human consumption.",
    	    "🌊 About 5 million tons of oil enter oceans every year.",
    	    "🐬 Plastic waste kills over 1 million sea animals annually.",
    	    "🐄 A cow can release 200-400 pounds of methane every year.",
    	    "♻ Recycling paper helps save trees and reduces landfill waste.",
    	    "🚿 A 5-minute shower can save dozens of liters of water."
    	};
    int random= (int)(Math.random()*facts.length);
    JLabel fact= new JLabel(
       "<html><center>"+facts[random]+"</center></html>",JLabel.CENTER);
    fact.setFont(new Font("Segoe UI Emoji", Font.BOLD,48));
    fact.setForeground(Color.BLACK);
    
    ImagePanel bg= new ImagePanel("didyouknow2.png");
    

    bg.setLayout(new BorderLayout());
    bg.add(fact,BorderLayout.CENTER);
  
    add(bg);
    Timer timer= new Timer(3000, e -> {
    	dispose();
      
new ResultPage(currlvl,score);
    	
    });
    timer.setRepeats(false);
    timer.start();
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setVisible(true);
   
}
}
