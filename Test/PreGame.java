import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PreGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreGame extends World
{
    private int option = 1;
    private int numOfOptions = 3;
    private boolean isDown = false;
    private int[] imgWidth = new int[numOfOptions];
    
    private Button playButton = new Button(new GreenfootImage("PlayButton-1.png"), getHeight()/3, 4);
    private Button achievementsButton = new Button(new GreenfootImage("AchievementsButton-1.png"), getHeight()/9, 3.8);
    private Button instructionsButton = new Button(new GreenfootImage("InstructionsButton-1.png"), getHeight()/9, 3.8);
    private Button exitButton = new Button(new GreenfootImage("ExitButton-1.png"), getHeight()/9, 3.8);
    
    
    private static GreenfootSound bgm = new GreenfootSound("cantfindgoodmusic.mp3");
    public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    public static GreenfootSound click = new GreenfootSound("Click.mp3");
    private Button musicOn = new Button(new GreenfootImage("SoundOn.png"), getHeight()/10, 1);
    private Button musicOff = new Button(new GreenfootImage("SoundOff.png"), getHeight()/10, 1);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public PreGame()
    {
        // Create a new world with 1280x720 cells with a cell size of 1x1 pixels
        super(1280, 720, 1, false);
        prepare();
    }
    
    private void prepare(){
        Button logo = new Button(new GreenfootImage("WordDrift Logo.png"), getHeight()/5, 3.272);
        addObject(logo, getWidth()/2 - 20, (int) (getHeight()/5));
        //bgm.playLoop();
        bgm.pause();
        //addObject(new Button("Play", getHeight()/15), getWidth()/2, getHeight()/25*12);
        draw();
    }
    
    public void draw(){
        removeObject(playButton);
        playButton = new Button(new GreenfootImage("PlayButton" + ((option == 3 ) ? "-2" : "-1") + ".png"), getHeight()/9, 4);
        addObject(playButton, getWidth()/2, getHeight()*4/5);
    }
    
    public void act(){
        if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("enter")) && !isDown){
            if(option == 1 || option == 2){
                if(Greenfoot.isKeyDown("down")){
                    cursor.play();
                    option++;
                }else if(Greenfoot.isKeyDown("right")){
                    
                }else if(Greenfoot.isKeyDown("left")){
                    
                }
            }else{
                if(Greenfoot.isKeyDown("enter")){
                    // remember to add params
                    Greenfoot.setWorld(new Game());
                }else if(Greenfoot.isKeyDown("up")){
                    option--;
                }
            }
            isDown = true;
            draw();
        }else if(!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("enter")) && isDown){
            isDown = false;
        }
        if(Greenfoot.mouseClicked(musicOn)){
            bgm.pause();
            addObject(musicOff, 1150, 100);
            removeObject(musicOn);
        }
        if(Greenfoot.mouseClicked(musicOff)){
            bgm.playLoop();
            addObject(musicOn, 1150, 100);
            removeObject(musicOff);
        }
    }
}
