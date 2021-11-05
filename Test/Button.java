import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates the buttons that the players interact with
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    public Button(String text, int fontSize, Color color){
        GreenfootImage playButton = new GreenfootImage(fontSize*2, fontSize*2);
        Font adjustedFont = new Font(true, false, fontSize);
        playButton.setFont(adjustedFont);
        playButton.setColor(color);
        playButton.fill();
        playButton.setColor(Color.BLACK);
        playButton.drawString(text, 0, fontSize);

        setImage(playButton);
    }
    
    public Button(GreenfootImage img, int height, double widthMulti){
        img.scale((int) (height*widthMulti), height);
        setImage(img);
    }
}
