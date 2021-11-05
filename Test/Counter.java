import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates counters so that the game could keep track of the number 
 * of words or boards the player finished
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    // The score of the counter
    public int score = 0;
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(new GreenfootImage("Score: " + score, 42, Color.GREEN, Color.BLACK));
    }
    
    //Whenever the player finishes a word or boards, the counter goes up by 1
    public void add(){
        score++;
    }
    
    //Returns the score of the counter
    public int getScore(){
        return score;
    }
}
