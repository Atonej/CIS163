package atmPack;

import javax.swing.JFrame;

import atmPack.MyATMPanel;

public class
        ATMGUI
{
    //-----------------------------------------------------------------
    //  Creates and displays the temperature converter GUI.
    //-----------------------------------------------------------------
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("ATM example");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        MyATMPanel panel = new MyATMPanel ();
        
        frame.getContentPane().add(panel);
        //frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}

