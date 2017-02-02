/**
 * Block class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;

public class Block
{
    private BoundedGrid<Block> grid;
    private Location location;
    private Color color;
    private boolean hit= false;
    private boolean partOfShip= false;
    private boolean guessed= false;

    //constructs a blue block, because blue is the greatest color ever!
    public Block()
    {
        color = Color.CYAN;
        grid = null;
        location = null;
    }
    
    public void setHit()
    {
        hit= true;
    }
    
    public boolean isHit()
    {
        return hit;
    }
    
    public void setPartOfShip()
    {
        partOfShip= true;
    }
    
    public boolean partOfShip()
    {
        return partOfShip;
    }
    
    public void setGuessed()
    {
        guessed= true;
    }
    public boolean isGuessed()
    {
        return guessed;
    }
    
    public Block(Location loc)
    {
        this();
        location= loc;
    }

    //gets the color of this block
    public Color getColor()
    {
        return color;
    }

    //sets the color of this block to newColor.
    public void setColor(Color newColor)
    {
       color = newColor;
    }

    //gets the grid of this block, or null if this block is not contained in a grid
    public BoundedGrid<Block> getGrid()
    {
        return grid;
    }

    //gets the location of this block, or null if this block is not contained in a grid
    public Location getLocation()
    {
        return location;
    }

    //removes this block from its grid
    //precondition:  this block is contained in a grid
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid= null;
        location= null;
    }

    //puts this block into location loc of grid gr
    //if there is another block at loc, it is removed
    //precondition:  (1) this block is not contained in a grid
    //               (2) loc is valid in gr
    public void putSelfInGrid(BoundedGrid<Block> gr, Location loc)
    {
        Block block= gr.get(loc);
        if (block!= null)
            block.removeSelfFromGrid();
            
        gr.put(loc, this);
        grid= gr;
        location = loc;
    }

    //moves this block to newLocation
    //if there is another block at newLocation, it is removed
    //precondition:  (1) this block is contained in a grid
    //               (2) newLocation is valid in the grid of this block
    public void moveTo(Location newLocation)
    {
        if (newLocation.equals(location))
            return; 
        
        grid.remove(location);
        Block other = grid.get(newLocation);
        if (other!= null)
            other.removeSelfFromGrid();
            
        location= newLocation;
        grid.put(location, this);
    }

    //returns a string with the location and color of this block
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}