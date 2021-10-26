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
    private int wordLength = 5;
    private int numOfWords = 4;
    private boolean isDown = false;
    private Color solved = new Color(200, 200, 200);
    private Color unsolved = new Color(150, 150, 150);
    // selected will keep track of which Block is selected in each column
    private int[] selected = new int[wordLength];
    // Create the game board
    private ArrayList<String> words = Reader.read(wordLength);
    private ArrayList<ArrayList<Block>> board = createBoard();
    // selectedColumn will keep track of, who could've guesses, the selected column...
    private int selectedColumn = 0;
    public Game(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        // Fill selected with 0
        Arrays.fill(selected, 0);
        // Draw board
        drawBoard();
    }
    
    public void act(){
        // Nav operations
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && !isDown){
            if(Greenfoot.isKeyDown("right") && selectedColumn < wordLength-1){
                selectedColumn++;
                //System.out.println(Arrays.toString(selected));
                drawBoard();
            }else if(Greenfoot.isKeyDown("left") && selectedColumn > 0){
                selectedColumn--;
                //System.out.println(Arrays.toString(selected));
                drawBoard();
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
                // Send back to home screen after game completion
                if(checkBoard()){
                    Greenfoot.setWorld(new TitleScreen());
                }
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
                // Send back to home screen after game completion
                if(checkBoard()){
                    Greenfoot.setWorld(new TitleScreen());
                }
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
        for(int i = 0; i < wordLength; i++){
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
        for(int i = 0; i < wordLength; i++){
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
        boolean toggle = false;
        for(int i = 0; i < selected.length; i++){
            int x = offSet+(blockSize*i);
            if(i == selectedColumn){
                solved = solved.brighter();
                unsolved = unsolved.brighter();
                toggle = true;
            }
            addObject(new Button(board.get(i).get(selected[i]).item, blockSize/2, board.get(i).get(selected[i]).solved ? solved : unsolved), offSet+blockSize*i, getHeight()/2);
            for(int j = selected[i]-1; j > -1; j--){
                addObject(new Button(board.get(i).get(j).item, blockSize/2, board.get(i).get(j).solved ? solved : unsolved), offSet+blockSize*i, getHeight()/2 - blockSize*(selected[i]-j));
            }
            for(int j = selected[i]+1; j < board.get(i).size(); j++){
                addObject(new Button(board.get(i).get(j).item, blockSize/2, board.get(i).get(j).solved ? solved : unsolved), offSet+blockSize*i, getHeight()/2 + blockSize*(j-selected[i]));
            }
            if(toggle){
                solved = solved.darker();
                unsolved = unsolved.darker();
                toggle = false;
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
    public boolean checkBoard(){
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
