
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
public class PrizePanel extends JPanel
{
    private static final int FRAME = 400;
    private static final Color BACKGROUND = new Color(204, 204, 204);
    private BufferedImage myImage;
    private Graphics myBuffer;
    private Ball myBall;
    private Polkadot[] myPDots;
    private Timer myTimer; 
    private int hits = 0;
    //constructor   
    public PrizePanel()
    {
        myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME,FRAME);
        int xPos = (int)(Math.random()*(FRAME-100) + 50);
        int yPos = (int)(Math.random()*(FRAME-100)+ 50);
        myBall = new Ball(xPos, yPos, 50, Color.BLACK);
        myPDots = new Polkadot[50]; 
        for(int i = 0; i < 50; i++){
            myPDots[i] = new Polkadot((int)(Math.random()*(FRAME-100) + 50), 
            (int)(Math.random()*(FRAME-100) + 50), 25, Color.RED);
        }
        myTimer = new Timer(5, new Listener());
        myTimer.start();

    }

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
            for(Polkadot p : myPDots)
                collide(myBall, p);
            myBall.draw(myBuffer);
            for(Polkadot p : myPDots)
                p.draw(myBuffer);
            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(new Font("Monospaced", Font.BOLD, 24));
            myBuffer.drawString("Count: " + hits, FRAME - 150, 25);
            repaint();
            
        }
    }   
    private void collide(Ball ballIn, Polkadot p)
    {
            double d = distance(ballIn.getX(), ballIn.getY(), p.getX(), p.getY() );  
            if (d<= ballIn.getDiameter()/2 + p.getRadius())
            {
                hits++;
                p.setX((int)(Math.random()*(FRAME-100) + 50));
                p.setY((int)(Math.random()*(FRAME-100) + 50));
            }
    }

    private double distance(double x1, double y1, double x2, double y2)
    {
        return  Math.sqrt(Math.pow(x1-x2 , 2) + Math.pow(y1-y2 , 2));  
    }
}