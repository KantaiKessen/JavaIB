// Name:                Date:

import java.awt.*;

public class Bumper {
    int x, y, xWidth, yWidth;
    Color c;

    public Bumper() {
        x = 50;
        y = 50;
        xWidth = 10;
        yWidth = 10;
        c = Color.BLACK;
    }

    public Bumper(int x, int y, int xWidth, int yWidth, Color c) {
        this.x = x;
        this.y = y;
        this.xWidth = xWidth;
        this.yWidth = yWidth;
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXWidth() {
        return xWidth;
    }

    public void setXWidth(int xWidth) {
        this.xWidth = xWidth;
    }

    public int getYWidth() {
        return yWidth;
    }

    public void setYWidth(int yWidth) {
        this.yWidth = yWidth;
    }

    public Color getColor() {
        return c;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    // modifier methods  (one for each field)

    // instance methods
    // chooses a random (x,y) location.  Bumper stays entirely in the window.
    public void jump(int rightEdge, int bottomEdge) {

    }

    // draws a rectangular bumper on the buffer
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(getColor());
        myBuffer.fillRect(getX(), getY(), getXWidth(), getYWidth());
    }

    // returns true if any part of the polkadot is inside the bumper
    public boolean inBumper(Polkadot dot) {
        for (int x = getX(); x <= getX() + getXWidth(); x++)   //starts at upper left corner(x,y)
            for (int y = getY(); y <= getY() + getYWidth(); y++)
                if (distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius()) //checks every point on the bumper
                    return true;
        return false;
    }

    // returns distance between (x1, y1) and (x2, y2)
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
