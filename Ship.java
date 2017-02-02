
/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Ship
{
    Block[] blocks;
    String name;
    public Ship(Block[] b, String s)
    {
        blocks=b;
        name= s;
        for (int i=0; i< blocks.length; i++)
            blocks[i].setColor(Color.red);
        }
        
    public void addToGrid(BoundedGrid<Block> gr)
    {
        for (int i=0; i< blocks.length; i++){
            blocks[i].putSelfInGrid(gr, blocks[i].getLocation());
            blocks[i].setColor(Color.black);
            blocks[i].setPartOfShip();
        }
    }
    
    public boolean isSunk()
    {
        for(int i =0; i< blocks.length; i++)
        {
            if (!blocks[i].isHit())
                return false;
        }
        return true;
    }
        
    public String getName()
    {
        return name;
    }
    
    public Location[] getLocations()
    {
        Location[] locs= new Location[blocks.length];
        
        for (int i=0; i< blocks.length; i++)
            locs[i]= blocks[i].getLocation();
            
        return locs;
    }
    
    public String toString()
    {
        String str= "";
        str+= name;
        for (int i=0; i<blocks.length; i++)
            str+=blocks[i].getLocation().toString();
            
        return str;    
    }
}
