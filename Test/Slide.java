import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This actor makes the GreenfootImage appear for 4 seconds then disappear from the world
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slide extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    public Slide(GreenfootImage img){
        setImage(img);
        timer.mark();
    }
    
    //When the number of milliseconds exceed 4000, the object will be removed
    public void act(){
        if (timer.millisElapsed() > 4000){
            getWorld().removeObject(this);
        }
    }    
}
