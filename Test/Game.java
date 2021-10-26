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
    private int numOfWords = 4;
    private boolean isDown = false;
    // Create the game board
    private ArrayList<String> words = Reader.read(wordLength);
    private ArrayList<ArrayList<Block>> board = createBoard();
    // selected will keep track of which Block is selected in each column
    private int[] selected = new int[wordLength];
    // selectedColumn will keep track of, who could've guesses, the selected column...
    private int selectedColumn = 0;
    public Game(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        drawBoard();
        //fill selected with 0
        Arrays.fill(selected, 0);
    }
    
    public void act(){
        // Nav operations
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && !isDown){
            if(Greenfoot.isKeyDown("right") && selectedColumn < wordLength-1){
                selectedColumn++;
                System.out.println(Arrays.toString(selected));
            }else if(Greenfoot.isKeyDown("left") && selectedColumn > 0){
                selectedColumn--;
                System.out.println(Arrays.toString(selected));
            }else if(Greenfoot.isKeyDown("up") && selected[selectedColumn] < board.get(selectedColumn).size()-1){
                selected[selectedColumn]++;
                String selectedStr = selectedString(board, selected);
                if(words.contains(selectedStr)){
                    for(int i = 0; i < selected.length; i++){
                        board.get(i).get(selected[i]).solved = true;
                    }
                }
                List objects = getObjects(null);
                removeObjects(objects);
                drawBoard();
            }else if(Greenfoot.isKeyDown("down") && selected[selectedColumn] > 0){
                selected[selectedColumn]--;
                String selectedStr = selectedString(board, selected);
                if(words.contains(selectedStr)){
                    for(int i = 0; i < selected.length; i++){
                        board.get(i).get(selected[i]).solved = true;
                    }
                }
                //temp way to remove objects, kill me
                List objects = getObjects(null);
                removeObjects(objects);
                drawBoard();
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && isDown){
            isDown = false;
        }
        //draw everything here eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    }
    
    public ArrayList<ArrayList<Block>> createBoard(){
        // Create all new ArrayLists
        Random r = new Random();
        ArrayList<ArrayList<String>> boardTemp = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < numOfWords; i++){
            ArrayList<String> temp = new ArrayList<String>();
            boardTemp.add(temp);
        }
        // Put the words into the board object
        for(int i = 0; i < numOfWords; i++){
            // Get a word and split
            String strT = words.get(r.nextInt(words.size()));
            System.out.println(strT);
            String[] strArr = strT.split("");
            // If the section doesn't already have said letter, add it
            for(int j = 0; j < wordLength; j++){
                if(!boardTemp.get(j).contains(strArr[j])){
                    boardTemp.get(j).add(strArr[j]);    
                }
            }
        }
        
        for(ArrayList<String> arr : boardTemp){
            for(String b : arr){
                System.out.print(b);
            }
            System.out.println();
        }
        // Copy over into a 2D Block Array for efficient completion check
        ArrayList<ArrayList<Block>> board2 = new ArrayList<ArrayList<Block>>();
        for(int i = 0; i < numOfWords; i++){
            ArrayList<Block> temp = new ArrayList<Block>();
            board2.add(temp);
        }
        for(int i = 0; i < wordLength; i++){
            for(int j = 0; j < boardTemp.get(i).size(); j++){
                Block temp = new Block();
                temp.solved = false;
                temp.item = boardTemp.get(i).get(j);
                board2.get(i).add(temp);
            }
        }
        
        for(ArrayList<Block> s : board2){
            Collections.shuffle(s);
        }
        
        return board2;
    }
    
    // oo oo a a draw board here
    public void drawBoard(){
        int blockSize = getWidth()/15;
        int offSet = (getWidth() - (blockSize*wordLength))/2;
        for(int i = 0; i < selected.length; i++){
            int x = offSet+(blockSize*i);
            addObject(new Button(board.get(i).get(selected[i]).item, 30), offSet+blockSize*i, getHeight()/2);
            for(int j = selected[i]-1; j > -1; j--){
                addObject(new Button(board.get(i).get(j).item, 30), offSet+blockSize*i, getHeight()/2 - blockSize*(selected[i]-j));
            }
            for(int j = selected[i]+1; j < board.get(i).size(); j++){
                addObject(new Button(board.get(i).get(j).item, 30), offSet+blockSize*i, getHeight()/2 + blockSize*(j-selected[i]));
            }
        }
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
