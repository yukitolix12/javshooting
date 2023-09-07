import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {

    private ArrayList <Integer> pressedButtons = new ArrayList<>();

    public Keyboard(){
        pressedButtons = new ArrayList<>();
    }

    public boolean isKeyPressed(int keyCode){
        return pressedButtons.contains(keyCode);
    }

    //override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        if(!pressedButtons.contains(e.getKeyCode()))pressedButtons.add(e.getKeyCode());
    }

    //override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
        pressedButtons.remove((Integer) e.getKeyCode());
    }
}
