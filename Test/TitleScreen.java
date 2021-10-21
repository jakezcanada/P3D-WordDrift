import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        prepare();
    }
    
    private void prepare(){
        GreenfootImage logo = new GreenfootImage("Logo.png");
        Picture logoPic = new Picture(logo);
        addObject(logoPic,getWidth()/2,150);
    }
    
    public void started(){
        
    }
    
    public void stoppped(){
        
    }
    
}
