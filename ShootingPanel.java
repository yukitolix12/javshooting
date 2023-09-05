public class ShootingPanel extends JPanel{
    public BufferImage gra;
    
    public ShootingPanel(){
        super();
    }

    //Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(gra,0,0, this);
    }
}
