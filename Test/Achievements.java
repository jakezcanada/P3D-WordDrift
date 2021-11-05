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
    
    public Achievement mfw = new Achievement(new GreenfootImage("MyFirstWord-GreyedAchievement.png"), 5, 5);
    public Achievement wn = new Achievement(new GreenfootImage("WordNerd-GreyedAchievement.png"), 5, 5);
    public Achievement lw = new Achievement(new GreenfootImage("LetterWizard-GreyedAchievement.png"), 5, 5);
    public Achievement bbu = new Achievement(new GreenfootImage("BigBrainUser-GreyedAchievement.png"), 5, 5);
    public Achievement sbe = new Achievement(new GreenfootImage("SpellingBeeExpert-GreyedAchievement.png"), 5, 5);
    public Achievement bm = new Achievement(new GreenfootImage("BoardMaster-GreyedAchievement.png"), 5, 5);
    public Achievement t = new Achievement(new GreenfootImage("Trivial-GreyedAchievement.png"), 5, 5);
    public Achievement dw = new Achievement(new GreenfootImage("DeathWish-GreyedAchievement.png"), 5, 5);
    public Achievement wd = new Achievement(new GreenfootImage("WristDamage-GreyedAchievement.png"), 5, 5);
    public Achievement ss = new Achievement(new GreenfootImage("StarStudent-GreyedAchievement.png"), 5, 5);
    public Achievement wdc = new Achievement(new GreenfootImage("WordDriftChampion-GreyedAchievement.png"), 5, 5);
    
    /**
     * Constructor for objects of class Achievements.
     * 
     */
    private Button exitAchievementsButton = new Button(new GreenfootImage("BackToMenu-1.png"), getHeight()/15   , 3.8);

    public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    public Achievements()
    {    
        super(1280, 720, 1, false);
        addObject(exitAchievementsButton, 150, 70);
        prepare();
    }
    
    public void prepare(){
        int width = 400;
        int height = 280;
        addAchievements();
        for(String key: map.keySet()){
            GreenfootImage im = new GreenfootImage(key,60,Color.WHITE,Color.BLACK);
            GreenfootImage img = map.get(key);
            Picture im1 = new Picture(im);
            Picture img1 = new Picture(img);
            addObject(im1,width,height);
            addObject(img1,width+200,height);
            height += 120;
        }
        //Cover all of the achievements with the greyed out version
        addObject(mfw, 239, 188);
        
        addObject(wn, 239, 301);
        addObject(lw, 239, 410);
        addObject(bbu, 239, 516);
        addObject(sbe, 239, 622);
        addObject(bm, 626, 188);
        addObject(t, 626, 296);
        addObject(dw, 626, 408);
        addObject(wd, 626, 519);
        addObject(ss, 626, 627);
        addObject(wdc,1080, 630);
        
        
    }
    public void act(){
        if(Greenfoot.isKeyDown("enter")) {
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        } else if (Greenfoot.mouseClicked(exitAchievementsButton)){
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        }
    }
    
    public void addAchievements(){
        int i = Game.counter.getScore();
        int j = Game.boardCounter.getScore();
        int m = Game.wordLength;
        int n = Game.numOfWords;
        if(i >= 1){
            removeObject(mfw);
        }
        if(i >= 5){
            removeObject(wn);
        }
        if(i >= 8){
            removeObject(lw);
        }
        if(j >= 1){
            removeObject(bbu);
        }
        if(j >= 3){
            removeObject(sbe);
        }
        if(j >= 3){
            removeObject(bm);
        }
        if(m == 1)
        {
            removeObject(t);
        }
        if(m > 13)
        {
            removeObject(dw);
        }
        if(n > 26)
        {
            removeObject(wd);
        }
    }
    
}
