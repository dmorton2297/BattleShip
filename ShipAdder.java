
/**
 * Write a description of class ShipAdder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Toolkit;
public class ShipAdder extends JFrame
{
    ArrayList<Block> ships= new ArrayList<Block>();
    public Button[][] buttons= new Button[10][10];
    private int count=0;
    private ActionListener pressed= new ButtonPressed();
    private Location tempLoc= null;
    public void prompt()
    {
        ActionListener pressed= new ButtonPressed();
        JPanel pane= new JPanel();
        Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocation((dim.width/2-this.getSize().width/2)- 350,  
                            (dim.height/2-this.getSize().height/2)-(350));
        this.setResizable(false);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pane);
        this.setTitle("Select where you want to place your ships");
        pane.setLayout(new GridLayout(10, 10));

        for (int row=0; row< 10; row++)
            for (int col=0; col<10; col++)
            {
                buttons[row][col]= new Button(new Location(row, col));
                buttons[row][col].addActionListener(pressed);
                // buttons[row][col].setBackGround(Color.white);
                buttons[row][col].setPreferredSize(new Dimension(30, 30));
                pane.add(buttons[row][col]);
        }

        this.setVisible(true);
        waitForUser(11);
    }

    public void waitForUser(int x)
    {
        if (count<4)
        {
            this.setTitle("Place your destroyer. Use (4) blocks for this");
        }
        if(count<7 && (count>=4))
        {
            this.setTitle("Place your cruiser. Use (3) blocks for this");
        }
        if (count<9 && (count>=7))
        {
            this.setTitle("Place your first Patroler. Use (2) blocks for this");
        }
        if (count<11 &&(count>=9))
        {
            this.setTitle("Place your second Patroler. Use (2) blocks for this");
        }
        if (count<x)
        {
            try{Thread.sleep(1000);}catch(Exception e){}
            waitForUser(x);
        }
    }

    public ArrayList<Block> getShips()
    {
        return ships;
    }

    private class ButtonPressed implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Button b=null;
            if (e.getSource() instanceof Button)
            {
                b= (Button)e.getSource();
            }
            else
                System.out.println("oops error on button");    
            try{    
                if (!b.isUsed())
                {   
                    ships.add(new Block(b.getLoc()));
                    b.setText("X");
                    b.setUsed();
                    count++;
                }
                else
                {
                    System.out.println("Button already used, select another please.");
                }
            }catch(Exception ex){}

        }
    }
}
