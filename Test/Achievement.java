import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * This is the class that keeps track of the progress of achievements
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement extends Actor
{
    /**
     * Act - do whatever the Achievement wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private final int BAR_LENGTH = 175;
    private final int BAR_HEIGHT = 10;
    private int progress;
    private int total;
    private GreenfootImage ach;
    public Achievement(GreenfootImage theAch, int theProgress, int theTotal)
    {
        progress = theProgress;
        ach = theAch;
        total = theTotal;
    }
    
    //Draws the progress bar
    private GreenfootImage progressBar(int progress, int total)
    {
        double percentReached = progress/(double)total;
        GreenfootImage bar;
        if(percentReached == 0.0){
            bar = new GreenfootImage(1, BAR_HEIGHT);
        }else{
            bar = new GreenfootImage((int) (BAR_LENGTH * percentReached), BAR_HEIGHT);
        }
            
        bar.setColor(Color.WHITE);
        bar.fill();
        return bar;
    }
    
    //Updates the progress
    public void setProgress(int prog)
    {
        progress = prog;
    }
    
    public void act() 
    {
        // Draws the prgress bar
        ach.setColor(Color.GRAY);
        ach.fillRect(97, 60, BAR_LENGTH, BAR_HEIGHT);
        ach.drawImage(progressBar(progress, total), 97, 60);
        setImage(ach);
    }    
}
