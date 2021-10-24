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

//feature suggestions: timed score achievement and unorthodox solution achievement
public class Game extends World{
    class Block{
        String item;
        Boolean solved;
    }
    // State board "size"
    private int wordLength = 4;
    private int numOfWords = 5;
    // Create the game board
    private ArrayList<String> words = Reader.read(wordLength);
    private ArrayList<ArrayList<Block>> board = createBoard(wordLength, numOfWords, words);
    // selected will keep track of which Block is selected in each column
    private int[] selected = new int[wordLength];
    // selectedColumn will keep track of, who could've guesses, the selected column...
    private int selectedColumn = 0;
    public Game(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        // Randomize words
        Collections.shuffle(words);
        // Randomize each columns' order
        for(ArrayList<Block> s : board){
            Collections.shuffle(board);
        }
        //fill selected with 0
        Arrays.fill(selected, 0);
    }
    
    public void act(){
        // Nav operations
        if(Greenfoot.isKeyDown("right") && selectedColumn < wordLength-1){
            selectedColumn++;
        }else if(Greenfoot.isKeyDown("left") && selectedColumn > 0){
            selectedColumn--;
        }else if(Greenfoot.isKeyDown("up") && selected[selectedColumn] < board.get(selectedColumn).size()-1){
            selected[selectedColumn]++;
            String selectedStr = selectedString(board, selected);
            if(words.contains(selectedStr)){
                for(int i = 0; i < selected.length; i++){
                    board.get(i).get(selected[i]).solved = true;
                }
            }
        }else if(Greenfoot.isKeyDown("down") && selected[selectedColumn] > 0){
            selected[selectedColumn]--;
            String selectedStr = selectedString(board, selected);
            if(words.contains(selectedStr)){
                for(int i = 0; i < selected.length; i++){
                    board.get(i).get(selected[i]).solved = true;
                }
            }
        }
    }
    
    public ArrayList<ArrayList<Block>> createBoard(int wordLength, int numOfWords, ArrayList<String> words){
        // Create all new ArrayLists
        ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
        for(int i= 0; i < numOfWords; i++){
            ArrayList<String> temp = new ArrayList<String>();
            board.add(temp);
        }
        // Put the words into the board object
        for(String str : words){
            // Get a word and split
            String[] strArr = str.split("");
            // If the section doesn't already have said letter, add it
            for(int j = 0; j < wordLength; j++){
                if(!board.get(j).contains(strArr[j])){
                    board.get(j).add(strArr[j]);    
                }
            }
        }
        // Copy over into a 2D Block Array for efficient completion check
        ArrayList<ArrayList<Block>> board2 = new ArrayList<ArrayList<Block>>();
        for(int i= 0; i < numOfWords; i++){
            ArrayList<Block> temp = new ArrayList<Block>();
            board2.add(temp);
        }
        for(int i= 0; i < wordLength; i++){
            for(int j = 0; j < board.get(i).size(); j++){
                Block temp = new Block();
                temp.solved = false;
                temp.item = board.get(i).get(j);
                board2.get(i).add(temp);
            }
        }
        
        return board2;
    }
    
    public String selectedString(ArrayList<ArrayList<Block>> board, int[] selected){
        String selectedString = "";
        for(int i = 0; i < selected.length; i++){
            selectedString += board.get(i).get(selected[i]).item;
        }
        return selectedString;
    }
    
    // Check if the current board is fully "solved"
    public boolean checkBoard(ArrayList<ArrayList<Block>> board){
        for(ArrayList<Block> arr : board){
            for(Block b : arr){
                if(!b.solved){
                    return false;
                }
            }
        }
        return true;
    }
    
}
