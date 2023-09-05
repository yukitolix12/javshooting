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
        int fps = 30;
        while(loop){
            startTime = System.currentTimeMillis();

            gra.setColor(Color.WHITE);
            gra.fillRect(0,0,500,500);

            try{
                Thread.sleep((1000 / fps) - (System.currentTimeMillis() = startTime));
            } catch (InterruptedExeption e){
                e.printStackTrace();
            }
        }
    }
}