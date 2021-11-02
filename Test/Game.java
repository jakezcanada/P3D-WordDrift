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
    
    // Inner class to store the properties of each tile/block
    class Block{
        String item;
        Boolean solved;
    }
    
    // Word length size is max at 23
    private int wordLength;
    private int numOfWords;
    // Counter
    public static Counter counter = new Counter();
    // ArrayList of solved words
    private ArrayList<String> solvedwords = new ArrayList<String>();
    // Word length size is max at 14
    private boolean isDown = false;
    private Random r = new Random();
    // selected will keep track of which Block is selected in each column
    private int[] selected;
    // Create the game board
    private ArrayList<String> words;
    private ArrayList<ArrayList<Block>> board;
    // selectedColumn will keep track of, who could've guesses, the selected column...
    private int selectedColumn = 0;
    public Game(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        
        // Ask user for word length
        while(true){
            String input = Greenfoot.ask("How long would you like the words to be? (1 - 23 characters)");
            if(isNumeric(input)){
                int temp = (int) Double.parseDouble(input);
                if(temp > 1 && temp < 24){
                    wordLength = temp;
                    break;
                }
            }
        }
        
        // Ask user for num of words
        while(true){
            //if there are not enough words, there will be as many as possible
            String input = Greenfoot.ask("How many words would you like? (under 8 is recommended)");
            if(isNumeric(input)){
                int temp = (int) Double.parseDouble(input);
                numOfWords = temp;
                break;
            }
        }
        
        // Initialize variables
        words = Reader.read(wordLength);
        selected = new int[wordLength];
        board = createBoard();
        
        // Fill selected with 0
        for(int i = 0; i < selected.length; i++){
            selected[i] = r.nextInt(board.get(i).size());
        }
        
        // Draw board
        drawBoard();
        String selectedStr = selectedString(board, selected);
        if(words.contains(selectedStr)){
            if(!solvedwords.contains(selectedStr)){
                TitleScreen.click.play();
                counter.add();
            }    
            solvedwords.add(selectedStr);
            for(int i = 0; i < selected.length; i++){
                board.get(i).get(selected[i]).solved = true;
            }
            checkAchievements();
        }
    }
    
    public void act(){
        // Add counter
        addObject(counter, 1080, 100);
        // Nav operations
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && !isDown){
            TitleScreen.cursor.play();
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
                    if(!solvedwords.contains(selectedStr)){
                        TitleScreen.click.play();
                        counter.add();
                    }    
                    solvedwords.add(selectedStr);
                    for(int i = 0; i < selected.length; i++){
                        board.get(i).get(selected[i]).solved = true;
                    }
                    checkAchievements();
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
                    if(!solvedwords.contains(selectedStr)){
                        TitleScreen.click.play();
                        counter.add();
                    }    
                    solvedwords.add(selectedStr);
                    for(int i = 0; i < selected.length; i++){
                        board.get(i).get(selected[i]).solved = true;
                    }
                    checkAchievements();
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
    
    // Draw the board
    public void drawBoard(){
        int blockSize = (wordLength%2 == 0) ? getWidth()/14 : getWidth()/15;
        int offSet = (getWidth() - (blockSize*wordLength))/2;
        if(wordLength > 13){
            offSet = getWidth()/(wordLength+2) + (getWidth()/(wordLength+2))/2;
            blockSize = getWidth()/(wordLength+2);
        }
        boolean toggle = false;
        for(int i = 0; i < selected.length; i++){
            int x = offSet+(blockSize*i);
            String prefix1 = "S" + (board.get(i).get(selected[i]).solved ? "S" : "U");
            //addObject(new Button(board.get(i).get(selected[i]).item, blockSize/2, board.get(i).get(selected[i]).solved ? solved : unsolved), offSet+blockSize*i, getHeight()/2);
            addObject(new Button(new GreenfootImage(prefix1 + "_" + (board.get(i).get(selected[i]).item.toUpperCase()) + ".png"), blockSize, 1), offSet+blockSize*i, getHeight()/2);
            for(int j = selected[i]-1; j > -1; j--){
                String prefix = "U" + (board.get(i).get(j).solved ? "S" : "U");
                addObject(new Button(new GreenfootImage(prefix + "_" + (board.get(i).get(j).item.toUpperCase()) + ".png"), blockSize, 1), offSet+blockSize*i, getHeight()/2 - blockSize*(selected[i]-j));
            }
            for(int j = selected[i]+1; j < board.get(i).size(); j++){
                String prefix = "U" + (board.get(i).get(j).solved ? "S" : "U");
                addObject(new Button(new GreenfootImage(prefix + "_" + (board.get(i).get(j).item.toUpperCase()) + ".png"), blockSize, 1), offSet+blockSize*i, getHeight()/2 + blockSize*(j-selected[i]));
            }
        }

    }
    
    // Get the selected String
    public String selectedString(ArrayList<ArrayList<Block>> board, int[] selected){
        String selectedString = "";
        for(int i = 0; i < selected.length; i++){
            selectedString += board.get(i).get(selected[i]).item;
        }
        return selectedString;
    }
    
    // Check if String is a number
    public static boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;
        }
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
    
    public void checkAchievements(){
        int w = counter.getScore();
        if(w == 5 || w == 10 || w == 20 || w == 30 || w == 50){
            GreenfootImage img = new GreenfootImage("You've solved " + w + " words and unlocked a new achievement!",40,Color.WHITE,Color.BLACK);
            Picture p = new Picture(img);
            addObject(p, 640, 320);
            Greenfoot.delay(180);
            removeObject(p);
        }
    }
}
