import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Shows the achievements the user has made and their progress for unachieved ones
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievements extends World
{
    private final int wn_FULL = 5;
    private final int lw_FULL = 8;
    private final int sbe_FULL = 3;
    private final int bm_FULL = 5;
    private final int t_FULL = 8;
    private final int dw_FULL = 10;
    private final int wd_FULL = 600000;
    private final int ss_FULL = 1200000;
    private final int wdc_FULL = 15;
    
    //Greyed Out Achievements
    public Achievement mfw = new Achievement(new GreenfootImage("MyFirstWord-GreyedAchievement.png"), 0, 1);
    public Achievement wn = new Achievement(new GreenfootImage("WordNerd-GreyedAchievement.png"), 0, wn_FULL);
    public Achievement lw = new Achievement(new GreenfootImage("LetterWizard-GreyedAchievement.png"), 0, lw_FULL);
    public Achievement bbu = new Achievement(new GreenfootImage("BigBrainUser-GreyedAchievement.png"), 0, 1);
    public Achievement sbe = new Achievement(new GreenfootImage("SpellingBeeExpert-GreyedAchievement.png"), 0, sbe_FULL);
    public Achievement bm = new Achievement(new GreenfootImage("BoardMaster-GreyedAchievement.png"), 0, bm_FULL);
    public Achievement t = new Achievement(new GreenfootImage("Trivial-GreyedAchievement.png"), 0, 1);
    public Achievement dw = new Achievement(new GreenfootImage("DeathWish-GreyedAchievement.png"), 0, dw_FULL);
    public Achievement wd = new Achievement(new GreenfootImage("WristDamage-GreyedAchievement.png"), 0, wd_FULL);
    public Achievement ss = new Achievement(new GreenfootImage("StarStudent-GreyedAchievement.png"), 0, ss_FULL);
    public Achievement wdc = new Achievement(new GreenfootImage("WordDriftChampion-GreyedAchievement.png"), 0, wdc_FULL);
    
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
        //Cover all of the achievements in the background with the greyed out version
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
        
        //Show the latest updated achievements
        addAchievements();
        
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
                       
        //When the player has completed an achievement, the greyed out version gets deleted
        //to show the lit up version underneath
        if(i >= 1){
            removeObject(mfw);
        }
        if(i >= wn_FULL){
            removeObject(wn);
        }
        else
        {
            wn.setProgress(i);
        }
        if(i >= lw_FULL){
            removeObject(lw);
        }
        else
        {
            lw.setProgress(i);
        }
        if(j >= 1){
            removeObject(bbu);
        }
        if(j >= sbe_FULL){
            removeObject(sbe);
        }
        else
        {
            sbe.setProgress(j);
        }
        if(j >= bm_FULL){
            removeObject(bm);
        }
        else
        {
            bm.setProgress(j);
        }
        if(j >= t_FULL){
            removeObject(t);
        }
        else
        {
            bm.setProgress(j);
        }
        if(j >= dw_FULL)
        {
            removeObject(dw);
        }
        else
        {
            dw.setProgress(j);
        }
        if(TitleScreen.timer.millisElapsed() > 600000)
        {
            removeObject(wd);
        }
        else
        {
            ss.setProgress(TitleScreen.timer.millisElapsed());
        }
        if(j >= wdc_FULL)
        {
            removeObject(wdc);
        }
        else
        {
            wdc.setProgress(j);
        }
        if(TitleScreen.timer.millisElapsed() > 1200000){
            removeObject(ss);
        } 
        else{
            ss.setProgress(TitleScreen.timer.millisElapsed());
        }    
    }
    
}
