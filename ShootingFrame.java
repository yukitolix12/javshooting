import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShootingFrame extends JFrame {
    public ShootingPanel panel;
    
    public ShootingFrame(){

        panel = new ShootingPanel();
        panel.setSize(500, 500);

        this.add(panel);

        this.addWindowListener(new WindowAdapter(){
            //override
            public void windowClosed(WindowEvent e){
                super.windowClosed(e);
                Shooting.loop = false;
            }
        });

        this.addKeyListener(new Keyboard());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shooting");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
}
