// Name:                Date:

   import java.awt.*;
   
    public class Bumper
   {
    //private fields, all ints, for a Bumper
    //hint: the "location" of the bumper begins at its top left corner.      
     int xPos, yPos, xLength, yLength;
     Color color;
   
   
     //constructors
       public Bumper()         //default constructor
      {
          xPos = 0;
          yPos = 0;
          xLength = 100;
          yLength = 150;
          color = Color.BLACK;
      }
       public Bumper(int x, int y, int xWidth, int yWidth, Color c)
      {
          xPos = x;
          yPos = y;
          xLength = xWidth;
          yLength = yWidth;
          color = c;
      }
      
     // accessor methods  (one for each field)
        public int getX()
        {return xPos;}
        public int getY()
        {return yPos;}
        public int getXWidth()
        {return xLength;}
        public int getYWidth()
        {return yLength;}
        public Color getColor()
        {return color;}
        
     // modifier methods  (one for each field)
        public void setX(int x)
        {xPos = x;}
        public void setY(int x)
        {yPos  = x;}
        public void setXWidth(int x)
        { xLength = x;}
        public void setYWidth(int x)
        { yLength = x;}
        public void setColor(Color x)
        { color = x;}
        
     // instance methods
     // chooses a random (x,y) location.  Bumper stays entirely in the window.
        public void jump(int rightEdge, int bottomEdge)
      {
         xPos =(int) (Math.random()* (rightEdge-xLength) + xLength/2);
         yPos = (int)(Math.random()* (bottomEdge-yLength) + yLength/2);
      }
      
       // draws a rectangular bumper on the buffer
       public void draw(Graphics myBuffer) 
      {
         myBuffer.setColor(getColor());
         myBuffer.fillRect(getX(), getY(), getXWidth(), getYWidth());
      }   
    // returns true if any part of the polkadot is inside the bumper
       public boolean inBumper(Polkadot dot)
      {
         for(int x = getX(); x <= getX() + getXWidth(); x++)   //starts at upper left corner(x,y)
            for(int y = getY(); y <= getY() + getYWidth(); y++)
               if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() ) //checks every point on the bumper
                  return true;            
         return false;
      }  
      // returns distance between (x1, y1) and (x2, y2)
       private double distance(double x1, double y1, double x2, double y2)
      {
         return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
      } 
   }
