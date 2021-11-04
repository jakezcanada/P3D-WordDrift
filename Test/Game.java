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
<<<<<<< Updated upstream
    // State board "size"
    private int wordLength = 5;
    private int numOfWords = 4;
    private boolean isDown = false;
    private Color solved = new Color(200, 200, 200);
    private Color unsolved = new Color(150, 150, 150);
=======
    public static GreenfootSound cursor = new GreenfootSound("Cursor.mp3");
    // Word length size is max at 23
    public static int wordLength;
    public static int numOfWords;
    // Word Counter and Board Counter
    public static Counter counter = new Counter();
    public static Counter boardCounter = new Counter();
    // ArrayList of solved words
    private ArrayList<String> solvedwords = new ArrayList<String>();
    private ArrayList<Integer> yeah = new ArrayList<Integer>();
    private ArrayList<Integer> boardcheck = new ArrayList<Integer>();
    private HashMap<Integer,GreenfootImage> woah = new HashMap<Integer,GreenfootImage>();
    private HashMap<Integer,GreenfootImage> lol = new HashMap<Integer,GreenfootImage>();
    private Button backtomenu = new Button(new GreenfootImage("BackToMenu-2.png"), getHeight()/15, 3.8);
    private boolean isDown = false;
    private boolean pause = false;
    private boolean hasWon = false;
    private int pauseOption = 1;
    private Random r = new Random();
>>>>>>> Stashed changes
    // selected will keep track of which Block is selected in each column
    private int[] selected = new int[wordLength];
    // Create the game board
    private ArrayList<String> words = Reader.read(wordLength);
    private ArrayList<ArrayList<Block>> board = createBoard();
    // selectedColumn will keep track of, who could've guesses, the selected column...
    private int selectedColumn = 0;
    public Game(){    
        super(1280, 720, 1);
<<<<<<< Updated upstream
=======

        // Ask user for word length
        while(true){
            String input = Greenfoot.ask("How long would you like the words to be? (1 - 20 characters)");
            if(isNumeric(input)){
                int temp = (int) Double.parseDouble(input);
                if(temp > 0 && temp < 21){
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
                if(temp > 1){
                    numOfWords = temp;
                    break;
                }

            }
        }

        // Initialize variables
        words = Reader.read(wordLength);
        selected = new int[wordLength];
        board = createBoard();


>>>>>>> Stashed changes
        // Fill selected with 0
        Arrays.fill(selected, 0);
        // Draw board
        drawBoard();
    }
    
    public void act(){
<<<<<<< Updated upstream
        // Nav operations
=======
        showText("Press ENTER to pause", 200,670);
        // Add counter
        addObject(counter, 1080, 100);
        
        // Add achievement pop-ups into hashmap
        lol.put(1,new GreenfootImage("MyFirstWord-PopUp.png"));
        lol.put(5,new GreenfootImage("WordNerd-PopUp.png"));
        lol.put(8,new GreenfootImage("LetterWizard-PopUp.png"));
        woah.put(1,new GreenfootImage("BigBrainUser-PopUp.png"));
        woah.put(3,new GreenfootImage("SpellingBeeExpert-PopUp.png"));
        woah.put(5,new GreenfootImage("BoardMaster-PopUp.png"));
        woah.put(15,new GreenfootImage("Champion-Achieved.png"));
        

        if(Greenfoot.isKeyDown("enter") && !pause && !hasWon){
            removeObjects(getObjects(null));
            pauseOption = 1;
            drawPauseMenu();
            pause = true;
        }
        // Nav operations
        if(pause){
            checkPauseInput();
        }else if(!hasWon){            
            checkShiftInput();
        }else if(hasWon && if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new TitleScreen());
        }
        
        if(Greenfoot.mouseClicked(backtomenu)) Greenfoot.setWorld(new TitleScreen());
    }

    public void checkShiftInput(){
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    Greenfoot.setWorld(new TitleScreen());
=======
                    boardCounter.add();
                    checkAchievements();
                    transition();
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                    Greenfoot.setWorld(new TitleScreen());
=======
                    boardCounter.add();
                    checkAchievements();
                    transition();
>>>>>>> Stashed changes
                }
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && isDown){
            isDown = false;
        }
