
/**
 * This TargetSelector object . . .
 * 
 * @author  
 * @version 
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
public class TargetSelector extends JFrame
{
    public TargetSelector(BoundedGrid<Block> gr)
    {
        grid= gr;
    }
    private BoundedGrid<Block> grid;
    private Button[][] buttons= new Button[10][10];
    private int count=0;
    private ActionListener pressed= new ButtonPressed();
    private Location loc= null;
    public void createAndShowGui()
    {
        JPanel pane= new JPanel();
        this.setVisible(true);
        this.setResizable(false);
        //this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pane);
        this.setTitle("Select a tageted location");
        pane.setLayout(new GridLayout(10, 10));
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(new Dimension(600, 600));
        Dimension windowSize = new Dimension(getPreferredSize());
        int wdwLeft = 260 + screenSize.width / 2 - windowSize.width / 2;
        int wdwTop = 0;//screenSize.height/2- windowSize.height / 2;
        pack();   
        //setLocation(wdwLeft, wdwTop);


        for (int row=0; row< 10; row++)
            for (int col=0; col<10; col++)
            {
                buttons[row][col]= new Button(new Location(row, col));
                buttons[row][col].addActionListener(pressed);
                // buttons[row][col].setBackGround(Color.white);
                buttons[row][col].setPreferredSize(new Dimension(30, 30));
                pane.add(buttons[row][col]);
        }
        
        showBlocks();
    }
    
    public void prompt()
    {
       waitForUser();
    }

    public void showBlocks()
    {
        for (int row=0; row<10; row++)
            for (int col=0; col<10; col++)
            {
                if (grid.get(new Location(row, col))!= null)
                    if (grid.get(new Location(row, col)).isHit()){
                        buttons[row][col].setText("H");
                        buttons[row][col].setBackground(Color.black);
                    }
                    else if (!grid.get(new Location(row, col)).partOfShip())
                    {
                        buttons[row][col].setText("M");
                        buttons[row][col].setBackground(Color.red);
                    }
            }
    }
    
    public void resetCount()
    {
        count=0;
    }
    public void waitForUser()
    {
        if (count<1)
        try{
            Thread.sleep(100);
            waitForUser();
        }catch(Exception e){}
    }
    public Location getLoc()
    {
        return loc;
    }
    
    private class ButtonPressed implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Button b= null;
            if (e.getSource() instanceof Button)
            {
                b= (Button)e.getSource();
                loc= b.getLoc();
                count++;
            }
        }
    }
}
