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
        long fpsTime = 0;
        int fps = 30;
        int FPS = 0;
        int FPSCount = 0;
        while(loop){
            if((System.currentTimeMillis() - fpsTime) >= 1000){
                fpsTime = System.currentTimeMillis();
                FPS = FPSCount;
                FPSCount = 0;
                System.out.println(FPS);
            }
            FPSCount++;
            startTime = System.currentTimeMillis();

            gra.setColor(Color.WHITE);
            gra.fillRect(0,0,500,500);

            gra.setColor(Color.BLACK);
            gra.fillRect(100,100,100,100);
            shootingFrame.panel.draw();

            try{
                Thread.sleep(1000 / fps - (System.currentTimeMillis() - startTime));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() - startTime);
        }
    }
}