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
 * I also tried to make the balls slowly stop rolling on the ground,
 * as opposed to rolling at full speed forever, but they look as if they 
 * stop abruptly since their top speed is 7 and we ca only decrement ints by 1.
 * Also, not all balls stop, but that's because I was trying to do extra and 
 * couldnt.
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random randy = new Random();

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
        int offset = randy.nextInt(61) - 30;
        myCanvas.drawLine((50-offset), (50-offset), (550+offset), (50-offset));
        myCanvas.drawLine((50-offset), (50-offset), (50-offset), (300+offset));
        myCanvas.drawLine((550+offset), (300+offset), (550+offset), (50-offset));
        myCanvas.drawLine((550+offset), (300+offset), (50-offset), (300+offset));
        
        ArrayList<BoxBall> sack = new ArrayList<BoxBall>();
        
        for(int i = 0; i < balls; i++) {
            sack.add(new BoxBall(offset,myCanvas));
        }
        
        for(int j = 0; j <= 200; j++) {
            //System.out.println(j);
            myCanvas.wait(50);
            for(BoxBall ball : sack) {
                ball.move();
            }
            myCanvas.drawLine((50-offset), (50-offset), (550+offset), (50-offset));
            myCanvas.drawLine((50-offset), (50-offset), (50-offset), (300+offset));
            myCanvas.drawLine((550+offset), (300+offset), (550+offset), (50-offset));
            myCanvas.drawLine((550+offset), (300+offset), (50-offset), (300+offset));
        }
        
    }
    
    public boolean areBouncing(ArrayList<BoxBall> sack) {
        for(BoxBall ball : sack) {
            if(ball.isBouncing()) return true;
        }
        return false;
    }
}
