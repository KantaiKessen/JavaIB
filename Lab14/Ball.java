import java.awt.*;

/**
 * A Ball is a polkadot with a mobility ability and a caroming effect. Balls are an extension
 * of polka dots, which cannot move in the same way that Balls can.
 *
 * @author Kevin Liu
 * @author Nick He
 * @version 1.0.0
 * @period 3
 */
public class Ball extends Polkadot {
    private double dx;
    private double dy;

    /**
     * Constructor for balls without specified positions, colors, etc.
     */
    public Ball() {
        super(200, 200, 50, Color.BLACK);
        dx = (Math.random() * 12 - 6);
        dy = (Math.random() * 12 - 6);
    }

    /**
     * Constructor for balls with some characteristics specified by the user
     */
    public Ball(double x, double y, double diam, Color c) {
        super(x, y, diam, c);
        dx = (Math.random() * 12 - 6);
        dy = (Math.random() * 12 - 6);
    }

    /**
     * Method that will set the x val.
     */
    public void setdx(double x) {
        dx = x;
    }

    /**
     * Method that will set the y val.
     */
    public void setdy(double y) {
        dy = y;
    }

    /**
     * Method that will get the x val.
     */
    public double getdx() {
        return dx;
    }

    /**
     * Method that will get the y val.
     */
    public double getdy() {
        return dy;
    }

    /**
     * This is the method that will allow the ball to move. When coming in contact with
     * set boundaries, the ball will bounce and start travelling away from the boundary.
     */
    public void move(double rightEdge, double bottomEdge) {
        setX(getX() + dx);
        setY(getY() + dy);

        if (getX() >= rightEdge - getRadius()) {
            setX(rightEdge - getRadius());
            dx *= -1;
        } else if (getX() <= getRadius()) {
            setX(getRadius());
            dx *= -1;
        }

        if (getY() >= bottomEdge - getRadius()) {
            setY(bottomEdge - getRadius());
            dy *= -1;
        } else if (getY() <= getRadius()) {
            setY(getRadius());
            dy *= -1;
        }
    }
}
