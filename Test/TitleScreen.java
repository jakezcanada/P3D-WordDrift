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
    private int[] imgWidth = new int[numOfOptions];
    
    private Button playButton = new Button(new GreenfootImage("play.png"), getHeight()/3, 4);
    private Button achievementsButton = new Button(new GreenfootImage("achievements.png"), getHeight()/15, 6.5);
    private Button instructionsButton = new Button(new GreenfootImage("instructions.png"), getHeight()/15, 6.4);
    private Button exitButton = new Button(new GreenfootImage("exit.png"), getHeight()/15, 2.4);
    
    
    private static GreenfootSound bgm = new GreenfootSound("cantfindgoodmusic.mp3");
    private Button musicOn = new Button(new GreenfootImage("musicon.png"), getHeight()/15, 2);
    private Button musicOff = new Button(new GreenfootImage("musicoff.png"), getHeight()/15, 2);
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
        //bgm.playLoop();
        bgm.pause();
        //addObject(new Button("Play", getHeight()/15), getWidth()/2, getHeight()/25*12);
        draw();
    }
    
    public void draw(){
        addObject(playButton, getWidth()/2, getHeight()/2);
        addObject(achievementsButton, getWidth()/4, getHeight()*3/4);
        addObject(instructionsButton, getWidth()/2, getHeight()*3/4);
        addObject(exitButton, getWidth()*3/4, getHeight()*3/4);
    }
    
    public void act(){
        if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("enter")) && !isDown){
            if(option == 1){
                if(Greenfoot.isKeyDown("down") && option==numOfOptions){
                option = 1;
                }
            }else{
                if(Greenfoot.isKeyDown("up")){
                    option = 1;
                }else if(Greenfoot.isKeyDown("left") && option == 2){
                    option = 4;
                }else if(Greenfoot.isKeyDown("right") && option == numOfOptions){
                    option = 2;
                }else if(Greenfoot.isKeyDown("left")){
                    option--;
                }else if(Greenfoot.isKeyDown("right")){
                    option++;
                }
            }
             
            if(Greenfoot.isKeyDown("enter")){
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
        }else if(!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("enter")) && isDown){
            isDown = false;
        }
        if(Greenfoot.mouseClicked(musicOn)){
            bgm.pause();
            addObject(musicOff, 1100, 100);
            removeObject(musicOn);
        }
        if(Greenfoot.mouseClicked(musicOff)){
            bgm.playLoop();
            addObject(musicOn, 1100, 100);
            removeObject(musicOff);
        }
    }
}
