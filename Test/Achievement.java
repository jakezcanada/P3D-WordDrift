import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Achievement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement extends Actor
{
    /**
     * Act - do whatever the Achievement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Achievement(GreenfootImage ach, int progress, int total)
    {
        setImage(ach);
    }
    
    private GreenfootImage progressBar()
    {
        int percentReached = progress/total;
        GreenfootImage bar = new GreenfootImage(10, 200);
        bar.setColor(Color.GRAY);
        bar.fill();
        
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
