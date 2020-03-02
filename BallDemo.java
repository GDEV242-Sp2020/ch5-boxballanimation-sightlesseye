import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Ryan Canuel
 * 
 * @version 2.1.2020
 * 
 * Extra credit was attempted.
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random randy;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    public void ballBounce(int balls) {
        int offset = randy.nextInt(11) - 5;
        ArrayList<BoxBall> sack = new ArrayList<BoxBall>();
        
        for(int i = 0; i < balls; i++) {
            sack.add(new BoxBall(offset,myCanvas));
        }
        
        while(areBouncing(sack)) {
            for(BoxBall ball : sack) {
                ball.move();
            }
        }
        
    }
    
    public boolean areBouncing(ArrayList<BoxBall> sack) {
        for(BoxBall ball : sack) {
            if(ball.isBouncing()) return true;
        }
        return false;
    }
}
