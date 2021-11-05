import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The pointer class creates the pointer that points to the player's button selection
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pointer extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[4];
    private int delay = 4, num = (int)(Math.random() * 4);
    public Pointer(int height, double widthMulti, boolean flip){
        for(int i = 1; i <= images.length; i++){
            GreenfootImage img = new GreenfootImage("pointer"+i+".png");
            if(flip){
                img.mirrorHorizontally();
            }
            img.scale((int) (height*widthMulti), height);
            images[i-1] = img;
        }
    }
    public int getWidth(){
        return images[num].getWidth();
    }
    public void act()
    {
        // Add your action code here.  
        if(delay == 0)
            delay = 4;
        if(delay == 1)
            
            num++;
            if(num >= images.length)
                num = 0;
        if(delay > 0)
            delay--;
        setImage(images[num]);
    }
}
