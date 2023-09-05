import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShootingPanel extends JPanel{
    public BufferedImage gra;
    
    public ShootingPanel(){
        super();
    }

    //Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(gra,0,0, this);
    }

    public void draw(){
        this.repaint();
    }
}
