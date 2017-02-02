
/**
 * Write a description of class ShipGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class ShipGenerator
{
    private Random r= new Random();
    private BoundedGrid<Block> grid;
    public ShipGenerator(BoundedGrid<Block> gr)
    {
        grid= gr;
    }
    public boolean fits(Block[] b)
    {
        for (int i=0; i< b.length; i++)
            if (!grid.isValid(b[i].getLocation()) || grid.get(b[i].getLocation())!= null)
                return false;
        return true;        
    }
    public Ship generateShip(int x, String s)
    {
        boolean fits= false;
        Block[] b= new Block[x];

        while (fits!= true)
        {
            Location start= new Location(r.nextInt(grid.getNumRows()), r.nextInt(grid.getNumCols()));
            int y= r.nextInt(2);
            if (y== 0)
            {
                for (int i=0; i< x; i++)
                {
                    b[i]= new Block(new Location(start.getRow(), i));
                }
            }
            else
            {
                for (int i=0; i< x; i++)
                {
                    b[i]= new Block(new Location(i, start.getCol()));
                }
            }
            if (fits(b))
                fits= true;
        }
        return new Ship(b, s);
    }
}
