import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Picture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Picture extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    boolean heh;
    public Picture(GreenfootImage myImage, boolean isAchievement){
        setImage(myImage);
        heh = isAchievement;
        timer.mark();
    }

    public void act(){
        if (heh && timer.millisElapsed() > 3000){
            getWorld().removeObject(this);
        }
    }    

}
