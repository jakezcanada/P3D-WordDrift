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
    private Pointer pL = new Pointer(getHeight()/10, 2.7, false);
    private Pointer pR = new Pointer(getHeight()/10, 2.7, true);
    private int[] imgWidth = new int[numOfOptions];
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
        Button playButton = new Button(new GreenfootImage("play.png"), getHeight()/15, 2.5);
        Button achievementsButton = new Button(new GreenfootImage("achievements.png"), getHeight()/15, 6.5);
        Button instructionsButton = new Button(new GreenfootImage("instructions.png"), getHeight()/15, 6.4);
        Button exitButton = new Button(new GreenfootImage("exit.png"), getHeight()/15, 2.4);
        
        imgWidth[0] = (int) (getHeight()/15*2.65);
        imgWidth[1] = (int) (getHeight()/15*6.5);
        imgWidth[2] = (int) (getHeight()/15*6.63);
        imgWidth[3] = (int) (getHeight()/15*2.51);
        
        addObject(playButton, getWidth()/2, getHeight()/25*12);
        addObject(achievementsButton, getWidth()/2, getHeight()/25*18);
        addObject(instructionsButton, getWidth()/2, getHeight()/25*15);
        addObject(exitButton, getWidth()/2, getHeight()/25*21);
        addObject(pL, getWidth()/2, getHeight()/25*12);
        addObject(pR, getWidth()/2, getHeight()/25*12);
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
                        Greenfoot.setWorld(new Achievements());
                        break;
                    case 3: // Instructions
                        Greenfoot.setWorld(new Instructions());
                        break;
                    case 4: // Exit
                        Greenfoot.stop();
                        break;
                }
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("UP") || Greenfoot.isKeyDown("DOWN") || Greenfoot.isKeyDown("ENTER")) && isDown){
            isDown = false;
        }
        
        pL.setLocation((getWidth()/2) - (imgWidth[option-1]/2) - (pL.getWidth()/2), getHeight()/25*9 + (getHeight()/25*3 * option));
        pR.setLocation((getWidth()/2) + (imgWidth[option-1]/2) + (pL.getWidth()/2), getHeight()/25*9 + (getHeight()/25*3 * option));
        
    }
}
