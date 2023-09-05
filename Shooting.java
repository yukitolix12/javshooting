import java.awt.*;

public class Shooting{
    public static ShootingFrame shootingFrame;
    public static boolean loop;

    public static void main(String[] args){
        shootingFrame = new ShootingFrame();
        loop = true;

        Graphics gra = shootingFrame.panel.image.getGraphics();
        
        //FPS
        long startTime;
        while(loop){
            startTime = System.currentTimeMillis();
        }
    }
}