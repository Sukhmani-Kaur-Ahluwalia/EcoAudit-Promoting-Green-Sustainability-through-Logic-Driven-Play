 import  java.awt.*;
 import javax.swing.*;
 
 class ImagePanel extends JPanel{
        Image bg;
        float opacity;
        public ImagePanel(String p) {
        this(p, 0.0f);}
        public ImagePanel(String p,float f){
            this.opacity=f;
            java.net.URL imgurl= getClass().getResource(p);
            if(imgurl!=null){
                bg= new ImageIcon(imgurl).getImage();

               
            }else{
                System.out.println("error. image could not load ");
            }
        }
        protected  void paintComponent(Graphics g){
            super.paintComponent(g);
            if(bg!=null){
                g.drawImage(bg, 0, 0, getWidth(),getHeight(),this);
                 //whitish overlay
                Graphics2D g2= (Graphics2D) g.create();
               
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));
                g2.setColor(Color.black);
                 g2.fillRect(0, 0, getWidth(), getHeight());
                 g2.dispose();
            }}}

           