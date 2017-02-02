
/**
 * Write a description of class GameDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class GameDisplay extends JFrame
{
    Panel[][] grid;
    BoundedGrid<Block> board;
    public GameDisplay(BoundedGrid<Block> gr)
    {
        board= gr;
        grid= new Panel[gr.getNumRows()][gr.getNumCols()];
    }
    
    public void createAndShowGui()
    {
        this.setVisible(true);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Your board. Red means missed, Blue means hit.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(board.getNumRows(), board.getNumCols()));
        
        for (int row=0; row<board.getNumRows(); row++)
        
            for (int col=0; col<board.getNumCols(); col++)
            {
                grid[row][col] = new Panel();
                grid[row][col].setBackground(Color.white);
                grid[row][col].setPreferredSize(new Dimension(30, 30));
                this.getContentPane().add(grid[row][col]);
            }
        
       showBlocks();
    }
    public void showBlocks()
    {
        ArrayList<Location> locs= board.getOccupiedLocations();
        for (int i=0; i< board.getOccupiedLocations().size(); i++)
        {
            Block temp= board.get(locs.get(i));
            if (temp.partOfShip()){
                if (temp.isHit())
                    grid[temp.getLocation().getRow()][temp.getLocation().getCol()].setHit();
                grid[temp.getLocation().getRow()][temp.getLocation().getCol()].setPartOfShip();
                grid[temp.getLocation().getRow()][temp.getLocation().getCol()].repaint();
            }
            else
            {
                 grid[temp.getLocation().getRow()][temp.getLocation().getCol()].setNotPartOfShip();
                 grid[temp.getLocation().getRow()][temp.getLocation().getCol()].repaint();
            }
        }
    }
}
