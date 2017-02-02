/**
 * BoundedGrid class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

//A BoundedGrid is a rectangular grid with a finite number of rows and columns.
public class BoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    //Constructs an empty BoundedGrid with the given dimensions.
    //(Precondition:  rows > 0 and cols > 0.)
    public BoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    //returns the number of rows
    public int getNumRows()
    {
        return occupantArray.length;
    }

    //returns the number of columns
    public int getNumCols()
    {
        return occupantArray[0].length;
    }

    //returns the object at location loc (or null if the location is unoccupied)
   //precondition:  loc is valid in this grid
    @SuppressWarnings("unchecked")  // needed to suppress compilation warnings
    public E get(Location loc)
    {
        return (E)occupantArray[loc.getRow()][loc.getCol()];
    }

    //puts obj at location loc in this grid and returns the object previously at that location (or null if the
    //location is unoccupied)
    //precondition:  loc is valid in this grid
    public E put(Location loc, E obj)
    {
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    //returns true if loc is valid in this grid, false otherwise
    //precondition:  loc is not null
    public boolean isValid(Location loc)
    {
        return (loc.getRow()< occupantArray.length &&
            loc.getRow()>=0 && loc.getCol()<occupantArray[0].length
            && loc.getCol()>=0);
    }

    //removes the object at location loc from this grid and returns the object that was removed (or null if the
    //location is unoccupied
    //precondition:  loc is valid in this grid
    public E remove(Location loc)
    {
        return put(loc, null);
    }

    //returns a list of all occupied locations in this grid
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for(int row = 0; row < occupantArray.length; row++)
            for(int col = 0; col < occupantArray[0].length; col++)
            {
                if(occupantArray[row][col] != null)
                {
                    temp.add(new Location(row, col));
                }
        }
        return temp;
    }
}