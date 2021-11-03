import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends World
{

    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        addObject(new Button(new GreenfootImage("WordDrift Instructions.png"), 650, 1.77), 640, 360);
        //Button exitInstructionsButton = new Button(new GreenfootImage("exitButton.png"), 650, 1.77);
        //addObject(exitInstructionsButton, 100, 400);
    }
}
