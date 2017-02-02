
/**
 * Write a description of class BattleShip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager.*;  
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class BattleShip
{
    public static void main(String [] args)
    {
        BattleShip game= new BattleShip();
        game.play();
    }

    private BoundedGrid<Block> gr;
    private BoundedGrid<Block> compGrid;
    private Ship destroyer, cruiser, patrolOne, patrolTwo;
    private GameDisplay display;    
    private Computer comp;
    private TargetSelector selector;
    private ArrayList<Ship> shipsInGrid;
    private boolean won= false;

    public BattleShip()
    {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        //try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception e){}
        gr= new BoundedGrid<Block>(10, 10);
        compGrid= new BoundedGrid<Block>(10, 10);
        display= new GameDisplay(gr);
        comp= new Computer(compGrid);
        selector= new TargetSelector(compGrid);
        shipsInGrid= new ArrayList<Ship>();
    }

    public void play()
    {
        Window w= new Window(400, 100);
        w.createGUI();
        w.waitForUser();
        ArrayList<Ship> compShipsInGrid= comp.getShips();
        addShips(gr);
        try{Thread.sleep(100);}catch(Exception e){}

        //comp.createAIBoard(compGrid);

        GameDisplay display= new GameDisplay(gr);
        display.createAndShowGui();
        selector.setLocation(display.getWidth(), 0);
        selector.createAndShowGui();
        while(true)
        {
            String temp= "";
            Location target= promptUserForTurn();

            if (comp.hit(target))
            {
                Block b= compGrid.get(target);
                b.setHit();
            }
            else
            {
                Block b= new Block(target);//creates a block so it can be displayed on the target selector next time it is refreshed
                b.putSelfInGrid(compGrid, target);
            }

            comp.go(gr);
            ArrayList<Ship> userShips= getSunkShips();
            ArrayList<Ship> compShips= comp.getSunkShips();
            if (userShips!= null)
                for (int i=0; i< userShips.size(); i++){
                    temp+=(userShips.get(i).getName() + " Sunk, ");
                    for (int j=0; j< shipsInGrid.size(); j++)
                        if (shipsInGrid.get(j).equals(userShips.get(i)))
                            shipsInGrid.remove(j);
            }

            if (compShips!= null)
                for (int i=0; i< compShips.size(); i++){
                    temp+= ("You have sunk the computers "+ compShips.get(i).getName());
                    for (int j=0; j< compShipsInGrid.size(); j++)
                        if (compShipsInGrid.get(j).equals(compShips.get(i)))
                            compShipsInGrid.remove(j);
            }

            display.showBlocks();
            selector.showBlocks();
            if (compShips!= null || userShips!= null)
            {
                RoundReport rep= new RoundReport(temp);
                rep.repaint();
                rep.waitForUser();
                rep.close();
            }
            if (shipsInGrid.isEmpty())
                break;
            if (compShipsInGrid.isEmpty())
            {
                won= true;
                break;
            }
        }

        if (won){
            RoundReport rep= new RoundReport("You won!");
            rep.repaint();
            rep.waitForUser();
            rep.close();
        }
        else{
            RoundReport rep= new RoundReport("You lost!");
            rep.repaint();
            rep.waitForUser();
            rep.close();
        }

    }

    public void addShips(BoundedGrid<Block> gr)
    {
        ShipAdder sa= new ShipAdder();
        sa.prompt();
        ArrayList<Block> ships= sa.getShips();
        sa.dispose();

        Block[] temp= new Block[4];
        for (int i=0; i<4; i++){
            temp[i]= ships.get(i);
            temp[i].setColor(Color.black);
        }
        destroyer= new Ship(temp, "Destroyer");
        temp= new Block[3];
        int counter=0;
        for (int i=4; i<7; i++){
            temp[counter]= ships.get(i);
            temp[counter].setColor(Color.black);
            counter++;
        }
        cruiser= new Ship(temp, "Cruiser");
        counter=0;
        temp= new Block[2];
        for (int i=7; i<9; i++){
            temp[counter]= ships.get(i);
            temp[counter].setColor(Color.black);
            counter++;
        }
        patrolOne= new Ship(temp, "Patrol 1");
        counter=0;
        temp= new Block[2];
        for (int i=9; i<11; i++){
            temp[counter]= ships.get(i);
            temp[counter].setColor(Color.white);
            counter++;
        }
        patrolTwo= new Ship(temp, "Patrol Two");
        destroyer.addToGrid(gr);
        cruiser.addToGrid(gr);
        patrolOne.addToGrid(gr);
        patrolTwo.addToGrid(gr);
        shipsInGrid.add(destroyer);
        shipsInGrid.add(cruiser);
        shipsInGrid.add(patrolOne);
        shipsInGrid.add(patrolTwo);
    }

    public ArrayList<Ship> getSunkShips()
    {
        ArrayList<Ship> temp= new ArrayList<Ship>();
        for (int i=0; i< shipsInGrid.size(); i++)
            if (shipsInGrid.get(i).isSunk())
            {
                temp.add(shipsInGrid.get(i));
                comp.resetLocMemory();
        }
        if (temp.isEmpty())
            return null;

        return temp;    
    }

    public Location promptUserForTurn()
    {
        selector.prompt();
        selector.resetCount();
        Location temp= selector.getLoc();
        return temp;
    }
}