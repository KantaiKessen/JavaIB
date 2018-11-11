import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
/**
 * The PongPanel provides a Pong emulation for a JFrame 400 by 400 pixels.
 * The PongPanel uses MouseEvents and KeyEvents to move the two "Paddles".
 * The "Paddles" consist of two bumpers each 40 pixels tall and 5 pixels wide.
 * They are moved by draggin the mouse for the right one and "W" and "S" for the left.
 * A Ball moves at 1 pixel per second up/down and left/right.
 * Scoring is provided on the two uper corners.
 * @author Kevin Liu
 * @version 0.1.010
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
     * The constructor for PongPanel. It will create the ball and two bumpers, start
     * a timer, add a mouse listener, add a key listener, and initialize some variables.
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
        bumperOne = new Bumper(360, 200, 5, 30, Color.WHITE);
        bumperTwo = new Bumper(20, 200, 5, 30, Color.WHITE);
        myTimer = new Timer(5, new Listener());
        myTimer.start();
        addMouseMotionListener(new Mouse());
        addKeyListener(new Key());
        setFocusable(true);
    }

    /**
     * Allows the buffer to replace the current image
     */
    public void paintComponent(Graphics g)
    {
        g.drawImage (myImage, 0 , 0, FRAME, FRAME, null);
    }

    /**
     * Method that allows the paddles to reverse the travel of the ball.
     *
     * @param bumper    gives the Bumper
     * @param pBall     gives the ball
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
         *  This method dictates what will happen when mouse input is detected. If
         *  When left clicked, the right paddle will be at the y coordinate of the mouse. The x coord will remain the same.
         *
         *  @param e    required event listener
         */
        public void mouseDragged(MouseEvent e)
        {
            bumperOne.setY(e.getY());
        }
    }
    private class Key extends KeyAdapter
    {
        /**
         * This method dictates what will happen when key input is detected. If
         * When W is pressed, the left paddle move up 5 pixels. S will move the paddle down 5 pixels. The x coord will remain the same.
         * 
         * @param e     required event listener
         */
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_W)
                bumperTwo.setY(bumperTwo.getY()-10);
            if(e.getKeyCode() == KeyEvent.VK_S)
                bumperTwo.setY(bumperTwo.getY()+10);
        }
        
    }

    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);
            if(pBall.getX() <= 8)
                scoreTwo++;
            if(pBall.getX() >=392)
                scoreOne++;
            if(scoreOne/9 > 10 || scoreTwo/9 > 10){
                System.exit(0);
            }
            collide(bumperOne, pBall);
            collide(bumperTwo, pBall);
            pBall.move(400,400);
            pBall.draw(myBuffer);
            bumperOne.draw(myBuffer);
            bumperTwo.draw(myBuffer);
            myBuffer.setColor(Color.WHITE);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 10));
            myBuffer.drawString("Score: " + scoreOne/9, 0, 25);
            myBuffer.drawString("Score: " + scoreTwo/9, FRAME-100, 25);
            repaint();
        }
    }

}