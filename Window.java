
/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
public class Window extends JPanel implements KeyListener
{
    String text;
    int width, height;
    JFrame frame;
    private int count=0;
    public Window(int x, int y)
    {
        width= x;
        height= y;
        frame= new JFrame();
        frame.addKeyListener(this);
        frame.add(this);
        frame.setLocationRelativeTo(null);
    }
    
    public void createGUI()
    {
        Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocation((dim.width/2-frame.getSize().width/2)- (width/2), 
                            (dim.height/2-this.getSize().height/2)-(height*2));
        frame.setSize(width, height);
        frame.setTitle("BattleShip by Dan Morton");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void keyPressed(KeyEvent e)
    {
        count++;
        frame.dispose();
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    public void keyTyped(KeyEvent e)
    {
        keyPressed(e);
    }
    
    public void waitForUser()
    {
        if (count<1)
        {
            try{Thread.sleep(100);}catch(Exception e){}
            waitForUser();
        }
    }
    public void paintComponent(Graphics g)
    {
        g.drawString("-BattleShip. Follow the instructions prompted at the top of the screen.", 10, 20);
        g.drawString("-Blocks MUST be placed adjacent to each other", 10, 40);
        g.drawString("-Press enter to continue", 10, 60);
    }
}
