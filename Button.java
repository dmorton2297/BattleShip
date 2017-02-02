
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
public class Button extends JButton
{
    private Location loc;
    private Boolean used= false;
   public Button(Location l)
   {
       super();
       loc= l;
    }
    
   public Location getLoc()
   {
       return loc;
    }
    
   public void setUsed()
   {
       used= true;
    }
    
   public boolean isUsed()
   {
       return used;
    }
}
