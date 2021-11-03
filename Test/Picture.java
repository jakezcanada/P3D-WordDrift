import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Picture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Picture extends Actor
{
    private int delayCount;
    public Picture(GreenfootImage myImage){
        setImage(myImage);
    }
    
    public void act(){
        if (paused()){
            if(--delayCount == 0){
                getWorld().removeObject(this);
            }    
            return;
        }
    }    
    
    public boolean paused()
    {
        return delayCount > 0;
    }
 
    public void setDelay(int actCount)
    {
        delayCount = actCount;
    }
}
