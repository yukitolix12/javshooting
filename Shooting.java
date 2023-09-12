import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

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
        int score = 0;
        int level = 0;
        long levelTimer = 0;
        ArrayList<Bullet> bullets_player = new ArrayList<>();
        ArrayList<Bullet> bullets_enemy = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        Random random = new Random();

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
                        bullets_enemy = new ArrayList<>();
                        enemies = new ArrayList<>();
                        playerX = 235;
                        playerY = 430;
                        score = 0;
                        level = 1;
                    }
                    break;    
                case GAME:
                    if(System.currentTimeMillis() - levelTimer > 10 * 1000){
                        levelTimer = System.currentTimeMillis();
                        level++;
                    }

                    gra.setColor(Color.BLUE);
                    gra.fillRect(playerX + 10,playerY,10,10);
                    gra.fillRect(playerX,playerY + 10,30,10);

                    gra.setColor(Color.BLUE);
                    for(int i = 0; i < bullets_player.size(); i++){
                        Bullet bullet = bullets_player.get(i);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y -= 10;
                        if(bullet.y<0) bullets_player.remove(i);

                        for(int l = 0; l < enemies.size(); l++){
                           Enemy enemy = enemies.get(l);
                           if(bullet.x>=enemy.x&&bullet.x<=enemy.x+30&&
                           bullet.y>=enemy.y&&bullet.y<=enemy.y+20){
                               enemies.remove(l);
                               score += 10;
                            }
                        } 
                    }

                    gra.setColor(Color.RED);

                    for(int i = 0; i < enemies.size(); i++){
                        Enemy enemy = enemies.get(i);
                        gra.fillRect(enemy.x, enemy.y, 30, 10);
                        gra.fillRect(enemy.x + 10, enemy.y + 10, 10, 10);                        
                        enemy.y += 3;
                        if(enemy.y > 500) enemies.remove(i);
                        if(random.nextInt(80)==i) bullets_enemy.add(new Bullet(enemy.x, enemy.y));
                        if((enemy.x>=playerX&&enemy.x<=playerX+30&&
                           enemy.y>=playerY&&enemy.y<=playerY+20) ||
                           (enemy.x+30>=playerX&&enemy.x+30<=playerX+30&& 
                           enemy.y+20>=playerY&&enemy.y+20<=playerY+20)){
                            screen = EnumShootingScreen.GAME_OVER;
                        }
                    }
                    if(random.nextInt(30)==1) enemies.add(new Enemy(random.nextInt(470),0));

                    for(int i = 0; i < bullets_enemy.size(); i++){
                        Bullet bullet = bullets_enemy.get(i);
                        gra.fillRect(bullet.x, bullet.y, 5, 5);
                        bullet.y += 10;
                        if(bullet.y > 500) bullets_enemy.remove(i);
                        if(bullet.x>=playerX&&bullet.x<=playerX+30&&
                        bullet.y>=playerY&&bullet.y<=playerY+30){
                            screen = EnumShootingScreen.GAME_OVER;
                        }      
                    }

                    if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT)&&playerX>0) playerX-=8;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT)&&playerX<470) playerX+=8;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_UP)&&playerY>30) playerY-=8;
                    if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN)&&playerY<450) playerY+=8;
            
                    if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)&&bulletInterval==0){
                        bullets_player.add(new Bullet(playerX + 12, playerY));
                        bulletInterval = 5;
                    }
                    if(bulletInterval>0) bulletInterval--;
            
                    gra.setColor(Color.BLACK);
                    font = new Font( "SansSerif", Font.PLAIN, 20);
                    metrics = gra.getFontMetrics(font);
                    gra.setFont(font);
                    gra.drawString( "SCORE " + score, 470 - metrics.stringWidth("SCOREE:" + score), 430);
                    gra.drawString( "LEVEL " + level, 470 - metrics.stringWidth("LEVEL" + level), 450);
                    break;

                case GAME_OVER:
                    gra.setColor(Color.BLACK);
                    font = new Font("SansSerif", Font.PLAIN, 50);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Game Over",250 - (metrics.stringWidth("Game Over") / 2), 100);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Score:"+score,250 - (metrics.stringWidth("Score:"+score) / 2), 150);
                    gra.drawString("Press ESC to Return Start Screen",250 - (metrics.stringWidth("Press ESC to Return Start Screen") / 2), 200);
                    if(Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)){
                        screen = EnumShootingScreen.START;
                    }
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