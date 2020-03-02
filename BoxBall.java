import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

/**
 * BoxBall simulates a bouncing ball in a box.
 *
 * @author Ryan Canuel, writer of songs and player of stringed instrument
 * @version 3.1.2020
 */
public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity
    
    private int ballDegradation = 1;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int leftWallPosition;
    private final int rightWallPosition;
    private final int roofPosition;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;
    private Random randy = new Random();
    
    /**
     * Creates a standard BoxBall.
     */
    public BoxBall() {
        xPosition = randy.nextInt(29);
        yPosition = 0;
        color = new Color(255,255,255);
        diameter = 10;
        groundPosition = 120;
        leftWallPosition = 20;
        rightWallPosition = 120;
        roofPosition = 20;
        canvas = new Canvas("New Canvas");
        ySpeed = 0;
        xSpeed = 0;
    }
    
    /**
     * Creates either a standard BoxBall or a slightly 
     * randomized BoxBall, based on the inputted boolean value.
     */
    public BoxBall(int offset, Canvas canva) {
        xPosition = randy.nextInt(500+offset+offset) + 
                    50 - offset;
        yPosition = randy.nextInt(250+offset+offset) + 
                    50 - offset;
                    
        color = new Color(randy.nextInt(225),
                          randy.nextInt(225),
                          randy.nextInt(225));
                          
        diameter = randy.nextInt(16) + 10;
        
        groundPosition = 300 + offset;
        leftWallPosition = 50 - offset;
        rightWallPosition = 550 + offset;
        roofPosition = 50 - offset;
        
        canvas = canva;
        ySpeed = randy.nextInt(14) - 7;
        if(ySpeed == 0) ySpeed = 7;
        xSpeed = randy.nextInt(14) - 7;
        if(xSpeed == 0) xSpeed = 7;
    }
    
    /**
     * Creates a new BoxBall with inputted parameters.
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, 
                   Color ballColor, int groundPos, int leftPos, 
                   int rightPos, int roofPos, Canvas drawingCanvas,
                   int ySpee, int xSpee)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        leftWallPosition = leftPos;
        rightWallPosition = rightPos;
        roofPosition = roofPos;
        canvas = drawingCanvas;
        ySpeed = ySpee;
        xSpeed = xSpee;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Returns whether or not the ball is still bouncing.
     */
    public boolean isBouncing() {
        if(ySpeed==0) return false;
        return true;
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    
    
    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation; 
        }
        
        //check if it has hit the left wall
        if(xPosition <= (leftWallPosition + diameter)) {
            xPosition = (int)(leftWallPosition + diameter);
            xSpeed = -xSpeed;
        }
        
        //check if it has hit the right wall
        if(xPosition >= (rightWallPosition - diameter)) {
            xPosition = (int)(rightWallPosition - diameter);
            xSpeed = -xSpeed;
        }
        
        //check if it has hit the roof
        if(yPosition <= (roofPosition + diameter)) {
            yPosition = (int)(roofPosition + diameter);
            ySpeed = -ySpeed + ballDegradation;
        }
        
        //if ySpeed = 0 slow roll
        if((ySpeed==0) && ((yPosition + diameter + 1 >= groundPosition))) {
            if(xSpeed > 0) xSpeed -= 1;
            if(xSpeed < 0) xSpeed += 1;
        }
       
        // draw again at new position
        draw();
    }
}