<<<<<<< Updated upstream
        //draw everything here eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
=======
        if (Greenfoot.mouseClicked(backtomenu)){
            cursor.play();
            Greenfoot.setWorld(new TitleScreen());
        }
        //if (Greenfoot.mouseClicked(backtomenu)){
        //    TitleScreen.cursor.play();
        //    Greenfoot.setWorld(new TitleScreen());
        //}
        //if(Greenfoot.isKeyDown("ENTER")){
        //    TitleScreen.cursor.play();
        //    Greenfoot.setWorld(new TitleScreen());
        //}
    }

    public void checkPauseInput(){
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("enter")) && !isDown){
            TitleScreen.cursor.play();
            if(Greenfoot.isKeyDown("up") && pauseOption > 1){
                pauseOption--;
                drawPauseMenu();
            }else if(Greenfoot.isKeyDown("down") && pauseOption < 3){
                pauseOption++;
                drawPauseMenu();
            }
            isDown = true;
        }else if(!(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) && isDown){
            isDown = false;
        }
        if(Greenfoot.isKeyDown("enter")){
            TitleScreen.click.play();
            switch(pauseOption){
                case 1: // Play
                    pause = false;
                    removeObjects(getObjects(null));
                    drawBoard();
                    break;
                case 2: // music option
                    if(TitleScreen.bgm.isPlaying()){
                        TitleScreen.bgm.pause();
                    }else{
                        TitleScreen.bgm.playLoop();
                    }
                    drawPauseMenu();
                    break;
                case 3: // back to menu
                    Greenfoot.setWorld(new TitleScreen());
                    break;
            }
        }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    
=======

    // Draw the paused menu
    public void drawPauseMenu(){
        removeObjects(getObjects(null));
        addObject(new Button(new GreenfootImage("Pause Menu Background.png"), getHeight(), 1), getWidth()/2, getHeight()/2);
        addObject(new Button(new GreenfootImage("PMenuResume" + ((pauseOption == 1) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8), getWidth()/2, getHeight()*3/6);
        addObject(new Button(new GreenfootImage("PMenuSound" + ((TitleScreen.bgm.isPlaying()) ? "On" : "Off") + ((pauseOption == 2) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8), getWidth()/2, getHeight()*4/6);
        addObject(new Button(new GreenfootImage("PMenuBackToMenu" + ((pauseOption == 3) ? "-2" : "-1") + ".png"), getHeight()/10, 3.8), getWidth()/2, getHeight()*5/6);
    }

    // Get the selected String
>>>>>>> Stashed changes
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
    
<<<<<<< Updated upstream
=======
    private void checkAchievements()
    {
        int w = counter.getScore();
        int x = boardCounter.getScore();
        
        if(!yeah.contains(w) && lol.containsKey(w)){
                Slide s = new Slide(lol.get(w));
                addObject(s, 640, 100);
                Greenfoot.delay(50);
        }
        yeah.add(w);
        if(!boardcheck.contains(x) && woah.containsKey(x)){
                Slide s = new Slide(woah.get(x));
                addObject(s, 640, 100);
                Greenfoot.delay(50);
        }
        boardcheck.add(x);
        if(wordLength==1){
            Slide s = new Slide(new GreenfootImage("Trivial-PopUp.png"));
            addObject(s, 640, 100);
            Greenfoot.delay(50);
        }
        if(wordLength > 13){
            Slide s = new Slide(new GreenfootImage("DeathWish-PopUp.png"));
            addObject(s, 640, 100);
            Greenfoot.delay(50);
        }
        if(numOfWords > 26){
            Slide s = new Slide(new GreenfootImage("WristDamage-PopUp.png"));
            addObject(s, 640, 100);
            Greenfoot.delay(50);
        }
    }

    public void transition(){
        hasWon = true;
        GreenfootImage img = new GreenfootImage("Win Screen.png");
        Picture p = new Picture(img);
        addObject(p,getWidth()/2, getHeight()/2);
        addObject(backtomenu,900,510);
        
    }
>>>>>>> Stashed changes
}
