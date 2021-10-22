import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    int wordLength = 4;
    int numOfWords = 5;
    ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        for(int i= 0; i < numOfWords; i++){
            ArrayList<String> temp = new ArrayList<String>();
            board.add(temp);
        }
        ArrayList<String> words = Reader.read(wordLength);
        Collections.shuffle(words);
        for(int i = 0; i < numOfWords; i++){
            //get a word and split
            String[] strArr = words.get(i).split("");
            //then add to each list???
            for(int j = 0; j < wordLength; j++){
                if(!board.get(j).contains(strArr[j])){
                    board.get(j).add(strArr[j]);    
                }
            }
        }
        for(ArrayList<String> s : board){
            Collections.shuffle(board);
        }
    }
}
