import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
/**
 * The pong panel class is a type of JFrame. It will be able to make two bumpers and a polkadot 
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
public class PongPanel extends JPanel
{
    private static final int FRAME = 400;
    private static final Color BACKGROUND = Color.BLACK;
    private BufferedImage myImage;
    private Graphics myBuffer;
    private Bumper bumperOne, bumperTwo;
    private Ball pBall;
    private Timer myTimer; 
    private int scoreOne, scoreTwo = 0;
    /**
     * This is the constructor for the prize panel. It will create the ball and polkadot, start
     * a timer, add a mouse listener, and initialize some variables.
     */   
    public PongPanel()
    {
        myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME,FRAME);
        pBall = new Ball(200,200,8,Color.WHITE);
        pBall.setdx(-1);
        pBall.setdy(-1);
        bumperOne = new Bumper(20, 200, 5, 30, Color.WHITE);
        myTimer = new Timer(5, new Listener());
        myTimer.start();
        addMouseMotionListener(new Mouse());
    }

    /**
     * Allows the buffer to replace the current image
     */
    public void paintComponent(Graphics g)
    {
        g.drawImage (myImage, 0 , 0, FRAME, FRAME, null);
    }

    /**
     * Method that will increae the counter when the ball and polkadot touch
     *
     * @param bumper gives the Bumper
     * @param pBall   gives the ball
     */
    private void collide(Bumper bumper, Ball pBall)
    {
        if (bumper.inBumper(pBall))
        {
            pBall.setdx(pBall.getdx()*-1);
        }
    }

    private class Mouse extends MouseMotionAdapter
    {
        /**
         * This method dictates what will happen when mouse input is detected. If
         *  When left clicked, the polkadot will move to the
         *  mouse. When right clicked, the ball will move to the mouse. When shift and
         *  left click are pressed, the ball will move in a random direction and speed.
         *
         *  @param e allows the method to detect what type of input has occured.
         */
        public void mouseDragged(MouseEvent e)
        {
            bumperOne.setY(e.getY());
        }
    }

    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            collide(bumperOne, pBall);
            pBall.move(400,400);
            pBall.draw(myBuffer);
            bumperOne.draw(myBuffer);
            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 24));
            myBuffer.drawString("Score: " + scoreOne, FRAME + 150, 25);
            repaint();

        }
    }

}