import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> read(int stringLength){
        ArrayList<String> arr = new ArrayList<String>();
        try{
            String url = "https://raw.githubusercontent.com/eneko/data-repository/master/data/words.txt";
            URL wordsURL = new URL(url);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(wordsURL.openStream()));
            String inputLine;
            // If the word has the correct length and isn't already contained in the array, add it
            while ((inputLine = in.readLine()) != null)
                if(inputLine.length() == stringLength && !arr.contains(inputLine.toUpperCase()))
                    arr.add(inputLine.toUpperCase());
            in.close();
        }catch(Exception e){
            System.out.print("");
        }
        return arr;
    }
}