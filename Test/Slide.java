import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slide here.
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

    public void act(){
        if (timer.millisElapsed() > 4000){
            getWorld().removeObject(this);
        }
    }    
}
