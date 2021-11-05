import java.util.*;
import greenfoot.*;
import java.util.Random;
import java.lang.Math;

/**
 * A glowing particle. Particles have a random movement vector and fly according to it, affected by gravity. They disappear once they hit the edge of the world.
 */
public class Particle extends Actor
{
    private static final double GRAVITY = 0.2;
    private static final double FORCE = 3;
    private Random rand =  new  Random();
    private int timer = (int)(Math.random() * 300);
    private static int imgNum = 0;
    
    private double x;
    private double y;
    private double dx;
    private double dy;

    //Create a new particle
    public Particle()
    {
        dx = rand.nextGaussian() * FORCE;
        dy = rand.nextGaussian() * FORCE;
        setImage("e.png");
        imgNum = (imgNum + 1) % 2;
    }

    //Add to world
    public void addedToWorld(World world)
    {
        x = getX();
        y = getY();
    }

    //Move the particle
    public void act()
    {
        if (getY() >= getWorld().getHeight() - 1) {
            dy = -1 * (dy * Math.random());
        }
        else if (isAtEdge()) {
            getWorld().removeObject(this);
            return;
        }   
        x = x + dx;
        y = y + dy;
        setLocation((int)x, (int)y);
        dy = dy + GRAVITY;
        if (timer <= 0) {
            getWorld().removeObject(this);
            return;
        }
        timer = timer - 1;
    }
}
