import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    private int option = 0;
    private int numOfOptions = 1;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels
        super(600, 400, 1, false);
        prepare();
    }
    
    private void prepare(){
        GreenfootImage logo = new GreenfootImage("Logo.png");
        logo.scale(getHeight()/3, getHeight()/5);
        Picture logoPic = new Picture(logo);
        addObject(logoPic, getWidth()/2, (int) (getHeight()/4.5));
        addObject(new Button("Play", getHeight()/15), getWidth()/2, getHeight()/25*12);
        addObject(new Button("Instructions", getHeight()/15), getWidth()/2, getHeight()/25*15);
        addObject(new Button("Achievements", getHeight()/15), getWidth()/2, getHeight()/25*18);
        addObject(new Button("Exit", getHeight()/15), getWidth()/2, getHeight()/25*21);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("up") && option!=0){
            option++;
        }else if(Greenfoot.isKeyDown("down") && option!=0){
            option++;
        }else if(Greenfoot.isKeyDown("enter")){
            switch(option){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        
    }
}
