import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private int option = 1;
    private int numOfOptions = 4;
    private boolean isDown = false;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels
        super(1280, 720, 1, false);
        prepare();
    }
    
    private void prepare(){
        GreenfootImage logo = new GreenfootImage("Logo.png");
        logo.scale(getHeight()/3, getHeight()/5);
        Picture logoPic = new Picture(logo);
        addObject(logoPic, getWidth()/2, (int) (getHeight()/4.5));
        //addObject(new Button("Play", getHeight()/15), getWidth()/2, getHeight()/25*12);
        GreenfootImage playButton = new GreenfootImage("play.png");
        GreenfootImage achievementsButton = new GreenfootImage("achievements.png");
        GreenfootImage instructionsButton = new GreenfootImage("instructions.png");
        GreenfootImage exitButton = new GreenfootImage("exit.png");
        
        addObject(new Button(playButton, getHeight()/15, 2.5), getWidth()/2, getHeight()/25*12);
        addObject(new Button(achievementsButton, getHeight()/15, 6.5), getWidth()/2, getHeight()/25*18);
        addObject(new Button(instructionsButton, getHeight()/15, 6.4), getWidth()/2, getHeight()/25*15);
        addObject(new Button(exitButton, getHeight()/15, 2.4), getWidth()/2, getHeight()/25*21);
    }
    
    public void act(){
        if((Greenfoot.isKeyDown("UP") || Greenfoot.isKeyDown("DOWN") || Greenfoot.isKeyDown("ENTER")) && !isDown){
            if(Greenfoot.isKeyDown("UP") && option==1){
                option = numOfOptions;
            }else if(Greenfoot.isKeyDown("DOWN") && option==numOfOptions){
                option = 1;
            }else if(Greenfoot.isKeyDown("UP")){
                option--;
            }else if(Greenfoot.isKeyDown("DOWN")){
                option++;
            }else if(Greenfoot.isKeyDown("ENTER")){
                switch(option){
                    case 1: // Play
                        Greenfoot.setWorld(new Game());
                        break;
                    case 2: // Achievements
                        // set world achievements
                        break;
                    case 3: // Instructions
                        // set world instructions
                        break;
                    case 4: // Exit
                        // Terminate
                        break;
                }
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("UP") || Greenfoot.isKeyDown("DOWN") || Greenfoot.isKeyDown("ENTER")) && isDown){
            isDown = false;
        }
            
    }
}
