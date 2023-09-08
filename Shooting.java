import java.awt.*;
import java.awt.event.KeyEvent;

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

        EnumShootingScreen screen = EnumShootingScreen.START;

        //GAME
        int playerX;
        int playerY;

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

            switch (screen){
                case START:
                    gra.setColor(Color.BLACK);
                    Font font = new Font("SansSerif", Font.PLAIN, 40);
                    gra.setFont(font);
                    FontMetrics metrics = gra.getFontMetrics(font);
                    gra.drawString("Shooting",250 - (metrics.stringWidth("Shooting") / 2), 100);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Press SPACE to Start",250 - (metrics.stringWidth("Press SPACE to Start") / 2), 160);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
                        screen = EnumShootingScreen.GAME;
                    }
                    break;    
                case GAME:
                    break;
                case GAME_OVER:
                    break;
            }

            gra.setColor(Color.BLACK);
            gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
            gra.drawString(FPS + "FPS", 0, 470);

            shootingFrame.panel.draw();

            try{
                long runTime = System.currentTimeMillis() - startTime;
                if(runTime<(1000 / fps)){
                    Thread.sleep(1000 / fps - (runTime));
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}