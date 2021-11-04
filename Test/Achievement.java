import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Achievement here.
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
    
    private final int BAR_LENGTH = 150;
    
    public Achievement(GreenfootImage ach, int progress, int total)
    {
        ach.drawImage(progressBar(progress, total), 100, 200);
        setImage(ach);
    }
    
    private GreenfootImage progressBar(int progress, int total)
    {
        double percentReached = progress/(double)total;
        GreenfootImage bar;
        if(percentReached == 0.0){
            bar = new GreenfootImage(1, 10);
        }else{
            bar = new GreenfootImage((int) (BAR_LENGTH * percentReached), 10);
        }
            
        
        bar.setColor(Color.WHITE);
        bar.fill();
        return bar;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
