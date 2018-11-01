
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
/**
 * The prize panel class is a type of JFrame. It will be able to make a ball and a polkadot 
 * appear. The ball will move in a random speed and direction. Every time the polkadot 
 * touches the ball, a counter in the top right corner of the panel will increase by 1. 
 * <p>
 * The panel also has mouse capablities. When left clicked, the polkadot will move to the 
 * mouse. When right clicked, the ball will move to the mouse. When shift and left click
 * are pressed, the ball will move in a random direction and speed.
 * 
 * @author Nick He
 * @author Kevin Liu
 * @version 10-29-18
 * @period 3
 * @teacher Coglianese
 */
public class PrizePanel extends JPanel
{
    private static final int FRAME = 400;
    private static final Color BACKGROUND = new Color(204, 204, 204);
    private BufferedImage myImage;
    private Graphics myBuffer;
    private Ball myBall;
    private Polkadot myPDot;
    private Timer myTimer; 
    private int hits = 0;
    /**
     * This is the constructor for the prize panel. It will create the ball and polkadot, start
     * a timer, add a mouse listener, and initialize some variables.
     */   
    public PrizePanel()
    {
        myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME,FRAME);
        int xPos = (int)(Math.random()*(FRAME-100) + 50);
        int yPos = (int)(Math.random()*(FRAME-100)+ 50);
        myBall = new Ball(xPos, yPos, 50, Color.BLACK);
        xPos = (int)(Math.random()*(FRAME-100) + 50);
        yPos = (int)(Math.random()*(FRAME-100)+ 50);
        myPDot = new Polkadot(xPos,yPos,25,Color.RED); 

        myTimer = new Timer(5, new Listener());
        myTimer.start();
        
        addMouseListener(new Mouse());
    }
        private class Mouse extends MouseAdapter
        {
            /**
             * This method dictates what will happen when mouse input is detected. If
             *  When left clicked, the polkadot will move to the 
             *  mouse. When right clicked, the ball will move to the mouse. When shift and 
             *  left click are pressed, the ball will move in a random direction and speed.
             *  
             *  @param e allows the method to detect what type of input has occured. 
             */
            public void mousePressed(MouseEvent e)
            {
                if (e.isMetaDown())
                {
                    myPDot.setX(e.getX());
                    myPDot.setY(e.getY());
                }
                else
                if(e.isShiftDown())
                {
                   myBall = new Ball();
                }
                    
                else
                myBall.setX( e.getX() );
                myBall.setY( e.getY() );
            }
        }
   
        /**
         * Allows the buffer to replace the current image
         */
    public void paintComponent(Graphics g)
    {
        g.drawImage (myImage, 0 , 0, FRAME, FRAME, null);
    }
    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            myBall.move(FRAME, FRAME);
            collide(myBall, myPDot);
            myBall.draw(myBuffer);
            myPDot.draw(myBuffer);
            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 24));
            myBuffer.drawString("Count: " + hits, FRAME - 150, 25);
            repaint();
            
        }
    }   
    /**
     * Method that will increae the counter when the ball and polkadot touch
     * 
     * @param ballIn gives the Ball
     * @param pDot   gives the polkadot
     */
    private void collide(Ball ballIn, Polkadot pDot)
    {
        double d = distance(ballIn.getX(), ballIn.getY(), pDot.getX(), pDot.getY() );  
        if (d<= ballIn.getDiameter()/2 + pDot.getRadius())
        {
            hits++;
        }
    }
    /**
     * Will determine the distance between two coordinates
     * 
     * @param x1  x value of the first coordinate
     * @param y1  y value of the first coordinate
     * @param x2  x value of the second coordinate
     * @param y2  y value of the second coordinate
     * @return the distance between the two coordinates
     */
    private double distance(double x1, double y1, double x2, double y2)
    {
        return  Math.sqrt(Math.pow(x1-x2 , 2) + Math.pow(y1-y2 , 2));  

    }
}