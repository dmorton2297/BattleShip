
/**
 * Write a description of class RoundReport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class RoundReport extends JPanel implements KeyListener
{
    private JPanel pane= new JPanel();
    private String str= "";
    private int count=0;
    private JFrame f= new JFrame();
    public RoundReport(String s)
    {
        f.setUndecorated(true);  
        f.getRootPane().setWindowDecorationStyle(JRootPane.NONE); 
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 100);
        f.add(this);
        f.setLocationRelativeTo(null);
        f.addKeyListener(this);
        str= s;
    }
    
    
    public void paintComponent(Graphics g)
    {
        g.drawRect(0, 0, 298, 98);
        g.drawString(str, 10, 15);
        g.drawString("Press space to continue", 10, 30);
    }
    
    public void keyPressed(KeyEvent e)
    {
        count++;
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
    
    public void close()
    {
        f.dispose();
    }
}

