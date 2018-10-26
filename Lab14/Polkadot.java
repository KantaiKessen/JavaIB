// Billington.  email: mlbillington@fcps.edu
// version: 7.25.2007
// updated by Phil Ero 16NOV07

import java.awt.*;

/**
 * This is the class for creating polka dots. It is able to create default red polka dots, or create
 * polka dots of different colors, sizes, and locations.
 *
 * @author Nick He
 * @author Kevin Liu
 * @version 1.0.0
 * @period 3
 * @teacher Coglianese
 */
public class Polkadot {
    private double myX;   // x and y coordinates of center
    private double myY;
    private double myDiameter;
    private Color myColor;
    private double myRadius;
    // constructors

    /**
     * Default constructor.
     */
    public Polkadot()     //default constructor
    {
        myX = 200;
        myY = 200;
        myDiameter = 25;
        myColor = Color.RED;
        myRadius = myDiameter / 2;
    }

    /**
     * Constructor if location, diameterm and color of the polka dot are specified by the user.
     */
    public Polkadot(double x, double y, double d, Color c) {
        myX = x;
        myY = y;
        myDiameter = d;
        myColor = c;
        myRadius = d / 2;
    }
    // accessor methods

    /**
     * Gives the x value of the dot.
     *
     * @return myX x value of the dot
     */
    public double getX() {
        return myX;
    }

    /**
     * changes the x value of the dot.
     *
     * @param x new x value
     */
    public void setX(double x) {
        myX = x;
    }

    /**
     * Gives the y value of the dot.
     *
     * @return myY y value of the dot
     */
    public double getY() {
        return myY;
    }

    /**
     * changes the y value of the dot.
     *
     * @param y new y value
     */
    public void setY(double y) {
        myY = y;
    }

    /**
     * Gives the diameter of the dot.
     *
     * @return myDiameter diameter of the dot
     */
    public double getDiameter() {
        return myDiameter;
    }
    // modifier methods

    /**
     * changes the diameter of the dot.
     *
     * @param d new diameter
     */
    public void setDiameter(double d) {
        myDiameter = d;
        myRadius = d / 2;
    }

    /**
     * Gives the color of the dot.
     *
     * @return myColor0 color of the dot
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * changes the color of the dot.
     *
     * @param c new Color
     */
    public void setColor(Color c) {
        myColor = c;
    }

    /**
     * Gives the radius of the dot.
     *
     * @return myRadius radius of the dot
     */
    public double getRadius() {
        return myRadius;
    }

    /**
     * changes the radius of the dot.
     *
     * @param r new radius
     */
    public void setRadius(double r) {
        myRadius = r;
        myDiameter = 2 * r;
    }
    //   instance methods

    /**
     * sets the polka dot's x and y values to a random double between the specified edges.
     *
     * @param rightEdge  rightmost edge where the user wants a polka dot
     * @param bottomEdge furthest down where the user wants a polka dot
     */
    public void jump(int rightEdge, int bottomEdge) {
        // moves location to random (x, y) within the edges
        myX = (Math.random() * (rightEdge - myDiameter) + myRadius);
        myY = (Math.random() * (bottomEdge - myDiameter) + myRadius);
    }

    /**
     * draws the Polkadot on the buffer image
     *
     * @param myBuffer the graphics buffer used to draw the Polkadots
     */
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(myColor);
        myBuffer.fillOval((int) (getX() - getRadius()), (int) (getY() - getRadius()), (int) getDiameter(), (int) getDiameter());
    }
}