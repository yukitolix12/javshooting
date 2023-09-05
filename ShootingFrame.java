import javax.swing.*;

public class ShootingFrame extends JFrame {
    public ShootingPanel panel;
    
    public ShootingFrame(){

        panel = new ShootingPanel();

        this.add(panel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shooting");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
}
