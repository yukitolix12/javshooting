import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        int playerX = 0,playerY = 0;
        int bulletInterval = 0;
        ArrayList<Bullet> bullets_player = new ArrayList<>();
        ArrayList<Bullet> bullets_enemy = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();

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
                        bullets_player = new ArrayList<>();
                        enemies = new ArrayList<>();
                        playerX = 235;
                        playerY = 430;
                    }
                    break;    
                case GAME:
                    gra.setColor(Color.BLUE);
                    gra.fillRect(playerX + 10,playerY,10,10);
                    gra.fillRect(playerX,playerY + 10,30,10);

                    gra.setColor(Color.BLUE);
                    for(int i = 0; i < bullets_player.size(); i++){
                        Bullet bullet = bullets_player.get(i);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y -= 10;
                        if(bullet.y<0) bullets_player.remove(i);
                    }

                    gra.setColor(Color.RED);

                    for(Enemy enemy : enemies){

                    }

                    for(int i = 0; i < bullets_enemy.size(); i++){
                        Bullet bullet = bullets_enemy.get(i);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y += 10;
                        if(bullet.y > 500) bullets_enemy.remove(i);
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&playerX>0) playerX-=5;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)&&playerX<470) playerX+=5;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_UP)&&playerY>30) playerY-=5;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)&&playerY<450) playerY+=5;
            
                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                        bullets_player.add(new Bullet(playerX + 12, playerY));
                        bulletInterval = 5;
                    }
                    if(bulletInterval>0) bulletInterval--;
            
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