import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShootingPanel extends JPanel{
    public BufferedImage image;
    
    public ShootingPanel(){
        super();
    }

    //Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,0,0, this);
    }

    public void draw(){
        this.repaint();
    }
}
