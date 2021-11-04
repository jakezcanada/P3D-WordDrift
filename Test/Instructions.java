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
    public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    private Button exitInstructionsButton = new Button(new GreenfootImage("BackToMenu-1.png"), getHeight()/15, 3.8);

    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        addObject(new Button(new GreenfootImage("WordDrift Instructions.png"), 720, 1.78), getWidth()/2, getHeight()/2);
        addObject(exitInstructionsButton, getWidth()/2, getHeight()*9/10);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")) {
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        } else if (Greenfoot.mouseClicked(exitInstructionsButton)){
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
