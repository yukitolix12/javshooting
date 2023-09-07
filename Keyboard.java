import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {

    private ArrayList<integer> pressedButtons;

    public Keyboard(){
        pressedButtons = new ArrayList<>();
    }

    //override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
    }

    //override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
    }
}
