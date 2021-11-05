    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the titlescreen, the first world the player can interact with
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    //To keep track of button options
    private int option = 1;
    private int numOfOptions = 4;
    private boolean isDown = false;
    private int[] imgWidth = new int[numOfOptions];
    
    //Buttons on the titlescreen
    private Button playButton = new Button(new GreenfootImage("PlayButton-1.png"), getHeight()/3, 4);
    private Button achievementsButton = new Button(new GreenfootImage("AchievementsButton-1.png"), getHeight()/9, 3.8);
    private Button instructionsButton = new Button(new GreenfootImage("InstructionsButton-1.png"), getHeight()/9, 3.8);
    private Button exitButton = new Button(new GreenfootImage("ExitButton-1.png"), getHeight()/9, 3.8);
    private Button exitScreen = new Button(new GreenfootImage("Exit Screen.png"), getHeight(), 1.77);

    //Background music and sound effects
    public static GreenfootSound bgm = new GreenfootSound("WordDrift Music.mp3");
    public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    public static GreenfootSound click = new GreenfootSound("Click.mp3");
    private Button musicOn = new Button(new GreenfootImage("SoundOn.png"), getHeight()/10, 1);
    private Button musicOff = new Button(new GreenfootImage("SoundOff.png"), getHeight()/10, 1);
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
    
    //Adds logo and music button
    private void prepare(){
        Button logo = new Button(new GreenfootImage("WordDrift Logo.png"), getHeight()/5, 3.272);
        addObject(logo, getWidth()/2 - 20, (int) (getHeight()/5));
        addObject(musicOff, 1150, 100);
        draw();
    }
    
    //Buttons would change when players hover over them
    public void draw(){
        removeObject(playButton);
        removeObject(achievementsButton);
        removeObject(instructionsButton);
        removeObject(exitButton);
        
        playButton = new Button(new GreenfootImage("PlayButton" + ((option == 1 ) ? "-2" : "-1") + ".png"), getHeight()/3, 4);
        achievementsButton = new Button(new GreenfootImage("AchievementsButton" + ((option == 2 ) ? "-2" : "-1") + ".png"), getHeight()/9, 3.8);
        instructionsButton = new Button(new GreenfootImage("InstructionsButton" + ((option == 3 ) ? "-2" : "-1") + ".png"), getHeight()/9, 3.8);
        exitButton = new Button(new GreenfootImage("ExitButton" + ((option == 4 ) ? "-2" : "-1") + ".png"), getHeight()/9, 3.8);
        
        addObject(playButton, getWidth()/2, getHeight()/2);
        addObject(achievementsButton, getWidth()/5, getHeight()*4/5);
        addObject(instructionsButton, getWidth()/2, getHeight()*4/5);
        addObject(exitButton, getWidth()*4/5, getHeight()*4/5);


    }
    
    public void act(){
        if(Greenfoot.mouseClicked(playButton)) Greenfoot.setWorld(new Game());
        if(Greenfoot.mouseClicked(achievementsButton)) Greenfoot.setWorld(new Achievements());
        if(Greenfoot.mouseClicked(instructionsButton)) Greenfoot.setWorld(new Instructions());
        if(Greenfoot.mouseClicked(exitButton)) Greenfoot.stop();
        if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("enter")) && !isDown){
            if(option == 1){
                if(Greenfoot.isKeyDown("down")){
                    cursor.play();
                    option = 2;
                }
            }else{
                if(Greenfoot.isKeyDown("up")){
                    cursor.play();
                    option = 1;
                }else if(Greenfoot.isKeyDown("left") && option == 2){
                    option = 4;
                }else if(Greenfoot.isKeyDown("right") && option == numOfOptions){
                    option = 2;
                }else if(Greenfoot.isKeyDown("left")){
                    cursor.play();
                    option--;
                }else if(Greenfoot.isKeyDown("right")){
                    cursor.play();
                    option++;
                }
            }
            draw();
            if(Greenfoot.isKeyDown("enter")){
                click.play();
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
                        addObject(exitScreen, getWidth()/2, getHeight()/2);
                        bgm.pause();
                        Greenfoot.stop();
                        break;
                }
            }
            isDown = true;
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
