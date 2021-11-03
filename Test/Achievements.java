import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Achievements here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievements extends World
{
    private HashMap<String, GreenfootImage> map = new HashMap<String, GreenfootImage>();
    /**
     * Constructor for objects of class Achievements.
     * 
     */
   public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    public Achievements()
    {    
        super(1280, 720, 1, false);
        Button exitInstructionsButton = new Button(new GreenfootImage("BackToMenu-1.png"), getHeight()/15   , 3.8);
        addObject(exitInstructionsButton, getWidth()/2, getHeight()*9/10);
        prepare();
    }
    
    public void prepare(){
        int width = 400;
        int height = 280;
        addAchievements();
        if(map.isEmpty()){
            String str = "You do not have any achievements yet :(";
            GreenfootImage im = new GreenfootImage(str,60,Color.WHITE,Color.BLACK);
            Picture im1 = new Picture(im);
            addObject(im1, 640, 320);
        }
        for(String key: map.keySet()){
            GreenfootImage im = new GreenfootImage(key,60,Color.WHITE,Color.BLACK);
            GreenfootImage img = map.get(key);
            Picture im1 = new Picture(im);
            Picture img1 = new Picture(img);
            addObject(im1,width,height);
            addObject(img1,width+200,height);
            height += 120;
        }
    }
    public void act(){
        if(Greenfoot.isKeyDown("enter")) {
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        }
    }
    
    public void addAchievements(){
        int i = Game.counter.getScore();
        if(i >= 5){
            map.put("Word Beginner", new GreenfootImage("a1.png"));
        }
        if(i >= 10){
            map.put("Word Nerd", new GreenfootImage("a2.png"));
        }
        if(i >= 20){
            map.put("Word Expert", new GreenfootImage("a3.png"));
        }
        if(i >= 30){
            map.put("Word Master", new GreenfootImage("a4.png"));
        }
        if(i >= 50){
            map.put("Word Lord", new GreenfootImage("a5.png"));
        }
    }
    
}
