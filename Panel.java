
/**
 * Write a description of class Panel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
public class Panel extends JPanel
{
    public boolean partOfShip= false;
    public boolean notPartOfShip = false;
    public boolean isHit= false;
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getHeight(), this.getWidth());
        if (partOfShip)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getHeight(), this.getWidth());
            g.drawRect(0, 0, this.getHeight(), this.getWidth());
        }
        if (notPartOfShip)
        {
               g.setColor(Color.red);
                g.fillRect(0, 0, this.getHeight(), this.getWidth());
                g.drawRect(0, 0, this.getHeight(), this.getWidth());
        }
        if (partOfShip && isHit)
        {
            g.setColor(Color.blue);
            g.fillRect(0, 0, this.getHeight(), this.getWidth());
            g.drawRect(0, 0, this.getHeight(), this.getWidth());
        }
    }

    public void setNotPartOfShip()
    {
        notPartOfShip= true;
    }

    public void setHit()
    {
        isHit= true;
    }
    
    public void setPartOfShip()
    {
        partOfShip= true;
    }
}
