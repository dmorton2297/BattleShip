
/**
 * Write a description of class Computer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;
public class Computer
{
    private Ship destroyer;
    private Ship cruiser;
    private Ship patrolOne;
    private Ship patrolTwo;
    private Random gen= new Random();
    private ArrayList<Ship> shipsInGrid= new ArrayList<Ship>();
    private Location tempLoc= null;
    private ArrayList<Location> guessedLocations=  new ArrayList<Location>();
    private ArrayList<Location> possibleLocations= null;
    private BoundedGrid<Block> userBoard;
    private int d=0;
    private int count=0;
    public Computer(BoundedGrid<Block> gr)
    {
        ShipGenerator gen= new ShipGenerator(gr);
        /* Block[] dest= {new Block(new Location(1, 1)), new Block(new Location(1,2)), 
        new Block(new Location(1, 3)), new Block(new Location(1,4))};
        destroyer= new Ship(dest, "Destroyer");

        Block[] cruis= {new Block(new Location(2, 4)), new Block(new Location(2,5)), 
        new Block(new Location(2, 6))};
        cruiser= new Ship(cruis, "Cruiser");

        Block[] patOne= {new Block(new Location(8, 1)), new Block(new Location(8,2))};
        patrolOne= new Ship(patOne, "Patrol One");

        Block[] patTwo= {new Block(new Location(6, 1)), new Block(new Location(6,2))};
        patrolTwo= new Ship(patTwo, "Patrol two");
         */
        destroyer= gen.generateShip(4, "Destroyer");
        destroyer.addToGrid(gr);
        
        cruiser= gen.generateShip(3, "Cruiser");
        cruiser.addToGrid(gr);
        
        patrolOne= gen.generateShip(2, "Patrol One");
        patrolOne.addToGrid(gr);
        
        patrolTwo= gen.generateShip(2, "Patrol Two");
        patrolTwo.addToGrid(gr);
        
        shipsInGrid.add(destroyer);
        shipsInGrid.add(cruiser);
        shipsInGrid.add(patrolOne);
        shipsInGrid.add(patrolTwo);

        userBoard= gr;
    }


    public boolean hit(Location loc)
    {
        for (int i=0; i< destroyer.getLocations().length; i++)
        {
            if (destroyer.getLocations()[i].equals(loc))
            {
                return true;
            }
        }
        for (int i=0; i< cruiser.getLocations().length; i++)
        {
            if (cruiser.getLocations()[i].equals(loc))
            {
                return true;
            }
        }
        for (int i=0; i< patrolOne.getLocations().length; i++)
        {
            if (patrolOne.getLocations()[i].equals(loc))
            {
                return true;
            }
        }
        for (int i=0; i< patrolTwo.getLocations().length; i++)
        {
            if (patrolTwo.getLocations()[i].equals(loc))
            {
                return true;
            }
        }
        return false;
    }

    public void go(BoundedGrid<Block> gr)
    {
        Location target= getLocation();

        if (gr.get(target)!= null && gr.get(target).partOfShip() && !gr.get(target).isHit()){
            gr.get(target).setHit();
            gr.get(target).setGuessed();
            tempLoc= target;
            count=0;
        }
        else if(gr.get(target)== null)
        {
            Block b= new Block(target);
            b.putSelfInGrid(gr, target);
            b.setGuessed();
            count=0;
            if (tempLoc!= null)
                d+= 45;
        }
        else if(gr.get(target)!= null && gr.get(target).isHit())
        {
            tempLoc= target;
            go(gr);
        }
        else if (gr.get(target)!= null && !gr.get(target).partOfShip())
        {
            if (tempLoc!= null)
                d+= 45;
            go(gr);    
        }
        else 
        {
            d=+ 45;
            go(gr);
        }
    }

    public Location getLocation()
    {
        if (count>8)
            tempLoc= null;
        if (tempLoc!= null)

            if (userBoard.isValid(tempLoc.getAdjacentLocation(d))){
                count++;
                return tempLoc.getAdjacentLocation(d);      
        }
        return new Location(gen.nextInt(10), gen.nextInt(10));
    }

    public ArrayList<Ship> getShips()
    {
        return shipsInGrid;
    }

    public void removeShip(int i)
    {
        shipsInGrid.remove(i);
    }

    public void resetLocMemory()
    {
        tempLoc= null;
        d= 0;
    }

    public ArrayList<Ship> getSunkShips()
    {
        ArrayList<Ship> temp= new ArrayList<Ship>();
        for (int i=0; i< shipsInGrid.size(); i++)
            if (shipsInGrid.get(i).isSunk())
            {
                temp.add(shipsInGrid.get(i));
        }
        if (temp.isEmpty())
            return null;

        return temp;   
    }
}
