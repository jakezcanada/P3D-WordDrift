import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class PreGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreGame extends World
{
    //To keep track of button options
    private int option = 1;
    private int numOfOptions = 4;
    private boolean isDown = false;

    //Game generation variables
    private Random r = new Random();
    private int numOfWords = r.nextInt(3) + 3;

    //Buttons on the titlescreen
    private Button easyButton = new Button(new GreenfootImage("Easy-1.png"), getHeight()/10, 3.8);
    private Button mediumButton = new Button(new GreenfootImage("Medium-1.png"), getHeight()/10, 3.8);
    private Button hardButton = new Button(new GreenfootImage("Hard-1.png"), getHeight()/10, 3.8);
    private Button insanityButton = new Button(new GreenfootImage("Insanity-1.png"), getHeight()/10, 3.8);

    /**
     * Constructor for objects of class PreGame.
     * 
     */
    public PreGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        addObject(new Button(new GreenfootImage("Difficulty selection layout.png"), getHeight(), 1.778), getWidth()/2, getHeight()/2);
        draw();
    }

    //Buttons would change when players hover over them
    public void draw(){
        removeObject(easyButton);
        removeObject(mediumButton);
        removeObject(hardButton);
        removeObject(insanityButton);

        easyButton = new Button(new GreenfootImage("Easy" + ((option == 1 ) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8);
        mediumButton = new Button(new GreenfootImage("Medium" + ((option == 2 ) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8);
        hardButton = new Button(new GreenfootImage("Hard" + ((option == 3 ) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8);
        insanityButton = new Button(new GreenfootImage("Insanity" + ((option == 4 ) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8);

        addObject(easyButton, getWidth()/7, getHeight()/2);
        addObject(mediumButton, getWidth()*2/5-25, getHeight()/2);
        addObject(hardButton, getWidth()*3/5+25, getHeight()/2);
        addObject(insanityButton, getWidth()*6/7, getHeight()/2);
    }

    //Allows player to interact with buttons
    public void act(){

        if(Greenfoot.mouseClicked(easyButton)) {
            TitleScreen.click.play();
            addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
            Greenfoot.delay(1);
            Greenfoot.setWorld(new Game(numOfWords, r.nextInt(5-1) + 1));
        }
        if(Greenfoot.mouseClicked(mediumButton)) {
            TitleScreen.click.play();
            addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
            Greenfoot.delay(1);
            Greenfoot.setWorld(new Game(numOfWords, r.nextInt(7-4) + 4));
        }
        if(Greenfoot.mouseClicked(hardButton)) {
            TitleScreen.click.play(); 
            addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
            Greenfoot.delay(1);
            Greenfoot.setWorld(new Game(numOfWords, r.nextInt(10-6) + 6));
        }
        if(Greenfoot.mouseClicked(insanityButton)) {
            TitleScreen.click.play(); 
            addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
            Greenfoot.delay(1);
            Greenfoot.setWorld(new Game(numOfWords, r.nextInt(18-10) + 10));
        }
        if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("enter")) && !isDown){
            if(Greenfoot.isKeyDown("right") && option == 4){
                TitleScreen.cursor.play();
                option = 1;
            }else if(Greenfoot.isKeyDown("left") && option == 1){
                TitleScreen.cursor.play();
                option = 4;
            }else if(Greenfoot.isKeyDown("left")){
                TitleScreen.cursor.play();
                option--;
            }else if(Greenfoot.isKeyDown("right")){
                TitleScreen.cursor.play();
                option++;
            }
            draw();
            if(Greenfoot.isKeyDown("enter")){
                TitleScreen.click.play();
                switch(option){
                    case 1: // easy
                        addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
                        Greenfoot.delay(1);
                        Greenfoot.setWorld(new Game(numOfWords, r.nextInt(5-1) + 1));
                        break;
                    case 2: // medium
                        addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
                        Greenfoot.delay(1);
                        Greenfoot.setWorld(new Game(numOfWords, r.nextInt(7-4) + 4));
                        break;
                    case 3: // hard
                        addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
                        Greenfoot.delay(1);
                        Greenfoot.setWorld(new Game(numOfWords, r.nextInt(10-6) + 6));
                        break;
                    case 4: // insanity
                        addObject(new Button(new GreenfootImage("please wait text.png"), getHeight()/20, 11.6363636), getWidth()/2, getHeight()/2+200);
                        Greenfoot.delay(1);
                        Greenfoot.setWorld(new Game(numOfWords, r.nextInt(18-10) + 10));
                        break;
                }
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("enter")) && isDown){
            isDown = false;
        }
    }
}
